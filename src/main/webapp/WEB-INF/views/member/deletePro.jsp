<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result > 0}">
	<script>
		alert("정상적으로 탈퇴 되었습니다.");
		location.href='/member/loginForm'
	</script>
</c:if>

<c:if test="${result <= 0}">
	<script>
		alert("비밀번호를 다시 확인하세요");
		history.go(-1);
	</script>
</c:if>


