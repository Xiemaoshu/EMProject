package mao.shu.em.service.back;

import mao.shu.em.vo.Dept;

import java.util.List;


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
}
