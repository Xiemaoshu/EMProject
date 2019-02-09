package mao.shu.em.servlet.back;


import mao.shu.em.service.back.IDeptServiceBack;
import mao.shu.em.service.back.IEmpServiceBack;
import mao.shu.em.service.back.ILevelServiceBack;
import mao.shu.em.service.back.impl.DeptServiceBackImpl;
import mao.shu.em.service.back.impl.EmpServiceBackImpl;
import mao.shu.em.service.back.impl.LevelServiceBackImpl;
import mao.shu.em.servlet.abs.EMServlet;
import mao.shu.em.vo.Dept;
import mao.shu.em.vo.Elog;
import mao.shu.em.vo.Emp;
import mao.shu.em.vo.Level;
import mao.shu.util.factory.ServiceFactory;
import mao.shu.util.split.SplitPageUtils;

import javax.servlet.annotation.WebServlet;
import java.util.Map;
@WebServlet("/pages/back/emp/EmpServletBack/*")
public class EmpServletBack extends EMServlet {
    Emp emp = new Emp();
    public Emp getEmp(){
        return this.emp;
    }
    public String addPre()throws Exception{
        //判断当前操作用户是否具备有操作雇员的权限
        if(super.auth("emp:add")){
            IEmpServiceBack empServiceBack = ServiceFactory.getInstance(EmpServiceBackImpl.class);
            Map<String,Object> map = empServiceBack.addPre();
            //保存所有的工资等级和有空位置的部门
            super.setSessionAttribute("allUnderDepts",map.get("allUnderDepts"));
            super.setSessionAttribute("allLevels",map.get("allLevels"));
            return "emp.add.page";
        }else{
            super.setErrors("auth","auth.failure.msg");
            return "error.page";
        }

    }
    public String add(){
        try {
            //判断当前是否有添加雇员的权限
            if(super.auth("emp:add")){
                //判断当前是否有文件上传
                if(super.isUploadFile()){
                    this.emp.setPhoto(super.createSingleFileName());//创建文件名称
                }else{
                    this.emp.setPhoto("nophoto.png");//使用默认的图片
                }
                this.emp.setMid(super.getMid());//设置添加该雇员的操作用户
                IEmpServiceBack empServiceBack = ServiceFactory.getInstance(EmpServiceBackImpl.class);
                Elog elog = new Elog();
                elog.setNote(super.getStringParameter("note"));
                if(empServiceBack.add(this.emp,elog)){
                    super.setUrlAndMsg("emp.add.servlet","vo.add.success.msg");
                    if(super.isUploadFile()){
                        super.saveUploadFile(this.emp.getPhoto());//保存原图片
                        super.saveScale(this.emp.getPhoto());//保存缩略图
                    }
                }else{
                    super.setUrlAndMsg("emp.add.servlet","vo.add.failure.msg");
                }
                return "forward.page";
            }else{
                super.setErrors("auth","auth.failure.msg");
                return "error.page";
            }
        } catch (Exception e) {

            e.printStackTrace();
            return "error.page";
        }

    }

    /**
     * 该方法用于验证新增的雇员要添加的部门是否有剩余人数,该方法主要提供给页面进行ajax异步验证处理
     */
    public void checkDept(){
        Integer deptno = super.getIntParameter("deptno");
        IDeptServiceBack deptServiceBack = ServiceFactory.getInstance(DeptServiceBackImpl.class);
        try {
            Dept dept = deptServiceBack.get(deptno);
           super.printData(dept.getCurrnum()<dept.getMaxnum());

        } catch (Exception e) {
            super.printData("false");
            e.printStackTrace();
        }
    }

    /**
     * 该方法用户对用户填写的新增雇员的工资是否在指定的工资等级范围之内做一个判断,
     * 该判断用于页面验证的ajax的异步判断
     */
    public void chekSal(){
        Double sal = super.getDoubleParameter("sal");
        Integer lid = super.getIntParameter("lid");
        ILevelServiceBack levelServiceBack = ServiceFactory.getInstance(LevelServiceBackImpl.class);
        try {
            Level level = levelServiceBack.get(lid);
            super.printData(level.getHisal()>= sal && level.getLosal()<= sal);

        } catch (Exception e) {
            super.printData("false");
            e.printStackTrace();
        }
    }

    public String list(){
        String urlkey = "emp.list.servlet";//保存执行分页servlet的地址
        //判断用户查询的是在职雇员还是离职雇员的标记
        Integer flag = super.getIntParameter("flag");
        //进行分页参数的获取
        SplitPageUtils splitPageUtils = new SplitPageUtils(super.request);
        Integer currentPage = splitPageUtils.getCurrentPage();
        Integer linesize = splitPageUtils.getLineSize();
        String keyword = splitPageUtils.getKeyWord();
        String column = splitPageUtils.getColumn();
        IEmpServiceBack empServiceBack = ServiceFactory.getInstance(EmpServiceBackImpl.class);
        try {
            Map<String,Object> splitResult = empServiceBack.listByFlag(super.getMid(),flag,currentPage,linesize,column,keyword);
            //将分页的数据传输到JSP中
           request.setAttribute("allEmps",splitResult.get("allEmps"));
           //分页组件传递分页所需的参数,实现切换页码
           super.setSplitPage(urlkey,(Integer) splitResult.get("allCount"),splitPageUtils);
           super.setSplitParam("flag",flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "emp.list.page";
    }
    @Override
    public String getDefaultColumn() {
        return "雇员姓名:ename|雇员职位:job";
    }

    @Override
    public String getUploadDir() {
        return "/upload/emp/";
    }

    @Override
    public String getType() {
        return "雇员";
    }
}
