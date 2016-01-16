<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="/js/jquery-1.11.3.js"></script>
<script src="/js/common.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#btnSave").bind({
		"click" : function(){
			
			var requestParm = {};
			
			requestParm.title = $("#title").val();
			requestParm.content = $("#content").val();
			requestParm.writer = $("#writer").val();
			
			com.submit("insertboard",requestParm,function(data){
				alert(data);
			});
		}
	});	
});

</script>
<title>Board</title>
</head>
<body>
<table border='0'>
  <colgroup>
    <col width="20%" />
    <col width="*" />
  </colgroup>
<thead>
</thead>
<tbody>
	<tr>
	    <td>제목</td>
	    <td><input type='text' id='title' value='${title}' size='20'></td>
	</tr>
	<tr>
	    <td>내용</td>
	    <td><textarea id='content' rows='20' cols='100'>${content}</textarea></td>
    </tr>
	<tr>
	    <td>작성자</td>
	    <td><input type='text' id='writer' value='${writer}' size='20'></td>
	</tr>
</tbody>
</table>
<br>
<input type="button" id='btnSave' value='저장'>
<input type="text" id='result' value='${result}'>

</body>
</html>