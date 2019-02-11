package mao.shu.em.service.back.impl;

import mao.shu.em.dao.IDeptDAO;
import mao.shu.em.dao.IElogDAO;
import mao.shu.em.dao.IEmpDAO;
import mao.shu.em.dao.ILevelDAO;
import mao.shu.em.dao.impl.DeptDAOImpl;
import mao.shu.em.dao.impl.ElogDAOImpl;
import mao.shu.em.dao.impl.EmpDAOImpl;
import mao.shu.em.dao.impl.LevelDAOImpl;
import mao.shu.em.service.abs.AbstractService;
import mao.shu.em.service.back.IEmpServiceBack;
import mao.shu.em.vo.Dept;
import mao.shu.em.vo.Elog;
import mao.shu.em.vo.Emp;
import mao.shu.em.vo.Level;
import mao.shu.util.DateUtil;
import mao.shu.util.factory.DAOFactory;


import java.util.*;

public class EmpServiceBackImpl extends AbstractService implements IEmpServiceBack {
    @Override
    public Map<String, Object> getDetails(String mid, Integer empno) throws Exception {
        Map<String,Object> all = new HashMap<String,Object>();
        if(super.auth(mid,"emp:list")){
            IDeptDAO deptdao = DAOFactory.getInstance(DeptDAOImpl.class);
            ILevelDAO levelDAO = DAOFactory.getInstance(LevelDAOImpl.class);
            IEmpDAO empDAO = DAOFactory.getInstance(EmpDAOImpl.class);
            IElogDAO elogDAO = DAOFactory.getInstance(ElogDAOImpl.class);
            Emp emp = empDAO.findById(empno);
            Dept dept = deptdao.findById(emp.getDeptno());
            Level level = levelDAO.findById(emp.getLid());
            Set<Elog> allElogs = elogDAO.findAllByEmp(empno);
            all.put("dept",dept);
            all.put("level",level);
            all.put("allElogs",allElogs);
            all.put("emp",emp);
        }
        return all;
    }

