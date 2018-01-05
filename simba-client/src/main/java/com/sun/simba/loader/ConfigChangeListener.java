package com.sun.simba.loader;

/**
 * Created by sunxin on 2018/1/5.
 */
public interface ConfigChangeListener extends LoadListener{
    void onChange(String key, String value);
}
