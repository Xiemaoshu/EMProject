package mao.shu.em.dao.impl;

import mao.shu.em.dao.IActionDAO;
import mao.shu.em.vo.Action;
import mao.shu.util.AbstractDAO;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionDAOImpl extends AbstractDAO implements IActionDAO {

    @Override
    public Set<String> findAllByMember(String mid) throws SQLException {
        String sql = "select flag from action where actid in( " +
                        " select actid from role_action where rid in( " +
                             " select rid from member_role where mid=? " +
                         " ) " +
                      " ) ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        ResultSet rs = super.pstmt.executeQuery();
        Set<String> action_flags = new HashSet<String>();
        while(rs.next()){
            action_flags.add(rs.getString(1));
        }
        return action_flags;

    }

    @Override
    public boolean exists(String mid,String flag) throws SQLException {
        String sql = "SELECT flag FROM action WHERE actid IN " +
                " (SELECT actid FROM role_action WHERE rid IN " +
                " (SELECT rid FROM member_role WHERE mid=?) " +
                " )AND flag=? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,mid);
        super.pstmt.setString(2,flag);
        ResultSet resultSet = super.pstmt.executeQuery();
        if(resultSet.next()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean doCreate(Action vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Action vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    @Override
    public Action findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Action> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Action> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Action> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
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
