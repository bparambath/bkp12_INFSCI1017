<%@page import="java.util.UUID"%>
<%@page import="Music.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Song</title>
</head>
<body>

<% 
String songTitle = "";
String songLength = "";
String songFilePath = "";
String songReleaseDate = "";
String songRecordDate = "";

if(request.getParameter("songTitle") != null && request.getParameter("songLength") != null
&& request.getParameter("songFilePath") != null && request.getParameter("songReleaseDate") != null && request.getParameter("songRecordDate") != null){
	songTitle = request.getParameter("songTitle");
	songLength = request.getParameter("songLength");
	songFilePath = request.getParameter("songFilePath");
	songReleaseDate = request.getParameter("songReleaseDate");
	songRecordDate = request.getParameter("songRecordDate");
	response.getWriter().println(songTitle);
	ManageSongs gm = new ManageSongs();
	
	gm.createSong(UUID.randomUUID().toString(), songTitle, Integer.parseInt(songLength), songFilePath, songReleaseDate, songRecordDate);
}
%>

<b><%= songTitle %></b>
<b><%= songLength %></b>
<b><%= songFilePath %></b>
<b><%= songReleaseDate %></b>
<b><%= songRecordDate %></b>

</body>
</html>