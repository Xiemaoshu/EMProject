package mao.shu.em.dao.impl;

import mao.shu.em.dao.IMemberDAO;
import mao.shu.em.vo.Member;
import mao.shu.util.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class MemberDAO extends AbstractDAO implements IMemberDAO {
    @Override
    public boolean doCreate(Member vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Member vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws SQLException {
        return false;
    }

    @Override
    public Member findById(String id) throws SQLException {
        String sql="SELECT mid,password,name,sflag FROM member WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,id);
        ResultSet rs = super.pstmt.executeQuery();
        Member vo = null;
        if(rs.next()){
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setPassword(rs.getString(2));
            vo.setName(rs.getString(3));
            vo.setSflag(rs.getInt(4));
        }
        return vo;
    }

    @Override
    public List<Member> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Member> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Member> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
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
