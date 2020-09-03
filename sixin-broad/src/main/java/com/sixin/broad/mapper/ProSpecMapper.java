package com.sixin.broad.mapper;

import com.sixin.broad.domain.ProSpec;

import java.util.List;

/**
 * 特种节目管理
 *
 * @author 周博
 * @date 2019-03-27
 */
public interface ProSpecMapper {

    public ProSpec selectProSpecById(String fid);

    public List<ProSpec> selectProSpecList(ProSpec proSpec);

    public List<ProSpec> selectAllProSpec();

    public int updateprospec(ProSpec proSpec);
}
