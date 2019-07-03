/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var accion, permiso, mensaje, mensajes;
$(document).ready(function () {
    accion = parseInt($("#accion").val());
    permiso = $("#permiso").val();
    mensaje = $("#mensaje").val();
    mensajes = $("#mensajes").val();
    scroll();
    if (mensaje === "true") {
        mostrarNotificaciones();
        
    }
    if (accion === 5) {
       window.location = "/VendorCMS/requestPending.jsp";
    }
});

if (permiso === "true") {
    scroll();

    $("#city").chosen({width: "100%"});

}

function saveRequest() {
    $("#ModalProcesando").modal({backdrop: 'static', keyboard: false});
    $("#accion").val(1);
    $("#frm").submit();
}

//function enableEmail2() {
//    document.getElementById("email2").readOnly = false;
//
//}
//function validateEmail() {
//    var t1, t2;
//    t1 = document.getElementById("email").value;
//    t2 = document.getElementById("email2").value;
//       if (t1 !== t2){
//           //          
//
//           mensajes = mensajes + "danger<>Error<>'The email address are not the same' .|";
//           mostrarNotificaciones();
//           document.getElementById("email").focus();
//       }
//}

