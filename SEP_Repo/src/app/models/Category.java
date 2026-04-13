package app.models;

import java.util.ArrayList;
import java.util.List;

import app.notices.Notice;

public class Category{

	private String categoryId;
	private String name;
	private String description;
	
	private List<Notice> notices;
	
	public Category(String categoryId, String name, String description) {
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.notices = new ArrayList<>();
	}
	
	public List<Notice> getNotices() {
		return this.notices;
	}
	
	public void updateCategory() {
		
	}
	
	public void deleteCategory() {
		
	}
}
