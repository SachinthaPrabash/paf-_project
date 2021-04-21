package model;

import java.sql.Statement;
import java.text.DateFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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
	
	public String insertItem(String name, String category, String desc, String amount, String startDate, String endDate)
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
			String query = " insert into funders(funderID,funderName,category,description,fundingAmount,fundStartDate,fundEndDate) values (?, ?, ?, ?, ?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, category);
			preparedStmt.setString(4, desc);
			preparedStmt.setDouble(5, Double.parseDouble(amount));
			preparedStmt.setString(6, startDate);
			preparedStmt.setString(7, endDate);
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readItems()
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
				output += "<tr><td>" + funderName + "</td>";
				output += "<td>" + category + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + fundingAmount + "</td>";
				output += "<td>" + fundingStartdate + "</td>";
				output += "<td>" + fundingEnddate + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger' >"
						+ "<input name='itemID' type='hidden' value='" + funderID + "'>"
						+ "</form></td></tr>";
			}
		}
		catch (Exception e)
		{
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteItem(String funderID)
	{
		String output = "";
		
		try
		{
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
			
			output = "Deleted successfully";
		}
		
		catch (Exception e)
		{
			output = "Error while deleting the funder.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String UpdateItem(String id,String name, String category, String desc, String amount, String fundingstartdate, String fundinendate)
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
			preparedStmt.setDate(5,Date.valueOf(fundingstartdate));;
			preparedStmt.setDate(6,Date.valueOf(fundinendate));
			preparedStmt.setInt(7, Integer.parseInt(id));
			
			//execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Update successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
