import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Connection_Utils {

	public static class Row extends ArrayList{
		
		public Integer getint(int i) {
			return Integer.parseInt(this.get(i).toString());
		}
		
		public String getStr(int i) {
			return this.get(i).toString();
		}
		public ImageIcon getBytes(int i,int x,int y) {
			return new ImageIcon(new ImageIcon((byte[])this.get(i)).getImage().getScaledInstance(x, y, 4));
		}
	}
	public List<Row> Query(String sql,Object...val){
		List<Row> list =new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost?serverTimezone=UTC&allowLoadLocalInfile=true","root","1234")){
			PreparedStatement s = con.prepareStatement(sql);
			for (int i = 0; i < val.length; i++) {
				s.setObject(i + 1, val[i]);
			}
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Row row =new Row();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getObject(i));
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void Update(String sql,Object...val){
		List<Row> list =new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost?serverTimezone=UTC&allowLoadLocalInfile=true","root","1234")){
			PreparedStatement s = con.prepareStatement(sql);
			for (int i = 0; i < val.length; i++) {
				s.setObject(i + 1, val[i]);
			}
			s.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
