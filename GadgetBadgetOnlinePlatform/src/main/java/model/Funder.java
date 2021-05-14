package model;

import java.sql.Statement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class Funder {

	public Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetonlineplatform", "root", "");
		
			
			//For testing
			System.out.print("Successfully connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertFund(String name, String category, String desc, String amount, String startDate, String endDate)
	{
		
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into funders(funderID,funderName,category,description,fundingAmount,fundStartDate,fundEndDate) values (?, ?, ?, ?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, category);
			preparedStmt.setString(4, desc);
			preparedStmt.setDouble(5, Double.parseDouble(amount));
			preparedStmt.setDate(6, Date.valueOf(startDate));
			preparedStmt.setDate(7, Date.valueOf(endDate));
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newFunds = readFunddata();
			output = "{\"status\":\"success\", \"data\":\"" +newFunds +"\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting funds.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readFunddata()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
		
			output = "<table border='1' class='table table-striped table-hover'>"
					+ "<tr><th>Funder Name</th><th>Category</th><th>Description</th>"
					+ "<th>Funding Amount</th><th>Funding StartDate</th><th>Funding EndDate</th><th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from funders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String funderID = Integer.toString(rs.getInt("funderID"));
				String funderName = rs.getString("funderName");
				String category = rs.getString("category");
				String description = rs.getString("description");
				String fundingAmount = Double.toString(rs.getDouble("fundingAmount"));
				String fundingStartdate = rs.getString("fundStartDate");
				String fundingEnddate = rs.getString("fundEndDate");
				
				
				
				// Add a row into the html table
				output += "<tr><td><input id='hidFundIDUpdate' name='hidFundIDUpdate' type='hidden' value='"+ funderID+ "'>" + funderName + "</td>";	
				output += "<td>" + category + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + fundingAmount + "</td>";
				output += "<td>" + fundingStartdate + "</td>";
				output += "<td>" + fundingEnddate + "</td>";
				
				// button
				
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-fundid='" + funderID + "'>" + "</td></tr>"; 
			}
			con.close();
			
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deletefundData(String funderID)
	{
		String output = "";
		
		try
		{
			//System.out.println(funderID);
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
				
			// create a prepared statement
			String query = "delete from funders where funderID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(funderID));
			
			// execute the statement
			preparedStmt.execute();
			
			con.close();
			
			//System.out.println(preparedStmt);
			
			String newFunds = readFunddata();
			output = "{\"status\":\"success\", \"data\":\"" +newFunds +"\"}";
		}
		
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting funds.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String Updatefund(String id,String name, String category, String desc, String amount, String fundingstartdate, String fundinendate)
	{
		
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " update funders set funderName=?,category=? ,description=?,fundingAmount=?, fundStartDate=?,fundEndDate=? where funderID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
						
			// binding values			
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, category);
			preparedStmt.setString(3, desc);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setDate(5, Date.valueOf(fundingstartdate));
			preparedStmt.setDate(6, Date.valueOf(fundinendate));
			preparedStmt.setInt(7, Integer.parseInt(id));
			
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			String newFunds = readFunddata();
			output = "{\"status\":\"success\", \"data\":\"" +newFunds +"\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating funds.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
