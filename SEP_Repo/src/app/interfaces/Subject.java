package app.interfaces;

import app.notices.Notice;

public interface Subject {
	
	public void subscribe(Observer obs);
	
	public void unsubscribe(Observer obs);
	
	public void notifyObservers(Notice notice);
}
