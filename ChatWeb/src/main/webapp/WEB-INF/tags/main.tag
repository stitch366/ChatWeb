<%-- 
    Document   : main
    Created on : Nov 25, 2017, 2:41:34 PM
    Author     : Sydney
--%>
boolean
<%@tag description="put the tag description here" pageEncoding="UTF-8" %>
<%@attribute name="base" required="false" type="java.lang.String" %>
<%@attribute name="title" required="false" type="java.lang.String" %>
<%@attribute name="inmail" required="false" type="java.lang.String" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="../custom.tld" prefix="chat" %>
<c:set var="sdata" value='${sessionScope["scopedTarget.sd"]}'/>
<c:set var="redirect" value="../"/>
<c:set var="logout" value="../logout"/>
<c:set var="pageTitle" value="Web Chat" />
<c:set var="basePath" value="../" />
<c:set var="inmailbox" value="${false}" />
<c:if test="${title != null}">
    <c:set var="pageTitle" value="${title}" />
</c:if>
<c:if test="${base != null}">
    <c:set var="basePath" value="${base}" />
</c:if>
<c:if test="${inmail != null}">
    <c:set var="inmailbox" value="${true}" />
    <c:set var="logout" value="../../logout" />
</c:if>
<c:if test="${sdata.getCu() == null}">
    <c:redirect url="${redirect}" />
</c:if>
<c:set var="jquery" value='${basePath}resources/js/jquery-3.2.1.min.js'/>
<c:set var="chatjs" value='${basePath}resources/js/chatjs.js'/>
<c:set var="css" value='${basePath}resources/css/site.css'/>
<c:set var="home" value="${basePath}user/" />
<c:set var="pubrooms" value="${basePath}user/publicRooms"/>
<c:set var="makereq" value="${basePath}user/makeRequest"/>
<html>
	<head>
		<title>${pageTitle}</title>
                <script src="${jquery}"></script>
                <script src="${chatjs}"></script>
                <link rel="stylesheet" type="text/css" href="${css}"/>
	</head>
	<body>
	<div class="main-nav">
          <div class="left">
            <ul class="clear">
              <li class="nav-link"><a href="${home}">Home</a></li>
              <li class="nav-link"><a href="${pubrooms}">Public Rooms</a></li>
              <li class="nav-link"><a href="${makereq}">Make Join Request</a></li>
            </ul>
          </div>
          <div class="right">
            <ul class="clear">
              <li class="nav-link"><a href="${logout}">Logout</a></li>
            </ul>
          </div>
        <div class="clear"></div>
      </div>
	  <div class="main-content clear">
			<div class="sidebar left">
                            <div class="col-body">
                               <div class="account">
                                    <div class="account-uname">
                                        <table>
                                            <tr class="user-out"><td class="uimg"><img src="${sdata.getCu().img()}"></td><td class="uname">${sdata.getCu().username()}</td></tr>
                                        </table>
                                    </div>
                                    <div class="account-data">
                                        <table>
                                            <tr><td colspan="2">${sdata.getCu().first()}&nbsp;${sdata.getCu().last()}</td></tr>
                                            <tr><td colspan="2">${sdata.getCu().email()}</td></tr>
                                            <tr><td>Font:</td><td>${sdata.getCu().font()}&nbsp;#${sdata.getCu().fontColor()}</td></tr>
                                        </table>
                                    </div>
                                    <div class="account-mail">
                                        <chat:mail inMailbox="${inmailbox}" cu="${sdata.getCu()}"/>
                                    </div>
                                </div>
                                <div class="user-grps">
                                    <div class="group-sec">
                                        <chat:sidegrps cu="${sdata.getCu()}" inMailbox="${inmailbox}" set="owned" title="Owned Groups"/>
                                    </div>
                                    <div class="group-bumper"></div>
                                    <div class="group-sec">
                                        <chat:sidegrps cu="${sdata.getCu()}" inMailbox="${inmailbox}" set="member" title="Where Member"/>
                                    </div>
                                </div>                               
                            </div>
			</div>
			<div class="page right">
				<div class="col-body">
					<jsp:doBody/>
				</div>
			</div>
		</div>
	</body>
</html>