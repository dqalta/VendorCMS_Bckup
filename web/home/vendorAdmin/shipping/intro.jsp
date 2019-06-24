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
        <st:include value="/generals/css-js-Chosen.jsp"/>
        <script src="/VendorCMS/home/vendorAdmin/shipping/script.js" type="text/javascript"></script>        
        <style>
            .codesZ {
                padding-bottom: auto;
                padding-left: 200px;
                padding-right: 200px;
                padding-top:  auto;
            }

        </style>
    </head>
    <body style="margin: 0px;">
        <st:if test="%{sesionActiva == true}">                  
            <st:hidden id="permiso" name="permiso" value="%{permiso}" />
            <st:hidden id="mensaje" name="mensaje" value="%{mensaje}" />
            <st:hidden id="mensajes" name="mensajes" value="%{mensajes}" />
            <st:include value="/generals/css-js-Head.jsp" />
            <st:include value="/generals/css-js-Bootstrap.jsp"/>
            <st:include value="/generals/css-js-Datatables.jsp"/>
            <st:include value="/generals/css-js-app.jsp"/>
            <st:include value="/generals/css-js-LcSwitch.jsp"/>

            <st:include value="/generals/navApps.jsp" /> 
            <st:include value="/generals/mainmenu.jsp" >
                <st:param name="title">Postal Codes to delivery</st:param>
            </st:include>
            <%--   <st:if test="%{permiso == true}"> --%>
            <st:form id="formulario" name="formulario" cssClass="form-vertical" action="shipping" method="post" theme="bootstrap">  

                <div  class="table-responsive codesZ">
                    <table id="table_codes" class="table table-striped" style="width:100%; margin: 0px auto;">
                        <thead>
                            <tr>
                                <th>City</th>
                                <th>PostalCode</th>
                                <th>We delivery</th>
                                <th>Cost Per Unit</th>                                 
                            </tr>
                        </thead>
                        <tbody>
                            <st:if test="%{!getShippingZones().isEmpty()}">
                                <st:iterator value="shippingZones" var="shippingZones" status="index">
                                    <tr>
                                        <td><st:property value="%{#shippingZones.city}" /></td>           
                                        <td><st:property value="%{#shippingZones.postalCode}" /></td>        
                                        <%-- <td><st:date name="%{#shippingZones.created}" format="dd/MM/yyyy"/></td>  --%>     
                                        <%--  <td><st:checkbox  fieldValue="%{#shippingZones.active}"/></td>        --%>                                                                        
                                        <%--   <td><st:date name="%{#shippingZones.modified}" format="dd/MM/yyyy"/></td>   --%>    
                                        <%--  <td><st:property value="%{#shippingZones.modifiedBy}" /></td>   --%>                                      
                                        <td>
                                            <st:if test="%{#shippingZones.active == true}">
                                                <!--<i class="glyphicon glyphicon-off text-success" data-toggle="tooltip" data-placement="top" title="" data-original-title="Active"</i>-->
                                                <st:checkbox id="active" name="active" value="%{#shippingZones.active}"/>
                                                <input  id="active" type="checkbox" name="active" value="%{#shippingZones.active}"  onChange="save()"/>

                                            </st:if>
                                            <st:else>
                                                <st:checkbox  id="active" name="active" value="%{#shippingZones.active}"/>

                                                <input type="checkbox"  id="active"  name="active" value="%{#shippingZones.active}" onChange="save();"/>



                                                <!--<i class="glyphicon glyphicon-off text-danger"  data-toggle="tooltip" data-placement="top" title="" data-original-title="Inactive"></i>-->

                                            </st:else>
                                        </td>  
                                        <td>
                                            <div class="input-group">
                                                <span class="input-group-addon">$</span>   
                                                <st:textfield  name="costPerUnit" id="costPerUnit" value = "%{shippingZones.costPerunit}" class="form-control" placeholder="Price"/>                                                
                                            </div>

                                        </td>    

                                    </tr>
                                </st:iterator>
                            </st:if>
                        </tbody>
                    </table>
                </div>
            </st:form>
            <div class="modal" id="ModalProcesando">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title"><i class="glyphicon glyphicon-folder-open"></i>&nbsp;&nbsp;Masonry CMS</h4>
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