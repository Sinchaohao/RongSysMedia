package com.sixin.broad.utils;

import java.util.UUID;

public class bCommonUtil {
    /**
     * 获取36位uuid
     * @return
     */
    public static String getUuid() {
       return UUID.randomUUID().toString().replace("-","");

    }
}
