<%-- 
    Document   : passchange
    Created on : Dec 5, 2017, 1:49:07 PM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<t:main title="Web Chat">
    <div>
        <h2>Your Account</h2>
        <t:accnav veiw='password' />
         <div style="margin:0px; display:inline-block; width:100%;">
             <form name="passFrm" method="POST" action="updatepass">
                 <table>
                     <tr><td>Old Password:</td><td><input name="old" id='ctxt' type="password" value="${old}"/></td><td id='err' class="err-out">${olderr}</td></tr>
                     <tr><td>New Password:</td><td><input name="newpass" id='ctxt' type="password" value="${newp}"/></td><td class="err-out">${newerr}</td></tr>
                     <tr><td>Confirm:</td><td><input name="confirm" id='ctxt'  type="password" value="${confirm}"/></td><td class="err-out">${confirmerr}</td></tr>
                     <tr><td colspan="3"><button id="btn">Change Password</button></td></tr>
                 </table>
             </form>
         </div>
     </div>
</t:main>
