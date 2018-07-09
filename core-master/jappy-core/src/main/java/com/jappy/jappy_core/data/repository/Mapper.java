package com.jappy.jappy_core.data.repository;

import java.util.ArrayList;
import java.util.List;

/** * Created by jhonnybarrios on 11/29/17. * colaborate  irenecedeno on 06-02-18. */

public abstract class Mapper<T1, T2> {

    public abstract T2 map(T1 value);

    public abstract T1 reverseMap(T2 value);

    public List<T2>  map(List<T1> values){
        ArrayList<T2> returnValues = new ArrayList<>();
        for (T1 value : values) {
            returnValues.add(map(value));
        }
        return returnValues;
    }

    public List<T1> reverseMap(List<T2> values) {
        ArrayList<T1> returnValues = new ArrayList<>();
        for (T2 value : values) {
            returnValues.add(reverseMap(value));
        }
        return returnValues;
    }



}