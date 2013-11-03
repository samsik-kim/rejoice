<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Generic page styles -->
<link rel="stylesheet" href="/resource/jQuery-File-Upload-9.0.1/css/style.css">
<link rel="stylesheet" href="/resource/jQuery-File-Upload-9.0.1/css/jquery.fileupload.css">
<!-- Bootstrap styles -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/resource/jQuery-File-Upload-9.0.1/css/jquery.fileupload-ui.css">

<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.iframe-transport.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-process.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-image.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-audio.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-video.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-validate.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-ui.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-jquery-ui.js"></script> -->
<!-- <script src="/resource/jQuery-File-Upload-9.0.1/js/main.js"></script> -->
<title></title>

</head>
<body>
<div class="container">
    <!-- The fileinput-button span is used to style the file input field as button -->
    <span class="btn btn-success fileinput-button">
        <i class="glyphicon glyphicon-plus"></i>
        <span>Select files...</span>
        <!-- The file input field used as target for the file upload widget -->
        <input id="fileupload" type="file" name="files[]" multiple>
    </span>
    <br>
    <br>
    <!-- The global progress bar -->
    <div id="progress" class="progress">
        <div class="progress-bar progress-bar-success"></div>
    </div>
    <!-- The container for the uploaded files -->
    <div id="files" class="files"></div>
    <br>
</div>
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/vendor/jquery.ui.widget.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script>
/*jslint unparam: true */
/*global window, $ */
$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:
//     var url = window.location.hostname === 'blueimp.github.io' ?
//                 '//jquery-file-upload.appspot.com/' : 'server/php/';
    var url = "/file/uploadBean";
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        done: function (e, data) {
            $.each(data.result.files, function (index, file) {
// 	        	alert(file.path + "\n" + file.size);
                $('<p/>').text(file.name).appendTo('#files');
            });
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            );
        }
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});
</script>
</body>
</html>