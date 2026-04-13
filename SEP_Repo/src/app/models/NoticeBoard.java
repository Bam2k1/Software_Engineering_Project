package app.models;

import java.util.ArrayList;
import java.util.List;

import app.interfaces.Observer;
import app.interfaces.Subject;
import app.notices.Notice;


public class NoticeBoard implements Subject {
	
	private String boardId;
	private String title;
	
	private List<Notice> notices;
	private List<Observer> observers;
	
	public NoticeBoard(String boardId, String title) {
		this.boardId = boardId;
		this.title = title;
		this.notices = new ArrayList<>();
		this.observers = new ArrayList<>();
	}
	
	public void addNotice(Notice notice) {
		
	}
	
	public void removeNotice(String noticeId) {
		
	}
	
	public List<Notice> getActiveNotice(){
		return notices;
		
	}
	
	public void subscribe(Observer obs) {
		
	}
	
	public void unsubscribe(Observer obs) {
		
	}
	
	public void notifyObservers(Notice notice) {
		
	}
	
	
}
