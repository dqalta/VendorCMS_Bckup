<style>
    /*customize picture*/
    .vlogo{
        height:80px;
        width: 150px;
        margin-left: 200px;
        margin-top: 0px;
        padding-top: 0px;
    }
    /*navigation class*/
    .apps{
        background-color: #f6f6f6;
        border: 0;
        min-height: auto;
        box-shadow: none;
        margin-bottom: auto;
    }
    .nextapps{
        background-color: transparent;
        border: 0;
        border-color:transparent;
        min-height: auto;
        box-shadow: none;   
        margin-bottom: auto;

    }
    /*div containers*/
    .cfl{
        min-height: auto;
    }
    .cfsearch{
        min-height: auto;
        display:flex;
        justify-content: center;
        vertical-align: middle;
    }
    /*form classes*/
    .pers{
        margin: 1px 150px 1px auto;
        border: 0;
        border-color:transparent;
    }
    .nrf2 {
        vertical-align: middle;  
        border: 0;
        border-color:transparent;
        margin: 0px auto auto auto;
    }
    /*buttom classes*/
    .btn-apps{
        border-style: none;
        border-color: transparent;
        background-color: transparent;
    }
    /*input form location*/
    .locations{
        vertical-align: middle;  
        margin-top: 30px;
    }
    @media (min-width: 768px){
        .navbar-form .input-group>.form-control {
            width: 400px;
        }}

</style>

<script type="text/javascript">
    function LogOut() {
        $("#ModalLogOut").modal("hide");
        window.location = "/VendorCMS/logOut.vdk";
    }
    function AskLogOut() {
        $("#ModalLogOut").modal();
    }
    $(document).keydown(function (event) {
        if (event.keyCode === 27) {
            $('#ModalLogOut').modal("hide");
        }
    });
</script>

<div class="modal" id="ModalLogOut">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><i class="fa fa-question-circle-o"></i>&nbsp;&nbsp;Vendor CMS</h4>
            </div>
            <div class="modal-body">
                <h4>You really want to close the session?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" style="background-color: #FF6600 !important; " class="btn btn-warning" onclick="LogOut();"><i class="glyphicon glyphicon-log-out"></i>&nbsp;Yes</button>
                <button type="button" class="btn btn-default" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>&nbsp;No</button>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-inverse  apps">
    <div class="container-fluid cfl">
        <form class="navbar-form navbar-right pers " action="#">
            <div class="input-group">
                <span class="navbar-header input-group">Welcome:<st:property value="#attr.userName" /></span>
                <div class="input-group-btn ">
                    <!--          <button class="btn btn-default btn-apps" type="submit">
                                <i class="glyphicon glyphicon glyphicon-user"></i>
                              </button>-->
                    //
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-apps dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="glyphicon glyphicon glyphicon-user"></i> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="dropdown">
                            <li><a href="/VendorCMS/vendorAdmin/admin/users.vdk">Users</a></li>
                            <li><a href="/VendorCMS/vendorAdmin/admin/rols.vdk">Permissions</a></li>
                            <li><a href="#">Profile</a></li>
                            <li role="separator" class="divider"></li>
                            <li> 
                                <button type="button" class="btn btn-default btn-apps" onclick="AskLogOut();">
                                    <i class="glyphicon glyphicon-log-out"></i> Close session
                                </button>
                            </li>
                        </ul>
                    </div>
                    <button class="btn btn-default btn-apps" type="submit">
                        <i class="glyphicon glyphicon glyphicon-bell"></i>
                    </button>
                    <button class="btn btn-default btn-apps" type="submit">
                        <i class="glyphicon glyphicon glyphicon-th"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
   
</nav>
<nav class="navbar navbar-inverse nextapps">
    <div class="container-fluid cfsearch">
        <div class="navbar-header ">
            <img class="img-responsive vlogo" src='/VendorCMS/home/img/logo.png'>
        </div>   
        <form class="navbar-form navbar-left nrf2" action="#">
            <div class="input-group locations">
                <input type="text" class="form-control" placeholder="Search" name="search">
                <div class="input-group-btn">
                    <button class="btn btn-default" type="submit">
                        <i class="glyphicon glyphicon-search"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</nav>
