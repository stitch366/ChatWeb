/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Form(baseid, errdel, errorout, feildmap, validation){
    var frminit = document.getElementById(baseid+"-init");
    var frmfin = document.getElementById(baseid+"-fin");
    function validate(felid){
        var v = true;
        var input = frminit.querySelector("#"+felid);
        var msg="";
        if(input.value === ""){
            msg = feildmap[felid].text+" is required.";
        }
        else if (validation[felid] != null){
           msg = validation[felid]();
        }
        return msg;
    }
    this.check = function(){
        errdel();
        var val = true;
        var msg;
        for(var x in feildmap){
            msg = validate(x);
            if(!(msg === "")){
                errorout(msg)
                val = false;
            }
        }
        return val;
    }
    this.submit=function(){
        if(this.check()){
            var m;
            for(var x in feildmap){
                m=feildmap[x].fin;
                frmfin.getElementById(m).value = frminit.getElementById(x).value;
            }
            frmfin.submit();
        };
    }
}
function eventAll(sel, evt, func){
    var elms = Array.prototype.slice.call(document.querySelectorAll(sel));
    for(var x =0; x < elms.length; x++){
        event(elms[x], evt, func);
    }
}
function event(elm, evt, func){
  if(typeof(elm) ==='string'){
    elm = document.querySelector(elm);
  }
  if (elm.addEventListener) {                    // For all major browsers, except IE 8 and earlier
    elm.addEventListener(evt, func);
  } else if (x.attachEvent) {                  // For IE 8 and earlier versions
    x.attachEvent("on"+evt,func);
  }
}
function ComboBox(id){
  var combo = document.querySelector('#'+id);
  var list = Array.prototype.slice.call(combo.querySelectorAll("ul li"));
  var input = document.querySelector('#'+id+"-in");
  function hide(opt){
    opt.classList.add("hidden");
  }
  function toggle(opt){
    opt.classList.toggle("hidden");
  }
  function show(opt){
    opt.classList.remove("hidden");
  }
  function unhideall(){
    list.forEach(show);
  }
  function hideall(){
    list.forEach(hide);
  }
  function filter(){
    var pattern = input.value;
    if(pattern !== ""){
      pattern = pattern.split("").join(".*");
      var reg = new RegExp(pattern);
      function test(opt){
        if(reg.test(opt.textContent)){
          show(opt);
        }
        else{
          hide(opt);
        }
      }
      list.forEach(test);
    }
    else{
      unhideall();
    }
  }
  function select(evt){
   var opt = evt.target;
    input.value = opt.textContent;
    input.blur()
  }
  this.value=function(){
    var l = list.filter(function(a){
      return a.textContent === input.value;
    });
    var v = null;
    if(l.length > 0){
      v = l[0].value;
    }
    return v;
  }
  this.set= function(value){
    var l = list.filter(function(a){
      return a.textContent === value;
    });
    if(l.length > 0){
      input.value = value;
    }
  }
  event(input, "focus", filter);
  event(input, "keyup", filter);
  event(input, "blur", hideall);
  list.forEach(function(opt){
    event(opt, "mousedown", select);
    hide(opt);
  })
  
}
function AjaxTimer(caller, data, sec){
    var timer;
    function doAjax(){
        caller.call(data);
    }
    this.start=function(){
        timer = window.setInterval(doAjax, (sec*1000));
    };
    this.stop=function(){
        window.clearInterval(timer);
    };
}
function AjaxCaller(url, method,postfunc){
  function makeURIdata(data){
      var keys = Object.keys(data);
      var d = "";
      for(var x =0; x < keys.length;x++){
          d+=keys[x]+"="+encodeURIComponent(data[keys[x]]);
          if(x != keys.length-1){
              d+="&";
          }
      }
      return d;
  }
  this.call= function(data){
    $.ajax({
      type : method,
      url : url,
      dataType:'text',
      data : makeURIdata(data),
      timeout : 100000,
      success : function(response) {
        postfunc(response);
      },
    });
  }
}
function make(tag, attrs){
  var elm = document.createElement(tag);
  for(var x in attrs){
    elm.setAttribute(x, attrs[x]);
  }
  return elm;
}
function userout(imgdata, uname){
  var tr=make("tr", {'class':'user-out'});
  var img = make("td", {'class':'uimg'});
  var unattrs = {'class':'uname'};
  if(imgdata !=""){
    img.appendChild(make('img', {'src':imgdata}));
    tr.appendChild(img);
  }
  var un = make("td", {'class':'uname'});
  un.textContent = uname;
  tr.appendChild(un);
  return tr;
}
function message(obj){
  function timestamp(t){
    var tr = make("tr", {'class':'timestamp'});
    var td = make("td", {'colspan':2});
    td.textContent = t;
    tr.appendChild(td);
    return tr;
  }
  function messageHead(img, sender){
    var table = make("table", {'class':'msg-head'});
    table.appendChild(userout(img, sender));
    return table;
  }
  var msgtable = make("table", {'class':'msg-table'});
  var head = make("td",{'class':'msg-user'});
  head.appendChild(messageHead(obj.img, obj.sender+" :"));
  var message = make("div", {'class':'msg-box'});
  message.classList.add(obj.sender);
  message.style.color = obj.color;
  message.style.fontFamily='"'+obj.font+'"';
  var text = make("div", {'class':'msg-text'});
  var tr = make("tr", {'class':'msg-row'});
  var body = make("td", {'class':'msg-body'});
  text.innerHTML = obj.msg;
  body.appendChild(text);
  tr.appendChild(head);
  tr.appendChild(body);
  msgtable.appendChild(timestamp(obj.time));
  msgtable.appendChild(tr);
  message.appendChild(msgtable);
  return message;
}
function UserStyle(user, font, color){
    this.user=function(){
        return this.user;
    }
    this.rule=function(){
        return "."+user+" *{ color:"+color+"; font-family: '"+font+"'; }";
    }
}
function Filter(table, tabletxtsel, inputid){
    var list = Array.prototype.slice.call(document.querySelectorAll("#"+table+" .scroll-body .scroll-row"));
    var input = document.getElementById(inputid);
    function hide(opt){
        opt.classList.add("hidden");
      }
      function toggle(opt){
        opt.classList.toggle("hidden");
      }
      function show(opt){
        opt.classList.remove("hidden");
      }
      function unhideall(){
        list.forEach(show);
      }
      function hideall(){
        list.forEach(hide);
      }
      function getText(p){
          return p.querySelector(tabletxtsel).textContent;
      }
      function testRow(reg, tr){
          var text = getText(tr);
          return reg.test(text);
      }
      function filter(){
        var pattern = input.value;
        if(pattern !== ""){
          pattern = pattern.split("").join(".*");
          var reg = new RegExp(pattern);
          function test(tr){
            if(testRow(reg, tr)){
              show(tr);
            }
            else{
              hide(tr);
            }
          }
          list.forEach(test);
        }
        else{
          unhideall();
        }
      }
      event(input, "keyup", filter);
}
function ImageUploadHandeler(inid, outid, extra, errout, errclear){
    function getImageData(inid, outid, call){
      var file = document.querySelector("#"+inid).files[0];
      var out = document.querySelector("#"+outid);
      function setOut(url){
        out.value=url;
        if(call != null)
        {
          call(url);
        }
      }
      var reader  = new FileReader();
      function getDataUri(url, callback) {
        var image = new Image();
        image.onload = function () {
          if((this.naturalWidth == 64) && (this.naturalHeight == 64)){
            if(errclear != null){
              errclear();
            }
            var canvas = document.createElement('canvas');
            canvas.width = this.naturalWidth; 
            canvas.height = this.naturalHeight; 
            canvas.getContext('2d').drawImage(this, 0, 0);
            callback(canvas.toDataURL('image/png'));
          }
          else{
            if(errout != null){
              errout();
            }
          }
        };

        image.src = url;
      }
      reader.addEventListener("load", function () {
        out.value = getDataUri(reader.result, setOut);
      }, false);
      if(file){
        reader.readAsDataURL(file);
      }
    }
    var x = document.getElementById(inid);
    if (x.addEventListener) {                    // For all major browsers, except IE 8 and earlier
      x.addEventListener("change", function(){ getImageData(inid, outid, extra)});
    } else if (x.attachEvent) {                  // For IE 8 and earlier versions
      x.attachEvent("onchange",function(){ getImageData(inid, outid, extra)});
    }
  }
