<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
       <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
<script type="text/javascript">
$(document).ready(function() {
 $("#tablediv").hide();
     $("#showUserDetails").click(function(event){
           $.post('UsersDetails',function(data) {
            if(data!=null){
                $("#showData").find("tr:gt(0)").remove();
                
                $("#showData").DataTable({
                    data : data,
                    columns: [
                        { data: 'id'},
                        { data: 'firstName'},
                        { data: 'email'},
                        { data: null, title: 'Action', wrap: true, "render": function (item) { return '<div class="btn-group"> <button type="button"  onclick="EditUserDetails(\'' + item.email + '\')" class="btn btn-info" >EDIT</button></div> ' } },
                        { data: null, title: 'DELETE', wrap: true, "render": function (item) { return '<div class="btn-group"> <button type="button"  onclick="DeleteUser(' + item.id + ')"  class="btn btn-danger">DELETE</button></div>' } },
                    ],

                });
            }
            else{
                alert("Data is coming null");
            }
            });

            $("#tablediv").show();          
  });      
            
});
function DeleteUser(userId) {
            
			$.post("DeleteUser", {
				"userId" : userId
			}, function() {
				window.location.reload();
                ('#showUserDetails').click();
			}
);              
     };     


function EditUserDetails(email) {
    alert("email:"+ email);
    // console.log("firstname"+ email);
    $.post("sessionStore",{"email": email},function() {
        window.location.href = "EditDetails"
    });
        
}  
 
            
</script>

</head>
<body>
   <button id="AdminProfile" class="btn btn-info"><a href="EditDetails">Profile</a></button>
   <button id="showUserDetails" class="btn btn-info">showUserDetails</button>
   <button id="addUser" class="btn btn-info"><a href="sessionStore" style="text-decoration:none">AddNewUser</a></button>
   <button id="Logout" class="btn btn-danger"><a href="index.jsp" style="text-decoration:none">Logout</a></button>
   
    <div id="tablediv">
<table cellspacing="0" id="showData"> 
    <thead> 
    <tr>
        <th style="text-align:start; width:33.3% !important;">ID</th> 
        <th style="text-align:start; width:33.3% !important;">Name</th> 
        <th style="text-align:start; width:33.3% !important;">Email</th> 
        <th style="text-align:start; width:33.3% !important;">EDIT</th> 
        <th style="text-align:start; width:33.3% !important;">DELETE</th> 
            
           </tr>    
    </thead>
    <tbody>
        
    </tbody> 
</table>
</div>


</body>
</html>
