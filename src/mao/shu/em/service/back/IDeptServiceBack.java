package mao.shu.em.service.back;

import mao.shu.em.vo.Dept;

public interface IDeptServiceBack {
    /**
     * 该方法根据一个部门编号查询处一个部门完整信息,使用的是IDeptDAO.findByID()方法实现
     * @param deptno 查询的部门编号
     * @return 返回一个完整的部门信息
     * @throws Exception
     */
    public Dept get(Integer deptno)throws Exception;
}
