<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="/board/updatePro" method="post" name="updatePro"><br/>
	작성자	<input type="text" name="writer" value="${dto.writer}" readonly="readonly" /><br/>
	비밀번호	<input type="password" name="passwd" /><br/>
	제목		<input type="text" name="title" value="${dto.title}"  /><br/>
	본문		<textarea name="content" rows="10" cols="40" >${dto.content}</textarea><br/>
			<input type="hidden" name="page" value="${page}" />
			<input type="hidden" name="id" value="${dto.id}" />
			<button type="submit" onclick="boardUpdate()">글수정</button>
</form>

<script>
	function boardUpdate() {
		var pass = "${dto.passwd}";
		var inputPas = document.getElementById("passwd").value;
		
		if(pass === inputPas) {
			document.updatePro.submit();
		} else {
			alert("비밀번호를 확인하세요")
		}
	};
</script>



  