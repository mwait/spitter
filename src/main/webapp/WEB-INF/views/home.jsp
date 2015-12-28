<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet"
type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1><s:message code="spittr.welcome" /></h1>
<%-- <a href="<c:url value="/spittles" />">Spittles</a> | --%>

<a href="<s:url value="/spittles" htmlEscape="true">
<s:param name="max" value="60" />
<s:param name="count" value="20" />
</s:url>">Spittles</a>

<a href="<s:url value="/spitter/register" />">Register</a>

<%--Budowanie adresu dla konkretnej strony profilu użytkownika (parametr wstawiany w miejsce sym. zastepczego)
 <s:url href="/spitter/{username}" var="spitterUrl">
<s:param name="username" value="jbauer" />
</s:url> --%>
</body>
</html>