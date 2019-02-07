package mao.shu.em.dao.impl;

import mao.shu.em.dao.ILevelDAO;
import mao.shu.em.vo.Level;
import mao.shu.util.AbstractDAO;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LevelDAOImpl extends AbstractDAO implements ILevelDAO {
    @Override
    public boolean doCreate(Level vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doUpdate(Level vo) throws SQLException {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    @Override
    public Level findById(Integer id) throws SQLException {
        String sql = "SELECT lid,losal,hisal,title,flag FROM level WHERE lid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,id);
        Level vo = null;
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()){
            vo = new Level();
            vo.setLid(rs.getInt(1));
            vo.setLosal(rs.getDouble(2));
            vo.setHisal(rs.getDouble(3));
            vo.setTitle(rs.getString(4));
            vo.setFlag(rs.getString(5));
        }
        return vo;
    }

    @Override
    public List<Level> findAll() throws SQLException {
        String sql = "SELECT lid,losal,hisal,title,flag FROM level";
        super.pstmt = super.conn.prepareStatement(sql);
        List<Level> allLevels = new ArrayList<Level>();
        ResultSet resultSet = super.pstmt.executeQuery();
        while(resultSet.next()){
            Level vo = new Level();
            vo.setLid(resultSet.getInt(1));
            vo.setLosal(resultSet.getDouble(2));
            vo.setHisal(resultSet.getDouble(3));
            vo.setTitle(resultSet.getString(4));
            vo.setFlag(resultSet.getString(5));
            allLevels.add(vo);
        }
        return allLevels;
    }

    @Override
    public List<Level> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    @Override
    public List<Level> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
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
