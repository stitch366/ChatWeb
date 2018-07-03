<%-- 
    Document   : grouplist
    Created on : Nov 30, 2017, 1:38:58 PM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<c:set var="sdata" value='${sessionScope["scopedTarget.sd"]}'/>
<!DOCTYPE html>
<t:main title="Web Chat">
    <div>
        <h2>Your Account</h2>
        <t:accnav veiw='group' />
         <div style="margin:0px; display:inline-block; width:100%;">
             <form id="group-frm" name="groupFrm" method="POST" action="creategroup">
                 <input type="hidden"  name="owner" value="${sdata.getCu().username()}"/>
                 <table>
                     <tr><td>Name:</td><td><input type="text" name="groupname"/></td><td class="err-out">${err}</td></tr>
                     <tr><td colspan="3"><button type="submit">Create</button></td></tr>
                 </table>
             </form>
         </div>
     </div>
</t:main>
