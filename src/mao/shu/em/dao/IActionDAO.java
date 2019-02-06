package mao.shu.em.dao;
import mao.shu.em.vo.Action;
import mao.shu.util.IDAO;


import java.sql.SQLException;
import java.util.Set;

public interface IActionDAO extends IDAO<Integer, Action> {
    /**
     * 该方法根据一个用户id取出该用户的所哟权限信息
     * @return
     * @throws SQLException
     */
    public Set<String> findAllByMember(String mid)throws SQLException;
}
