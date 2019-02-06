package mao.shu.em.dao;

import mao.shu.em.vo.Role;
import mao.shu.util.IDAO;

import java.sql.SQLException;
import java.util.Set;

public interface IRoleDAO extends IDAO<Integer, Role> {
    /**
     * 该方法根据一个用户id查找该用户的所有角色信息
     * @return
     * @throws SQLException
     */
    public Set<String> findAllByMember(String mid)throws SQLException;
}
