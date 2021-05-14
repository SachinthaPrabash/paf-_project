<%@ page import="model.Funder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Funder</title>
<script src="Components/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="views/bootstrap.min.css">
<meta name="viewport" content="width-device-width, initial-scale=1">
<script src="Components/FundJS.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Funding Management</h1>
				<form id="formFund" name="formFund" method="post" action="Fund.jsp"  >
				
					Funder name: <input id="funderName" name="funderName" type="text"class="form-control"><br>
					
					Category: <input id="category" name="category" type="text" class="form-control"><br>
					
					Description: <input id="description" name="description" type="text" class="form-control"><br>
					
					Amount: <input id="fundingAmount" name="fundingAmount" type="text" class="form-control"><br>
					
					Funding Start date: <input id="fundStartDate" name="fundStartDate" type="text" class="form-control"><br>
					
					Funding End date: <input id="fundEndDate" name="fundEndDate" type="text" class="form-control"><br>
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
					
					<input type="hidden" id="hidFundIDSave" name="hidFundIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				
				<div id="alertError" class="alert alert-danger"></div>
								
				<br><br>
				
				<div id="divFundGrid">
					<%		
						Funder fundObj = new Funder();
						out.print(fundObj.readFunddata());
					%>
				</div>
						
			</div>
		</div>
	</div>

</body>
</html>