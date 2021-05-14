/**
 * 
 */
$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
 {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateFundForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
		{
			url: "FunderAPI",
			type: type,
			data: $("#formFund").serialize(),
			dataType: "text",
			complete: function(response, status) {
				
				onFundSaveComplete(response.responseText,status);
			}
		});
});

function onFundSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divFundGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidFundIDSave").val("");  
	$("#formFund")[0].reset(); 
} 


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
 {
	$("#hidFundIDSave").val($(this).closest("tr").find('#hidFundIDUpdate').val());
	$("#funderName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#category").val($(this).closest("tr").find('td:eq(1)').text());
	$("#description").val($(this).closest("tr").find('td:eq(2)').text());
	$("#fundingAmount").val($(this).closest("tr").find('td:eq(3)').text());
	$("#fundStartDate").val($(this).closest("tr").find('td:eq(4)').text());
	$("#fundEndDate").val($(this).closest("tr").find('td:eq(5)').text());
});

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "FunderAPI",   
		type : "DELETE",   
		data : "funderID=" + $(this).data("fundid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onFundDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onFundDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divFundGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

// CLIENT-MODEL================================================================
function validateFundForm() {
	// CODE
	if ($("#funderName").val().trim() == "") {
		return "Insert Fund name.";
	}
	// NAME
	if ($("#category").val().trim() == "") {
		return "Insert Fund category";
	}
	// Discription
	if ($("#description").val().trim() == "") {
		return "Insert Fund description";
	}
	// PRICE-------------------------------
	if ($("#fundingAmount").val().trim() == "") {
		return "Insert Fund amount.";
	}
	// is numerical value
	var tmpPrice = $("#fundingAmount").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for fund amount.";
	}
	// convert to decimal price
	$("#fundingAmount").val(parseFloat(tmpPrice).toFixed(2));

	//funder amount start data
	if($("#fundStartDate").val().trim() == ""){
		return "Insert fund amount start date.";
	}
		
	//funder amount end data
	if($("#fundEndDate").val().trim() == ""){
		return "Insert fund amount End date.";
	} 
		
	return true;
}
