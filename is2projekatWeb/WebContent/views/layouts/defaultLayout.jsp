<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title><tiles:getAsString name="title" /></title>
	<link href="<c:url value='/css/bootstrap.min.css' />"  rel="stylesheet"></link>
	<link href="<c:url value='/css/font-awesome.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/css/animate.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/css/animate.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/css/style.css' />" rel="stylesheet"></link>
</head>
 
<body>
		<header id="header">
			<tiles:insertAttribute name="header" />
		</header>
			
		<section id="site-content">
			<tiles:insertAttribute name="body" />
			<c:if test="${! empty deck }">
				<tiles:insertAttribute name="right" />	
			</c:if>
		</section>
		
		<footer id="footer">
			<tiles:insertAttribute name="footer" />
		</footer>
		
		<script src="/js/jquery.js"></script>
		<script src="/js/bootstrap.min.js"></script>	
		<script src="/js/wow.min.js"></script>
		<script>
			wow = new WOW({}).init();
		</script>
</body>
</html>