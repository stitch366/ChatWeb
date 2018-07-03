<%-- 
    Document   : room
    Created on : Nov 15, 2017, 8:13:47 PM
    Author     : Sydney
--%>

<%@include file="../inc/page.jsp" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="../../custom.tld" prefix="chat" %>
<!DOCTYPE html>
<t:main>
    <h2>${name}</h2>
    <t:chatnav rmid="${id}" islogveiw="${false}"></t:chatnav>
        <div class="chat">
                <style id="chat-style"></style>
              <div id="chat-out" class="chat-out scroll">
              </div>
              <div id="chat-in" class="chat-in">
                <h3>Enter Message:</h3>
                <textarea id="msg"></textarea>
              </div>
            </div>
        <script>
            (function(){
                var scroll = new Scroller('chat-out');
                var stylelist =[];
                var msglist=[];
                function postCall(res){
                    var out = document.getElementById('chat-out');
                    var style;
                    var json = JSON.parse(res);
                    for(var x =0; x < json.length;x++){
                        if(!check(json[x])){
                            msglist.push(json[x]);
                            style = new UserStyle(json[x].sender, json[x].font, json[x].color);
                            addStyle(style);
                            out.appendChild(message(json[x]));
                            
                        }
                    }
                    scroll.bottom();
                }
                function addStyle(style){
                    var selm = document.getElementById("chat-style");
                    function checker(style){
                        var l = stylelist.filter(function(e){return e.user() == style.user()});
                        return (l.length > 0);
                    }
                    if(!checker(style)){
                        stylelist.push(style);
                        selm.sheet.insertRule(style.rule());
                    }
                }
                function check(obj){
                    var list = msglist.filter(function(e){ return e.id == obj.id;});
                    return (list.length > 0);
                }
                var feeder = new AjaxCaller("chatfeed","POST", postCall);
                feeder.call({'hr':'2'});
                var sender = new AjaxCaller("sendmsg", "POST", postCall);
                var feederTimer = new AjaxTimer(feeder, {'hr':'2'}, 10);
                feederTimer.start();
                event("#msg", "keypress", function(evt){
                    if(evt.key == "Enter"){
                        var text = document.getElementById("msg");
                        var str = text.value;
                        text.value ="";
                        resetCursor(text);
                        sender.call({'msg':str});
                    }
                });
                function leave(){
                    $.ajax({
                        type : "POST",
                        url: "leaving",
                        async:false
                    });
                }
                event(window, "beforeunload", function(){ leave()});
                
            })();
        </script>
</t:main>

