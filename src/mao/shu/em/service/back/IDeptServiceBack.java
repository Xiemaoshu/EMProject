package mao.shu.em.service.back;

import mao.shu.em.vo.Dept;

import java.util.List;
import java.util.Map;


public interface IDeptServiceBack {
    /**
     * 该方法根据一个部门编号查询处一个部门完整信息,使用的是IDeptDAO.findByID()方法实现
     * @param deptno 查询的部门编号
     * @return 返回一个完整的部门信息
     * @throws Exception
     */
    public Dept get(Integer deptno)throws Exception;

    /**
     * 查询出所有部门信息的方法,调用IDeptDao中的findAll()方法
     * @param mid 操作用户的id,主要用于判断权限
     * @return 所有部门信息的集合
     * @throws Exception
     */
    public List<Dept> getALl(String mid)throws Exception;

    /**
     * 修改制定部门的最大部门人数
     * @param mid 操作员的id,主要用于判断权限
     * @param dept 修改的部门信息,其中包含被修改的部门编号,和要调整的最大部门人数
     * @return
     * @throws Exception
     */

    public boolean editMaxnum(String mid,Dept dept)throws Exception;

    /**
     * 对部门中的雇员进行分页显示
     * @param mid 操作员的id 用于进行权限判断
     * @param deptno 部门编号
     * @param currentPage 要查询的当前所在页数
     * @param lineSize 每个页面显示的数据个数
     * @return 查询出的结果有两个内容
     * <ol>
     *  <PRE>
     *       <code>key = allEmps  value = 查询出的雇员数据</code>
     *  </PRE>
     *  <PRE>
     *      <code>key = allRecorders value = 查询出所有雇员数量</code>
     * </PRE>
     * </ol>
     * @throws Exception
     */
    public Map<String,Object> listEmpByDept(String mid,Integer deptno,Integer currentPage,Integer lineSize)throws Exception;
}
