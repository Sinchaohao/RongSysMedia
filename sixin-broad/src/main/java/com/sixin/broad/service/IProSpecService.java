package com.sixin.broad.service;

import com.sixin.broad.domain.ProSpec;

import java.util.List;

/**
 * Created by MI on 2019/3/27.
 */
public interface IProSpecService {

    public ProSpec selectProSpecById(String fid);

    public List<ProSpec> selectProSpecList(ProSpec proSpec);

    public List<ProSpec> selectAllProSpec();

    public int updateprospec(ProSpec proSpec);
}
