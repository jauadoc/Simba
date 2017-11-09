package com.sun.simba.client;

import com.sun.simba.client.config.ConfigDuplicate;

/**
 * Created by sunxin on 2017/11/9.
 */
public class Simba {

    private static ConfigDuplicate configDuplicate = ConfigDuplicate.getInstance();


    public static String get(String key){
        return configDuplicate.get(key);
    }
}
