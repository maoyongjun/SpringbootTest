package org.foxconn.springbootStart.lucene.localDiskSeacher;

public class Doc {
	private String fileName;
	private String fileDir;
	private Double fileSize;
	private String simpleContent;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public String getSimpleContent() {
		return simpleContent;
	}
	public void setSimpleContent(String simpleContent) {
		this.simpleContent = simpleContent;
	}
	
	
}
