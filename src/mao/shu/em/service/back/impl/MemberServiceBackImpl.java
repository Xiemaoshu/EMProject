package mao.shu.em.service.back.impl;

import mao.shu.em.dao.IActionDAO;
import mao.shu.em.dao.IMemberDAO;
import mao.shu.em.dao.IRoleDAO;
import mao.shu.em.dao.impl.ActionDAOImpl;

import mao.shu.em.dao.impl.MemberDAOImpl;
import mao.shu.em.dao.impl.RoleDAOImpl;
import mao.shu.em.service.abs.AbstractService;
import mao.shu.em.service.back.IMemberServiceBack;
import mao.shu.em.vo.Member;
import mao.shu.em.vo.Role;
import mao.shu.util.EncryptUtil;
import mao.shu.util.factory.DAOFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MemberServiceBackImpl extends AbstractService implements IMemberServiceBack {

    @Override
    public Map<String, Object> login(Member vo) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        IMemberDAO memberDAO = DAOFactory.getInstance(MemberDAOImpl.class);
        Member ckVO = memberDAO.findById(vo.getMid());
        if(ckVO!= null) {
            if (ckVO.getPassword().equals(vo.getPassword())) {
                IRoleDAO roleDAO = DAOFactory.getInstance(RoleDAOImpl.class);
                IActionDAO actionDAO = DAOFactory.getInstance(ActionDAOImpl.class);
                resultMap.put("flag", true);//保存用户登录结果
                resultMap.put("name", ckVO.getName());//保存用户的真实姓名
                resultMap.put("sflag", ckVO.getSflag());//保存用户的超级管理员标记
                resultMap.put("allRoles",roleDAO.findAllByMember(vo.getMid()));
                resultMap.put("allActions",actionDAO.findAllByMember(vo.getMid()));

            } else {
                resultMap.put("flag", false);
            }
        }else{
            resultMap.put("flag", false);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> list(String mid) throws Exception {
        if(super.auth(mid,"member:list")){
            IMemberDAO memberDAO = DAOFactory.getInstance(MemberDAOImpl.class);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allMembers",memberDAO.findAll());
            return map;
        }
        return null;

    }

    @Override
    public Map<String, Object> addPre(String mid) throws Exception {
        if (super.admin(mid)){
            IRoleDAO roleDAO = DAOFactory.getInstance(RoleDAOImpl.class);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allRoles",roleDAO.findAll());
            return map;
        }
        return null;
    }

    @Override
    public boolean add(String mid, Member vo, Set<Integer> rids) throws Exception {
       if(super.admin(mid)){
           IMemberDAO memberDAO = DAOFactory.getInstance(MemberDAOImpl.class);
           IRoleDAO roleDAO = DAOFactory.getInstance(RoleDAOImpl.class);
            Member addMember = memberDAO.findById(vo.getMid());
           if(addMember == null){
               vo.setSflag(0);//新增加的管理员默认都为普通管理员
               if(memberDAO.doCreate(vo)){
                   return roleDAO.doCreateByMember(vo.getMid(),rids);
               }
           }
       }
       return false;
    }

    @Override
    public Member checkMid(String loginMid,String addMid) throws Exception {
        if(super.admin(loginMid)){
            IMemberDAO memberDAO = DAOFactory.getInstance(MemberDAOImpl.class);
            return memberDAO.findById(addMid);
        }
        return null;
    }
}
