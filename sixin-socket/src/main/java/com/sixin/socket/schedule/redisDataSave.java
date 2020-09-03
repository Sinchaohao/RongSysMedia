package com.sixin.socket.schedule;

import com.sixin.socket.utils.SpringContextUtils;
import com.sixin.common.utils.RedisUtil;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.impl.EnergyServiceImpl;
import com.sixin.iot.service.impl.EnvironlServiceImpl;
import com.sixin.iot.service.impl.TorrentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: ruoyi
 * @description: redis中终端数据自动保存
 * @author: Mr.Liu
 * @create: 2020-03-24 15:24
 **/
@Component
public class redisDataSave {
    @Value("${spring.redis.isOpen}")
    private boolean isOpen = false;

    private static final Logger logger = LoggerFactory.getLogger(redisDataSave.class);
    private static EnvironlServiceImpl tbEnvironmentService = null;
    private static TorrentServiceImpl tbIoterminalService =null;
    private static EnergyServiceImpl tbEnergyService = null;
    private static RedisUtil redisUtil =null;

    /**
     * 终端实现，下线的处理
     * 0.1s一次
     */
    @Scheduled(fixedRate = 100)
    public void autoSaveRedisIOT(){
        if(!isOpen){
            return;
        }else {
            if(redisUtil==null || tbEnvironmentService==null || tbIoterminalService==null||tbEnergyService==null){
                try {
                    redisUtil = (RedisUtil) SpringContextUtils.getBeanByClass(RedisUtil.class);
                    tbEnvironmentService = (EnvironlServiceImpl) SpringContextUtils.getBeanByClass(EnvironlServiceImpl.class);
                    tbIoterminalService = (TorrentServiceImpl) SpringContextUtils.getBeanByClass(TorrentServiceImpl.class);
                    tbEnergyService = (EnergyServiceImpl) SpringContextUtils.getBeanByClass(EnergyServiceImpl.class);
                }catch (Exception e){

                }
            }else {
                //logger.info("终端实现，下线的处理1");
                Object o = redisUtil.listLiftPop("iot_ioterminal");
                if(o!=null){
                    tbIoterminalService.onlineOrDisOnline(redisUtil.fromJson((String) o, Torrent.class));
                }
                //logger.info("终端实现，下线的处理2");
            }
        }


    }

    /**
     * 终端环境数据的处理
     *  0.05s一次
     */
    @Scheduled(fixedRate = 50)
    public void autoSaveRedisENV(){
        if(!isOpen){
            return;
        }else {
            if(redisUtil==null || tbEnvironmentService==null || tbIoterminalService==null||tbEnergyService==null){
                try {
                    redisUtil = (RedisUtil) SpringContextUtils.getBeanByClass(RedisUtil.class);
                    tbEnvironmentService = (EnvironlServiceImpl) SpringContextUtils.getBeanByClass(EnvironlServiceImpl.class);
                    tbIoterminalService = (TorrentServiceImpl) SpringContextUtils.getBeanByClass(TorrentServiceImpl.class);
                    tbEnergyService = (EnergyServiceImpl) SpringContextUtils.getBeanByClass(EnergyServiceImpl.class);
                }catch (Exception e){

                }
            }else {
                //logger.info("终端环境数据的处理1");
                Object o = redisUtil.listLiftPop("iot_environment");
                if(o!=null){
                    tbEnvironmentService.insertEnvironl(redisUtil.fromJson((String) o, Environl.class));
                }
                //logger.info("终端环境数据的处理2");
            }
        }

    }
    /**
     * 终端能源数据的处理
     *  0.05s一次
     */
    @Scheduled(fixedRate = 50)
    public void autoSaveRedisEnergy(){
        if(!isOpen){
            return;
        }
        if(redisUtil==null || tbEnvironmentService==null || tbIoterminalService==null||tbEnergyService==null){
            try {
                redisUtil = (RedisUtil) SpringContextUtils.getBeanByClass(RedisUtil.class);
                tbEnvironmentService = (EnvironlServiceImpl) SpringContextUtils.getBeanByClass(EnvironlServiceImpl.class);
                tbIoterminalService = (TorrentServiceImpl) SpringContextUtils.getBeanByClass(TorrentServiceImpl.class);
                tbEnergyService = (EnergyServiceImpl) SpringContextUtils.getBeanByClass(EnergyServiceImpl.class);
            }catch (Exception e){

            }
        }else {
            //logger.info("终端环境数据的处理1");
            Object o = redisUtil.listLiftPop("iot_energy");
            if(o!=null){
                tbEnergyService.insertEnergy(redisUtil.fromJson((String) o, Energy.class));
            }
            //logger.info("终端环境数据的处理2");
        }
    }
}
