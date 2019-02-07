package mao.shu.em.dao.impl;

import mao.shu.em.dao.IDeptDAO;
import mao.shu.em.vo.Dept;
import mao.shu.util.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeptDAOImpl extends AbstractDAO implements IDeptDAO {
    @Override
    public List<Dept> findUnders() throws SQLException {
        String sql = "SELECT deptno,dname,maxnum,currnum FROM dept WHERE CURRNUM < MAXNUM";
        super.pstmt = super.conn.prepareStatement(sql);
        List<Dept> under_Depts = new ArrayList<Dept>();
        ResultSet rs = super.pstmt.executeQuery();
        while(rs.next()){
            Dept vo = new Dept();
            vo.setDeptno(rs.getInt(1));
            vo.setDname(rs.getString(2));
            vo.setMaxnum(rs.getInt(3));
            vo.setCurrnum(rs.getInt(4));
            under_Depts.add(vo);
        }
        return under_Depts;
    }

    @Override
    public boolean updateCurrnum(Integer deptno,Integer updateNum) throws SQLException {
        String sql = "UPDATE dept SET currnum=currnum+"+updateNum+" WHERE deptno=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,deptno);
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doCreate(Dept vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Dept vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    @Override
    public Dept findById(Integer id) throws SQLException {
       String sql = "SELECT deptno,dname,maxnum,currnum FROM dept WHERE deptno=?";
       super.pstmt = super.conn.prepareStatement(sql);
       super.pstmt.setInt(1,id);
       ResultSet resultSet = super.pstmt.executeQuery();
        Dept vo = null;
       if(resultSet.next()){
           vo = new Dept();
           vo.setDeptno(resultSet.getInt(1));
           vo.setDname(resultSet.getString(2));
           vo.setMaxnum(resultSet.getInt(3));
           vo.setCurrnum(resultSet.getInt(4));
       }
       return vo;
    }

    @Override
    public List<Dept> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Dept> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Dept> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
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
