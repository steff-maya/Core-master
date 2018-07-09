package com.jappy.jappy_core.presentation;

/**
 * Created by irenecedeno on 07-02-18.
 */

public interface OnItemClickListener<T> {
    void onItemClick(int adapterPosition, T item);
}
