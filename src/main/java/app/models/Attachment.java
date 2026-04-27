package app.models;

import java.io.File;

public class Attachment {

    private String attachmentId;
    private String fileName;
    private String fileType;
    private int fileSize;

    public Attachment(String attachmentId, String fileName, String fileType, int fileSize) {
        this.attachmentId = attachmentId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public File download() {
        return new File(fileName);
    }

    public boolean upload() {
        return true;
    }

    public String getFileName()    { return fileName; }
    public String getFileType()    { return fileType; }
    public int getFileSize()       { return fileSize; }
    public String getAttachmentId(){ return attachmentId; }
}
