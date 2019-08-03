package com.matrix.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Matrix
 * @date 2018/10/29 11:05:15
 */
public class MathUtil {

    public static Double round(Double d, int scale) {

        if (null == d) {
            return null;
        }

        BigDecimal bg = new BigDecimal(d).setScale(scale, RoundingMode.HALF_EVEN);

        return bg.doubleValue();
    }
}
