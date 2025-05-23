<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2>/ajax/ajaxTest</h2>

<script
  src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous">
</script>

<input type="text" id="message" placeholder="메세지를 입력하세요">
<br/><br/>

<!-- 버튼 클릭시 sendGet() 함수 호출 -->
<button onclick="sendGet()">GET 요청 보내기</button>
<button onclick="sendPost()">Post 요청 보내기</button>

<div id="result" style="margin-top:20px; border:1px solid #ccc; padding:10px;"></div>

<script>
	function sendGet() {
		var msg = $("#message").val();
		
		$.ajax({
			type: "get" ,
			url: "/ajax/getTest" ,
			data: {message: msg} ,
			success: function(response) {
				$("#result").html("입력값 : " + response)
			},
			error: function(xhr, status, error) {
				$("#result").html("error : " + error)
			}
		});
	};
</script>


<script>
	function sendPost() {
		var msg = $("#message").val();
		
		$.ajax({
			type: "post" ,
			url: "/ajax/postTest" ,
			data: {message: msg} ,
			success: function(response) {
				$("#result").html("입력값 : " + response)
			},
			error: function(xhr, status, error) {
				$("#result").html("error : " + error)
			}
		});
	};
</script>
















