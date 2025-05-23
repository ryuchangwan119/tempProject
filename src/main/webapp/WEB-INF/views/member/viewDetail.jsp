<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h2>회원 상세</h2>

<table border="1" width="500" >
	<tr>
		<th>회원번호</th>
		<td>${dto.num}</td>
	</tr>

	<tr>
		<th>아이디</th>
		<td>${dto.id}</td>
	</tr>
	
	<tr>
		<th>이름</th>
		<td>${dto.name}</td>
	</tr>
	
	<tr>
		<th>이메일</th>
		<td>${dto.email}</td>
	</tr>
	
	<tr>
		<th>가입일</th>
		<td>${dto.regdate}</td>
	</tr>
</table>
<button onclick="location.href='/member/home'">메인</button>

<form action="/member/updateForm" method="post" style="display:inline">
	<input type="hidden" name="id" value="${dto.id}">
	<button type="submit">수정</button>
</form>

<form action="/member/deleteForm" method="post" style="display:inline">
	<input type="hidden" name="id" value="${dto.id}">
	<button type="submit">탈퇴</button>
</form>
 
 
 
 
 
 
    