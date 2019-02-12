package mao.shu.em.dao;

import mao.shu.em.vo.Emp;
import mao.shu.util.IDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IEmpDAO extends IDAO<Integer, Emp> {
    /**
     * 根据emp表中的flag字段进行分页查询
     * @param flag
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyword
     * @return
     * @throws SQLException
     */
    public List<Emp> splitAllByFlag(Integer flag,Integer currentPage,Integer lineSize,String column,String keyword)throws SQLException;

    public List<Emp> splitAllByFlag(Integer flag,Integer currentPage,Integer lineSize)throws SQLException;

    /**
     * 更具flag字段同级雇员人数
     * @param flag
     * @param cloumn
     * @param keyword
     * @return
     * @throws SQLException
     */
    public Integer getAllCountByFlag(Integer flag,String cloumn,String keyword)throws SQLException;
    public Integer getAllCountByFlag(Integer flag)throws SQLException;

    /**
     * 进行多个雇员在职状态的更新操作
     * @param empnos 雇员的编号集合
     * @param flag 在职的状态,flag=1 表示在职,flag=2 表示离职
     * @return 如果所有的雇员状态更新成功返回true,否则返回false
     * @throws SQLException
     */
    public boolean doUpdateByFlag(Set<Integer> empnos,Integer flag)throws SQLException;

    /**
     * 根据部门编号进行雇员分页显示
     * @param deptno 部门编号
     * @param currentPage 当前所在页数
     * @param linesize 每页显示个数
     * @return 返回一个雇员信息集合
     * @throws SQLException
     */
    public List<Emp> splitAllByDept(Integer deptno,Integer currentPage,Integer linesize)throws SQLException;

    /**
     * 根据部门编号,统计相同部门的雇员人数
     * @param deptno
     * @return
     * @throws SQLException
     */
    public Integer getALlCountByDept(Integer deptno)throws SQLException;
}
