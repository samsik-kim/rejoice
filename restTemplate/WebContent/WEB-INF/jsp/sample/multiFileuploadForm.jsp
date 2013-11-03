<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/dark-hive/jquery-ui.css" id="theme">
<link rel="stylesheet" href="/resource/jQuery-File-Upload-9.0.1/css/jquery.fileupload-noscript.css">
<link rel="stylesheet" href="/resource/jQuery-File-Upload-9.0.1/css/jquery.fileupload-ui-noscript.css">
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Load-Image/js/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<!-- blueimp Gallery script -->
<script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-ui.js"></script>
<!-- The File Upload jQuery UI plugin -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/jquery.fileupload-jquery-ui.js"></script>
<!-- The main application script -->
<script src="/resource/jQuery-File-Upload-9.0.1/js/main.js"></script>
<title></title>
<script>
// Initialize the jQuery UI theme switcher:
$('#theme-switcher').change(function () {
    var theme = $('#theme');
    theme.prop(
        'href',
        theme.prop('href').replace(
            /[\w\-]+\/jquery-ui.css/,
            $(this).val() + '/jquery-ui.css'
        )
    );
});
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#addItemBtn").click(function(){
		// item 의 최대번호 구하기
        var lastItemNo = $("#example tr:last").attr("class").replace("item", "");
        var newitem = $("#example tr:eq(0)").clone();
        newitem.removeClass();
        newitem.find("td:eq(0)").attr("rowspan", "1");
        newitem.addClass("item"+(parseInt(lastItemNo)+1));
        $("#example").append(newitem);
	});
});

</script>
</head>
<body>
<form>
    <label for="theme-switcher">Theme:</label>
    <select id="theme-switcher" class="pull-right">
        <option value="black-tie">Black Tie</option>
        <option value="blitzer">Blitzer</option>
        <option value="cupertino">Cupertino</option>
        <option value="dark-hive" selected>Dark Hive</option>
        <option value="dot-luv">Dot Luv</option>
        <option value="eggplant">Eggplant</option>
        <option value="excite-bike">Excite Bike</option>
        <option value="flick">Flick</option>
        <option value="hot-sneaks">Hot sneaks</option>
        <option value="humanity">Humanity</option>
        <option value="le-frog">Le Frog</option>
        <option value="mint-choc">Mint Choc</option>
        <option value="overcast">Overcast</option>
        <option value="pepper-grinder">Pepper Grinder</option>
        <option value="redmond">Redmond</option>
        <option value="smoothness">Smoothness</option>
        <option value="south-street">South Street</option>
        <option value="start">Start</option>
        <option value="sunny">Sunny</option>
        <option value="swanky-purse">Swanky Purse</option>
        <option value="trontastic">Trontastic</option>
        <option value="ui-darkness">UI Darkness</option>
        <option value="ui-lightness">UI Lightness</option>
        <option value="vader">Vader</option>
    </select>
</form>
<form action="/Mupload.do" name="frm" method="post" enctype="multipart/form-data">
	<a href="#" id="addItemBtn">옵션추가</a>
	<table>
		<tr>
			<td align="center" width="100">첨부1</td>
			<td colspan="3"><input type="file" name="mapFiles"></td>
		</tr>
		<tr>
			<td align="center" width="100">첨부2</td>
			<td colspan="3"><input type="file" name="mapFiles"></td>
		</tr>
		<tr>
			<td align="center" width="100">첨부3</td>
			<td colspan="3"><input type="file" name="mapFiles"></td>
		</tr>
	</table>
	<table id="example">
		<tr class="item1">
			<td class="rowtitle" align="center" width="100">첨부</td>
			<td colspan="3"><input type="file" name="files" id="file"></td>
		</tr>
	</table>
	<input type="submit" name="upload" value="upload"/>
</form>
<form id="fileupload" action="//jquery-file-upload.appspot.com/" method="POST" enctype="multipart/form-data">
    <!-- Redirect browsers with JavaScript disabled to the origin page -->
    <noscript><input type="hidden" name="redirect" value="http://blueimp.github.io/jQuery-File-Upload/"></noscript>
    <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
    <div class="fileupload-buttonbar">
        <div class="fileupload-buttons">
            <!-- The fileinput-button span is used to style the file input field as button -->
            <span class="fileinput-button">
                <span>Add files...</span>
                <input type="file" name="files[]" multiple>
            </span>
            <button type="submit" class="start">Start upload</button>
            <button type="reset" class="cancel">Cancel upload</button>
            <button type="button" class="delete">Delete</button>
            <input type="checkbox" class="toggle">
            <!-- The global file processing state -->
            <span class="fileupload-process"></span>
        </div>
        <!-- The global progress state -->
        <div class="fileupload-progress fade" style="display:none">
            <!-- The global progress bar -->
            <div class="progress" role="progressbar" aria-valuemin="0" aria-valuemax="100"></div>
            <!-- The extended global progress state -->
            <div class="progress-extended">&nbsp;</div>
        </div>
    </div>
    <!-- The table listing the files available for upload/download -->
    <table role="presentation"><tbody class="files"></tbody></table>
</form>
</body>
</html>