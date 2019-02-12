package mao.shu.em.dao;

import mao.shu.em.vo.Dept;
import mao.shu.util.IDAO;

import java.sql.SQLException;
import java.util.List;

public interface IDeptDAO extends IDAO<Integer, Dept> {
    /**
     * 查询出所有还未满员的部门
     * @return 所有未满员的部门数据
     * @throws SQLException
     */
    public List<Dept> findUnders()throws SQLException;

    /**
     * 对指定部门的当前人数进行更新
     * @param deptno 更新的部门编号
     * @param updateNum  更新的数量,该参数允许为负数,如果是负数,则更新为减少部门人数
     * @return 更新的结果,成功返回true,失败返回false
     * @throws SQLException
     */
    public boolean updateCurrnum(Integer deptno,Integer updateNum)throws SQLException;

    /**
     * 根据指定雇员的编号对该雇员所在的部门的当前人数进行更新
     * @param empno 雇员的编号
     * @param deptno 更新的部门编号
     * @param updateNum  更新的数量,该参数允许为负数,如果是负数,则更新为减少部门人数
     * @return 更新的结果,成功返回true,失败返回false
     * @throws SQLException
     */
    public boolean updateCurrnumByEmpno(Integer empno,Integer deptno,Integer updateNum)throws SQLException;

    /**
     *  进行制定部门的最大人数调整,部门最大人数修改成功返回true,否则返回false
     * @param deptno 修改的部门编号
     * @param maxnum 调整的部门最大人数
     * @return
     * @throws SQLException
     */
    public boolean doUpdateMaxnum(Integer deptno,Integer maxnum)throws SQLException;
}
