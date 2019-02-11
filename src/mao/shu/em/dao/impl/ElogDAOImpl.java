package mao.shu.em.dao.impl;

import mao.shu.em.dao.IElogDAO;
import mao.shu.em.vo.Elog;
import mao.shu.util.AbstractDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElogDAOImpl extends AbstractDAO implements IElogDAO {
    @Override
    public Set<Elog> findAllByEmp(Integer empno) throws SQLException {
        Set<Elog> allElogs = new HashSet<Elog>();
        String sql = "SELECT elid,empno,deptno,mid,lid,job,sal,comm,sflag,flag,note FROM elog WHERE empno=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,empno);
        ResultSet resultSet = super.pstmt.executeQuery();
        while(resultSet.next()){
            Elog elog = new Elog();
            elog.setElid(resultSet.getInt(1));
            elog.setEmpno(resultSet.getInt(2));
            elog.setDeptno(resultSet.getInt(3));
            elog.setMid(resultSet.getString(4));
            elog.setLid(resultSet.getInt(5));
            elog.setJob(resultSet.getString(6));
            elog.setSal(resultSet.getDouble(7));
            elog.setComm(resultSet.getDouble(8));
            elog.setSflag(resultSet.getInt(9));
            elog.setFlag(resultSet.getInt(10));
            elog.setNote(resultSet.getString(11));
            allElogs.add(elog);
        }
        return allElogs;
    }

    @Override
    public boolean doCreate(Elog vo) throws SQLException {
        String sql = "INSERT INTO elog(empno,deptno,mid,lid,job,sal,comm,sflag,flag,note) VALUES(?,?,?,?,?,?,?,?,?,?)" ;
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,vo.getEmpno());
        super.pstmt.setInt(2,vo.getDeptno());
        super.pstmt.setString(3,vo.getMid());
        super.pstmt.setInt(4,vo.getLid());
        super.pstmt.setString(5,vo.getJob());
        super.pstmt.setDouble(6,vo.getSal());
        super.pstmt.setDouble(7,vo.getComm());
        if (vo.getSflag()==null) {
            super.pstmt.setInt(8, Types.NULL);
        }else{
            super.pstmt.setInt(8, vo.getSflag());
        }
        super.pstmt.setInt(9,vo.getFlag());
        super.pstmt.setString(10,vo.getNote());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Elog vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    @Override
    public Elog findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Elog> findAll() throws SQLException {
        return null;
    }

    @Override
    public List<Elog> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Elog> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
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
