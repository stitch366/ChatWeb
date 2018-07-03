<%-- 
    Document   : setFont
    Created on : Nov 30, 2017, 6:29:40 PM
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
        <t:accnav veiw='font' />
         <div style="margin:0px; display:inline-block; width:100%;">
             <form>
                 <table>
                     <tr><td>Font:</td><td><chat:combo id='fnt' items="${fonts}"/></td><td id='err' class="err-out"></td></tr>
                     <tr><td>Color:</td><td>#<input id='ctxt' type="text"/><input id='col' type="color"/></td><td class="err-out"></td></tr>
                     <tr><td colspan="3"><a id='btn' class="crtbtn" >Set Font</a></td></tr>
                 </table>
             </form>
             <form id="frm" name="fontFrm" method="POST" action="setfont">
                <input id='font' type="hidden" name='font' />
                <input id='color' type="hidden" name='color' />
             </form>
             <script>
                 (function(){
                     var font = "${dfont}";
                     var color = "${dcolor}";
                     var combo = new ComboBox('fnt');
                     combo.set(font);
                     ColorSelector('ctxt', 'col', 'color', color);
                     function Submitter(){
                         var form = document.getElementById("frm");
                         var btn = document.getElementById("btn");
                         var font = document.getElementById("font");
                         var err = document.getElementById("err");
                         function submit(){
                             var f = combo.value();
                             if(f == null){
                                 err.textContent = "Invalid Font Given";
                                 combo.set(font);
                             }
                             else{
                                 font.value = f;
                                 form.submit();
                             }
                         }
                         event(btn, "click", submit);
                     }
                     Submitter();
                 })();
             </script>
         </div>
     </div>
</t:main>
