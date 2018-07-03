<%-- 
    Document   : sent
    Created on : Nov 25, 2017, 8:47:46 PM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>

<t:main title="Web Chat">  
    <h2>${group.getGroupname()}</h2>
    <t:groupnav veiw="sent" gid="${group.getId()}" isowner="${isowner}"/>
    <t:search/> 
    <div style="margin:0px; display:inline-block; width:100%; height:calc(100% - 111px)">
            <chat:requests id="data" css="twoCols2 sixfour" requests="${group.sentInvites()}" showgroup="${false}" showuser="${true}" btnaction="uninvite/USER" btntext="Cancel"  />
    </div>
    <t:searchjs tableid="data" compare="tr.user-out td:last-child" />
</t:main>
