<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script
  src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous">
</script>

<script>
	function checkId() {
		/* input태그에 입력한 ID값을 idValue 변수에 저장 */
		var idValue = $("#memberId").val();
		
		if(idValue === "") {
			$("#idCheckResult").html("아이디를 입력하세요");
			return;
		}
		$.ajax({
			type: "post" ,
			url: "/member/checkId" ,
			data: {id:idValue} ,
			
			success: function(reponse) {
				$("#idCheckResult").html(reponse);
			},
			error: function() {
				$("#idCheckResult").html("서버에 오류가 발생했습니다.");
			}
		});
	};
</script>
<h2>회원 가입</h2>
<form action="/member/inputPro" method="post">
	아이디	<input type="text" name="id"  id="memberId"/>
			<input type="button" value="아이디 중복 확인" onclick="checkId()"><br/><br/>
			<div id="idCheckResult" style="color:red;"></div>
	비밀번호	<input type="password" name="passwd" /><br/>
	이름		<input type="text" name="name" /><br/>
	이메일	<input type="text" name="email" /><br/>
			<input type="submit" value="회원가입">
</form>

