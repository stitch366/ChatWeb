<%-- 
    Document   : groupNav
    Created on : Nov 25, 2017, 4:17:39 PM
    Author     : Sydney
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8" dynamic-attributes="attrs" %>
<div class="nav">
    <ul class="clear">
        <c:choose>
            <c:when test="${attrs.veiw == 'members'}">
                <li class="nav-link curr"><a href="">Members</a></li>
                <li class="nav-link"><a href="rooms">Rooms</a></li>
                <c:if test="${attrs.isowner}">
                    <li class="nav-link"><a href="invite">Invite</a></li>
                    <li class="nav-link"><a href="sent">Sent Invites</a></li>
                </c:if>
            </c:when>
            <c:when test="${attrs.veiw == 'rooms'}">
                <li class="nav-link"><a href="grp${attrs.gid}">Members</a></li>
                <li class="nav-link curr"><a href="">Rooms</a></li>
                <c:if test="${attrs.isowner}">
                    <li class="nav-link"><a href="invite">Invite</a></li>
                    <li class="nav-link"><a href="sent">Sent Invites</a></li>
                </c:if>
            </c:when>
            <c:when test="${attrs.veiw == 'invite'}">
                <li class="nav-link"><a href="grp${attrs.gid}">Members</a></li>
                <li class="nav-link"><a href="rooms">Rooms</a></li>
                <c:if test="${attrs.isowner}">
                    <li class="nav-link curr"><a href="">Invite</a></li>
                    <li class="nav-link"><a href="sent">Sent Invites</a></li>
                </c:if>
            </c:when>
            <c:otherwise>
                <li class="nav-link"><a href="grp${attrs.gid}">Members</a></li>
                <li class="nav-link"><a href="rooms">Rooms</a></li>
                <c:if test="${attrs.isowner}">
                    <li class="nav-link"><a href="invite">Invite</a></li>
                    <li class="nav-link curr"><a href="">Sent Invites</a></li>
                </c:if>
            </c:otherwise>
        </c:choose>
    </ul>
</div>