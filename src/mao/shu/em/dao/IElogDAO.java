package mao.shu.em.dao;

import mao.shu.em.vo.Elog;
import mao.shu.util.IDAO;

import java.sql.SQLException;
import java.util.Set;

public interface IElogDAO extends IDAO<Integer, Elog> {
    /**
     * 查询一个雇员的所有日志信息.
     * @param empno 雇员的编号
     * @return
     * @throws SQLException
     */
    public Set<Elog> findAllByEmp(Integer empno)throws SQLException;
}
