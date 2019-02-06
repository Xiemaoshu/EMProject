package mao.shu.em.dao.impl;

import mao.shu.em.dao.IRoleDAO;
import mao.shu.em.vo.Role;
import mao.shu.util.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDAOImpl extends AbstractDAO implements IRoleDAO {
    @Override
    public Set<String> findAllByMember(String mid) throws SQLException {
        String sql = "SELECT flag FROM role WHERE rid IN(" +
                "SELECT rid FROM member_role WHERE mid=?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        ResultSet rs = super.pstmt.executeQuery();
        Set<String> rids = new HashSet<String>();
        while(rs.next()){
            rids.add(rs.getString(1));
        }
        return rids;
    }

    @Override
    public boolean doCreate(Role vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Role vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    @Override
    public Role findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Role> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Role> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Role> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public Integer getAllCount() throws SQLException {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
