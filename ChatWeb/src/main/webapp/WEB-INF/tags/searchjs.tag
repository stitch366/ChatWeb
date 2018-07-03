<%-- 
    Document   : searchjs
    Created on : Nov 27, 2017, 8:22:53 PM
    Author     : Sydney
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8" dynamic-attributes="attrs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
        Filter("${attrs.tableid}", "${attrs.compare}", "filter");
</script>