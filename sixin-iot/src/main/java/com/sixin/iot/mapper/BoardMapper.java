package com.sixin.iot.mapper;

import com.sixin.iot.domain.Board;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface BoardMapper {
    /**
     * 广告牌记录列表
     *
     * @return
     */
    List<Board> selectBoardList(Board board);
    /**
     * 删除广告牌信息
     * @param id
     * @returni
     */
    public int deleteBoardByid(Long id);
    /**
     * 根据id批量删除数据
     * @param ids
     */
    public int deleteBoardByids(String[] ids);
    /**
     * 添加广告牌信息
     * @param board
     * @return
     */
    int insertBoard(Board board);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    Board selectByid(Long id);
    /**
     * 根据id批量选择数据
     * @param sfids
     * @return
     */
    public List<Board> selectBoardByids(List<String> sfids);

    List<Torrent> queryList(Torrent torrent);

    List<Iotype> selectList(Iotype iotype);

    /**
     * 保存用户修改
     * @param board
     * @return
     */
    int updateBoard(Board board);


}