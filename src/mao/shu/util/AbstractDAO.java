package mao.shu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import mao.shu.util.dbc.DatabaseConnection;

public class AbstractDAO {
	protected Connection conn ;
	protected PreparedStatement pstmt ;
	public AbstractDAO() {
		this.conn = DatabaseConnection.get() ;
	}
	
}