function ColorSelector(txt, picker, hidden, defaultv){
  var text = document.getElementById(txt);
  var color = document.getElementById(picker);
  var forSubmit = document.getElementById(hidden);
  var def= "000000";
  if(defaultv != null){
    def=defaultv;
  }
  function evt(x, evt, func){
    if (x.addEventListener) {// For all major browsers, except IE 8 and earlier
      x.addEventListener(evt, func);
    } else if (x.attachEvent) {                  // For IE 8 and earlier versions
      x.attachEvent("on"+evt, func);
    }
  }
  function setValue(value){
    var v = value.replace("#", "");
    var inputs = [text, color, forSubmit];
    inputs.forEach(function(e){
      if(e != color){
        e.value = v;
      }
      else{
        e.value = "#"+v;
      }
    })
  }
  function checkHex(value){
    return /^([A-Fa-f0-9]{3}$)|([A-Fa-f0-9]{6}$)/.test(value);
  }

  setValue(def);
  function ValChange(keypress){
    if(keypress){
      if(checkHex(text.value)){
        setValue(text.value);
      }
    }
    else{
      if(checkHex(text.value)){
        setValue(text.value);
      }
      else{
        setValue(def);
      }
    }
  }
  evt(text, "keyup", function(){ ValChange(true)});
  evt(text, "blur", function(){ ValChange(false)});
  evt(color, "change", function(){ setValue(color.value)});
}
function Scroller(id){
  var pane = document.getElementById(id);
  this.bottom = function(){
    pane.scrollTop = pane.scrollHeight;

  }
}
function resetCursor(txtElement) { 
    if (txtElement.setSelectionRange) { 
        txtElement.focus(); 
        txtElement.setSelectionRange(0, 0); 
    } else if (txtElement.createTextRange) { 
        var range = txtElement.createTextRange();  
        range.moveStart('character', 0); 
        range.select(); 
    } 
}
function LogWindow(file, name, features){
    var win = window.open(file, name, features);
}


