<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>회원목록</h2>

<table border="1" width="700">
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>이름</th>
		<th>이메일</th>
		<th>회원조회</th>
		<th>회원삭제</th>
	</tr>

	<c:forEach var="dto" items="${memList}">
		<tr>
			<td>${dto.num}</td>
			<td>${dto.id}</td>
			<td>${dto.name}</td>
			<td>${dto.email}</td>
			<td align="center">
				<form action="/member/view" method="post" style="display:inline">
					<input type="hidden" name="num" value="${dto.num}">
					<button type="submit">조회</button>
				</form>
			</td>
			<td align="center">
				<form action="/member/delete" method="post"  style="display:inline">
					<input type="hidden" name="num" value="${dto.num}">
					<button type="submit">삭제</button>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

