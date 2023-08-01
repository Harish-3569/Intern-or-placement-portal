package Placement;

import java.sql.*;
import java.util.Scanner;
class Student {
    protected int RollNo;
    protected int course_Id;
    protected long phone_No;
    protected double cgpa;
    protected String Name;

    public Student(int RollNo, int course_Id, long phone_No, double cgpa, String Name) {
        this.RollNo = RollNo;
        this.course_Id = course_Id;
        this.phone_No = phone_No;
        this.cgpa = cgpa;
        this.Name = Name;
    }

	public int getRollNo() {
		return RollNo;
	}

	public void setRollNo(int rollNo) {
		RollNo = rollNo;
	}

	public int getCourse_Id() {
		return course_Id;
	}

	public void setCourse_Id(int course_Id) {
		this.course_Id = course_Id;
	}

	public long getPhone_No() {
		return phone_No;
	}

	public void setPhone_No(long phone_No) {
		this.phone_No = phone_No;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

   
}
class Intern extends Student{
    public Intern(int RollNo, int course_Id, long phone_No, double cgpa, String Name) {
		super(RollNo, course_Id, phone_No, cgpa, Name);
	}

	public static void main(String args[]) throws ClassNotFoundException {
        Connection con = null;

        PreparedStatement p = null;
        ResultSet rs = null;
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
            String sql = "select * from company";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            // Printing ID, name, email, and role of companies
            System.out.println("CompanyId\t\tCompany_name\t\temail\t\trole");
            System.out.println("____________________________________________________________________________________________");
            // Loop through the result set
            while (rs.next()) {
                int id = rs.getInt("CompanyId");
                String name = rs.getString("Company_name");
                String email = rs.getString("email");
                String role = rs.getString("role");
                System.out.println(id + "\t\t\t" + name + "\t\t" + email + "\t\t" + role);
                System.out.println("____________________________________________________________________________________________");

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
        System.out.println("Dear Student,");
        System.out.println("Kindly enter your details to put it into database");
        System.out.println("Enter your Name:");
        String Name=f.nextLine();
        System.out.println("Enter your RollNo:");
        int RollNo=f.nextInt();
        System.out.println("Enter your course_Id:");
        int course_Id=f.nextInt();
        System.out.println("Enter your phone_No:");
        long phone_No=f.nextLong();
        System.out.println("Enter your cgpa:");
        double cgpa=f.nextDouble();
        
        
        addCandidate(RollNo,course_Id,phone_No,cgpa,Name);
    }

	public static Connection connectDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern", "root", "Ha@260204");
		return con;
	}
	public static void addCandidate(int RollNo, int course_Id, Long phone_No,double cgpa, String Name) {
		String sql = "INSERT INTO student (RollNo, course_Id, phone_No,cgpa, Name) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection in=DriverManager.getConnection( "jdbc:mysql://localhost:3306/intern",
					"root", "Ha@260204");
			PreparedStatement pstmt = in.prepareStatement(sql);
			pstmt.setInt(1, RollNo);
			pstmt.setInt(2, course_Id);
			pstmt.setLong(3, phone_No);
			pstmt.setDouble(4, cgpa);
			pstmt.setString(5, Name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}    
}

