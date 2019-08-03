package com.matrix.common.utils;

import java.util.UUID;

import com.matrix.common.base.IdWorker;

/**
 * @Description: 全局唯一id生成工具
 * @author Matrix
 * @date 2018/07/05 14:03:50
 */
public class IDUtil {

    private static final IdWorker worker = new IdWorker(1);

    /**
     * 获取全局唯一ID
     * 
     * twitter在把存储系统从MySQL迁移到Cassandra的过程中由于Cassandra没有顺序ID生成机制，于是自己开发了一套全局唯一ID生成服务：Snowflake。 <br/>
     * 1. 41位的时间序列（精确到毫秒，41位的长度可以使用69年）<br/>
     * 2. 10位的机器标识（10位的长度最多支持部署1024个节点） <br/>
     * 3. 12位的计数顺序号（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号） 最高位是符号位，始终为0。 <br/>
     * 优点：高性能，低延迟；独立的应用；按时间有序。 <br/>
     * 缺点：需要独立的开发和部署。
     * 
     * @return 全局唯一ID
     */
    public static long getLongId() {
        return worker.nextId();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getUUIDUpper() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String getUUIDLower() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(getLongId());
        System.out.println(getLongId());
        System.out.println(getUUID());
        System.out.println(getUUIDUpper());
        System.out.println(getUUIDLower());
    }
}
