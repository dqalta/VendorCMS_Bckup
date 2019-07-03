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

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link rel="icon" type="image/png" href="/VendorCMS/home/img/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="/VendorCMS/home/js/register/css/register.css">

        <script src="/VendorCMS/scriptRegister.js" type="text/javascript"></script>   


        <title>Sign Up Vendor Masonry Store</title>
    </head>
    <body>


        <div class="container-fluid">

            <div class="container login_customized">           
                <div class="row">                           
                    <div class="page-header">

                        <h1>Important <span class="glyphicon glyphicon-info-sign"></span><small> Your account in now in revition</small></h1>
                        

                    </div>
                    <br>
                </div>
                <div class="row">
                    <div class="col-sm-8  col-xs12 col-sm-offset-2 ">
                        <p>Our team in now working in the review of all the data provided</p>
                        <p>We will be in contact with you in period until 24 hours</p>
                        <p>Once our team had been approved your account you will be receiving a email with your credential to get access into the website</p>
                        <p>Any doubt or query, contact us to <a href="mailto:contact@masonryorderdesk.ca">contact@masonryorderdesk.ca</a></p>
                        <p>Thanks to be part of Masonry Order Desk   </p>
                    </div>
                </div>
                <!--closes first row-->
                <br>
                
                <div class="wrap_button">
                    <a class="btn btn-warning newb"  href="/VendorCMS/intro.jsp" >
                        <i class="glyphicon glyphicon-log-in"></i>&nbsp;Go to login page </a>
                </div>
            </div>
            <!--                  <div class="wrap_button">
                                    <button type="submit" class="btn btn-warning newb" onclick="saveRequest();">Register Now</button>
                                </div>-->

            <div style="text-align: left; color:#e6e6e6;">
                <st:fielderror />
                <st:actionerror />
                <st:actionmessage />            
            </div>

        </div>
    </body>
</html>
