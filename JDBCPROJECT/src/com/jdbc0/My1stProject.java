package com.jdbc0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class My1stProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dao d = new Dao();
		// d.creating();
//		d.insertData(1, "Mr Jarvis", 30000);
//		d.insertData(2, "Mr Nishant", 70000);
//		d.insertData(3, "Miss Ritu", 50000);
//		d.insertData(4, "Jr Aadvik", 0);
		// d.insertData(5, "Goku", 100000);
		// d.updateData(4, 15000);
		//d.deleteData(5);
		d.displayData();
	}

}

class Dao {
	public void creating() {
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC0", "root", "Admin@123");
			s = c.createStatement();
			String create = "CREATE TABLE Employee0(eid int PRIMARY KEY, ename varchar(40), esalary int)";
			s.executeUpdate(create);
			System.out.println("Table and Rows Successfully Created>>>>>>");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to load the Driver!!!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Unable to connect with DataBase / Wrong Query!!!!");
			e.printStackTrace();
		} finally {
			try {
				if (s != null) {
					s.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (Exception e) {
				System.out.println("Something Unusual Happened!!!!");
			}
		}
	}

	public void insertData(int eid, String ename, int esalary) {

		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC0", "root", "Admin@123");
			String insert = "INSERT INTO employee0 values (?, ?, ?)";
			ps = c.prepareStatement(insert);
			ps.setInt(1, eid);
			ps.setString(2, ename);
			ps.setInt(3, esalary);
			ps.executeUpdate();
			System.out.println("Data Inserted Successfully>>>");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Not Loaded Properly!!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Problem with Data-Base/ Query!!");
			e.printStackTrace();
		} finally {
			try {
				if (ps != null && c != null) {
					ps.close();
					c.close();
				}
			} catch (SQLException e) {
				System.out.println("Something unusual happened!!");
				e.printStackTrace();
			}
		}

	}

	public void updateData(int eid, int esalary) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC0", "root", "Admin@123");
			String update = "UPDATE employee0 SET esalary = ? WHERE eid = ?";
			ps = c.prepareStatement(update);
			ps.setInt(1, esalary);
			ps.setInt(2, eid);
			ps.executeUpdate();
			System.out.println("Data updated Successfullu>>>");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Not Loaded Properly!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Problem with Data-Base/Query");
		} finally {
			try {
				if (ps != null && c != null) {
					ps.close();
					c.close();
				}
			} catch (SQLException e) {
				System.out.println("Something unusual happened!!");
				e.printStackTrace();
			}
		}
	}

	public void deleteData(int eid) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC0", "root", "Admin@123");
			String delete = "DELETE FROM employee0 WHERE eid = ?";
			ps = c.prepareStatement(delete);
			ps.setInt(1, eid);
			ps.executeUpdate();
			System.out.println("Query Executed Successfully>>>");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver Not Loaded Properly!!!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something is wrong with Data-Base/Query!!!");
		} finally {
			try {
				if (ps != null && c != null) {
					ps.close();
					c.close();
				}
			} catch (Exception e) {
				System.out.println("Unexpected Error Occured!!!");
			}
		}
	}

	public void displayData() {
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC0", "root", "Admin@123");
			String display = "SELECT * FROM employee0";
			s = c.createStatement();
			ResultSet rs = s.executeQuery(display);
			while(rs.next()) {
				int eid = rs.getInt("eid");
				String ename =  rs.getString("ename");
				int esalary = rs.getInt("esalary");
				System.out.println("Eid   Ename   Esalary");
				System.out.println(eid + " " + ename + " " + esalary);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStacktrace();
		} finally {
			try{
				if(rs != null && s != null && c != null){
					rs.close();
					s.close();
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
