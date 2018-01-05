package com.sun.simba.loader;

/**
 * Created by sunxin on 2017/11/9.
 */
public class ConfigLoaderManageFactory {

    //TODO 待思考:是否需要使用工厂,目前仅能想到部分内容可能需要客户端自定义,绝大部分情况使用默认配置即可.
    //1.监听的黑白名单  意义不大,可能根本不存在这种场景
    //2.


    public static ConfigLoaderManage newInstance() {
        //TODO 创建一个configManage
        return new ConfigLoaderManage();
    }
}
