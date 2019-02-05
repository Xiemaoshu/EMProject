package mao.shu.em.service.back.impl;

import mao.shu.em.dao.IMemberDAO;
import mao.shu.em.dao.impl.MemberDAO;
import mao.shu.em.service.back.IMemberServiceBack;
import mao.shu.em.vo.Member;
import mao.shu.util.factory.DAOFactory;

import java.util.HashMap;
import java.util.Map;

public class MemberServiceBackImpl implements IMemberServiceBack {

    @Override
    public Map<String, Object> login(Member vo) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        IMemberDAO memberDAO = DAOFactory.getInstance(MemberDAO.class);
        Member ckVO = memberDAO.findById(vo.getMid());
        if(ckVO!= null) {
            if (ckVO.getPassword().equals(vo.getPassword())) {
                resultMap.put("flag", true);//保存用户登录结果
                resultMap.put("name", ckVO.getName());//保存用户的真实姓名
                resultMap.put("sflag", ckVO.getSflag());//保存用户的超级管理员标记

            } else {
                resultMap.put("flag", false);
            }
        }else{
            resultMap.put("flag", false);
        }
        return resultMap;
    }
}
