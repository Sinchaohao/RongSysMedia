package com.sixin.system.service.impl;


import com.sixin.system.domain.SysForePage;
import com.sixin.system.mapper.SysForePageColMapper;

import com.sixin.system.service.ISysForePagecolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysForePageColImpl implements ISysForePagecolService {
    @Autowired
    private SysForePageColMapper sysForePagecolMapper;

    @Override
    public List<SysForePage> selectForePageStatus()
    {
        return sysForePagecolMapper.selectForePageStatus();
    }
}
