package view;

public class Command {
	
	private String name;
	private String description;
	private Runnable action;
	
	public void execute() {
		action.run();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public Command(String name, String description, Runnable action) {
		super();
		this.name = name;
		this.description = description;
		this.action = action;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Runnable getAction() {
		return action;
	}

	public void setAction(Runnable action) {
		this.action = action;
	}

}
