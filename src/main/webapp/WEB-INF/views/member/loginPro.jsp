<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.memId and result > 0}">
	<c:redirect url="/member/home"></c:redirect>
</c:if>

<c:if test="${empty sessionScope.memId or result <= 0}">
	<script>
		alert("아이디/비밀번호를 확인하세요")
		location.href='/member/loginForm'
	</script>
</c:if>





