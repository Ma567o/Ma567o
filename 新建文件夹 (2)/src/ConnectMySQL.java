import java.sql.*;

public class ConnectMySQL {
    public Connection registerDriver() {
        Connection conn = null;
        try {
            // 注册驱动，可以省略
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接，连接本机可以简写url
            String url = "jdbc:mysql://localhost:3306/student";
            String username = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void freeResources(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
