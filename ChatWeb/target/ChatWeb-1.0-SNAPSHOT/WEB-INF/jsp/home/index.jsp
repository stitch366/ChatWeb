<%@include file="../inc/page.jsp" %>
<%@include file="../inc/include.jsp" %>
<%@include file="../inc/scripts.jsp" %>

<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Chat - Login</title>
    </head>
    <body>
        <div id="login-frm-con" class="cen-frm type1">
            <h2>Login</h2>
            <form id="login-frm" name="loginFrm" method="POST" action="login">
                <table>
                    <tr><td>Username:</td><td><input name="uname" type="text" value="${un}"></td></tr>
                    <tr><td>Password:</td><td><input name="pass" type="password" value=""></td></tr>
                    <tr><td class="err-out" colspan="2">${err}</td></tr>
                    <tr><td><button type="submit">Login</button></td><td><a class="crtbtn" href="newuser">New User</a></td></tr>
                </table>
            </form>
        </div>
    </body>
</html>
