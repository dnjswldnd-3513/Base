package Resultset_관련;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class 어떤느낌인지_rs extends Base{
	ResultSet rs;//base안에 추가하는것도 괜찮은듯 상속할때마다의 각 창에서 rs라는게 생기는 거니까.
	DefaultTableModel model = new DefaultTableModel("1,2,3,4".split(","),0);
	JTable tb = new JTable(model);
	
	public 어떤느낌인지_rs() throws SQLException {
		init();
	}
	

	public static void main(String[] args) throws SQLException {
		new 어떤느낌인지_rs();
	}
	
	@Override
	void init() {
		add(tb);
		String sql = "";//sql문
		String value = "" ;//추가할거
		rs = query(sql,value);//값을 가져옴
		List<Row> tablelist = changeList(rs);
//		addRow(tablelist);
//		이걸로 화면에 뿌려줌. 값들을
	}


	@Override
	void action() {
		tb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tb.getSelectedRow();
				int colum = tb.getColumnCount();

				//일단 간추려서 대충 형태로 보면 좋겠습니다.
				String newValue = ""	;//선택한 값
				if (row != -1) {
					String selectvalue = tb.getValueAt(row, colum).toString();
					//값을 저장해놔야 한다면 이때 저장
					
					try {
						rs.absolute(row+1);
						rs.updateString("컬럼이름", newValue);
						rs.updateRow();
						//값이 추가됨
						//이후 다시 리스트로 뿌려줘도 됨
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
			}
		});
	}
}
