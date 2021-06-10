package com.example.basic;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Factory {

    protected static Map<String, IService> map = new HashMap<>();

    public static IService getFactory(String type) {
        return map.get(type);
    }


}