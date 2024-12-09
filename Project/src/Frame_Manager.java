import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

public class Frame_Manager {
	
	private Stack<Base_Frame> frameStack;

	public Frame_Manager() {
		
		frameStack = new Stack<>();
		
	}

	public void pushFrame(Base_Frame frame) {
		
		frameStack.push(frame);
		frame.setVisible(true);
		
	}

	public void popFrame() throws SQLException {
		
		
		
		if (!frameStack.isEmpty()) {
			
			Base_Frame f = frameStack.pop();
			f.setVisible(false);
			f.dispose();
			
			if (!frameStack.isEmpty()) {
				frameStack.peek().setVisible(true);
			}
			
		}
		
	}

	public void exit() {
		
		System.exit(0);
		
	}
	
}
