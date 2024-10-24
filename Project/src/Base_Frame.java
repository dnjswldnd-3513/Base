import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public abstract class Base_Frame extends JFrame{

	abstract void init();
	abstract void action();
	
	String n = "North";
	String s = "South";
	String w = "West";
	String e = "East";
	String c = "Center";
	
	
	Border line =new LineBorder(Color.black);
}
