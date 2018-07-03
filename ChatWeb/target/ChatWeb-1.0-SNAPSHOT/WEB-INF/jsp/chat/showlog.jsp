<%-- 
    Document   : showlog
    Created on : Dec 2, 2017, 2:45:11 PM
    Author     : Sydney
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Web Chat</title>
        <meta charset="UTF-8">
        <script src="../resources/js/jquery-3.2.1.min.js"></script>
        <script src="../resources/js/chatjs.js"></script>
        <link rel="stylesheet" type="text/css" href="../resources/css/site.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style id="style"></style>
    </head>
    <body>
        <h1>Room:  ${name}</h1>
        <h1>Date:  ${log}</h1>
        
        <div id='out'></div>
        <script>
            function init(){
                var data='${data}';
                var stylesheet = document.getElementById("style");
                var out = document.getElementById("out");
                var stylelist =[];
                var json = JSON.parse(data);
                var itemstyle;
                function addStyle(style){
                    function checker(style){
                        var l = stylelist.filter(function(e){return e.user() == style.user()});
                        return (l.length > 0);
                    }
                    if(!checker(style)){
                        stylelist.push(style);
                        stylesheet.sheet.insertRule(style.rule());
                    }
                }
                for(var x =0; x < json.length;x++){
                    itemstyle = new UserStyle(json[x].sender, json[x].font, json[x].color);
                    addStyle(itemstyle);
                    out.appendChild(message(json[x]));
                }
                
            }
            init();
        </script>
    </body>
</html>
