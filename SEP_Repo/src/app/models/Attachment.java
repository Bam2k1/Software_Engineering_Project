package app.models;

import java.io.File;

public class Attachment {
	
	private String attachment;
	private String fileName;
	
	public File download() {
		return new File(this.fileName);
	}
	
	public boolean upload() {
		return true;
	}
}
