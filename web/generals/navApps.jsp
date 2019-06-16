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
        margin: auto auto auto auto;
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
  
</style>

  <nav class="navbar navbar-inverse apps">
  <div class="container-fluid cfl">
    <form class="navbar-form navbar-right pers " action="/action_page.php">
      <div class="input-group">
        <div class="input-group-btn ">
          <button class="btn btn-default btn-apps" type="submit">
            <i class="glyphicon glyphicon glyphicon-user"></i>
          </button>
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
     <form class="navbar-form navbar-left nrf2" action="/action_page.php">
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
