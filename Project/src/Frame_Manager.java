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

	public void popFrame() {
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
