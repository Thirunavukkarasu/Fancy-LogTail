package com.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogFile {
	String logFileName;
	String logFileAbsolutePath;
	public String getLogFileAbsolutePath() {
		return logFileAbsolutePath;
	}
	public void setLogFileAbsolutePath(String logFileAbsolutePath) {
		this.logFileAbsolutePath = logFileAbsolutePath;
	}
	String logFileType;
	public String getLogFileName() {
		return logFileName;
	}
	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}
	public String getLogFileType() {
		return logFileType;
	}
	public void setLogFileType(String logFileType) {
		this.logFileType = logFileType;
	}
	
}
