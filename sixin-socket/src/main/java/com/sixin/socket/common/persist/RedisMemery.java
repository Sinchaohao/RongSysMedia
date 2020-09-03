package com.sixin.socket.common.persist;

import com.sixin.socket.utils.SpringContextUtils;
import com.sixin.common.utils.RedisUtil;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.domain.Torrent;

/**
 * @program: ruoyi
 * @description: redis
 * @author: Mr.Liu
 * @create: 2020-03-24 11:22
 **/
public class RedisMemery implements BaseMo{

    private static RedisUtil redisUtil=(RedisUtil) SpringContextUtils.getBeanByClass(RedisUtil.class);
    @Override
    public boolean insert(Object msg) {
        if(msg instanceof Environl){
            try{
                String s = redisUtil.toJson(msg);
                redisUtil.listRightSet("iot_environment",s);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(msg instanceof Energy){
            try{
                String s = redisUtil.toJson(msg);
                redisUtil.listRightSet("iot_energy",s);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Object msg) {
        if(msg instanceof Torrent){
            try {
                String s = redisUtil.toJson(msg);
                redisUtil.listRightSet("iot_ioterminal",s);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
