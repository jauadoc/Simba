package com.sun.simba.loader;

/**
 * Created by sunxin on 2018/1/5.
 */
public abstract class AbstractConfigLoader implements ConfigLoader{

    //TODO 待思考:abstract中是否需要规范下添加监听的行为,不需要则直接交给子类来实现,需要则规范下行为后给子类一个补充实现的方法.
    @Override
    public void addListener(LoadListener loadListener) {

    }
}
