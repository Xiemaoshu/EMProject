package mao.shu.em.servlet.back;
import mao.shu.em.service.back.IDeptServiceBack;
import mao.shu.em.service.back.impl.DeptServiceBackImpl;
import mao.shu.em.servlet.abs.EMServlet;
import mao.shu.em.vo.Dept;
import mao.shu.util.factory.ServiceFactory;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/pages/back/dept/DeptServlet/*")
public class DeptServlet extends EMServlet {
    Dept dept = new Dept();
    public Dept getDept(){
        return this.dept;
    }
    public String list()throws Exception{
        if(super.auth("dept:list")){
            IDeptServiceBack deptServiceBack = ServiceFactory.getInstance(DeptServiceBackImpl.class);
            List<Dept> allDepts = deptServiceBack.getALl(super.getMid());
            super.setRequestAttribute("allDepts",allDepts);
            return "dept.list.page";
        }else{
            super.setErrors("auth","auth.failure.msg");
            return "error.page";
        }
    }


    @Override
    public String getDefaultColumn() {
        return null;
    }

    @Override
    public String getUploadDir() {
        return null;
    }

    @Override
    public String getType() {
        return "部门";
    }
}
