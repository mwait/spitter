<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false"%>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">

<style rel="stylesheet" type="text/css" >
label.error {
color: red;
}
div.errors {
background-color: #ffcccc;
border: 2px solid red;
}
 </style>
</head>
<body>
	<h1>Register</h1>
	<sf:form method="POST" commandName="spitter">
<sf:errors path="*" element="div" cssClass="errors" />
<sf:label path="firstName" cssErrorClass="error">First Name</sf:label>:
<sf:input path="firstName" cssErrorClass="error" /><br/>
<sf:label path="lastName" cssErrorClass="error">Last Name</sf:label>:
<sf:input path="lastName" cssErrorClass="error" /><br/>
<sf:label path="email" cssErrorClass="error">Email</sf:label>:
<sf:input path="email" type="email" cssErrorClass="error" /><br/>
<sf:label path="username" cssErrorClass="error">Username</sf:label>:
<sf:input path="username" cssErrorClass="error" /><br/>
<sf:label path="password" cssErrorClass="error">Email</sf:label>:
<sf:password path="password" cssErrorClass="error" /><br/>

<input type="submit" value="Register" />

<%-- Błędy obok formularza: 	<sf:form method="POST" commandName="spitter">
First Name: <sf:input path="firstName" /><sf:errors path="firstName" cssClass="error" /><br/>
Last Name: <sf:input path="lastName" /><sf:errors path="lastName" cssClass="error" /><br/>
Email: <sf:input path="email" /><br/> Można używać typów z HTML5 od Springa 3.1 np. data, range, email
Email: <sf:input path="email" type="email" /><sf:errors path="email" cssClass="error" /><br/>
Username: <sf:input path="username" /><sf:errors path="username" cssClass="error" /><br/>
Password: <sf:password path="password" /><sf:errors path="password" cssClass="error" /><br/>
<input type="submit" value="Register" /> --%>
</sf:form>
</body>
</html>