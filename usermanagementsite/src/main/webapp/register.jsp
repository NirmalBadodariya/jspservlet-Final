<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<%@page import="model.UserBean"%>
<%@page import="model.AddressBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    
    <!-- Title Page-->
    <title>Register</title>
    <!-- Font special for pages-->
	<script src="./jquery-ui.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
    
    
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="./css/register.css" rel="stylesheet" media="all">

	<link href="./css/address.css" rel="stylesheet" media="all">

    <style>
        body{background-color:#F9F9FC;font-family: 'Roboto', sans-serif;font-size:14px;}
        .select2-container {
            padding: 0;
            border: none;
        }
        h3{margin-bottom:0;}
        .card{box-shadow: 0 0 13px 0 rgba(82,63,105,.05);background-color: #fafafa;margin-bottom: 20px;border-radius: 4px;padding: 20px;}
        .select2-choice {
            display: block;
            width: 100%;
            height: calc(1.5em + .75rem + 2px) !important;
            padding: .375rem .75rem !important;
            font-size: 1rem;
            font-weight: 400;
            line-height: 1.5 !important;
            color: #495057;
            background-color: #fff;
            background-clip: padding-box;
            border: 1px solid #ced4da;
            border-radius: .25rem;
            transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
        }
        </style>
	<%-- <c:import url="./header.jsp"/> --%>

</head>

            <c:set var="user" scope="session" value="${requestScope.detailsofUser}"/> 

            
<body>
    <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
        <div class="wrapper wrapper--w790">
            <div class="card card-5">
                <div class="card-heading">
                    <h2 class="title">User Registration form</h2>
                </div>
                
                <h1 style="text-align:center"><c:out value="${errMsg}"/></h1>
                <div class="card-body">
                    <form action="Signup" method="POST" enctype="multipart/form-data" onsubmit="return validateform()">
                        <div class="form-row m-b-55">
                            <div class="name">Name</div>
                            <div class="value">
                                <div class="row row-space">
                                    <div class="col-2">
                                        <div class="input-group-desc d-none">
                                            <input class="input--style-5" type="text" name="id" id="id" value="<c:out value="${user.getId()}"/>">
                                            <label class="label--desc">first name</label>
                                        </div>
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="text" name="firstname" id="firstname" value="<c:out value="${user.getFirstName()}"/>">
                                            <label class="label--desc">first name</label>
                                        </div>
                                    </div>
                                    <c:if test="${firstname != null}">
                                         <c:out value="${firstname}" />
                                    </c:if>
                                    <div class="col-2">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="text" name="lastname"  id="lastname" value="<c:out value="${user.getLastName()}"/>">
                                            <label class="label--desc">last name</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="name">Email</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="email" id="email" name="email" value="<c:out value="${user.getEmail()}"/>">
                                </div>
                            </div>
                        </div>
                        <div class="form-row m-b-55">
                            <div class="name">Phone</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="number" name="phone" id="phone" value="<c:out value="${user.getPhone()}"/>">
                                            <label class="label--desc">Phone Number</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-row p-t-20">
                            <div class="name">Gender</div>
                            Male:<input type="radio" name="gender"  value="M" id="Gmale">
                           Female:<input type="radio" name="gender" value="F" id="Gfemale">
                        </div>
                        <div class="form-row m-b-55">
                            <div class="name">DOB</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="date" name="dob" id="dob" value="<c:out value="${user.getDob()}"/>">
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                                
                        <div class="form-row m-b-55">
                            <div class="name">Password</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="password" name="pass" id="pass" value="<c:out value="${user.getPass()}"/>">
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                                            
                        <div class="form-row m-b-55">
                            <div class="name">Profile image</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input type="file" id="img" name="image" accept="image/*">
                                            <img src="data:image/jpg;base64,${user.getBase64Image()}" width="240" height="300"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-row m-b-55">
                            <div class="name">Security question</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <p>What was your first school name?</p>
                                            <input class="input--style-5" type="text" name="SecurityAns" id="securityans" value="<c:out value="${user.getSecurityAns()}"/>">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

						<div class="container" style="margin:150px auto">
    <div class="margin-t-md">
        <div class="customer-form">
            
            <%-- <c:set var="addresses" scope="session" value="${requestScope.addresses}"/>  --%>

            
            <form action="Signup" method="post" role="form" autocomplete="off" >

                
                <div id="main-container">
                    
                    <div class="panel card container-item">

                        <%-- value="<c:out value="${addresses.get(0).getAddressLine2()}"/>" --%>
                        <div class="panel-body">
                            <div class="panel-body">

                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group d-none">
                                            <label class="control-label" for="address_id_0">Address line 1</label>
                                            <input type="text" id="address_id_0" class="form-control" name="Address[0][address_id]">
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="address_line_one_0">Address line 1</label>
                                            <input type="text" id="address_line_one_0" class="form-control" name="Address[0][address_line1]" maxlength="255" >
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label class="control-label" for="address_line_two_0">Address line 2</label>
                                            <input type="text" id="address_line_two_0" class="form-control" name="Address[0][address_line2]" maxlength="255" >
                                            <p class="help-block help-block-error"></p>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="city_0">city</label>
                                            <select id="city_0" class="form-control" name="Address[0][city]">
												<option value="Ahmedabad">Ahmedabad</option>
												<option value="Delhi">Delhi</option>
												<option value="Mumbai">Mumbai</option>
												<option value="Jaipur">Jaipur</option>
												<option value="banglore">banglore</option>
												
										</select>

                                        </div>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="state_0">State</label>
                                            <select id="state_0" class="form-control" name="Address[0][state]">
												<option value="gujrat">Gujrat</option>
												<option value="MP">MP</option>
												<option value="maharastra">maharastra</option>
												<option value="Delhi">Delhi</option>
												<option value="Rajasthan">Rajasthan</option>
												
										</select>
                                                             
                                        </div>
                                    </div>
                                                        
                                </div>
                                <div class="col-sm-6">
                                        <div class="form-group">
                                        <input class="form-control" id="pincode_0" type="number" name="Address[0][pincode]" placeholder="pincode" >
                                        </div>
                                        </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div>
                                            <a href="javascript:void(0)" class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="card">
                    <div>
                       <div > <a type="button" class="btn btn-success btn-sm" id="add-more" onclick="return checkAddress()"   role="button"><i class="fa fa-plus"></i> Add more address</a></div>
                       <div id="add-more-hidden" class="d-none"> <div type="button" class="btn btn-success btn-sm" ><i class="fa fa-plus"></i> Add more address</div></div>
                        <input type="submit" class="btn btn-primary btn-sm" value="Submit">
                    </div>
                </div>
            </form>
            
        </div>
    </div>
</div>
					<br><br>
				</th> 
                    </form>
                    
                </div>
            </div>
        </div>
    </div>  
	
    

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.js"></script>
<script src="https://cdn.ckeditor.com/4.5.1/standard/ckeditor.js"></script>
<script src="./js/cloneData.js" type="text/javascript"></script>
                <script>var i =0;
                    // document.getElementById("id").value = "<c:out value="${user.getId()}"/>";

                </script>
                <c:forEach items="${requestScope.addresses}" var="address">
                    <script>

                jQuery(function(){ 
                            
                        <c:if test="{address.getGender()== M }" >
                        document.getElementById("Gmale").checked = true;
                        </c:if>
                        <c:if test="{address.getGender()== F }" >
                          document.getElementById("Gfemale").checked = true;

                        </c:if>
                            // document.getElementById("email").disabled = true;
                            if(i+1<${fn:length(addresses)}){
                                 jQuery('#add-more').trigger('click');
                            } 
                            
                            document.getElementById("address_id_"+i).value = "<c:out value="${address.getAddressId()}"/>";
                            document.getElementById("address_line_one_"+i).value = "<c:out value="${address.getAddressLine1()}"/>";
                            document.getElementById("address_line_two_"+i).value = "<c:out value="${address.getAddressLine2()}"/>";
                            document.getElementById("city_"+i).value = "<c:out value="${address.getCity()}"/>";
                            document.getElementById("state_"+i).value = "<c:out value="${address.getState()}"/>";
                            document.getElementById("pincode_"+i).value = "<c:out value="${address.getPincode()}"/>";
                       
                         i++;
                    });
                    </script>
                </c:forEach>
                <%--  --%>
                <script>    
                        
                    </script>
<script>


    $('a#add-more').cloneData({
        mainContainerId: 'main-container', // Main container Should be ID
        cloneContainer: 'container-item', // Which you want to clone
        removeButtonClass: 'remove-item', // Remove button for remove cloned HTML
        removeConfirm: true, // default true confirm before delete clone item
        removeConfirmMessage: 'Are you sure want to delete?', // confirm delete message
        //append: '<a href="javascript:void(0)" class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>', // Set extra HTML append to clone HTML
        minLimit: 1, // Default 1 set minimum clone HTML required
        maxLimit: 0, // Default unlimited or set maximum limit of clone HTML
        defaultRender: 1,
        init: function () {
            console.info(':: Initialize Plugin ::');
        },
        beforeRender: function () {
            console.info(':: Before rendered callback called');
        },
        afterRender: function () {
            console.info(':: After rendered callback called');
            //$(".selectpicker").selectpicker('refresh');
        },
        afterRemove: function () {
            console.warn(':: After remove callback called');
        },
        beforeRemove: function () {
            console.warn(':: Before remove callback called');
        }

    });
    
    /*$('.select2').select2({
        placeholder: 'Select a month'
    });*/
    
    $(document).ready(function () {
        $('#_startDatePicker').datepicker({minDate: 0, maxDate: '1y'});
    });

    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
    /*CKEDITOR.editorConfig = function (config) {
        config.language = 'es';
        config.uiColor = '#F7B42C';
        config.height = 300;
        config.toolbarCanCollapse = true;

    };*/
    //CKEDITOR.replace('editor1');

</script>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<%-- <script>
try {
  fetch(new Request("https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js", { method: 'HEAD', mode: 'no-cors' })).then(function(response) {
    return true;
  }).catch(function(e) {
    var carbonScript = document.createElement("script");
    carbonScript.src = "//cdn.carbonads.com/carbon.js?serve=CK7DKKQU&placement=wwwjqueryscriptnet";
    carbonScript.id = "_carbonads_js";
    document.getElementById("carbon-block").appendChild(carbonScript);
  });
} catch (error) {
  console.log(error);
}
</script> --%>
<%-- Validating form with the function --%>
<script>
		function validateform() {
			
			// var fname = $("#firstname").val();
            var lname = $("#lastname").val();
            var email = $("#email").val();
            var phone = $("#phone").val();
            
            var dob = $("#dob").val();
            var pass = $("#pass").val();
            var securityans = $("#securityans").val();
            var genderselect = $("input[type = 'radio']:checked"); 
            var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            var passcheck = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
            
            // var addressLineOne = $("#address_line_one_0").val();
            // var addressLineTwo = $("#address_line_two_0").val();
            // var pincode = $("#pincode_0").val();


			if ( lname != "" && email != "" && phone !="" && genderselect.length > 0 && dob != "" && pass != "" && securityans != "") {
                if ( lname.length>10) {
                    alert("Too long name");
                    return false;
                }
                else if(!filter.test(email)){
                    alert("Not a valid email");
                    return false;
                }
                else if(phone.length!=10){
                    alert("phone number must have 10 numbers");
                    return false;
                }
                else if(!passcheck.test(pass)){
                    alert("Password must contain at least 1 capital letter,\n\n1 small letter, 1 number and 6 letters");
                    return false;
                }
                  
                // else if(addressLineOne == "" || addressLineTwo == "" || pincode == ""){
                //     alert("no address filed should be empty");
                //     return false;
                // }
                else{
                    
                    return true;
                }

			} else {
				alert("No Fields should be empty");
				return false;
			}
            
		}
        var count =1;
	</script>
    <script>

            
            $('body').on('click', '#add-more', function (e) {
                 count++;
                    
            });

    // for(var j=0;j<count;j++){
    // $(document).on('keyup', '#address_line_one_'+j, checkAddress());
    // }
    function checkAddress() {
        var temp=0;
        // alert(count);
        for(var i = 0;i<count;i++){
            
            var addressLineOne = $("#address_line_one_"+i).val();
            var addressLineTwo = $("#address_line_two_"+i).val();
            var addressId  =$("#address_id_"+i).val();     
            var pincode = $("#pincode_"+i).val();
            // if(addressId==""){
            //     alert("wasd);
            // }
             while(addressLineOne=="" || addressLineTwo=="" || pincode==""){

                //  alert(document.getElementById("add-more").innerHTML);
                        document.getElementById('add-more-hidden').classList.remove('d-none');
                        document.getElementById('add-more').classList.add('d-none');
                        
                //      $("#add-more").click(function(){return false;});
                    //  document.getElementById("add-more").disabled=true;
                    temp++;
                        alert("no address field should be empty");
                        return false;
             }
                     
        }
        if(temp==0){

            return true;
        }
        
        
        
    }
    </script>
    <%-- <footer>
	<jsp:include page="footer.jsp" />
	</footer> --%>
</body>

</html>


