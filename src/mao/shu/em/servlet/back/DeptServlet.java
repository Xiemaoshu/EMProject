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

    /**
     * 列出所有部门信息的方法,将所有部门的信息设置到request属性中,共页面获取
     * @return
     * @throws Exception
     */
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

    /**
     * 该方法用于页面中调整部门最大人数的ajax操作,直接将程序的执行结果打印到页面之中
     * 所以没有任何返回结果.
     */
    public void editMaxnum(){
        try {
            if(super.auth("dept:edit")){
                //获取页面请求中的部门编号和修改的部门最大人数
                Integer deptno = super.getIntParameter("deptno");
                Integer maxnum = super.getIntParameter("maxnum");
                IDeptServiceBack deptServiceBack = ServiceFactory.getInstance(DeptServiceBackImpl.class);
                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setMaxnum(maxnum);
                boolean resultFlag = deptServiceBack.editMaxnum(super.getMid(),dept);
                super.printData(resultFlag);
            }
        } catch (Exception e) {
            super.printData("false");
            e.printStackTrace();
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
