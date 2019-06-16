<%-- 
    Document   : intro
    Created on : 15/06/2019, 12:34:42 PM
    Author     : developer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <%@ taglib uri="/struts-tags" prefix="st"%>
        <%@ taglib uri="/struts-bootstrap-tags" prefix="stb"%>
        <title>JSP Page</title>
    </head>
          <st:form id="frm" name="frm" action="iniciar" method="post" cssClass="form-horizontal">
                <div style="text-align: center; color:#666666;">  
                    <div> <br></div>
                    <h3 style="color:white">Vendor CMS - Masonry Supply<br>C.M.S</h3>                
                </div>
                <br>


                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <st:textfield class="input" cssClass="form-control" name="usuario" id="usuario" placeholder="Ingrese el usuario..."/>
                </div>

                <br>

                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <st:password  class="input" cssClass="form-control" placeholder="Contraseña..." id="contrasena" name="contrasena" />
                </div>

                <br>
                <br>
                <st:submit value="Login"/>
                <div style="text-align: left; color:#e6e6e6;">
                    <st:fielderror />
                    <st:actionerror />
                    <st:actionmessage />            
                </div>
            </st:form> 
</html>
