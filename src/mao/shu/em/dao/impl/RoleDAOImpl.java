package mao.shu.em.dao.impl;

import mao.shu.em.dao.IRoleDAO;
import mao.shu.em.vo.Role;
import mao.shu.util.AbstractDAO;
import org.eclipse.jdt.internal.compiler.ast.SuperReference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    public boolean doCreateByMember(String mid, Set<Integer> rids) throws SQLException {
        String sql = "INSERT INTO member_role(mid,rid)VALUES(?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        Iterator<Integer> ridIterator = rids.iterator();
        while(ridIterator.hasNext()){
            Integer rid = ridIterator.next();
            super.pstmt.setString(1,mid);
            super.pstmt.setInt(2,rid);
            super.pstmt.addBatch();
        }
        int[] resultArray = super.pstmt.executeBatch();
        for (int i : resultArray) {
            if (i < 1) {
                return false;
            }

        }
        return true;
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
       String sql = "SELECT rid,title,flag FROM role;";
        super.pstmt = super.conn.prepareStatement(sql);
        ResultSet resultSet = super.pstmt.executeQuery();
        List<Role> list = new ArrayList<Role>();
        while(resultSet.next()){
            Role role = new Role();
            role.setRid(resultSet.getInt(1));
            role.setTitle(resultSet.getString(2));
            role.setFlag(resultSet.getString(3));
            list.add(role);
        }
        return list;
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
