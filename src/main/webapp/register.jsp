<%--
Registration page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="register" var="loc"/>
<fmt:message bundle="${loc}" key="registerTitle" var="register_title"/>

<t:page title="${register_title}">
        <jsp:attribute name="header">
        <t:header/>
    </jsp:attribute>

    <jsp:attribute name="footer">
        <t:footer/>
    </jsp:attribute>

    <jsp:body>
        <div>
            <h2>${register_title}</h2>
            <t:profile actionLink="registerUser" cancelLink="/index.jsp" />
        </div>
    </jsp:body>
</t:page>