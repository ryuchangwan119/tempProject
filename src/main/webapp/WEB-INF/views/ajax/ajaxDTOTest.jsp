<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script
  src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous">
</script>


<h1>/ajax/ajaxDTOTest.jsp</h1>


<h2>Ajax DTO로 매개변수 받기(GET, POST)</h2>

<input type="text" id="dtoMsg" placeholder="msgDto값 입력" >
<input type="text" id="dtoExtra" placeholder="dtoExtra값 입력" >
<br/><br/>

<!--  버튼 클릭시 sendGet() 함수 호출 -->
<button onclick="sendGet()">GET 요청 보내기</button>
<button onclick="sendPost()">POST 요청 보내기</button>

<!-- div태그에 입력값 출력 -->
<div id="result" style="margin-top:20px; padding:10px; border:1px solid #ccc;"></div>


<script>
	function sendPost() {
		var msg = $("#dtoMsg").val();
		var extra = $("#dtoExtra").val();
		
		$.ajax({
			type:"post" ,
			url: "/ajax/postDTOTest" ,
			data: {msg:msg , extra:extra} ,
			success: function(response) {
				$("#result").html("AjaxDTO-POST = " + response);
			} ,
			error: function(xhr, status, error) {
				$("#result").html("AjaxDTO ERROR : " + error);
			}
		});
	};


	function sendGet() {
		var msg = $("#dtoMsg").val();
		var extra = $("#dtoExtra").val();
		
		$.ajax({
			type:"get" ,
			url: "/ajax/getDTOTest" ,
			data: {msg:msg , extra:extra} ,
			success: function(response) {
				$("#result").html("AjaxDTO-GET = " + response);
			} ,
			error: function(xhr, status, error) {
				$("#result").html("AjaxDTO ERROR : " + error);
			}
		});
	};
</script>





