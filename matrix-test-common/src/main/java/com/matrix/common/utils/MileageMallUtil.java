package com.matrix.common.utils;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description: 商城产品接口
 * @author peng.cheng
 * @date 2019/07/30 15:08:09
 */
@Repository
public class MileageMallUtil {

    /**
     * 里程商城专区商品列表接口地址
     */
    @Value("${ccard.productlist.address}")
    private String ccardProductListAddress;

    /**
     * 里程商城商品下单次数地址
     */
    @Value("${ccard.productlist.order.address}")
    private String mallProductOrderAddress;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 黑金卡专区商品列表
     *
     * @param unionId：unionId
     * @return
     */
    public JSONArray getCcardProductList(String unionId, String openId, String identCode) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("unionId", unionId);
            jsonObject.put("openId", openId);
            jsonObject.put("identCode", identCode);

            String result = "";
            try {
                HttpHeaders headers  = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> requestEntity = new HttpEntity<>(jsonObject.toJSONString(), headers);
                result = restTemplate.postForObject(ccardProductListAddress, requestEntity, String.class);
                //返回结果记录日志
                logger.info("黑金卡商品列表接口返回结果(" + ccardProductListAddress + ")==result:" + result);
                if (StringUtils.isEmpty(result)) {
                    logger.warn("黑金卡商品列表接口返回结果(" + ccardProductListAddress + ")==result:" + result);
                    return null;
                }
                JSONObject resObj = JSONObject.parseObject(result);
                if (resObj == null) {
                    logger.warn("黑金卡商品列表接口返回结果(" + ccardProductListAddress + ")==result:" + result);
                    return null;
                }
                if (Integer.parseInt(JSONObject.parseObject(result).get("code").toString()) != 1) {
                    logger.warn("黑金卡商品列表接口返回结果(" + ccardProductListAddress + ")==result:" + result);
                    return null;
                }
                JSONArray jsonArray = JSON.parseArray(resObj.get("productList").toString());
                if(jsonArray.size()>0) {
                    BigDecimal rmbPrize=null;
                    BigDecimal activityRmbPrize=null;
                    BigDecimal saveRmbPrice=null;
                    for(int i=0;i<jsonArray.size();i++){
                        JSONObject  tempObject= jsonArray.getJSONObject(i); 
                        rmbPrize=new BigDecimal(tempObject.getString("rmbPrice"));
                        activityRmbPrize=new BigDecimal(tempObject.getString("activityRmbPrice"));
                        saveRmbPrice=rmbPrize.subtract(activityRmbPrize).setScale(1, BigDecimal.ROUND_HALF_UP);
                        tempObject.put("saveRmbPrice", saveRmbPrice.stripTrailingZeros().toPlainString());
                    }
                }
                return jsonArray;
            } catch (Exception e) {
                logger.error("黑金卡商品列表接口调用方法异常，url：" + ccardProductListAddress + "param：" + unionId+",异常信息="+e.getMessage());
                return null;
            }
        } catch (Exception e) {
            logger.error("黑金卡商品接口异常，异常消息：" + e.getMessage());
            return null;
        }
    }


    /**
     * 查询商品订单个数
     *
     * @param unionId：unionId
     * @return
     */
    public JSONObject getCardOrdersInfo(String unionId, Long productId) {
        try {
            String result = restTemplate.getForObject(mallProductOrderAddress + "?unionId=" + unionId + "&productId=" + productId,String.class);
            logger.info("根据unonId获取productId订单次数返回：" + result);
            if (StringUtils.isEmpty(result)) {
                logger.warn("根据unonId获取productId订单次数接口返回为空");
                throw new Exception("根据unonId获取productId订单次数返回为空");
            }
            JSONObject jsonRelation = JSONObject.parseObject(result);
            int rspcode = jsonRelation.getIntValue("code");
            String message = jsonRelation.getString("msg");
            if (rspcode == 0) {
                logger.warn("根据unonId获取productId订单次数返回错误(code=" + rspcode + ")(message=" + message + ")");
                return null;
            }
            return jsonRelation.getJSONObject("data");
        } catch (Exception e) {
            logger.error("根据unonId获取productId订单次数异常，异常消息：" + e.getMessage());
            return null;
        }
    }
}
