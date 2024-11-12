package Resultset_관련;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class Base extends JFrame{
	
	abstract void init();
	abstract void action();
	
	public ResultSet query(String sql,Object...val){//try를 사용하면 블록이 종료되는 순간 자동으로 닫힌다.
		ResultSet rs = null;
		try (Connection con = createConnection()){
			PreparedStatement s =  settingPreparedstatement(con, sql, val);
			rs = s.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	class Row extends ArrayList{
		public String getStr(int i) {
			return this.get(i).toString();
		}
	}
	
	public List<Row> changeList(ResultSet rs) {
		List<Row> list =new ArrayList<>();
		try {
			while (rs.next()) {
				Row row  = new Row();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getObject(i));
				}
				list.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
			
	public void update(String sql,Object...val){
		try (Connection con = createConnection()){
			PreparedStatement s = settingPreparedstatement(con,sql,val);
			s.executeUpdate();
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
	private final static Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/데이터베이스이름?allowLoadLocalInfile=true&serverTimzone=UTC","root","1234");
	}
	
	private static PreparedStatement settingPreparedstatement(Connection con, String sql, Object[] val) throws SQLException {
		PreparedStatement s = con.prepareStatement(sql);
		for (int i = 0; i < val.length; i++) {
			s.setObject(i+1, val[i]);
		}
		return s;
	}
}
