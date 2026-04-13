package app.notices;

import java.util.Date;

import app.models.Attachment;
import app.models.Category;
import app.models.User;

public abstract class Notice {

	private String noticeId;
	private String title;
	private String content;
	private Date datePosted;
	private Date xpiryDate;
//	private Priority priority;  //was giving error will have to figure out what to do with this
	private User author;
	private Category category;
	
	public void publish() {
		
	}
	
	public void archive() {
		
	}
	
	public boolean isExpired() {
		return false;
	}
	
	public void addAttachment(Attachment file) {
		
	}
	
	public abstract String getDetails();
	
}
