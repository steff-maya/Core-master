package com.jappy.jappy_core.domian.usecase;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
/** * Created by jhonnybarrios on 11/29/17. * colaborate  irenecedeno on 06-02-18. */

public abstract class UseCase<T> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public void execute( UseCaseObserver<T> disposableObserver) {
        Preconditions.checkNotNull(disposableObserver);
        Observable<T> observable = this.createObservableUseCase()
                .subscribeOn(getSubscribeOn())
                .observeOn(getObserveOn());
        UseCaseObserver<T> observer = observable.subscribeWith(disposableObserver);
        compositeDisposable.add(observer);
    }

    public Observable<T> getObservable() {
        return createObservableUseCase();
    }

    protected Scheduler getSubscribeOn() {return Schedulers.io();}
    protected Scheduler getObserveOn(){return AndroidSchedulers.mainThread();}

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Observable<T> createObservableUseCase();
}
