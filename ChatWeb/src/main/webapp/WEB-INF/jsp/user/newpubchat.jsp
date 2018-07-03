<%-- 
    Document   : newpubchat
    Created on : Nov 13, 2017, 6:27:02 PM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<!DOCTYPE html>
<t:main title="Web Chat">
    <h2>New Public Chat Room</h2>
    <form id="room-frm" name="roomFrm" method="POST" action="createroom">
            <table>
                <tr><td>Name:</td><td><input name="name" type="text" value=""></td><td class="err-out" >${err}</td></tr>
                   <tr><td><button type="submit">Create</button></td><td></td></tr>
            </table>
            <input type="hidden" name="group" value="0"/>
            <input type="hidden" name="type" value="PUBLIC"/>
        </form>
</t:main>
