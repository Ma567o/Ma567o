import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login教师 {
    public Login教师() {
        Scanner in = new Scanner(System.in);
        Connection conn;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt1 = null;

        //连接MySQL
        ConnectMySQL cm = new ConnectMySQL();
        conn = cm.registerDriver();
        try {
            //sql语句
            String sql = "select * from teacher";
            // 获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            // 执行SQL
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("请输入姓名：");
        String name = in.next();

        boolean isExit = false;
        try {
            boolean flag = rs.next();
            while (flag) {
                String teachername = rs.getString("teachername");
                if (teachername.equals(name)) {
                    System.out.println("注册失败，该用户已存在");
                    isExit = true;
                    break;
                }
                flag = rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //判断用户是否存在
            if (!isExit) {
                String sql1 = "insert into teacher(teachername,password) values(?,?);";
                pstmt1 = conn.prepareStatement(sql1);
                System.out.println("请输入你的密码：");
                String pwd = in.next();
                // 设置参数
                pstmt1.setString(1, name);
                pstmt1.setString(2, pwd);
                pstmt1.executeUpdate();
                System.out.println("添加成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //释放资源
        cm.freeResources(rs, pstmt1, conn);
        cm.freeResources(rs, pstmt, conn);
    }
}
