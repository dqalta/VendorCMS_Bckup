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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
      
           <link rel="icon" type="image/png" href="/VendorCMS/home/img/favicon.ico" />
        
   
        <title>Sign Up Vendor Masonry Store</title>

        <!-- Font Icon -->
        <link rel="stylesheet" href="/VendorCMS/home/js/register/fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="/VendorCMS/home/js/register/css/style.css">
        <title>Create a vendor account</title>
    </head>
    <body>
    <st:form id="frm" name="frm" action="register" method="post" cssClass="form-horizontal">
        <div class="main">

            <section class="signup">
                <!-- <img src="images/signup-bg.jpg" alt=""> -->
                <div class="container">
                    <div class="signup-content">
                        <form method="POST" id="signup-form" class="signup-form">
                            <h2 class="form-title">Create account</h2>
                            <div class="form-group">
                                <input type="text" class="form-input" name="name" id="name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-input" name="email" id="email" placeholder="Your Email"/>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-input" name="password" id="password" placeholder="Password"/>
                                <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-input" name="re_password" id="re_password" placeholder="Repeat your password"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up"/>
                            </div>
                        </form>
                        <p class="loginhere">
                            Have already an account ? <a href="/VendorCMS/intro.jsp" class="loginhere-link">Login here</a>
                        </p>
                    </div>
                </div>
            </section>

        </div>


        <div style="text-align: left; color:#e6e6e6;">
            <st:fielderror />
            <st:actionerror />
            <st:actionmessage />            
        </div>
    </st:form> 
   <st:include value="/generals/css-js-Jquery.jsp"/>
        </body>
</html>
