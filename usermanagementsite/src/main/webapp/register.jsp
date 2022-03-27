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
    <script type = "text/javascript"src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"> </script>
	<script src="./jquery-ui.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
    
    <!-- <link href="./jquery-ui.min.css" rel="stylesheet" media="all"> -->
    <!-- Main CSS-->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="./css/register.css" rel="stylesheet" media="all">
	<!-- <link href="./normalize.css" rel="stylesheet" media="all"> -->
	<!-- <link href="./pygment_trac.css" rel="stylesheet" media="all"> -->
	<link href="./css/address.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-gra-03 p-t-45 p-b-50">
        <div class="wrapper wrapper--w790">
            <div class="card card-5">
                <div class="card-heading">
                    <h2 class="title">User Registration form</h2>
                </div>
                <div class="card-body">
                    <form action="Signup" method="POST" enctype="multipart/form-data">
                        <div class="form-row m-b-55">
                            <div class="name">Name</div>
                            <div class="value">
                                <div class="row row-space">
                                    <div class="col-2">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="text" name="firstname">
                                            <label class="label--desc">first name</label>
                                        </div>
                                    </div>
                                    <div class="col-2">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="text" name="lastname">
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
                                    <input class="input--style-5" type="email" name="email">
                                </div>
                            </div>
                        </div>
                        <div class="form-row m-b-55">
                            <div class="name">Phone</div>
                            <div class="value">
                                <div class="row row-refine">
                                    
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="number" name="phone">
                                            <label class="label--desc">Phone Number</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-row p-t-20">
                            <div class="name">Gender</div>
                            Male:<input type="radio" name="gender" value="M">
                           Female:<input type="radio" name="gender" value="F">
                        </div>
                        <div class="form-row m-b-55">
                            <div class="name">DOB</div>
                            <div class="value">
                                <div class="row row-refine">
                                    <div class="col-9">
                                        <div class="input-group-desc">
                                            <input class="input--style-5" type="date" name="dob">
                                            
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
                                            <input class="input--style-5" type="password" name="pass">
                                            
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
                                            <input class="input--style-5" type="text" name="SecurityAns">

                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
						<!-- multiple address fields -->
						<div class="name">Addresses</div>
						<div class="repeat">


							<table class="wrapper" width="50%">
								<thead>
									<tr>
										<td  width="10%"><span class="add btn btn-success">Add</span></td>
									</tr>
								</thead>
								<tbody class="container">
								<tr  class="template row">
									
									<td>
										<dt>Address Line 1</dt>
										<dd><input type="text" name="ALine1" class="form-control" placeholder="house no , Apartment"/></dd><br>
										<dt>Address Line 2</dt>
										<dd><input type="text" name="ALine2" class="form-control" placeholder="Street , Nearby Landmark" /></dd><br>
										<dt>City</dt>
										<dd>
											<select name="city" id="cityselect">
												<option>Ahmedabad</option>
												<option>Delhi</option>
												<option>mumbai</option>
												<option>Chennai</option>
												<option>jaipur</option>
												
										</select></dd><br>
										<dt>Pincode</dt>
										<dd><input type="number" name="pin" class="form-control" placeholder="pincode" /></dd><br>
										<dt>State</dt>
										<dd>    
											<select name="state" id="stateselect">
												<option>Gujrat</option>
												<option>Delhi</option>
												<option>Maharastra</option>
												<option>Rajasthan</option>
												<option>Bihar</option>
												<option>Goa</option>
                                                    
										</select></dd><br>
										
									</td>
									
									<td><button class="remove btn btn-danger">Remove</button></td>
								</tr>
								</tbody>
							</table> 
                            
					</div>
					<br><br>
				</th> 
                        <div>
                            <button class="btn btn--radius-2 btn--red" type="submit">Register</button>
                        </div>
                    </form>
                    
					
				
                </div>
            </div>
        </div>
    </div>
	

    

<script src="./js/main.js"></script>
	<script>
		jQuery(function() {
	jQuery('.repeat').each(function() {
		jQuery(this).repeatable_fields();
	});
});
</script>
	

    
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>


