<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2>로그인</h2>

<form action="/member/loginPro" method="post">
	아이디	<input type="text" name="id"><br/>
	비밀번호	<input type="password" name="passwd"><br/>
			<input type="submit" value="로그인">
</form>
<button onclick="location.href='/member/inputForm'">회원 가입</button>