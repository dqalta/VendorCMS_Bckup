<%-- 
    Document   : main
    Created on : 15/06/2019, 11:13:00 AM
    Author     : developer
--%>
<%-- insertion of valid struts labels--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="st"%>
<%@ taglib uri="/struts-jquery-tags" prefix="stj"%>
<%@ taglib uri="/struts-bootstrap-tags" prefix="stb"%>

<!DOCTYPE html>
<html>
    <head>
        <st:include value="/generals/css-js-Jquery.jsp"/>
        <st:include value="/generals/css-js-Head.jsp" />
        <st:include value="/generals/css-js-Bootstrap.jsp"/>
        <st:include value="/generals/css-js-app.jsp"/>

        <link rel="icon" type="image/png" href="/VendorCMS/home/img/favicon.ico" />


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendor's Admin Area</title>
    </head>
    <body style="margin: 0px;">
        <st:if test="%{sesionActiva == true}">     
            <st:include value="/generals/navApps.jsp" /> 
            <st:include value="/generals/mainmenu.jsp" >
                <st:param name="title">HOME VENDOR</st:param>
                <st:set var="userName">${param.userName}</st:set>
            </st:include>
            <st:include value="/generals/navBarFooter2.jsp" /> 

        </st:if>
        <st:else>
            <st:include value="/generals/permiso.jsp" />
        </st:else>        
        <st:else>
            <%response.sendRedirect("/VendorCMS/");%>
        </st:else> 

    </body>
</html>
