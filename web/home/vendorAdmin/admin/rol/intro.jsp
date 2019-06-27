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
        <st:include value="/generals/css-js-Icheck.jsp"/>
        <script src="/VendorCMS/home/vendorAdmin/admin/rol/script.js" type="text/javascript"></script>   
        <style>
            .rules{
                margin-left: 200px !important; 
                margin-right: 200px !important;
            }
            .permits {
                margin-left: 180px !important;
                margin-right: 180px !important;
            }
        </style>
    </head>
    <body style="margin: 0px;">
        <st:if test="%{sesionActiva == true}">        
            <st:hidden id="permiso" name="permiso" value="%{permiso}" />
            <st:hidden id="mensaje" name="mensaje" value="%{mensaje}" />
            <st:hidden id="mensajes" name="mensajes" value="%{mensajes}" />
            <st:include value="/generals/navApps.jsp" /> 
            <st:include value="/generals/mainmenu.jsp" />
            <st:param name="title">ROLS</st:param>
                <br>
                <br>
                <br>
            <st:if test="%{permiso == true}"> 

                <st:form id="formulario" name="formulario" cssClass="form-vertical" action="rols" method="post" theme="bootstrap">                                        
                    <st:hidden id="accion" name="accion" value="%{accion}"/>
                    <st:hidden id="idEdit" name="idEdit" value="%{idEdit}"/>
                    <div class="panel-group rules">

                        <div class="panel panel-default">
                            <div class="panel-heading clickable">
                                <h4 class="panel-title">
                                    <a><i class="glyphicon glyphicon-chevron-down"></i></a>
                                </h4>
                            </div>
                            <div id="form-panel" class="panel-collapse collapse">
                                <div class="panel-body"> 
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <st:textfield label="Description:" name="description" id="description" class="form-control" value="%{description}" placeholder="Description..."/>
                                        </div>  
                                    </div> 
                                    <div class="btn-group pull-right">
                                        <a class="btn btn-danger pull-right" onclick="saveRol();"><i class="glyphicon glyphicon-ok"></i>&nbsp;Save</a>
                                        <a class="btn btn-default pull-right" onclick="cancel();"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cancel</a>
                                    </div> 
                                    <ul class="nav nav-tabs">
                                        <li class="active"><a href="#maintenanceTab" data-toggle="tab" aria-expanded="true">Permissions</a></li>                  
                                    </ul>
                                    <div id="tabsContents" class="tab-content">
                                        <div class="tab-pane fade active in" id="maintenanceTab">    
                                            <div class="container-fluid">    
                                                <br>
                                                <div class="row">
                                                    <div class="col-sm-2"> 
                                                        <label><strong>Administration&nbsp;</strong></label>
                                                    </div>   
                                                </div>
                                                <hr>
                                                <div class="row">
                                                    <div class="col-sm-1">
                                                        &nbsp;
                                                    </div>
                                                    <div class="col-sm-2"> 
                                                        <label><st:checkbox theme="simple"  value="%{user}" id="user" name="user"/>&nbsp;Users</label>
                                                    </div>   
                                                    <div class="col-sm-2"> 
                                                        <label><st:checkbox theme="simple"  value="%{rol}" id="rol" name="rol"/>&nbsp;Rols</label>
                                                    </div>   
                                                    <div class="col-sm-4"> 
                                                        <label><st:checkbox theme="simple"  value="%{audit}" id="audit" name="audit"/>&nbsp;See Audit Fields (All Tables)</label>
                                                    </div>   
                                                </div> 
                                                <br>

                                                <div class="row">
                                                    <div class="col-sm-2"> 
                                                        <label><strong>Products  & Orders &nbsp;</strong></label>
                                                    </div>   
                                                </div>
                                                <hr>  
                                                <div class="row">
                                                    <div class="col-sm-1">
                                                        &nbsp;
                                                    </div>
                                                    <div class="col-sm-2"> 
                                                        <label><st:checkbox theme="simple"  value="%{myProducts}" id="myProducts" name="myProducts"/>&nbsp;My Products</label>
                                                    </div>
                                                    <div class="col-sm-2"> 
                                                        <label><st:checkbox theme="simple"  value="%{orders}" id="orders" name="orders"/>&nbsp;Orders</label>
                                                    </div> 
                                                    <div class="col-sm-2"> 
                                                        <label><st:checkbox theme="simple"  value="%{inventory}" id="inventory" name="inventory"/>&nbsp;Inventory</label>
                                                    </div> 
                                                    <div class="col-sm-2"> 
                                                        <label><st:checkbox theme="simple"  value="%{shippingRules}" id="shippingRules" name="shippingRules"/>&nbsp;Shipping Roles</label>
                                                    </div> 
                                                    <div class="col-sm-2"> 
                                                        <label><st:checkbox theme="simple"  value="%{shippingCodes}" id="shippingCodes" name="shippingCodes"/>&nbsp;Shipping Zones</label>
                                                    </div> 
                                                </div>   
                                                <br>                                                                                                 
                                                <hr>  
                                                <br>

                                                <br>

                                                <br>  
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </st:form>  

                <div style="padding: 20px;" class="table-responsive permits">
                    <table id="table_rols" class="table table-striped" style="width:100%; margin: 0px auto;">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Description</th>
                                <th>Created</th>
                                <th>Created By</th>
                                <th>Modified</th>
                                <th>Modified By</th>
                                <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <st:if test="%{!getRoles().isEmpty()}">
                                <st:iterator value="roles" var="roles" status="index">
                                    <tr>
                                        <td><st:property value="%{#roles.id}" /></td>           
                                        <td><st:property value="%{#roles.description}" /></td>        
                                        <td><st:date name="%{#roles.created}" format="dd/MM/yyyy"/></td>       
                                        <td><st:property value="%{#roles.createdBy}" /></td>    
                                        <td><st:date name="%{#roles.modified}" format="dd/MM/yyyy"/></td>    
                                        <td><st:property value="%{#roles.modifiedBy}" /></td>        
                                        <td onclick="edit('<st:property value="%{#roles.id}" />');"><i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="left" title="" data-original-title="Edit Row"></i></td>
                                    </tr>
                                </st:iterator>
                            </st:if> 
                        </tbody>
                    </table>
                </div>

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

            </st:if> 
            <st:else>
                <st:include value="/generals/permiso.jsp" />
            </st:else>

            <%--   <st:include value="/generals/navBarFooter.jsp" /> --%>
        </st:if>
        <st:else>
            <%response.sendRedirect("/VendorCMS/");%>
        </st:else> 
    </body>
</html>