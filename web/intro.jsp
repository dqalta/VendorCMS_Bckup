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
<html>
    <head>
        <st:include value="/generals/css-js-Jquery.jsp"/>
        <st:include value="/generals/css-js-Head.jsp" />
         <st:include value="/generals/css-js-app.jsp"/>
        <st:include value="/generals/css-js-Bootstrap.jsp"/>
       
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link rel="icon" type="image/png" href="/VendorCMS/home/img/favicon.ico" />
        <!-- Font Icon -->
        <link rel="stylesheet" href="/VendorCMS/home/js/register/fonts/material-icon/css/material-design-iconic-font.min.css">
        <!-- Main css -->
        <link rel="stylesheet" href="/VendorCMS/home/js/register/css/style.css">
        <title>Login Area</title>
        <script src="/VendorCMS/script.js" type="text/javascript"></script>  
    </head>
    <body>
        <st:form id="frm" name="frm" action="login" method="post" cssClass="form-horizontal">
            <st:hidden id="mensaje" name="mensaje" value="%{mensaje}" />
            <st:hidden id="mensajes" name="mensajes" value="%{mensajes}" />
            <div style="text-align: center; color:#666666;">  
                <div> <br></div>
                <!--            <h3 style="color:white">Vendor CMS - Masonry Supply<br>C.M.S</h3> -->
            </div>
            <br>
            <div class="main">
                <section class="signup">
                    <!-- <img src="images/signup-bg.jpg" alt=""> -->
                    <div class="container">
                        <div class="signup-content">
                            <form method="POST" id="signup-form" class="signup-form">

                                <!--<img src="/VendorCMS/home/img/logo.png" high="200px" width="250px" class="form-input"   >-->

                                <h2 class="form-title"> Login page</h2>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon glyphicon glyphicon-user"> </span>
                                        <st:textfield class="form-control"  id="vendorUser" name="vendorUser" placeholder="Username..."/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon glyphicon glyphicon-lock"></span>
                                        <st:password  class="form-control"   id="vendorPassword" name="vendorPassword" placeholder="Password..." />
                                    </div>
                                </div>

                                <div class="row">
                                    <a class="btn btn-warning modifi center-block"  onclick="loginVendor();" > <strong> Login </strong></a>
                                </div>
                                <br>

                                <div class="row">
                                    <a class="btn btn-warning modifi pull-right"  href="/VendorCMS/register.vdk" > <span class="glyphicon glyphicon-edit"></span>
                                        </span>Create an account</a>
                                </div>  
                                <br>
                                <div style="text-align: left; color:#e6e6e6;">
                                    <st:fielderror />
                                    <st:actionerror />
                                    <st:actionmessage />   
                                  
                                </div>
                                
                            </form>
                            <!--                    <p class="loginhere">
                                                   Don't have any account ? <a href="/VendorCMS/intro.jsp" class="loginhere-link">Register here</a>
                                                </p>-->
                        </div>
                                <div class="modal" id="modalProcess">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title"><i class="glyphicon glyphicon-folder-open"></i>Vendor CMS</h4>
                                            </div>
                                            <div class="modal-body">
                                                <h4>Processing, Please wait...<i class="glyphicon glyphicon-repeat fast-right-spinner"></i></h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    </div>
                </section>


                <br>

                <br>
            </div>


        </st:form> 

    </body>
</html>
