<%-- 
    Document   : newuser
    Created on : Oct 30, 2017, 12:58:34 PM
    Author     : Sydney
--%>
<%@include file="../inc/page.jsp" %>
<%@include file="../inc/include.jsp" %>
<%@include file="../inc/scripts.jsp" %>
<!DOCTYPE html>
<c:set var="uerrcss" value=""/>
<c:if test="${unerr != null && unerr != ''}">
    <c:set var="uerrcss" value="frm-err"/>
</c:if>
<c:set var="emerrcss" value=""/>
<c:if test="${emerr != null && emerr != ''}">
    <c:set var="emerrcss" value="frm-err"/>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Chat - New User</title>
    </head>
    <body>
        <div id="login-frm-con" class="cen-frm type2">
            <h2>New User</h2>
            <form id="acc-init" method="POST" name="accntFrm" action="adduser">
                <table id="frm-t">
                   <tr ><td>Username:</td><td><input name="username" id="uname" type="text" value="${name}"/></td></tr>
                   <tr><td class="err-out" colspan="2">${unerr}</td></tr>
                   <tr><td>Password:</td><td><input id="pass" name="password" type="password" value="${pass}"/></td></tr>
                   <tr><td class="err-out" colspan="2">${passerr}</td></tr>
                   <tr><td>Confirm Password:</td><td><input id="pass2" name="confirmpass" type="password" value="${confirm}"/></td></tr>
                   <tr><td class="err-out" colspan="2">${confirmerr}</td></tr>
                   <tr><td>First Name:</td><td><input id="fname" name="firstname" type="text" value="${first}" /></td></tr>
                   <tr><td class="err-out" colspan="2">${firsterr}</td></tr>
                   <tr><td>Last Name:</td><td><input id="lname" name="lastname" type="text" value="${last}"/></td></tr>
                   <tr><td class="err-out" colspan="2">${lasterr}</td></tr>
                   <tr><td>Email:</td><td><input name="email" id="em" type="text" value="${email}" /></td></tr>
                   <tr><td class="err-out" colspan="2">${emailerr}</td></tr>
                   <tr><td><button id="btn">Submit</button></td><td><a class="crtbtn" href="../ChatWeb/">Cancel</a></td></tr>
                </table>
            </form>
        </div>
    </body>
</html>
