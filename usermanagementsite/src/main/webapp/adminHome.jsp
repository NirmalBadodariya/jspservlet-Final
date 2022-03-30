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
                
//                 $.each(data, function(i, item) {
//     $('<tr>').html("<td><button>EDIT</button></td>").appendTo('#showData');
// });
                
                $("#showData").DataTable({
                    data : data,
                    columns: [
                        { data: 'id'},
                        { data: 'firstName'},
                        { data: 'email'},
                        { data: null, title: 'Action', wrap: true, "render": function (item) { return '<div class="btn-group"> <button type="button"  onclick="deleteUr()" value="0" class="btn btn-info" data-toggle="modal" data-target="#myModal">EDIT</button></div>' } },
                        { data: null, title: 'DELETE', wrap: true, "render": function (item) { return '<div class="btn-group"> <button type="button"  onclick="DeleteUser(' + item.id + ')"  class="btn btn-danger">DELETE</button></div>' } },
                    ],

                    // columnDefs: [
                    //     {
                    //         "defaultContent": "-",
                    //         "targets": "_all",
                    //     },
                    // ],
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
			});

		}
</script>

</head>
<body>
   <button id="showUserDetails" class="btn btn-info">showUserDetails</button>
    <div id="tablediv">
<table cellspacing="0" id="showData"> 
    <thead> 
    <tr>
        <th style="text-align:start; width:33.3% !important;">Name</th> 
        <th style="text-align:start; width:33.3% !important;">Id</th> 
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