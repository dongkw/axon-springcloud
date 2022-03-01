package com.example.basic;

import com.example.basic.domain.saga.Result;
import com.example.basic.model.CreateCmd;
import com.example.basic.model.CreateEvt;

import java.util.List;

public interface IService<T extends Basic> {


    CreateCmd buildCreateCmd(T basic);

    CreateEvt buildCreateEvt(String aggrId, T basic);

    T getBy(CreateEvt evt);

    T getBy(Basic basic);

    List<Class<?>> setCmplList();

    void cmplStart(T t);

    void cmplRollback(T t);

    Result cmplEventHandler(Object event);



}