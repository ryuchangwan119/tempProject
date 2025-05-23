<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2>회원 정보 수정</h2>

<form action="/member/updatePro" method="post">
			<input type="hidden" name="num" value="${dto.num}" />
			<input type="hidden" name="id" value="${dto.id}" />
	아이디 : 	${dto.id} <br/>
	이름 :	<input type="text" name="name"  value="${dto.name}" /> <br/>
	이메일 :	<input type="text" name="email"  value="${dto.email}" /> <br/>
			<input type="submit" value="수정" /> <br/>
</form>
