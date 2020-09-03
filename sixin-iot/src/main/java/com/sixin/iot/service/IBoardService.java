package com.sixin.iot.service;
import com.sixin.iot.domain.Board;
import com.sixin.iot.domain.Iotype;
import com.sixin.iot.domain.Torrent;

import java.util.List;

public interface IBoardService {
    /**
     * 查询广告牌记录列表
     *
     * @return
     */
    public List<Board> selectBoardList(Board board);

    public List<Torrent> queryList(Torrent torrent);
    public List<Iotype> selectList(Iotype iotype);
    /**
     * 添加广告牌公告数据
     * @param  board
     * @return
     */
    public int insertBoard(Board board);
    /**
     * 根据id选择数据
     * @param id
     * @return
     */
    public Board selectByid(Long id);

    /**
     * 根据id批量删除数据
     * @param sfids
     */
    public List<Board> selectBoardByids(List<String> sfids);

    /**
     * 保存用户修改
     * @param board
     * @return
     */
    public int updateBoard(Board board);
    /**
     * 删除广告牌信息
     * @param id
     * @return
     */
    public int deleteBoardByid(Long id);
    public int deleteBoardByids(String ids);

    public int changeStatus(Board board);

}
