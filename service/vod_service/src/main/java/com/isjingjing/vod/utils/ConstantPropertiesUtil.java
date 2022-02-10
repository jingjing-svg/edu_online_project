package com.isjingjing.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @authors:静静
 * @description:null
 */
//该接口的实现方法会在spring启动完成之后执行
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.file.keyid}")
    private String key;

    @Value("${aliyun.oss.file.keysecret}")
    private String secret;

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = key;
        ACCESS_KEY_SECRET = secret;
    }

}
