import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Operation {
    public void quit() {
        System.out.println("感谢光临！欢迎下次再来！");
        //关闭Java虚拟机
        System.exit(0);
    }
    public ResultSet setRs() {
        ConnectMySQL cm = new ConnectMySQL();
        Connection conn = cm.registerDriver();
        ResultSet rs = null;
        // 定义sql
        String sql = "select * from student";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    //查找图书
    public void seek() {
        Scanner in = new Scanner(System.in);
        System.out.println("输入要查找学生的名字：");
        String name = in.next();
        ResultSet rs = setRs();
        boolean isExit = false;
        try {
            boolean flag = rs.next();
            while (flag) {
                String stuName = rs.getString("name");
                if (stuName.equals(name)) {
                    Student b = new Student(rs.getString("name"), rs.getString("major"),rs.getString("grade"), rs.getString("number"),rs.getFloat("math"),rs.getFloat("java"),rs.getFloat("english"));
                    System.out.println("为你找到" + name+ "的信息：");
                    System.out.println(b);
                    isExit = true;
                    break;
                }
                //让rs结果集中的索引指针向下移动
                flag = rs.next();
            }
            if (!isExit) {
                System.out.println("抱歉，这里没有你要查找的学生。");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //添加图书
    public void add() {
        Scanner in = new Scanner(System.in);
        ConnectMySQL cm = new ConnectMySQL();
        Connection conn = cm.registerDriver();
        // 执行SQL
        ResultSet rs = setRs();
        PreparedStatement pstmt = null;
        System.out.println("请输入新增学生的名字：");
        String name = in.next();
        boolean isExit = false;
        try {
            boolean flag = rs.next();
            while (flag) {
                String stuName = rs.getString("name");
                if (stuName.equals(name)) {
                    System.out.println("添加失败，该学生已存在");
                    isExit = true;
                    break;
                }
                flag = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (!isExit) {
                String sql1 = "insert into student(name,major,grade,number,math,java,english) values(?,?,?,?,?,?,?);";
                pstmt = conn.prepareStatement(sql1);
                System.out.println("请输入新增学生的专业方向：");
                String major=in.next();
                System.out.println("请输入新增学生的年级：");
                String grade = in.next();
                System.out.println("请输入新增学生的学号：");
                String number = in.next();
                System.out.println("请输入新增学生的数学成绩：");
                float math=in.nextFloat();
                System.out.println("请输入新增学生的java成绩：");
                float java=in.nextFloat();
                System.out.println("请输入新增学生的英语成绩：");
                float english=in.nextFloat();
                pstmt.setString(1, name);
                pstmt.setString(2,major);
                pstmt.setString(3, grade);
                pstmt.setString(4, number);
                pstmt.setFloat(5,math);
                pstmt.setFloat(6,java);
                pstmt.setFloat(7,english);
                pstmt.executeUpdate();
                System.out.println("添加成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //释放资源
        cm.freeResources(rs, pstmt, conn);
    }

    //删除图书
    public void delete() {
        Scanner in = new Scanner(System.in);
        ConnectMySQL cm = new ConnectMySQL();
        Connection conn = cm.registerDriver();
        ResultSet rs = setRs();
        PreparedStatement pstmt = null;

        System.out.println("请输入删除学生的名字：");
        String name = in.next();
        boolean isExit = false;
        try {
            boolean flag = rs.next();
            while (flag) {
                String stuName = rs.getString("name");
                if (stuName.equals(name)) {
                        // 定义SQL
                        String sql = " delete from student where name = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, name);
                        pstmt.executeUpdate();
                        System.out.println("删除成功！");
                    isExit = true;
                    break;
                }
                flag = rs.next();
            }
            if (!isExit) {
                System.out.println("未找到该学生！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //释放资源
        cm.freeResources(rs, pstmt, conn);
    }

    public void show() {
        ResultSet rs = setRs();
        try {
            while (rs.next()) {
                Student b = new Student(rs.getString("name"), rs.getString("major"),rs.getString("grade"), rs.getString("number"),rs.getFloat("math"),rs.getFloat("java"),rs.getFloat("english"));
                System.out.println(b);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void change() throws SQLException {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入你要修改的学生的姓名：");
        String name=sc.next();
        System.out.println("请输入修改后的数学成绩：");
        float math=sc.nextFloat();
        System.out.println("请输入修改后的java成绩：");
        float java=sc.nextFloat();
        System.out.println("请输入修改后的英语成绩：");
        float english=sc.nextFloat();
        ConnectMySQL cm = new ConnectMySQL();
        Connection conn = cm.registerDriver();
        ResultSet rs = setRs();
        PreparedStatement pstmt = null;
        try {
            while(rs.next()) {
                String stuname = rs.getString("name");
                if (stuname.equals(name)) {
                    String sql = "update student set math=?,java=?,english=? where name=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setFloat(1,math);
                    pstmt.setFloat(2,java);
                    pstmt.setFloat(3,english);
                    pstmt.setString(4,stuname);
                    pstmt.executeUpdate();
                    System.out.println("修改成功！");
                }
            }
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void scorerank(){
        Student[] stu=new Student[10000];
        Student s=new Student();
        ResultSet rs = setRs();
        for(int i=0;i< stu.length;i++){
            stu[i]=new Student();
        }
        int k=0;
        try {
            while (rs.next()) {
                stu[k]= new Student(rs.getString("name"), rs.getString("major"),rs.getString("grade"), rs.getString("number"),rs.getFloat("math"),rs.getFloat("java"),rs.getFloat("english"));
                k++;
            }
            for(int i=0;i< stu.length-1;i++){
                for(int j=1;j<stu.length-1;j++){
                    if(stu[i].getAverage()<stu[j].getAverage()){
                        s=stu[i];
                        stu[i]=stu[j];
                        stu[j]=s;
                    }
                }
            }
            for(int i=0;i<stu.length;i++){
                if(stu[i].getGrade()!=null) {
                    System.out.println(stu[i]);
                }
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void lesson() throws SQLException {
        ConnectMySQL cm = new ConnectMySQL();
        Connection conn = cm.registerDriver();
        ResultSet rs = null;
        // 定义sql
        String sql = "select * from lesson";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("id\t\tsubject\t\tname\t\t\t周一\t\t\t\t周二\t\t\t\t周三\t\t\t\t周四\t\t\t\t周五");

        while(rs.next()){
            try {
                int id=rs.getInt("id");
                String subject=rs.getString("subject");
                String name=rs.getString("name");
                String 周一=rs.getString("周一");
                String 周二=rs.getString("周二");
                String 周三=rs.getString("周三");
                String 周四=rs.getString("周四");
                String 周五=rs.getString("周五");
                System.out.println(id + "\t\t" + subject + "\t\t"
                        + name + "\t\t" + 周一 + "\t" + 周二+"\t"+周三+"\t"+周四+"\t"+周五+"\t");


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
    public void changepassword1(){
        Scanner in = new Scanner(System.in);
        Connection conn;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt1 = null;
        ConnectMySQL cm = new ConnectMySQL();
        conn = cm.registerDriver();
        System.out.println("请先确认姓名：");
        String name = in.next();
        try {
                System.out.println("请输入你的新密码：");
                String pwd = in.next();
                
                String sql1 = "update user set password=? where username=?";
                pstmt1 = conn.prepareStatement(sql1);
                pstmt1.setString(1, pwd);
                pstmt1.setString(2, name);
                pstmt1.executeUpdate();
                System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cm.freeResources(rs, pstmt1, conn);
        cm.freeResources(rs, pstmt, conn);

    }
    public void changepassword2(){
        Scanner in = new Scanner(System.in);
        Connection conn;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt1 = null;
        ConnectMySQL cm = new ConnectMySQL();
        conn = cm.registerDriver();
        System.out.println("请先确认姓名：");
        String name = in.next();
        try {
            System.out.println("请输入你的新密码：");
            String pwd = in.next();
            String sql1 = "update teacher set password=? where teachername=?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, pwd);
            pstmt1.setString(2, name);
            pstmt1.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cm.freeResources(rs, pstmt1, conn);
        cm.freeResources(rs, pstmt, conn);
    }
    public void changelesson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你所要修改的学科：");
        String lessonname = sc.next();
        System.out.println("你所要修改周几的课表：");
        String name = sc.next();
        if (name.equals("周一")||name.equals("一")) {
            System.out.println("请输入周一的上课状况：");
            String state=sc.next();
            ConnectMySQL cm = new ConnectMySQL();
            Connection conn = cm.registerDriver();
            String sql0 = "select * from lesson";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql0);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String stuname = rs.getString("subject");
                    if (stuname.equals(lessonname)) {
                        String sql1 = "update lesson set 周一=? where subject=?";
                        pstmt = conn.prepareStatement(sql1);
                        pstmt.setString(1, state);
                        pstmt.setString(2, lessonname);
                        pstmt.executeUpdate();
                        System.out.println("修改成功！");
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       else if (name.equals("周二")||name.equals("二")) {
            System.out.println("请输入周二的上课状况：");
            String state=sc.next();
            ConnectMySQL cm = new ConnectMySQL();
            Connection conn = cm.registerDriver();
            String sql0 = "select * from lesson";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql0);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String stuname = rs.getString("subject");
                    if (stuname.equals(lessonname)) {
                        String sql1 = "update lesson set 周二=? where subject=?";
                        pstmt = conn.prepareStatement(sql1);
                        pstmt.setString(1, state);
                        pstmt.setString(2, lessonname);
                        pstmt.executeUpdate();
                        System.out.println("修改成功！");
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       else if (name.equals("周三")||name.equals("三")) {
            System.out.println("请输入周三的上课状况：");
            String state=sc.next();
            ConnectMySQL cm = new ConnectMySQL();
            Connection conn = cm.registerDriver();
            String sql0 = "select * from lesson";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql0);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String stuname = rs.getString("subject");
                    if (stuname.equals(lessonname)) {
                        String sql1 = "update lesson set 周三=? where subject=?";
                        pstmt = conn.prepareStatement(sql1);
                        pstmt.setString(1, state);
                        pstmt.setString(2, lessonname);
                        pstmt.executeUpdate();
                        System.out.println("修改成功！");
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (name.equals("周四")||name.equals("四")) {
            System.out.println("请输入周四的上课状况：");
            String state=sc.next();
            ConnectMySQL cm = new ConnectMySQL();
            Connection conn = cm.registerDriver();
            String sql0 = "select * from lesson";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql0);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String stuname = rs.getString("subject");
                    if (stuname.equals(lessonname)) {
                        String sql1 = "update lesson set 周四=? where subject=?";
                        pstmt = conn.prepareStatement(sql1);
                        pstmt.setString(1, state);
                        pstmt.setString(2, lessonname);
                        pstmt.executeUpdate();
                        System.out.println("修改成功！");
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (name.equals("周五")||name.equals("五")) {
            System.out.println("请输入周五的上课状况：");
            String state=sc.next();
            ConnectMySQL cm = new ConnectMySQL();
            Connection conn = cm.registerDriver();
            String sql0 = "select * from lesson";
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql0);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    String stuname = rs.getString("subject");
                    if (stuname.equals(lessonname)) {
                        String sql1 = "update lesson set 周五=? where subject=?";
                        pstmt = conn.prepareStatement(sql1);
                        pstmt.setString(1, state);
                        pstmt.setString(2, lessonname);
                        pstmt.executeUpdate();
                        System.out.println("修改成功！");
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public  void 登录() throws Exception {
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("学生用户请输入0：" + "\n" + "教师用户请输入1：");
            String n = in.next();
            if (n.equals("1")) {
                教师端.main();
            } else if (n.equals("0")) {
                学生端.main();
            } else {
                System.out.println("输入错误！");
            }
        } while (true);
    }

}
