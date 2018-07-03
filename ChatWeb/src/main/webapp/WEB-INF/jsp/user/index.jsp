<%-- 
    Document   : index
    Created on : Oct 31, 2017, 8:05:26 AM
    Author     : Sydney
--%>
<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="sdata" value='${sessionScope["scopedTarget.sd"]}'/>
<!DOCTYPE html>
<t:main title="Web Chat">
    <div>
        <h2>Your Account</h2>
        <t:accnav veiw='home' />
        <div class="button-panel">
            <a class="crtbtn" href="newpublic">New Public Chat</a><a class="crtbtn" href="newprivate">New Group Chat</a>
         </div>
         <div style="margin:0px; display:inline-block; width:100%; height:calc(100% - 111px)">
             <chat:roomList css="threeCols2" rooms="${sdata.getCu().ownedRooms()}" showtype="${true}" showgroup="${true}" showowner="${false}"/>
         </div>
     </div>
</t:main>
