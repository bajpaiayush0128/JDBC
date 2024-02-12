import java.sql.*;

import java.util.Scanner;

public class MySql {

    public static void main(String[] args) {

        try {

            String name = null;

            String pname = null;

            int quantity;

            try (Scanner sc = new Scanner(System.in)) {
                System.out.print("Enter the Customer name,product name and amount: ");

                name = sc.next();

                pname = sc.next();

                quantity = sc.nextInt();
            }

            Class.forName("com.mysql.cj.jdbc.Drive\r");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root",
                    "");

            Statement stmt = con.createStatement();

            PreparedStatement ps = con.prepareStatement("insert into persons values(?,?,?)");

            ps.setString(1, name);

            ps.setString(2, pname);

            ps.setInt(3, quantity);

            ps.executeUpdate();

            System.out.println("Successfully");

            ResultSet rs = stmt.executeQuery("select * from persons");

            while (rs.next())

                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getInt(3));

            System.out.println("Successfully");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}