<%-- 
    Document   : makeRequest
    Created on : Nov 28, 2017, 10:56:30 AM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<!DOCTYPE html>
<t:main title="Web Chat">
    <h2>New Join Request</h2>
    <t:search/> 
    <div style="margin:0px; display:inline-block; width:100%; height:calc(100% - 76px)">
        <chat:groups id="data" css="sixfour threeCols3" grps="${groups}" aslink="${false}" btn="${true}" showowner="${true}"/>
     </div>
     <t:searchjs tableid="data" compare=".scroll-cell:first-child" />
</t:main>
