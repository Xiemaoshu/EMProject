package mao.shu.em.dao.impl;

import mao.shu.em.dao.IMemberDAO;
import mao.shu.em.vo.Member;
import mao.shu.util.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MemberDAOImpl extends AbstractDAO implements IMemberDAO {
    @Override
    public boolean doCreate(Member vo) throws SQLException {
        String sql = "INSERT INTO member(mid,password,name,sflag)VALUES(?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getMid());
        super.pstmt.setString(2,vo.getPassword());
        super.pstmt.setString(3,vo.getName());
        //新的管理员只能是普通管理员,只有超级管理员才能够将修改普通管理员
        super.pstmt.setInt(4,vo.getSflag());
        return super.pstmt.executeUpdate() > 0;
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
       String sql = "SELECT mid,name,sflag FROM member ";
       super.pstmt = super.conn.prepareStatement(sql);
       ResultSet resultSet = super.pstmt.executeQuery();
       List<Member> list = new ArrayList<Member>();
       while(resultSet.next()){
           Member vo = new Member();
           vo.setMid(resultSet.getString(1));
           vo.setName(resultSet.getString(2));
           vo.setSflag(resultSet.getInt(3));
           list.add(vo);
       }
       return list;
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
