import java.util.Scanner;

public class 主测试 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("------------------------欢迎进入学生管理系统！----------------------------");
        while (true) {
            System.out.println("注册请输入0： "+"\n"+"登录请输入1：");
            String n = in.next();
            if (n.equals("1")) {
                Operation op=new Operation();
                op.登录();
            } else if (n.equals("0")) {
                System.out.println("注册学生用户请输入0："+"\n"+"注册教师用户请输入1：");
                int i=in.nextInt();
                if(i==0) {
                    new Login学生();
                }
                else {
                    new Login教师();
                }

            } else {
                System.out.println("输入错误！");
            }
         }
       }
    }
