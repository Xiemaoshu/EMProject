package mao.shu.em.servlet.back;


import mao.shu.em.service.back.IEmpServiceBack;
import mao.shu.em.service.back.impl.EmpServiceBackImpl;
import mao.shu.em.servlet.abs.EMServlet;
import mao.shu.em.vo.Emp;
import mao.shu.util.factory.ServiceFactory;

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
                if(empServiceBack.add(this.emp)){
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
    @Override
    public String getDefaultColumn() {
        return null;
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
