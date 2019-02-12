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

    @Override
    public boolean editMaxnum(String mid, Dept dept) throws Exception {
        if(super.auth(mid,"dept:edit")){
            IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
            //查询处原部门信息
            Dept oldDept = deptDAO.findById(dept.getDeptno());
            //判断要修改的最大人数是否小于当前部门人数
            //如果修改的部门最大人数等于当前部门人数也是也可以通过的
            if(dept.getMaxnum() >= oldDept.getCurrnum()){
                return deptDAO.doUpdateMaxnum(dept.getDeptno(),dept.getMaxnum());
            }else{
                return false;
            }
        }
        return false;

    }
}
