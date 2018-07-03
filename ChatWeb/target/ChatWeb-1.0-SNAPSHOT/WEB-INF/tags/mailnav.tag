<%-- 
    Document   : mailNav
    Created on : Nov 28, 2017, 10:35:20 AM
    Author     : Sydney
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8" dynamic-attributes="attrs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- any content can be specified here e.g.: --%>
<div class="nav">
<ul class="clear">
    <c:choose>
        <c:when test="${attrs.veiw == 'invites'}">
            <li class="nav-link curr"><a href="">Invites</a></li>
            <li class="nav-link"><a href="../mail/requests">Requests</a></li>
            <li class="nav-link"><a href="../mail/submitted">Your Requests</a></li>
        </c:when>
        <c:when test="${attrs.veiw == 'requests'}">
            <li class="nav-link"><a href="../mail/invites">Invites</a></li>
            <li class="nav-link curr"><a href="">Requests</a></li>
            <li class="nav-link"><a href="../mail/submitted">Your Requests</a></li>
        </c:when>
        <c:otherwise>
            <li class="nav-link"><a href="../mail/invites">Invites</a></li>
            <li class="nav-link"><a href="../mail/requests">Requests</a></li>
            <li class="nav-link curr"><a href="">Your Requests</a></li>
        </c:otherwise>
    </c:choose>
</ul>
</div>