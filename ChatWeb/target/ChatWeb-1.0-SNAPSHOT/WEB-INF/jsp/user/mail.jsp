<%-- 
    Document   : mail
    Created on : Nov 28, 2017, 11:33:05 AM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<!DOCTYPE html>
<t:main title="Web Chat" base="../../" inmail="true">
    <h2>Mailbox</h2>
    <t:mailnav veiw="${veiw}"/>
    <div style="margin:0px; display:inline-block; width:100%; height:calc(100% - 81px)">
       <chat:requests css="${css}" requests="${mail}" showgroup="${true}" showuser="${showuser}" twobtns="${twobtn}" btnaction="${btnact1}" btntext="${btntxt1}"  btnaction2="${btnact2}" btntext2="${btntxt2}"/>
    </div>
</t:main>
