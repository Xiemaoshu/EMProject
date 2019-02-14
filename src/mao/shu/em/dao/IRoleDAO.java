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

    /**
     * 进行向member_role 表中添加多条数据,保存指定用户的角色信息
     * @param mid 添加的用户id
     * @param rids 保存的角色id集合
     * @return 所有数据保存成功返回他如何,否则返回false
     * @throws SQLException
     */
    public boolean doCreateByMember(String mid, Set<Integer> rids)throws SQLException;
}
