package mao.shu.em.dao;
import mao.shu.em.vo.Action;
import mao.shu.util.IDAO;


import java.sql.SQLException;
import java.util.Set;

public interface IActionDAO extends IDAO<Integer, Action> {
    /**
     * 该方法根据一个用户id取出该用户的所有权限信息
     * @return
     * @throws SQLException
     */
    public Set<String> findAllByMember(String mid)throws SQLException;

    /**
     * 该方法进行对一个指定用户,判断该用户是否具备有指定权限的方法
     * @param mid 用户的id
     * @param flag 权限的标记 以"emp:add"的形式传入
     * @return 如果该用户具备有制定权限返回true,否则返回false
     * @throws SQLException
     */
    public boolean exists(String mid,String flag)throws SQLException;
}
