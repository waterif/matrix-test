package com.matrix.controller.demo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.matrix.bean.DemoInfo;
import com.matrix.bean.UserInfo;
import com.matrix.common.annotation.LogAnnotation;
import com.matrix.common.base.ResponseEntity;
import com.matrix.common.utils.LocalUserHandler;
import com.matrix.common.utils.ResponseUtil;
import com.matrix.common.utils.ValidatorUtils;
import com.matrix.service.demo.DemoService;

/**
 * @Description: demo
 * @author waterif
 * @date 2019/03/29 15:00:20
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoService demoService;

    @GetMapping("getDemo")
    @LogAnnotation
    public ResponseEntity<DemoInfo> getDemo() {

        UserInfo localUserInfo = LocalUserHandler.getLocalUserInfo();

        if (null == localUserInfo || StringUtils.isBlank(localUserInfo.getUnionid())) {
            return ResponseUtil.returnFailed("无效的用户");
        }

        return ResponseUtil.returnSuccess(demoService.getDemoInfo(localUserInfo.getUnionid()));
    }
    
    @GetMapping("getOtherDemo")
    @LogAnnotation
    public ResponseEntity<DemoInfo> getOtherDemo(@RequestParam(name = "unionid", required = true) String unionid) {
        
        UserInfo localUserInfo = LocalUserHandler.getLocalUserInfo();
        
        logger.info("当前用户：{}", JSON.toJSONString(localUserInfo));
        
        if (null == localUserInfo || StringUtils.isBlank(localUserInfo.getUnionid())) {
            return ResponseUtil.returnFailed("无效的用户");
        }
        
        return ResponseUtil.returnSuccess(demoService.getDemoInfo(unionid));
    }

    @PostMapping("saveDemo")
    @LogAnnotation
    public ResponseEntity<Boolean> saveDemo(@RequestBody DemoInfo demoInfo) {

        ValidatorUtils.validateEntity(demoInfo);

        UserInfo localUserInfo = LocalUserHandler.getLocalUserInfo();

        if (null == localUserInfo || StringUtils.isBlank(localUserInfo.getUnionid())) {
            return ResponseUtil.returnFailed("无效的用户");
        }

        return ResponseUtil.returnSuccess(demoService.saveDemoInfo(demoInfo));
    }

}
