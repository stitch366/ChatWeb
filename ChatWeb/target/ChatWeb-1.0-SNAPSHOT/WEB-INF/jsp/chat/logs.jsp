<%-- 
    Document   : logs
    Created on : Dec 2, 2017, 12:48:49 PM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<!DOCTYPE html>
<t:main>
    <h2>${name}</h2>
    <t:chatnav rmid="${id}" islogveiw="${true}"></t:chatnav>
        <div style="margin:0px; display:inline-block; width:100%; height:calc(100% - 81px)">
           <chat:logs set="${logs}"/>
        </div>
        <script>
            (function(){
                function action(evt){
                    var action = evt.target.getAttribute("act");
                    var date = action.replace("log", "");
                    LogWindow(action, date, '');
                }
                eventAll(".logbtn", "click", action);
            })();
        </script>
</t:main>