    /**
     * 批量进行多个雇员的离职处理,将雇员的flag值改为 0,同时添加一个雇员离职的日志信息,和修改离职雇员的所在部门的当前人数
     * @param mid 操作离职处理的用户id
     * @param empnos 多个离职的雇员编号集合
     * @return 成功返回true,否则返回false
     * @throws Exception
     */
    public boolean removeEmp(String mid, Set<Integer> empnos){
        try {
            if(super.auth(mid,"emp:remove")){
                IEmpDAO empDAO = DAOFactory.getInstance(EmpDAOImpl.class);
                if(empDAO.doUpdateByFlag(empnos,0)){
                    Iterator<Integer> empnosIte = empnos.iterator();
                    IElogDAO  elogDAO = DAOFactory.getInstance(ElogDAOImpl.class);
                    IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
                    while(empnosIte.hasNext()){
                        Integer empno = empnosIte.next();
                        Emp emp = empDAO.findById(empno);//得到每个雇员信息
                        Integer deptno = emp.getDeptno();//得到每个雇员所在的部门编号
                        //保存日志信息
                        Elog elog = new Elog();
                        elog.setEmpno(empno);
                        elog.setNote(DateUtil.getFormatDateTime()+"[雇员离职]");
                        elog.setDeptno(deptno);
                        elog.setMid(mid);
                        elog.setFlag(0);//设为离职处理
                        elog.setLid(emp.getLid());
                        elog.setJob(emp.getJob());
                        elog.setSal(emp.getSal());
                        elog.setComm(emp.getComm());
                        //保存日志信息
                        if(elogDAO.doCreate(elog)){
                            //将雇员所在的部门当前人数-1
                            if(!deptDAO.updateCurrnum(deptno,-1)){
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }else{

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<String, Object> addPre(String mid) throws Exception {
        if(super.auth(mid,"emp:add") ){
            Map<String, Object> addPre_result = new HashMap<String, Object>();
            IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
            ILevelDAO levelDAO = DAOFactory.getInstance(LevelDAOImpl.class);
            addPre_result.put("allUnderDepts", deptDAO.findUnders());
            addPre_result.put("allLevels", levelDAO.findAll());
            return addPre_result;
        }
        return null;

    }
    public Map<String,Object> editPre(String mid,Integer empno)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        if(super.auth(mid,"emp:edit")){
            IEmpDAO empdao = DAOFactory.getInstance(EmpDAOImpl.class);
            IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
            ILevelDAO levelDAO = DAOFactory.getInstance(LevelDAOImpl.class);
            map.put("allDepts",deptDAO.findAll());
            map.put("allLevels",levelDAO.findAll());
            map.put("emp",empdao.findById(empno));
        }
        return map;
    }

    @Override
    public boolean edit(Emp editEmp, Elog elog) throws Exception {
        if(super.auth(editEmp.getMid(),"emp:edit")){
            //empDAO操作对象
            IEmpDAO empDAO = DAOFactory.getInstance(EmpDAOImpl.class);
            //leveldao操作对象
            ILevelDAO levelDAO = DAOFactory.getInstance(LevelDAOImpl.class);
            //deptDao操作对象
            IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);

            //得到雇员修改前的数据
            Emp oldEmp =  empDAO.findById(editEmp.getEmpno());
            //判断雇员的工资是否有变化,日志elog中的sflag=1表示工资上涨,sflag=2表示工资下降,sflag=3表示工资没有变化
            if(editEmp.getSal() > oldEmp.getSal()){
                elog.setSflag(1);
            }else if(editEmp.getSal() < oldEmp.getSal()){
                elog.setSflag(2);
            }else{
                elog.setSflag(3);
            }

            //保存工资修改结果
            Boolean editSal_Boolean = false;
            if(!editEmp.getSal().equals(oldEmp.getSal())) {
                //判断修改的雇员工资是否在对应的等级之中
                Level editLevel = levelDAO.findById(editEmp.getLid());
                //如果修改的工资为所在的等级返回中
                if (editEmp.getSal() >= editLevel.getLosal() && editEmp.getSal() <= editLevel.getHisal()) {
                    editSal_Boolean = true;
                } else {
                    editSal_Boolean = false;
                }
                //如果雇员工资没有被修改
            }else{
                editSal_Boolean = true;
            }

            //保存部门修改结果
            Boolean editDept_Boolean = false;
            //判断雇员所在部门是否被修改
            if(!editEmp.getEmpno().equals(oldEmp.getDeptno())){
                //判断雇员要修改的部门是否有空余人数
                Dept editDept =  deptDAO.findById(editEmp.getDeptno());
                if(editDept.getCurrnum() < editDept.getMaxnum()){
                    //增加新部门的人数
                    deptDAO.updateCurrnum(editDept.getDeptno(),1);
                    //减少旧部门的人数
                    deptDAO.updateCurrnum(oldEmp.getDeptno(),-1);
                    editDept_Boolean = true;

                }else{//如果没有修改所在部门,则不需要进行任何操作
                    editDept_Boolean = false;
                }
            }else{
                editDept_Boolean = true;
            }

            //如果修改的工资和修改的部门都符合条件,那么可以进行雇员的修改
            if(editSal_Boolean && editDept_Boolean){
                //修改雇员信息
                if(empDAO.doUpdate(editEmp)){
                    //设置日志信息
                    elog.setEmpno(editEmp.getEmpno());
                    elog.setDeptno(editEmp.getDeptno());
                    elog.setMid(editEmp.getMid());
                    elog.setLid(editEmp.getLid());
                    elog.setJob(editEmp.getJob());
                    elog.setSal(editEmp.getSal());
                    elog.setComm(editEmp.getComm());
                    elog.setFlag(1);//修改的雇员一定是在职状态
                    elog.setNote("["+ DateUtil.getFormatDateTime() +"]"+elog.getNote());
                    //添加日志信息
                    IElogDAO elogDAO = DAOFactory.getInstance(ElogDAOImpl.class);
                    return elogDAO.doCreate(elog);
                }
            }

        }
        return false;
    }

    @Override
    public boolean add(Emp vo, Elog elog) throws Exception {
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
                        Integer empno = empDAO.getLastId();
                        //如果添加雇员成功,则将对应部门的人数加1
                        if( deptDAO.updateCurrnum(vo.getDeptno(),1)){
                            elog.setEmpno(empno);
                            elog.setDeptno(vo.getDeptno());
                            elog.setMid(vo.getMid());
                            elog.setLid(vo.getLid());
                            elog.setJob(vo.getJob());
                            elog.setSal(vo.getSal());
                            elog.setComm(vo.getComm());
                            elog.setSflag(0);//0表示刚入职,1表示涨工资,2表示减工资
                            elog.setFlag(1);
                            elog.setNote("["+ DateUtil.getFormatDateTime() +"]"+elog.getNote());
                            IElogDAO elogDAO = DAOFactory.getInstance(ElogDAOImpl.class);
                            return elogDAO.doCreate(elog);
                        }
                    }
                }
            }

        }
        return false;
    }



    @Override
    public Map<String, Object> listByFlag(String mid,Integer flag, Integer currentPage, Integer lineSize, String column, String keyword) throws Exception {
        if(super.auth(mid,"emp:list")) {
            IEmpDAO empdao = DAOFactory.getInstance(EmpDAOImpl.class);
            IDeptDAO deptDAO = DAOFactory.getInstance(DeptDAOImpl.class);
            ILevelDAO levelDAO= DAOFactory.getInstance(LevelDAOImpl.class);
            Map<String,Object> map = new HashMap<String,Object>();
            if (column == null || keyword == null || "".equals(column) || "".equals(keyword)) {
                map.put("allEmps",empdao.splitAllByFlag(flag,currentPage,lineSize));
                map.put("allCount",empdao.getAllCountByFlag(flag));
            } else {
                map.put("allEmps",empdao.splitAllByFlag(flag,currentPage,lineSize,column,keyword));
                map.put("allCount",empdao.getAllCountByFlag(flag,column,keyword));
            }

            //得出所有部门的信息和工资等级的信息
            List<Dept> deptsList = deptDAO.findAll();
            List<Level> levelList = levelDAO.findAll();

            //因为El表达式中无法识别int类型,所以使用Long代替
            //保存部门编号和部门名称
            Map<Long,String> deptno_dname = new HashMap<Long,String>();
            //保存等级编号和等级名称
            Map<Long,String> lid_title_flag = new HashMap<Long,String>();
            Iterator<Dept> deptIterator = deptsList.iterator();
            while(deptIterator.hasNext()){
                Dept temp = deptIterator.next();
                deptno_dname.put(temp.getDeptno().longValue(),temp.getDname());
            }

            Iterator<Level> levelIterator = levelList.iterator();
            while(levelIterator.hasNext()){
                Level temp = levelIterator.next();
                lid_title_flag.put(temp.getLid().longValue(),temp.getTitle()+"-"+temp.getFlag());
            }

            //将部门和等级信息保存到 map集合中
            map.put("deptno_dname",deptno_dname);
            map.put("lid_title_flag",lid_title_flag);
            return map;
        }
        return null;
    }
}
