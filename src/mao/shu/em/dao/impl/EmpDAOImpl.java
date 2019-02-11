package mao.shu.em.dao.impl;

import mao.shu.em.dao.IEmpDAO;
import mao.shu.em.vo.Emp;
import mao.shu.util.AbstractDAO;


import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
        String sql = " UPDATE emp SET deptno=?,lid=?,ename=?,job=?,sal=?,comm=?,photo=? WHERE empno=? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,vo.getDeptno());
        super.pstmt.setInt(2,vo.getLid());

       super.pstmt.setString(3,vo.getEname());
       super.pstmt.setString(4,vo.getJob());
       super.pstmt.setDouble(5,vo.getSal());
       super.pstmt.setDouble(6,vo.getComm());
       super.pstmt.setString(7,vo.getPhoto());
       super.pstmt.setInt(8,vo.getEmpno());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    @Override
    public Emp findById(Integer id) throws SQLException {
        String sql = "SELECT empno,deptno,mid,lid,job,sal,comm,hiredate,photo,ename FROM emp WHERE empno=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,id);
        ResultSet resultSet = super.pstmt.executeQuery();
        if(resultSet.next()){
            Emp vo = new Emp();
            vo.setEmpno(resultSet.getInt(1));
            vo.setDeptno(resultSet.getInt(2));
            vo.setMid(resultSet.getString(3));
            vo.setLid(resultSet.getInt(4));
            vo.setJob(resultSet.getString(5));
            vo.setSal(resultSet.getDouble(6));
            vo.setComm(resultSet.getDouble(7));
            vo.setHiredate(resultSet.getDate(8));
            vo.setPhoto(resultSet.getString(9));
            vo.setEname(resultSet.getString(10));
            return vo;
        }
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

    @Override
    public List<Emp> splitAllByFlag(Integer flag, Integer currentPage, Integer lineSize, String column, String keyword) throws SQLException {
       String sql = "SELECT empno,deptno,mid,lid,ename,job,sal,comm,hiredate,photo,flag FROM emp WHERE " +
                " "+column+" LIKE ? AND flag=? LIMIT ?,?";
       super.pstmt = super.conn.prepareStatement(sql);
       super.pstmt.setString(1,"%"+keyword+"%");
       super.pstmt.setInt(2,flag);
       super.pstmt.setInt(3,(currentPage-1)*lineSize);
       super.pstmt.setInt(4,lineSize);
        ResultSet resultSet = super.pstmt.executeQuery();
        List<Emp> allEmps = new ArrayList<Emp>();
        while(resultSet.next()){
            Emp emp = new Emp();
            emp.setEmpno(resultSet.getInt(1));
            emp.setDeptno(resultSet.getInt(2));
            emp.setMid(resultSet.getString(3));
            emp.setLid(resultSet.getInt(4));
            emp.setEname(resultSet.getString(5));
            emp.setJob(resultSet.getString(6));
            emp.setSal(resultSet.getDouble(7));
            emp.setComm(resultSet.getDouble(8));
            emp.setHiredate(resultSet.getDate(9));
            emp.setPhoto(resultSet.getString(10));
            emp.setFlag(resultSet.getInt(11));
            allEmps.add(emp);
        }
        return allEmps;
    }

    @Override
    public List<Emp> splitAllByFlag(Integer flag, Integer currentPage, Integer lineSize) throws SQLException {
        String sql = "SELECT empno,deptno,mid,lid,ename,job,sal,comm,hiredate,photo,flag FROM emp WHERE " +
                " flag=? LIMIT ?,?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,flag);
        super.pstmt.setInt(2,(currentPage-1)*lineSize);
        super.pstmt.setInt(3,lineSize);
        ResultSet resultSet = super.pstmt.executeQuery();
        List<Emp> allEmps = new ArrayList<Emp>();
        while(resultSet.next()){
            Emp emp = new Emp();
            emp.setEmpno(resultSet.getInt(1));
            emp.setDeptno(resultSet.getInt(2));
            emp.setMid(resultSet.getString(3));
            emp.setLid(resultSet.getInt(4));
            emp.setEname(resultSet.getString(5));
            emp.setJob(resultSet.getString(6));
            emp.setSal(resultSet.getDouble(7));
            emp.setComm(resultSet.getDouble(8));
            emp.setHiredate(resultSet.getDate(9));
            emp.setPhoto(resultSet.getString(10));
            emp.setFlag(resultSet.getInt(11));
            allEmps.add(emp);
        }
        return allEmps;
    }

    @Override
    public Integer getAllCountByFlag(Integer flag, String cloumn, String keyword) throws SQLException {
        String sql = "SELECT COUNT(*) FROM emp WHERE flag=? AND "+cloumn+" LIKE ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,flag);
        super.pstmt.setString(2,"%"+keyword+"%");
        ResultSet resultSet = super.pstmt.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }else{
            return null;
        }
    }

    @Override
    public Integer getAllCountByFlag(Integer flag) throws SQLException {
        String sql = "SELECT COUNT(*) FROM emp WHERE flag=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,flag);
        ResultSet resultSet = super.pstmt.executeQuery();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }else{
            return null;
        }
    }

    @Override
    public boolean doUpdateByFlag(Set<Integer> empnos, Integer flag) throws SQLException {
        String sql = "UPDATE emp set flag=? WHERE empno=?";
        super.pstmt = super.conn.prepareStatement(sql);
        Iterator<Integer> idsIte = empnos.iterator();
        while(idsIte.hasNext()) {
            super.pstmt.setInt(1, flag);
            super.pstmt.setInt(2, idsIte.next());
            super.pstmt.addBatch();
        }
        int[] result = super.pstmt.executeBatch();
        for (int i = 0; i < result.length; i++) {
            if(result[i]<1){
                return false;
            }
        }
        return true;
    }
}
