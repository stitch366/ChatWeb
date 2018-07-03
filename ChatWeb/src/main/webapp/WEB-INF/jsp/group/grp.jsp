<%-- 
    Document   : grp
    Created on : Nov 18, 2017, 12:13:01 PM
    Author     : Sydney
--%>
<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>

<t:main title="Web Chat">  
    <h2>${group.getGroupname()}</h2>
    <t:groupnav veiw="members" gid="${group.getId()}" isowner="${isowner}"/>
    <t:search/> 
    <div style="margin:0px; display:inline-block; width:100%; height:calc(100% - 111px)">
            <chat:users id="data" css="twoCols2" users="${group.members((!isowner))}" btn1="${false}" btn2="${true}"/>
    </div>
    <t:searchjs tableid="data" compare="tr.user-out td:last-child" />
</t:main>
