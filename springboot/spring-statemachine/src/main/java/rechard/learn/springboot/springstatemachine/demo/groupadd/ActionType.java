package rechard.learn.springboot.springstatemachine.demo.groupadd;

/**
 * Event
 */
public enum ActionType {
 APPROVE(1), REJECT(2);
	
	private int action;
	
	private ActionType(int action) {
		this.action = action;
	}

	public int getAction() {
		return action;
	}
}
