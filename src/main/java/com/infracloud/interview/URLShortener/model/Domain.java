package com.infracloud.interview.URLShortener.model;

import java.util.*;

public class Domain {


    static Domain domain = null;

    private Domain(){

    }

    public List<String> domainList(){
        List<String> domainList = Arrays.asList("http://shorl.sm/","http://tin.mk/" );
        return domainList;
    }

    public static Domain getInstance(){
        if(domain == null ){
            domain = new Domain();
        }
        return domain;

    }

}
