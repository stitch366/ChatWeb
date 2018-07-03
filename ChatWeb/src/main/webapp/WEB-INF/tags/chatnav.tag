<%-- 
    Document   : chatnav
    Created on : Nov 25, 2017, 3:49:19 PM
    Author     : Sydney
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8" dynamic-attributes="attrs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- any content can be specified here e.g.: --%>
<div class="nav">
<ul class="clear">
    <c:choose>
        <c:when test="${attrs.islogveiw}">
            <li class="nav-link"><a href="../chat/room${attrs.rmid}">Live</a></li>
            <li class="nav-link curr"><a href="">Past Logs</a></li>
        </c:when>
        <c:otherwise>
            <li class="nav-link curr"><a href="">Live</a></li>
            <li class="nav-link"><a href="../chat/logs">Past Logs</a></li>
        </c:otherwise>
    </c:choose>
</ul>
</div>