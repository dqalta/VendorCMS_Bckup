<%-- 
Document   : intro
Created on : 15/06/2019, 12:34:42 PM
Author     : developer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="st"%>
<%@ taglib uri="/struts-bootstrap-tags" prefix="stb"%>
<!DOCTYPE html>
<html>
    <head>
        <st:include value="/generals/css-js-Jquery.jsp"/>
        <st:include value="/generals/css-js-Head.jsp" />
        <st:include value="/generals/css-js-Bootstrap.jsp"/>
        <link rel="icon" type="image/png" href="/VendorCMS/home/img/favicon.ico" />
        <!-- Font Icon -->
        <link rel="stylesheet" href="/VendorCMS/home/js/register/fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="/VendorCMS/home/js/register/css/style.css">
        <title>Login Area</title>
    </head>
    <body>
        <st:form id="frm" name="frm" action="login" method="post" cssClass="form-horizontal">
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
                                        <st:textfield class="form-control" name="usuario" id="usuario" placeholder="Username..."/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <span class="input-group-addon glyphicon glyphicon-lock"></span>
                                            <st:password  class="form-control"  placeholder="Contraseña..." id="contrasena" name="Password" />
                                    </div>
                                </div>

                                <div class="row">
                                    <a class="btn btn-warning modifi center-block"  href="/VendorCMS/login.vdk" > <strong> Login </strong></a>
                                </div>
                                    <br>
                                    
                                <div class="row">
                                    <a class="btn btn-warning modifi pull-right"  href="/VendorCMS/register.vdk" > <span class="glyphicon glyphicon-edit"></span>
                                        </span>Create an account</a>
                                </div>  
                            </form>
                            <!--                    <p class="loginhere">
                                                   Don't have any account ? <a href="/VendorCMS/intro.jsp" class="loginhere-link">Register here</a>
                                                </p>-->
                        </div>
                    </div>
                </section>


                <br>

                <br>
            </div>

            <div style="text-align: left; color:#e6e6e6;">
                <st:fielderror />
                <st:actionerror />
                <st:actionmessage />   
                <st:include value="/generals/css-js-Jquery.jsp"/>
            </div>
        </st:form> 
    </body>
</html>
