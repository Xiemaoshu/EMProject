package mao.shu.em.service.back.impl;

import mao.shu.em.dao.IDeptDAO;
import mao.shu.em.dao.IEmpDAO;
import mao.shu.em.dao.ILevelDAO;
import mao.shu.em.dao.impl.DeptDAOImpl;
import mao.shu.em.dao.impl.EmpDAOImpl;
import mao.shu.em.dao.impl.LevelDAOImpl;
import mao.shu.em.service.abs.AbstractService;
import mao.shu.em.service.back.IEmpServiceBack;
import mao.shu.em.vo.Dept;
import mao.shu.em.vo.Emp;
import mao.shu.em.vo.Level;
import mao.shu.util.factory.DAOFactory;

import javax.servlet.annotation.WebServlet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmpServiceBackImpl extends AbstractService implements IEmpServiceBack {

    @Override
    public Map<String, Object> addPre() throws Exception {
        Map<String,Object> addPre_result = new HashMap<String,Object>();
        IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
        ILevelDAO levelDAO = DAOFactory.getInstance(LevelDAOImpl.class);
        addPre_result.put("allUnderDepts",deptDAO.findUnders());
        addPre_result.put("allLevels",levelDAO.findAll());
        return addPre_result;
    }

    @Override
    public boolean add(Emp vo) throws Exception {
        //判断操作用户的权限是否具备添加用户的权限
        if(super.auth(vo.getMid(),"emp:add")){
            IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
           Dept dept = deptDAO.findById(vo.getDeptno());
           //判断当前添加的部门人数是否已满
            if(dept.getCurrnum()<dept.getMaxnum()){
                ILevelDAO levelDAO = DAOFactory.getInstance(LevelDAOImpl.class);
                Level level =  levelDAO.findById(vo.getLid());
                //判断新增雇员的工资是否在对应的工资等级范围中
                if(vo.getSal()>=level.getLosal()&&vo.getSal()<=level.getHisal()){
                    vo.setFlag(1);
                    vo.setHiredate(new Date());
                    //执行添加雇员操作
                    IEmpDAO empDAO = DAOFactory.getInstance(EmpDAOImpl.class);
                    if(empDAO.doCreate(vo)){

                        //如果添加雇员成功,则将对应部门的人数加1
                        return deptDAO.updateCurrnum(vo.getDeptno(),1);
                    }
                }
            }

        }
        return false;
    }
}
