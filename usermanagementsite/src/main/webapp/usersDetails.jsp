<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%> 
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>  
        <sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
         url = "jdbc:mysql://localhost:3306/users_management"
         user = "root"  password = "nirmal15"/>
        
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from users;
      </sql:query>


   <table id="example"  class="display" style="width:100%">
    <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var = "row" items = "${result.rows}">
            <tr>
                <td><c:out value = "${row.id}"/></td>
                <td><c:out value = "${row.firstname}"/></td>
                <td><c:out value = "${row.email}"/></td>
                <td><button class="btn btn-success">Edit</button></td>
                <td><button class="btn btn-danger">Delete</button></td>
                    

            </tr>
        </c:forEach>
        </tbody>
   </table>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
       <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
            
   <script>
   $(document).ready( function () {
    $('#example').DataTable();
} );
   </script>
</body>
</html>