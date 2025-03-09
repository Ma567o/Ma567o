import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class 学生端 {
    public static void main(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入你的名字：");
        String name = in.nextLine();
        System.out.println("请输入你的密码：");
        String pwd = in.nextLine();

        ConnectMySQL cm = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            cm = new ConnectMySQL();
            conn = cm.registerDriver();
            // 定义sql
            String sql = "select * from user where username = ? and password= ?";
            // 获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            // 设置？的值
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            // 执行sql
            rs = pstmt.executeQuery();

            // 判断登录是否成功
            if (rs.next()) {
//                Student.randomCode();
                Scanner sc = new Scanner(System.in);
                while(true) {
                    System.out.println("----------------------欢迎来到学生信息管理系统------------------------");
                    System.out.println("查询学生信息请输入1：" + "\n" + "打印所有学生请输入2：" + "\n" + "修改你的密码请输入3："+"\n"+"查询本周课表请输入4："+"\n"+"学生平均成绩排名请输入5："+"\n"+"退出学生管理系统请输入0：");
                    int k = sc.nextInt();
                    Operation op = new Operation();
                    if (k == 1) {
                        op.seek();
                    } else if (k == 2) {
                        op.show();
                    } else if (k==3) {
                        op.changepassword1();
                    } else if (k==4) {
                        op.lesson();
                    } else if (k==5) {
                        op.scorerank();
                    } else if (k == 0) {
                        System.out.println("-------------------------欢迎下次使用！----------------------------");
                        op.quit();
                    }
                }
            }
            else {
                System.out.println("用户名或密码错误！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cm.freeResources(rs, pstmt, conn);
    }
}
