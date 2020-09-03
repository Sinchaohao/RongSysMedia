package com.sixin.iot.service.impl;
import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.exception.BusinessException;
import com.sixin.common.support.Convert;
import com.sixin.common.utils.StringUtils;
import com.sixin.common.utils.spring.SpringUtils;
import com.sixin.iot.domain.Board;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;
import com.sixin.iot.mapper.BoardMapper;
import com.sixin.iot.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardServiceImpl implements IBoardService {
    @Autowired
    private BoardMapper boardMapper;

    /**
     * 查询广告牌记录列表
     *
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Board> selectBoardList(Board board) {
        return boardMapper.selectBoardList(board);
    }

    /**
     * 根据id批量删除数据
     * @param id
     */
    // @Override
    // @DataSource(value = DataSourceType.SXINFOM)
    // public int deleteBoardByids(String id) {
    //     return boardMapper.deleteBoardByids(Convert.toStrArray(id));
    // }

    /**
     * 添加广告牌公告数据
     * @param  Board
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int insertBoard(Board board) {
        return boardMapper.insertBoard(board);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Torrent> queryList(Torrent torrent) {return boardMapper.queryList(torrent);}

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Iotype> selectList(Iotype iotype) {return boardMapper.selectList(iotype);}

    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public Board selectByid(Long id) {
        return boardMapper.selectByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Board> selectBoardByids(List<String> sfids)
    {
        return boardMapper.selectBoardByids(sfids);
    }


    /**
     * 删除广告牌信息
     * @param Board
     * @return
     */
    //@Override
    //@DataSource(value = DataSourceType.SXINFOM)
    //public int deleteBoardByid(Long id) {
    //    return boardMapper.deleteBoardByid(id) > 0 ? true : false;
    //}

    /**
     * 保存用户修改
     * @param Board
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int updateBoard(Board board) {
        return boardMapper.updateBoard(board);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteBoardByid(Long id) {
        return boardMapper.deleteBoardByid(id);
    }

    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int deleteBoardByids(String ids) {
        return boardMapper.deleteBoardByids(Convert.toStrArray(ids));
    }


    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public int changeStatus(Board board)
    {
        return boardMapper.updateBoard(board);
    }

}
