package mao.shu.em.servlet.back;
import mao.shu.em.dao.IDeptDAO;
import mao.shu.em.service.back.IDeptServiceBack;
import mao.shu.em.service.back.impl.DeptServiceBackImpl;
import mao.shu.em.servlet.abs.EMServlet;
import mao.shu.em.vo.Dept;
import mao.shu.util.factory.ServiceFactory;
import mao.shu.util.split.SplitPageUtils;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/pages/back/dept/DeptServletBack/*")
public class DeptServletBack extends EMServlet {
    Dept dept = new Dept();
    public Dept getDept(){
        return this.dept;
    }


    /**
     * <p>该方法进行部门雇员的分页显示,使用的是ajax异步处理调用,所以没有任何返回值</p>
     * <p>将分页去除的数据直接以JSON数据格式打印到页面之中</p>
     * <p>
     *     打印的json数据中包含以下内容
     *     <ol>
     *         <li>
     *             flag:方法执行的结果
     *             allEmps:分页查询的雇员数据
     *             allRecorders:所有部门人员的数量
     *         </li>
     *     </ol>
     * </p>
     * @throws Exception
     */
    public void listEmp()throws Exception{
        JSONObject jsonObject = new JSONObject();
        if(super.auth("dept:list")){
            jsonObject.put("flag",true);
            //进行分页所需的基本数据处理,如果没有使用默认的数据
            SplitPageUtils splitPageUtils = new SplitPageUtils(super.request);
            Integer currentPage = splitPageUtils.getCurrentPage();
            Integer lineSize = splitPageUtils.getLineSize();
            Integer deptno  = super.getIntParameter("deptno");
            IDeptServiceBack deptServiceBack = ServiceFactory.getInstance(DeptServiceBackImpl.class);
            Map<String,Object> map = deptServiceBack.listEmpByDept(super.getMid(),deptno,currentPage,lineSize) ;
            jsonObject.put("allEmps",map.get("allEmps"));
            jsonObject.put("allRecorders",map.get("allRecorders"));
            jsonObject.put("allLevels",map.get("allLevels"));
        }else{
            jsonObject.put("flag",false);
        }
        //向也页面之中打印JSON数据
        super.response.setContentType("JSON");
        super.printData(jsonObject);
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
