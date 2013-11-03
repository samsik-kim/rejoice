<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
 
<script src="/resource/jQuery-File-Upload-9.0.1/js/vendor/jquery.ui.widget.js"></script>
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.iframe-transport.js"></script>
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload.js"></script>
 
<!-- bootstrap just to have good looking page -->
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<link href="/resource/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />

<!-- we code these -->
<link href="/resource/css/dropzone.css" type="text/css" rel="stylesheet" />
<script src="/resource/myuploadfunction.js"></script>

<title>Insert title here</title>
</head>
<body>
<h1>Spring MVC - jQuery File Upload</h1>
<div style="width:500px;padding:20px">
 
    <input id="fileupload" type="file" name="files[]" data-url="/controller/upload" multiple>
 
    <div id="dropzone">Drop files here</div>
 
    <div id="progress">
        <div style="width: 0%;"></div>
    </div>
 
    <table id="uploaded-files">
        <tr>
            <th>File Name</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Download</th>
        </tr>
    </table>
 
</div>
</body>
</html>