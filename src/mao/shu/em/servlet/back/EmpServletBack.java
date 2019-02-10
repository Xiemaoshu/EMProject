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
import java.util.List;
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
            Map<String,Object> map = empServiceBack.addPre(super.getMid());
            //保存所有的工资等级和有空位置的部门
            super.setSessionAttribute("allUnderDepts",map.get("allUnderDepts"));
            super.setSessionAttribute("allLevels",map.get("allLevels"));
            return "emp.add.page";
        }else{
            super.setErrors("auth","auth.failure.msg");
            return "error.page";
        }

    }

    public String editPre()throws Exception{
        //从请求中取得当前的雇员编号
        Integer empno = super.getIntParameter("empno");
        //判断当前用户的权限是否有编辑用户的权限
        if(super.auth("emp:edit")){
            IEmpServiceBack empServiceBack = ServiceFactory.getInstance(EmpServiceBackImpl.class);
            Map<String,Object> map = empServiceBack.editPre(super.getMid(),empno);
            super.setSessionAttribute("allDepts",map.get("allDepts"));
            super.setSessionAttribute("allLevels",map.get("allLevels"));
            super.setSessionAttribute("emp",map.get("emp"));
            return "emp.edit.page";
        }else{
            super.setErrors("auth","auth.failure.msg");
            return "error.page";
        }
    }

    /**
     * 进行用户的添加操作,在进行添加操作的时候需要判断操作用户是否具备有"emp:add"的权限.
     * 1. 如果雇员有上传图片嘻嘻,则进行保存文件,如果没有,则保存默认图片
     * 2. 保存雇员数据
     * 3. 保存日志数据
     * 4. 添加成功利用forward.jsp页面跳转到 EmpServletBack.addPre()方法上,如果添加失败,则跳转到 error.page 页面上
     * @return
     */
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

    public String edit(){

        //判断操作用户的权限是否有"emp:edit",修改用户的权限
        try {
            if(super.auth("emp:edit")){
                //判断用户是否有上传文件
                if(super.isUploadFile()){
                    //因为默认的图片使用的名称为"notphoto.png",如果雇员之前使用的是默认的图片,则应该新建一个新的图片名称
                    if("nophoto.png".equals(this.emp.getPhoto())){
                        this.emp.setPhoto(super.createSingleFileName());
                    }
                }

                //修改操作员的编号
                this.emp.setMid(super.getMid());
                //保存日志信息
                Elog elog = new Elog();
                //在日志中保存雇员简介
                elog.setNote(super.getStringParameter("note"));

                //得到empserviceBack 业务层的操作接口对象
                IEmpServiceBack empServiceBack = ServiceFactory.getInstance(EmpServiceBackImpl.class);

                //修改雇员数据和保存日志数据
                if(empServiceBack.edit(this.emp,elog)){
                    super.setUrlAndMsg("emp.list.servlet","vo.edit.success.msg");
                    //保存新的图片和缩略图
                    if(super.isUploadFile()){
                        super.saveUploadFile(this.emp.getPhoto());
                        super.saveScale(this.emp.getPhoto());
                    }
                }else{
                    super.setUrlAndMsg("emp.list.servlet","vo.edit.failure.msg");
                }
                return "forward.page";
            }else{
                super.setErrors("auth","auth.failure.msg");
                return "error.page";
            }
        } catch (Exception e) {
            super.setUrlAndMsg("emp.list.page","vo.edit.failure.msg");
            e.printStackTrace();
            return "error.page";
        }
    }


    /**
     * 该方法用于验证新增的雇员要添加的部门是否有剩余人数,该方法主要提供给页面进行ajax异步验证处理
     */
    public void checkDept(){
        Integer deptno = super.getIntParameter("deptno");
        Integer currDeptno = super.getIntParameter("currDeptno");
        //判断当前雇员编号和原雇员编号是否一样
        if(deptno == currDeptno){
            super.printData("true");
            return;
        }
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
        Integer flag = null;
        try {
            flag =  super.getIntParameter("flag");
            //如果没有flag参数,则默认为1
        }catch(Exception e){
            flag=1;
        }

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
           request.setAttribute("deptno_dname",splitResult.get("deptno_dname"));
           request.setAttribute("lid_title_flag",splitResult.get("lid_title_flag"));

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
