<%-- 
    Document   : setAvatar
    Created on : Dec 2, 2017, 11:07:40 AM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<t:main title="Web Chat">
    <div>
        <h2>Your Account</h2>
        <t:accnav veiw='avatar' />
         <div style="margin:0px; display:inline-block; width:100%;">
             <form id="group-frm" >
                 <table>
                     <tr><td>Image:</td><td><input id="in" type="file" accept=".png,.jpeg"/></td><td id='err' class="err-out"></td></tr>
  
                     <tr><td colspan="3"><a id='btn' class="crtbtn" >Set Avatar</a></td></tr>
                 </table>
             </form>
             <form id="frm" name="stringFrm" method="POST" action="setimg">
                <input id='out' type="hidden" name='str' />
             </form>
             <script>
                 (function(){
                     var form = document.getElementById("frm");
                     var btn = document.getElementById("btn");
                     var err = document.getElementById("err");
                     var haserr = true;
                     function errdis(){
                         haserr = true;
                         err.textContent = 'Image Must be 64px by 64px';
                     }
                     function errclear(){
                         haserr = false;
                         err.textContent = '';
                     }
                     ImageUploadHandeler("in", "out", null, errdis, errclear);
                     event(btn, "click", function(){
                         if(!haserr){
                             frm.submit();
                         }
                     })
                 })();
             </script>
         </div>
     </div>
</t:main>
