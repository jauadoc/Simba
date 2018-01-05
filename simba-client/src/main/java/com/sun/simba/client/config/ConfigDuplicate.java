package com.sun.simba.client.config;

import com.sun.simba.loader.ConfigChangeListener;
import com.sun.simba.loader.ConfigLoaderManage;
import com.sun.simba.loader.ConfigLoaderManageFactory;
import com.sun.simba.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunxin on 2017/11/9.
 */
public class ConfigDuplicate implements ConfigChangeListener{

    private static ConfigLoaderManage configLoaderManage = ConfigLoaderManageFactory.newInstance();


    //TODO 待思考:这里的配置每次通过loader加载后,是否要全部重新更新该map?防止陈旧的废弃配置污染环境?还是保留冗余保证程序正常运行
    //TODO 待思考:是否需要定时将配置写入硬盘,防止zk cluster 挂掉?一般来说集群了话稳定性应该没问题.写入硬盘唯一的好处就是集群挂了的时候服务器恰好重启可以硬盘读配置保证服务可运行.但是client应该避免资源占用.
    //TODO 待思考:这里的配置仅维护在一个map中?是否可以维护在多个map中来区分不同来源 或给配置划分级别/类别/other?

    private Map<String, String> localConfigCache = new HashMap<String, String>();

    private static ConfigDuplicate configDuplicate;
    public static ConfigDuplicate getInstance(){
        if (configDuplicate == null){
            synchronized (ConfigDuplicate.class){
                if (configDuplicate == null){
                    configDuplicate = new ConfigDuplicate();
                    //如果副本新创建,则证明系统初次启动,本地并无配置副本,直接从远程加载配置信息
                    //TODO 启动本地线程,根据定时策略进行定时拉取配置信息,防止因为网络问题导致配置信息无法被同步到该应用中


                    //向管理器注册配置修改监听
                    configLoaderManage.addListener(configDuplicate);
                }
            }
        }
        return configDuplicate;
    }

    public String get(String key) {

        String value = localConfigCache.get(key);
        if (StringUtils.isBlank(value)){
            //TODO 如果要获取的配置在本地配置缓存中没有相关的值,则通过loader去远程加载,加载后将内容加入localConfig

            //经过loader加载后再次从localConfig中获取配置,如果依旧为空重复策略配置的次数后仍为空,返回null或者"" TODO 待思考,如果配置未找到,是否返回null导致程序异常?
            //TODO 重试策略
            value = localConfigCache.get(key);


        }

        //TODO ""/null
        return value;
    }

    @Override
    public void onChange(String key, String value) {
        this.localConfigCache.put(key, value);
    }
}
