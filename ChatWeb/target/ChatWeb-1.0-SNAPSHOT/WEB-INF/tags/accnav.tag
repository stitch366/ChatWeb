<%-- 
    Document   : accnav
    Created on : Nov 27, 2017, 8:08:29 PM
    Author     : Sydney
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8" dynamic-attributes="attrs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- any content can be specified here e.g.: --%>
<div class="nav">
<ul class="clear">
    <c:choose>
        <c:when test="${attrs.veiw == 'home'}">
            <li class="nav-link curr"><a href="">Your Rooms</a></li>
            <li class="nav-link"><a href="../user/newgrp">New Group</a></li>
            <li class="nav-link"><a href="../user/changeFont">Change Font</a></li>
            <li class="nav-link"><a href="../user/changeImg">Change Avatar</a></li>
            <li class="nav-link"><a href="../user/passchange">Change Password</a></li>
        </c:when>
        <c:when test="${attrs.veiw == 'group'}">
            <li class="nav-link"><a href="../user/">Your Rooms</a></li>
            <li class="nav-link curr"><a href="">New Group</a></li>
            <li class="nav-link"><a href="../user/changeFont">Change Font</a></li>
            <li class="nav-link"><a href="../user/changeImg">Change Avatar</a></li>
            <li class="nav-link"><a href="../user/passchange">Change Password</a></li>
        </c:when>
        <c:when test="${attrs.veiw == 'font'}">
            <li class="nav-link curr"><a href="../user/">Your Rooms</a></li>
            <li class="nav-link"><a href="../user/newgrp">New Group</a></li>
            <li class="nav-link"><a href="">Change Font</a></li>
            <li class="nav-link"><a href="../user/changeImg">Change Avatar</a></li>
            <li class="nav-link"><a href="../user/passchange">Change Password</a></li>
        </c:when>
        <c:when test="${attrs.veiw == 'avatar'}">
            <li class="nav-link"><a href="../user/">Your Rooms</a></li>
            <li class="nav-link"><a href="../user/newgrp">New Group</a></li>
            <li class="nav-link"><a href="../user/changeFont">Change Font</a></li>
            <li class="nav-link curr"><a href="">Change Avatar</a></li>
            <li class="nav-link"><a href="../user/passchange">Change Password</a></li>
        </c:when>
        <c:otherwise>
            <li class="nav-link"><a href="../user/">Your Rooms</a></li>
            <li class="nav-link"><a href="../user/newgrp">New Group</a></li>
            <li class="nav-link"><a href="../user/changeFont">Change Font</a></li>
            <li class="nav-link"><a href="../user/changeImg">Change Avatar</a></li>
            <li class="nav-link curr"><a href="">Change Password</a></li>
        </c:otherwise>
    </c:choose>
</ul>
</div>