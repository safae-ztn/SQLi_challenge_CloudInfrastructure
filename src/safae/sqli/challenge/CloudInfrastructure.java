package safae.sqli.challenge;

import java.util.ArrayList;
import java.util.List;

public class CloudInfrastructure {

	private List<Store> stores;
	private List<Machine> machines;
	public CloudInfrastructure() {
		super();
		stores = new ArrayList<>();
		machines = new ArrayList<>();
	}

	/*
	 * Store operations
	 */
	public void createStore(String name){
		try {
			if(!stores.contains(new Store(name))) {
				Store store = new Store(name);
				stores.add(store);
			}else {
				throw new CreateStoreException("you can not create two stores with the same name!");
			}
		}catch(CreateStoreException e) {
			e.printStackTrace();
		}
	}

	public void uploadDocument(String... allfiles) {
		String nameStore = allfiles[0];
		Store store = null;
		for(Store s : stores) {
			if(s.getName().equals(nameStore)) {
				store = s;
				for(int i=1; i<allfiles.length; i++) {
					store.getList().add(allfiles[i]);
				}
			}
		}
	}
	
	public String listStores() {
		String res = "";
		for(Store store : this.stores) {
			res += store.getName()+":";
			if(store.getList().isEmpty()) {
				res += "empty";
			}else {
				res+= store.getList().toString().replace("[", "").replace("]", "");
			}
			if(!this.stores.get(stores.size() - 1).equals(store)) {//if it is not the latest store we add ||
				res += "||";
			}
		}
		return res;
	}
	
	public void deleteStore(String name) {
		for(Store store : this.stores) {
			if(store.getName().equals(name)) {
				stores.remove(store);
			}
		}
	}


	public void emptyStore(String name) {
		for(Store store: this.stores) {
			if(store.getName().equals(name)) {
				store.getList().clear();
			}
		}
	}
	/*
	 * Machine operations
	 */
	public void createMachine(String name, String opSystem, String diskSize, String memorySize) {
		Machine machine = new Machine(name,opSystem,diskSize,memorySize);
		this.machines.add(machine);
	}

	public void startMachine(String name) {
		try {
			for(Machine m : machines) {
				if(m.getName().equals(name)) {
					if(m.getState().equals("running")) {
						throw new MachineStateException("you can not start an already running machine");
					}else {
						m.setState("running");
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void stopMachine(String name) {
		for(Machine m : machines) {
			if(m.getName().equals(name)) {
				m.setState("stopped");
				break;
			}
		}
	}

	public String listMachines() {//"machine1:running||machine2:inactive"
		String res = "";
		for(Machine machine : this.machines) {
			res += machine.getName()+":"+machine.getState();
			if(!this.machines.get(machines.size() - 1).equals(machine)) {//if it is not the latest machine we add ||
				res += "||";
			}
		}
		return res;
	}

	public int usedMemory(String name) {
		int res =0;
		for(Machine machine : this.machines) {
			if(machine.getName().equals(name)) {
				if(machine.getState().equals("running")) {
					res = Integer.valueOf(machine.getMemorySize().substring(0, 1));
				}
			}
		}
		return res;
	}

	public double usedDisk(String name) {
		double res =0;
		for(Machine machine : this.machines) {
			if(machine.getName().equals(name)) {
				res += Double.valueOf(machine.getDiskSize().substring(0, 2));
			}
		}
		for(Store store : this.stores) {
			if(!store.getList().isEmpty()) {
				res += store.getList().size()*0.1;
			}
		}
		return res;
	}

	public double globalUsedDisk() {
		double res =0;
		for(Machine machine : this.machines) {
			res += Double.valueOf(machine.getDiskSize().substring(0, 2));
		}
		for(Store store : this.stores) {
			if(!store.getList().isEmpty()) {
				res += store.getList().size()*0.1;
			}
		}
		return res;
	}

	public double globalUsedMemory() {
		double res =0;
		for(Machine machine : this.machines) {
			res += usedMemory(machine.getName());
		}
		for(Store store : this.stores) {
			res += usedMemory(store.getName());
		}
		return res;
	}

	public List<Store> getStores() {
		return this.stores;
	}
	
}
