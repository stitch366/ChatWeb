<%-- 
    Document   : rooms
    Created on : Nov 25, 2017, 8:47:27 PM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>

<t:main title="Web Chat">  
    <h2>${group.getGroupname()}</h2>
    <t:groupnav veiw="rooms" gid="${group.getId()}" isowner="${isowner}"/>
    <t:search/> 
    <div style="margin:0px; display:inline-block; width:100%; height:calc(100% - 111px)">
            <chat:roomList id="data" css="twoCols1 sixfour" rooms="${group.rooms()}" showtype="${false}" showgroup="${false}" showowner="${true}"/>
    </div>
    <t:searchjs tableid="data" compare=".scroll-cell:first-child" />
</t:main>
