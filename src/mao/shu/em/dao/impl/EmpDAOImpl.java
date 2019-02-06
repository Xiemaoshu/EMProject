package mao.shu.em.dao.impl;

import mao.shu.em.dao.IEmpDAO;
import mao.shu.em.vo.Emp;
import mao.shu.util.AbstractDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class EmpDAOImpl extends AbstractDAO implements IEmpDAO {

    @Override
    public boolean doCreate(Emp vo) throws SQLException {
        String sql = "INSERT INTO emp(deptno,mid,lid,ename,job,sal,comm,hiredate,photo,flag)VALUES(?,?,?,?,?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,vo.getDeptno());
        super.pstmt.setString(2,vo.getMid());
        super.pstmt.setInt(3,vo.getLid());
        super.pstmt.setString(4,vo.getEname());
        super.pstmt.setString(5,vo.getJob());
        super.pstmt.setDouble(6,vo.getSal());
        super.pstmt.setDouble(7,vo.getComm());
        super.pstmt.setDate(8,new java.sql.Date(vo.getHiredate().getTime()));
        super.pstmt.setString(9,vo.getPhoto());
        super.pstmt.setInt(10,vo.getFlag());
        return super.pstmt.executeUpdate()> 0;
    }

    @Override
    public boolean doUpdate(Emp vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    @Override
    public Emp findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Emp> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Emp> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Emp> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
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
