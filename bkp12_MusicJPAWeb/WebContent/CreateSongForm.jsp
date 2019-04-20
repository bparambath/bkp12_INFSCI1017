CreateSongForm.jsp<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Song Form</title>
</head>
<body>
<form method="post" name="frmCreateSong" id="frmCreateSong" action="servlets/CreateSong">
	<p>
	Song title: 
	<input type="text" value="" name="songTitle" />
	</p>
	<p>
	Song length:
	<input type="text" value="" name="songLength"></input>
	</p>
	<p>
	Song file path:
	<input type="text" value="" name="songFilePath"></input>
	</p>
	<p>
	Song release date:
	<input type="text" value="" name="songReleaseDate"></input>
	</p>
	<p>
	Song record date:
	<input type="text" value="" name="songRecordDate"></input>
	</p>
	<input type="submit" name="btnSubmit" value="Save" />
</form>
</body>
</html>