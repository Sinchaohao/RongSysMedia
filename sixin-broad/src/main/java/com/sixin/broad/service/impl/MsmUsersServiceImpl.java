package com.sixin.broad.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixin.broad.dao.UserDaoSupport;
import com.sixin.broad.domain.Users;
import com.sixin.broad.service.IMsmUsersService;
import com.sixin.common.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 短信播出端
 *
 * @author 周博
 * @date 2019-03-31
 */
@Service
public class MsmUsersServiceImpl implements IMsmUsersService {

    @Autowired
    private UserDaoSupport dao;

    /**登录判断
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public Users getUserByNameAndPwd(PageData pd)throws Exception{
        @SuppressWarnings("unchecked")
        List<Users> users =(List<Users>)dao.findForList("UsersMapper.getUserInfo", pd);
        if(users.size()>0)
            return users.get(0);
        else return null;
    }
    /**登陆名是否存在判断
     * @param username
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> checkUsername(String username)throws Exception{
        return (List<String>)dao.findForList("UsersMapper.getUseridByUsername", username);
    }

    /**用户注册
     * @param pd
     * @throws Exception
     */
    @Override
    public void insertUser(PageData pd)throws Exception{
        dao.save("UsersMapper.insertUser", pd);
        dao.save("UsersMapper.insertUserSet", pd);
        dao.save("UsersMapper.insertInfoMUserSet", pd);
    }
    /**通过useid获取数据
     * @param pd
     * @return
     * @throws Exception
     */
    @Override
    public Users findByUserid(PageData pd)throws Exception{
        return (Users)dao.findForObject("UsersMapper.findByUserid", pd);
    }

    /**修改用户
     * @param pd
     * @throws Exception
     */
    @Override
    public void editU(PageData pd)throws Exception{
        dao.update("UsersMapper.editUsers", pd);
        dao.update("UsersMapper.editUserSet", pd);
    }
    /**修改用户密码
     * @param pd
     * @throws Exception
     */
    @Override
    public void editPwd(PageData pd) throws Exception {
        dao.update("UsersMapper.editUserPwd", pd);
    }

    /**
     * 删除用户设置和信息
     */
    @Override
    public void deleteU(String userid) throws Exception {
        dao.delete("UsersMapper.deleteUI", userid);
        dao.delete("UsersMapper.deleteUserSet", userid);
        dao.delete("UsersMapper.deleteInfoMUserSet", userid);
    }

    /**修改用户账号可用状态
     * @param pd
     * @throws Exception
     */
    @Override
    public void editIsenabled(PageData pd) throws Exception {
        dao.update("UsersMapper.editIsenabled", pd);
    }
    /**
     * 根据用户编号获取其区域下的用户信息(excel导出)
     * @param  page
     * @param @throws Exception
     * @return List<PageData>
     */
    @SuppressWarnings("unchecked")
    public List<Users> getUserListByIds(PageData pd) throws Exception{
        return (List<Users>) dao.findForList("UsersMapper.getUserListByIds", pd);
    }
    /**
     * 用户信息列表
     * @param  page
     * @param @throws Exception
     * @return List<User>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Users> getAllUserList(Page page) throws Exception {
        return (List<Users>) dao.findForList("UsersMapper.getUserslistPage", page);
    }
    /**通过用户ID获取区域编号
     * @param integer
     * @return
     * @throws Exception
     */
    @Override
    public String getAidByUserid(String userid) throws Exception {
        // TODO Auto-generated method stub
        return (String) dao.findForObject("UsersMapper.getAidByUserid", userid);
    }
    //	@SuppressWarnings("unchecked")
//	@Override
//	public List<PageData> getUserByAid(String aid) throws Exception {
//		return (List<PageData>) dao.findForList("UsersMapper.getUserByAid", aid);
//	}
    @Override
    public void resetPwd(PageData pd) throws Exception {
        dao.update("UsersMapper.resetPwd", pd);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<PageData> getUserByAidlist(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UsersMapper.getUserByAidlist", pd);
    }
    //	@Override
//	public PageData getUserByName(String account) throws Exception {
//		PageData user =(PageData)dao.findForObject("UsersMapper.getUserByName", account);
//		return user;
//	}
    @SuppressWarnings("unchecked")
    @Override
    public List<PageData> getAllUserNameAndId(String str) throws Exception {
        return (List<PageData>) dao.findForList("UsersMapper.getAllUserNameAndId", str);
    }
}
