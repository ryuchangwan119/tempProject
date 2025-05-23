<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>HOME</h2>

<c:if test="${empty sessionScope.memId}">
	<script>
		alert("로그인이 필요한 서비스입니다.");
		location.href='/member/loginForm'
	</script>
</c:if>

<c:if test="${not empty sessionScope.memId}">
	<h3>${sessionScope.memId}님 어서오세요</h3>
	<button onclick="location.href='/member/inputForm'">회원 가입</button>
	<button onclick="location.href='/member/logout'">로그아웃</button>
	<button onclick="location.href='/member/myPage'">마이페이지</button>
	<c:if test="${sessionScope.memId == 'admin'}">
		<button onclick="location.href='/member/memberList'">회원목록</button>
	</c:if>
</c:if>













