package app.interfaces;

import app.notices.Notice;

public interface Observer {
	
	public void update(Notice notice);
}
