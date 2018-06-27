 <html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
</head>
<body>
<form method="post" action="UploadServlet" enctype="multipart/form-data">
Select file to upload:
<input type="file" name="dataFile" id="fileChooser"/><br/><br/>
Percent of document you want as a Summary: <input type="text" name="percent" id="percent"/><br>
<input type="submit" value="Upload" />
</form>
</body>
</html>