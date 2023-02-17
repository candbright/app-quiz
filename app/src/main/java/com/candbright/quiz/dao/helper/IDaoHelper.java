package com.candbright.quiz.dao.helper;

import com.candbright.quiz.model.data.Question;

import java.util.List;

public interface IDaoHelper<Data> {

    /**
     * add
     */
    long insert(Data data);

    /**
     * add
     */
    void insert(List<Data> data);

    void insertOrReplace(Data data);

    /**
     * delete all
     */
    void deleteAll();

    /**
     * update
     */
    void update(Data data);

    List<Data> searchAll();
}
