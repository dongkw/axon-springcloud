package com.example.domain.aggr;

public interface IInstruction {


    //下达
    void create();

    //审批

    void approve();

    //分发

    void distribute();

    //执行

    void execute();



}
