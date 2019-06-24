/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var accion, permiso, mensaje, mensajes;
$(document).ready(function () {
    

///
     accion = parseInt($("#accion").val());
    permiso = $("#permiso").val();
    mensaje = $("#mensaje").val();
    mensajes = $("#mensajes").val();
    if (permiso === "true") {
        scroll();
        dataTable("table_codes");
          $("#province").chosen({width: "100%"});
          $("#city").chosen({width: "100%"});
          $("#postalCode").chosen({width: "100%"});
    }
    if (mensaje === "true") {
        mostrarNotificaciones();
    }
    $('#form-panel').collapse();
  //  chargeChecks();
   // chargeCheckBoxes();
});
function chargeCities() {
    var province = $("#province").val();
    if (province === null) {
        province = "";
    }

    $.ajax({
        tradional: true,
        type: "post",
        datatype: "html",
        data:
                {
                    "province": province
                },
        url: "/VendorCMS/vendorAdmin/queries/ajax/select-cities.vdk",
        success: function (html)
        {
            $("#city").html(html).trigger("chosen:updated");
        },
        error: function (estado)
        {
            //   alert(estado);
        }
    });
}
//function chargePostalCodes() {
//    var city = $("#city").val();
//    if (city === null) {
//        city = "";
//    }
//    $.ajax({
//        tradional: true,
//        type: "post",
//        datatype: "html",
//        data:
//                {
//                    "city": city
//                },
//        url: "/VendorCMS/vendorAdmin/shipping.vdk",
//        success: function (html)
//        {
//            $("#postalCode").html(html).trigger("chosen:updated");
//        },
//        error: function (estado)
//        {
//            //   alert(estado);
//        }
//    });
//}
/*
function chargeCheckBoxes() {
    var arr = ["active"];
    for (var i = 0; i < arr.length; i++) {
        if ($("#" + arr[i]).val() === "true") {
            $("#check_" + arr[i]).lcs_on();
        } else {
            $("#check_" + arr[i]).lcs_off();
        }
    }
}

function chargeChecks() {
    $(".lcc").lc_switch('Yes', 'No');
    $('body').delegate('.lcc', 'lcs-statuschange', function () {
        var status = ($(this).is(':checked')) ? true : false;
        var id = $(this).attr("id");
        $("#" + id.replace("check_", "")).val(status);
    });
}

function cancel() {
    $("#ModalProcesando").modal({backdrop: 'static', keyboard: false});
    window.location = "/MasonryCMS/masonryAdmin/maintenance/manufacturer.mdk";
}



function edit(id) {
    $("#ModalProcesando").modal({backdrop: 'static', keyboard: false});
    $("#idEdit").val(id);
    $("#accion").val(2);
    $("#formulario").submit();
}
*/
//saves the data 
function save() {
  
    $("#ModalProcesando").modal({backdrop: 'static', keyboard: false});
    $("#accion").val(1);
    $("#formulario").submit();
}
function dataTable(id) {
    $("#" + id).DataTable({
        "sPaginationType": "full_numbers",
        "iDisplayLength": 25,
        "bAutoWidth": true,
        "bProcessing": true,
        "bDeferRender": true,
        "lengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todo"]],
        "oLanguage":
                {
                    "sProcessing": "Cargando...",
                    "sLengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
                    "sZeroRecords": "No se encontr&oacute; ning&uacute;n registro",
                    "sInfo": "Mostrando de _START_ a _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando 0 registros",
                    "sInfoFiltered": "(Filtrado de _MAX_ total registros)",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar",
                    "oPaginate":
                            {
                                "sFirst": "Primero",
                                "sPrevious": "Anterior",
                                "sNext": "Siguiente",
                                "sLast": "Final"
                            }
                },
        "sScrollX": "100%",
        "scrollCollapse": true
    });

        //filtering

$('#table_codes').dataTable().yadcf([
{column_number : 0},
]);
//{column_number : 2, data: ["Yes", "No"], filter_default_label: "Select Yes/No"},
//{column_number : 3, text_data_delimiter: ",", enable_auto_complete: true},
//{column_number : 4, column_data_type: "html", html_data_type: "text", filter_default_label: "Select tag"}]);

}
 //column_data_type: "html", html_data_type: "text", filter_default_label: "Select City"