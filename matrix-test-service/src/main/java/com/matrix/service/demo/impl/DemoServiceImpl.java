package com.matrix.service.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.matrix.bean.DemoInfo;
import com.matrix.common.annotation.Cacheable;
import com.matrix.common.annotation.LogAnnotation;
import com.matrix.service.demo.DemoService;

/**
 * @Description: demo
 * @author waterif
 * @date 2019/07/29 17:22:08
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @LogAnnotation
    public DemoInfo getDemoInfo(String unionid) {
        DemoInfo demoInfo = new DemoInfo();
        demoInfo.setUnionid(unionid);
        demoInfo.setOpenid(unionid);
        demoInfo.setName("test");
        return demoInfo;
    }

    @Override
    @LogAnnotation
    public boolean saveDemoInfo(DemoInfo demoInfo) {
        return true;
    }

}
