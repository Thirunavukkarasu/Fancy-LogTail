 
package com.rest;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("LogTailerImpl")
public class LogTailerImpl {

	@GET
	@Path("/getDirectoriesList")
	@Produces({"application/json"})
	public ArrayList<LogFile> getDirectoriesList() {
		File directory = new File("C:/nodejs/Bootstrap_Examples");
		File[] flist = directory.listFiles();
		ArrayList<LogFile> listOfLogFiles = new ArrayList<LogFile>();
		for(File file:flist){
			if(file.isDirectory()){
				LogFile logFile = new LogFile();
				logFile.setLogFileName(file.getName());
				logFile.setLogFileAbsolutePath(file.getAbsolutePath());
				logFile.setLogFileType(file.isFile()?"File":"Directory");
				listOfLogFiles.add(logFile);				
			}
		}
		return listOfLogFiles;
	}
	
	@GET
	@Path("/getDirectoryContent/{directory_name}")	
	@Produces({"application/json"})
	public ArrayList<LogFile> getDirectoryContent(@PathParam("directory_name") String directory_name) {
		File directory = new File("C:/nodejs/Bootstrap_Examples/"+directory_name);
		File[] flist = directory.listFiles();
		ArrayList<LogFile> listOfLogFiles = new ArrayList<LogFile>();
		for(File file:flist){
			if(file.isFile()){
				LogFile logFile = new LogFile();
				logFile.setLogFileName(file.getName());
				logFile.setLogFileAbsolutePath(file.getAbsolutePath());
				logFile.setLogFileType(file.isFile()?"File":"Directory");
				listOfLogFiles.add(logFile);				
			}
		}
		return listOfLogFiles;
	}	
	
	@GET
	@Path("/downloadLogFile")	
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile(@QueryParam("logFileAbsolutePath") String logFileAbsolutePath,@QueryParam("logFileName") String logFileName) {
	    File file = new File(logFileAbsolutePath);
	    ResponseBuilder response = Response.ok((Object) file);
	    response.header("Content-Disposition","attachment; filename="+logFileName);
	    return response.build();
	}	
}