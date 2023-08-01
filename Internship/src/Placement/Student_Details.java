package Placement;
import java.sql.*;
import java.util.Scanner;
class Student_Details {
	int RollNo=0;
	int course_Id=0;
	
	 public static void main(String args[]) throws ClassNotFoundException {
	        Connection con = null;
	        PreparedStatement p = null;
	        ResultSet rs = null;
	        Student_Details obj=new Student_Details();
	        Scanner f=new Scanner(System.in);
	        try {
	   		 Class.forName("com.mysql.jdbc.Driver");
	   		 Connection in=DriverManager.getConnection( "jdbc:mysql://localhost:3306/intern",
	   	                "root", "Ha@260204");
	   		 
	   	 }
	   	 catch(SQLException e) {
	   		 System.out.println(e);
	   	 }
	        try {
	            con = connectDB(); // Call the connectDB method to establish the connection
	            String sql = "select * from student";
	            p = con.prepareStatement(sql);
	            rs = p.executeQuery();

	            // Printing ID, name, email, and role of companies
	            System.out.println("RollNo\t\tcourse_Id\t\tphone_No\t\tcgpa\t\tName");

	            // Loop through the result set
	            while (rs.next()) {
	                obj.RollNo = rs.getInt("RollNo");
	                obj.course_Id = rs.getInt("course_Id");
	                long phone_No = rs.getLong("phone_No");
	                double cgpa = rs.getDouble("cgpa");
	                String Name=rs.getString("Name");
	                System.out.println(obj.RollNo + "\t\t" + obj.course_Id + "\t\t\t" + phone_No + "\t\t" + cgpa+"\t\t"+Name);
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        } finally {
	            // Close the resources in the reverse order of their creation
	            try {
	                if (rs != null)
	                    rs.close();
	                if (p != null)
	                    p.close();
	                if (con != null)
	                    con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        System.out.println("Dear User,");
	        System.out.println("Kindly enter the details of your company details to put it into database");
	        System.out.println("Enter your Company_name:");
	        String Company_name=f.nextLine();
	        System.out.println("Enter your email:");
	        String email=f.nextLine();
	        System.out.println("Enter your role:");
	        String role=f.nextLine();
	        System.out.println("Enter your CompanyId:");
	        int CompanyId=f.nextInt();
	        System.out.println("Enter your package:");
	        int package1=f.nextInt();
	        
	        
	        addCandidate(CompanyId,Company_name,email,role,package1);
	 }

	    public static Connection connectDB() throws ClassNotFoundException, SQLException {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern", "root", "Ha@260204");
	        return con;
	    }
	    public static void addCandidate(int CompanyId, String Company_name,String  email, String role,int  package1) {
	        String sql = "INSERT INTO company (CompanyId,Company_name, email,role, package1) VALUES (?, ?, ?, ?, ?)";
	        try {
	        	Connection in=DriverManager.getConnection( "jdbc:mysql://localhost:3306/intern",
	   	                "root", "Ha@260204");
				PreparedStatement pstmt = in.prepareStatement(sql);
	            pstmt.setInt(1,CompanyId);
	            pstmt.setString(2, Company_name);
	            pstmt.setString(3, email);
	            pstmt.setString(4, role);
	            pstmt.setInt(5, package1);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }    
	 

}
