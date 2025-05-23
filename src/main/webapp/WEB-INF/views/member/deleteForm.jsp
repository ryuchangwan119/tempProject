<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2>회원탈퇴</h2>

<form action="/member/deletePro" method="post">
	비밀번호	<input type="password" name="passwd" /><br/>
			<input type="hidden" name="id" value="${id}" /><br/>
			<input type="submit" value="탈퇴" />
</form>