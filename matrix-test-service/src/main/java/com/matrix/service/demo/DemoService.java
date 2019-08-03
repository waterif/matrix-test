package com.matrix.service.demo;

import com.matrix.bean.DemoInfo;

/**
 * @Description: demo
 * @author waterif
 * @date 2019/07/29 17:18:48
 */
public interface DemoService {

    DemoInfo getDemoInfo(String unionid);

    boolean saveDemoInfo(DemoInfo demoInfo);

}
