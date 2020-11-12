package safae.sqli.challenge;

public class Machine {
	
	private String name;
	private String opSystem;
	private String diskSize;
	private String memorySize;
	private String state;
	
	public Machine(String name, String opSystem, String diskSize, String memorySize) {
		this.name = name;
		this.opSystem = opSystem;
		this.diskSize = diskSize;
		this.memorySize = memorySize;
		this.state = "inactive";
	}
	//getters
	public String getName() {
		return name;
	}

	public String getOpSystem() {
		return opSystem;
	}

	public String getDiskSize() {
		return diskSize;
	}

	public String getMemorySize() {
		return memorySize;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
