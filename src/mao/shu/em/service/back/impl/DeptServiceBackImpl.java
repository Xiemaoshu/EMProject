package mao.shu.em.service.back.impl;

import mao.shu.em.dao.IDeptDAO;
import mao.shu.em.dao.impl.DeptDAOImpl;
import mao.shu.em.service.abs.AbstractService;
import mao.shu.em.service.back.IDeptServiceBack;
import mao.shu.em.vo.Dept;
import mao.shu.util.factory.DAOFactory;

import java.util.List;
import java.util.Set;

public class DeptServiceBackImpl extends AbstractService implements IDeptServiceBack {

    @Override
    public Dept get(Integer deptno) throws Exception {
        IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
        return deptDAO.findById(deptno);
    }

    @Override
    public List<Dept> getALl(String mid) throws Exception {
        if(super.auth(mid,"dept:list")){
            IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
            return deptDAO.findAll();
        }
        return null;
    }
}
