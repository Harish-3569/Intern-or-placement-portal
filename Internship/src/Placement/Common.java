package Placement;
import java.sql.*;
 class Common {
	public static void main(String args[]) throws ClassNotFoundException {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet rs = null;
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
            String sql = "select * from relation";
            p = con.prepareStatement(sql);
            rs = p.executeQuery();

            // Printing ID, name, email, and role of companies
            System.out.println("roll_No\t\tCompanyId");
        System.out.println("_________________________________________________________");
            // Loop through the result set
            while (rs.next()) {
                int roll_No = rs.getInt("roll_No");
                String CompanyId = rs.getString("CompanyId");
          
                System.out.println(roll_No + "\t\t\t" + CompanyId);
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
    }

    public static Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/intern", "root", "Ha@260204");
        return con;
    }

}
