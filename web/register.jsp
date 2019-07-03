<%-- 
    Document   : intro
    Created on : 15/06/2019, 12:34:42 PM
    Author     : developer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="st"%>
<%@ taglib uri="/struts-jquery-tags" prefix="stj"%>
<%@ taglib uri="/struts-bootstrap-tags" prefix="stb"%>
<!DOCTYPE html>
<html class="fondoRegister">
    <head>
        <st:include value="/generals/css-js-Head.jsp" />
        <st:include value="/generals/css-js-Bootstrap.jsp"/>
        <st:include value="/generals/css-js-Chosen.jsp"/>
        <st:include value="/generals/css-js-app.jsp"/>
        <st:include value="/generals/css-js-Icheck.jsp"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link rel="icon" type="image/png" href="/VendorCMS/home/img/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="/VendorCMS/home/js/register/css/register.css">

        <script src="/VendorCMS/scriptRegister.js" type="text/javascript"></script>   


        <title>Sign Up Vendor Masonry Store</title>
    </head>
    <body>
        <st:hidden id="permiso" name="permiso" value="%{permiso}" />
        <st:hidden id="mensaje" name="mensaje" value="%{mensaje}" />
        <st:hidden id="mensajes" name="mensajes" value="%{mensajes}" />

        <st:form id="frm" name="frm" action="register" method="post" cssClass="form-group">
            <st:hidden id="accion" name="accion" value="%{accion}"/>
            <st:hidden id="idEdit" name="idEdit" value="%{idEdit}"/>

            <div class="container-fluid">

                <div class="container login_customized">
                    <h2 class="titles-h2">  Register Vendor Account</h2>
                    <!--makes a linear view of inputs-->
                    <br>

                    <div class="row">                           
                        <div class="col-sm-6 col-xs-12 form-group">
                            <label for="companyName">Company Name</label>                             
                            <st:textfield label="Company Name" name="companyName" id="companyName" class="form-control" value="%{companyName}" placeholder="Company name..."/>                   
                        </div>                              
                    </div>

                    <!--closes first row-->

                    <div class="row">

                        <div class="col-sm-6 col-xs-12 form-group">
                            <label for="name"> Contact Name</label>
                            <st:textfield  name="name" id="name" class="form-control" value="%{name}" placeholder="Name of contact..."/>
                        </div>


                        <div class=" col-sm-6 col-xs-12 form-group">
                            <label for="phoneNumber"> Phone Number (format: xxx-xxx-xxxx)</label>
                            <st:textfield  name="phoneNumber" id="phoneNumber" class="form-control" value="%{phoneNumber}" placeholder="Phone Number" pattern="^\d{3}-\d{3}-\d{4}$"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-xs-12 form-group">
                            <label for="webSite">Website</label>
                            <st:textfield  name="webSite" id="webSite" class="form-control" value="%{webSite}" placeholder="Input your website..." />
                        </div>
                        <div class="col-sm-6 col-xs-12 form-group" style="overflow: visible !important;">
                            <label for="city">City</label>
                            <st:select class="form-control"  id="city" name="city" value="%{city}" list="citiesRegister" listKey="description" listValue="description" />

                        </div>
                    </div>   
                    <div class="row">
                        <div class="col-sm-6 col-xs-12 form-group">


                            <label for="email">Email</label>
                            <st:textfield  name="email" id="email" class="form-control" value="%{email}"  onmouseout="enableEmail2();" placeholder="Email..."/>  
                        </div>
                        <div class="col-sm-6 col-xs-12 form-group ">
                            <label for="pwd">Repeat Email</label>
                            <st:textfield  name="email2" id="email2" class="form-control" value="%{email2}"   onmouseout="validateEmail();" placeholder="Email verification..."/>  
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-xs-12 form-group">
                            <label for="password">Password</label>
                            <st:password  name="password" id="password" class="form-control"  value="%{password}" placeholder="Password..."/> 
                        </div>
                        <div class="col-sm-6 col-xs-12 form-group">
                            <label for="password2">Repeat password</label>
                            <st:password  name="password2" id="password2" class="form-control"  value="%{password2}" placeholder="Password verification..."/>

                        </div>
                    </div>


                    <div class="wrap_button">
                        <button type="submit" class="btn btn-warning newb" onclick="saveRequest();"><strong>Register Now</strong></button>
                    </div>

                    <div class="btn-group pull-right">       

                        <a class="btn btn-warning modifi pull-right"  href="/VendorCMS/intro.jsp" >
                            <i class="glyphicon glyphicon-log-in"></i>&nbsp;Go to login page </a>
                    </div>


                </div>
            </div>



            <div style="text-align: left; color:#e6e6e6;">
                <st:fielderror />
                <st:actionerror />
                <st:actionmessage />            
            </div>
        </st:form> 

    </body>
</html>
