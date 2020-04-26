package Java2_09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
public class JdbcPreparedStatementTest {
    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:8888/ebookshop", "myuser", "xxxx");
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into books values (?, ?, ?, ?, ?)");

                PreparedStatement pstmtSelect = conn.prepareStatement("Select * from books");
                ) {
            pstmt.setInt(1, 7002);
            pstmt.setString(2, "Mahjong 102");
            pstmt.setString(3, "Kumar");
            pstmt.setDouble(4, 88.88);
            pstmt.setInt(5, 88);
            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + "rows affected");

            pstmt.setInt(1, 7002);
            pstmt.setString(2, "Mahjong 102");
            rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + "rows affected");

            ResultSet rset = pstmtSelect.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getInt("id") + ", "
                + rset.getString("author + ") + ", "
                + rset.getString("title") + ", "
                + rset.getDouble("price") + ", "
                + rset.getInt("qty"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
