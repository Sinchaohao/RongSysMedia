package com.sixin.socket.common.persist;

import com.sixin.socket.utils.SpringContextUtils;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.Environl;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.service.impl.EnergyServiceImpl;
import com.sixin.iot.service.impl.EnvironlServiceImpl;
import com.sixin.iot.service.impl.TorrentServiceImpl;

/**
 * @program: ruoyi
 * @description: mysql
 * @author: Mr.Liu
 * @create: 2020-03-24 11:13
 **/
public class Memery implements BaseMo{
    private static EnvironlServiceImpl tbEnvironmentService = (EnvironlServiceImpl) SpringContextUtils.getBeanByClass(EnvironlServiceImpl.class);
    private static EnergyServiceImpl tbEnergyService = (EnergyServiceImpl) SpringContextUtils.getBeanByClass(EnergyServiceImpl.class);
    private static TorrentServiceImpl tbIoterminalService = (TorrentServiceImpl) SpringContextUtils.getBeanByClass(TorrentServiceImpl.class);

    @Override
    public boolean insert(Object msg) {
        if(msg instanceof Environl){
            return tbEnvironmentService.insertEnvironl((Environl)msg)>0;
        }
        if(msg instanceof Energy){
            return tbEnergyService.insertEnergy((Energy)msg)>0;
        }
        return false;
    }

    @Override
    public boolean update(Object msg) {
        if(msg instanceof Torrent){
            return tbIoterminalService.onlineOrDisOnline((Torrent) msg)>0;
        }
        return false;
    }
}
