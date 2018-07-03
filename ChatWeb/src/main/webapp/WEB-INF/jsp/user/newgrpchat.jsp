<%-- 
    Document   : newpubchat
    Created on : Nov 13, 2017, 6:27:02 PM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<!DOCTYPE html>
<t:main title="Web Chat">
    <h2>New Group Chat Room</h2>
    <form id="room-frm">
            <table>
                <tr><td>Name:</td><td><input id="n" type="text" value=""></td><td class="err-out" >${err}</td></tr>
               <tr><td>Group:</td><td><chat:combo id='grp' name='grp' items="${grps}"/></td></tr>
               <tr><td><button id="btn" type="button">Create</button></td><td></td></tr>
            </table>
            <table id='errs'>
                <tbody></tbody>
            </table>
        </form>
        <form id="frm" name="roomFrm" method="POST" action="createroom">
            <input id='name' type="hidden" name='name' />
            <input id='group' type="hidden" name='group' />
            <input type="hidden" name="type" value="GROUP"/>
        </form>
        <script>
            (function(){
                var combo = new ComboBox('grp');
                function Submitter(){
                    var form = document.getElementById("frm");
                    var name = document.getElementById("n");
                    var out1 = document.getElementById("name");
                    var out2 = document.getElementById("group");
                    var errout = document.querySelector("#errs tbody");
                    function clearErrors(){
                        while(errout.firstChild != null){
                            errout.removeChild(errout.firstChild);
                        }
                    }
                    function makeerr(txt){
                        var tr = make("tr",{});
                        var td = make("td", {'class':'err-out'});
                        td.textContent =txt;
                        tr.appendChild(td);
                        return tr;
                    }
                    function check(){
                        clearErrors();
                        var v1 = document.getElementById("n").value;
                        var v2 = combo.value();
                        var test1 = (v1!="" && v1!=null);
                        if(!test1){
                            errout.appendChild(makeerr("Enter Name for Chatroom."));
                        }
                        var test2 = (v2!=null);
                        if(!test2){
                            errout.appendChild(makeerr("Invalid Group Specified"))
                        }
                        return (test1 && test2);
                    }
                    this.act = function(){
                        if(check()){
                            out1.value = name.value;
                            out2.value = combo.value();
                            form.submit();
                        }
                    }
                }
                var submit = new Submitter();
                event("#btn", "click", function(e){
                    submit.act();
                });
            })();
        </script>
</t:main>
