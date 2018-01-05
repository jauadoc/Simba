package com.sun.simba.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Created by sunxin on 2017/11/9.
 */
public class ConfigLoaderManage {

    private List<ConfigLoader> configLoaders;

    public ConfigLoaderManage(){
        ServiceLoader<ConfigLoader> serviceLoader = ServiceLoader.load(ConfigLoader.class);
        if (configLoaders == null){
            configLoaders = new ArrayList<ConfigLoader>();
        }
        while (serviceLoader.iterator().hasNext()){
            configLoaders.add(serviceLoader.iterator().next());
        }
    }


    public void addListener(LoadListener loadListener){
        for(int i=0; i<configLoaders.size() ; i++){
            configLoaders.get(i).addListener(loadListener);
        }
        //TODO register all loader, now just only zkloader, maybe properties/datasouce/other loaders will be create.
    }
}
