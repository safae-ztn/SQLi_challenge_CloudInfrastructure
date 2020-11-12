package safae.sqli.challenge;

import java.util.LinkedList;
import java.util.List;

public class Store {
	
	private String name;
	private List<String> list;
	
	public Store(String name) {
		super();
		this.name = name;
		this.list = new LinkedList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}
