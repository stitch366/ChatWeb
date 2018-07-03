<%-- 
    Document   : publicRooms
    Created on : Nov 28, 2017, 10:46:04 AM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<!DOCTYPE html>
<t:main title="Web Chat">
    <h2>Public Chat Rooms</h2>
    <t:search/> 
    <div style="margin:0px; display:inline-block; width:100%; height:calc(100% - 76px)">
       <chat:roomList id="data" css="sixfour twoCols1" rooms="${rooms}" showtype="${false}" showgroup="${false}" showowner="${true}"/>
     </div>
       <t:searchjs tableid="data" compare=".scroll-cell:first-child" />
</t:main>
