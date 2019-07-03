<%-- 
    Document   : inicio
    Created on : 10-oct-2017, 15:13:01
    Author     : CR108002
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="st"%>
<%@ taglib uri="/struts-jquery-tags" prefix="stj"%>
<%@ taglib uri="/struts-bootstrap-tags" prefix="stb"%>
<!DOCTYPE html>
<html>
    <head>    
        <st:include value="/generals/css-js-Head.jsp" />
        <st:include value="/generals/css-js-Bootstrap.jsp"/>
        <st:include value="/generals/css-js-Datatables.jsp"/>
        <st:include value="/generals/css-js-app.jsp"/>
        <st:include value="/generals/css-js-LcSwitch.jsp"/>

        <script src="/VendorCMS/home/vendorAdmin/roles/script.js" type="text/javascript"></script>  
        <script>
            function doalert() {
                if ($("#check_free").is(":checked")) {


                    $("#hideRow").fadeIn(400, 'linear');


                } else {


                    $("#hideRow").fadeOut(400, 'linear');
                }
            }


        </script>
        <style>
            .shipr {
                padding-left: 200px !important;
                padding-top: auto;
                padding-right: 200px !important;
                padding-bottom: auto;
            }
            .modifi {
                background-color: #ff6600 !important;
                margin-right: 15px;
               
            }
        </style>
    </head>
    <body style="margin: 0px;">
        <st:if test="%{sesionActiva == true}">        

            <st:hidden id="permiso" name="permiso" value="%{permiso}" />
            <st:hidden id="mensaje" name="mensaje" value="%{mensaje}" />
            <st:hidden id="mensajes" name="mensajes" value="%{mensajes}" />


            <st:include value="/generals/navApps.jsp" /> 
            <st:include value="/generals/mainmenu.jsp" >

                <st:param name="title">Vendor Roles</st:param>
            </st:include>
            <%--   <st:if test="%{permiso == true}"> --%>
            <div class="container-fluid shipr">
                <st:form id="formulario" name="formulario" cssClass="form-vertical" action="roles" method="post" theme="bootstrap">  
                    <st:hidden id="accion" name="accion" value="%{accion}"/>
                    <st:hidden id="idEdit" name="idEdit" value="%{idEdit}"/>
                    <div class="container-fluid">
                        <div class="row">
                            <h3>Retail Shipping Costs</h3>
                            <br>
                            <br>
                            <div class="col-sm-10">
                                <label for="check_free">Free Shipping</label>
                            </div>
                            <div class="col-sm-2 pull-right">
                                <st:hidden id="freeShipping" name="freeShipping" value="%{freeShipping}" />
                                <st:checkbox class="lcc" theme="simple" name="check_free" id="check_free" fieldValue="%{freeShipping}" onChange="doalert()"/>
                            </div>
                        </div>   
                        <br>
                        <div id="hideRow"  class="row">
                            <div class="col-sm-10">
                                <label for="minValue">Min Value</label>
                                <i class="glyphicon glyphicon-question-sign" data-toggle="tooltip" title="Minimum value for shipping shall only appear if do you do free shipping on min value order"></i>
                            </div>

                            <div class="col-sm-2 pull-right">
                                <div class="input-group">
                                    <span class="input-group-addon">$</span> 
                                    <st:textfield  name="minValue" id="minValue" class="form-control" value="%{minValue}" placeholder="Min Value..."/>                                       </div>
                            </div>   
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-sm-10">
                                <label for="additionalCost">Additional Cost</label>    
                                <i class="glyphicon glyphicon-question-sign" data-toggle="tooltip" title="If there are any other additional charges for each order post the amount here"></i>
                            </div>
                            <div class="col-sm-2 pull-right">                               
                                <div class="input-group">
                                    <span class="input-group-addon">$</span> 
                                    <st:textfield  name="additionalCost" id="additionalCost" class="form-control" value="%{additionalCost}" placeholder="Addicional Cost..."/>
                                </div>

                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-sm-10">
                                <label for="flatRate">Flat Rate</label>
                                <i class="glyphicon glyphicon-question-sign" data-toggle="tooltip" title="Enter the flat rate price you charge if you always charge for every shipping order!"></i>
                            </div>
                            <div class="col-sm-2 pull-right"  >
                                <div class="input-group">
                                    <span class="input-group-addon">$</span> 
                                    <st:textfield  name="flatRate" id="flatRate" class="form-control" value="%{flatRate}" placeholder="Flat Rate..."/>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-sm-10">
                                <label for="skidDeposit">Skid Deposit Fee</label>
                                <i class="glyphicon glyphicon-question-sign" data-toggle="tooltip" title="Post the cost of your skid deposit fee here"></i>
                            </div>
                            <div class="col-sm-2 pull-right" >
                                <div class="input-group">
                                    <span class="input-group-addon">$</span> 
                                    <st:textfield  name="skidDeposit" id="skidDeposit" class="form-control" value="%{skidDeposit}" placeholder="Skid fee..."/>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-sm-10">
                                <label for="check_active">Active?</label>
                            </div>
                            <div class="col-sm-2 pull-right">
                                <st:hidden id="active" name="active" value="%{active}" />
                                <st:checkbox class="lcc" theme="simple" name="check_active" id="check_active" fieldValue="%{active}" />
                            </div>
                            <br>   
                        </div>
                            <br>
                        <div class="row">

                            <div class="btn-group pull-right">
                                <a class="btn btn-danger modifi pull-right " onclick="save();"><i class="glyphicon glyphicon-ok"></i>&nbsp;Save Changes</a>
                            </div>
                        </div>  

                    </div>

                </div>

            </div>

        </st:form>

    </div>                     







</div> 
<div class="modal" id="ModalProcesando">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Vendor CMS</h4>
            </div>
            <div class="modal-body">
                <h4>Processing, Please wait...<i class="glyphicon glyphicon-repeat fast-right-spinner"></i></h4>
            </div>
        </div>
    </div>
</div>

<%--     </st:if> --%>
<st:else>
    <st:include value="/generals/permiso.jsp" />
</st:else>

<st:include value="/generals/navBarFooter.jsp" /> 
</st:if>
<st:else>

    <%response.sendRedirect("/VendorsCMS/");%>
</st:else> 


</body>
</html>