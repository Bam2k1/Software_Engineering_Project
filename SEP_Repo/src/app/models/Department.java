package app.models;

import java.util.ArrayList;
import java.util.List;

public class Department {

	private String deptId;
	private String name;
	private String description;
	private User headOfDept;
	
	private List<User> members;
	
	public Department(String deptId, String name, String description) {
		this.deptId = deptId;
		this.name = name;
		this.description = description;
		this.members = new ArrayList<>();
	}
	
	public List<User> getMembers(){
		return this.members;
	}
	
	public void addNoticeBoard() {
		
	}
	

	public void assignHead(User user) {
		this.headOfDept = user;
	}
}
