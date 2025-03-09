import java.util.Random;
import java.util.Scanner;

public class Student {
    private String name;
    private String major;
    private String grade;
    private String number;
    private float math;
    private float java;
    private float average;
    private float english;

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public void setJava(float java) {
        this.java = java;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public void setEnglish(float english) {
        this.english = english;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getGrade() {
        return grade;
    }

    public String getNumber() {
        return number;
    }

    public float getMath() {
        return math;
    }

    public float getJava() {
        return java;
    }

    public float getAverage() {
        return average;
    }

    public float getEnglish() {
        return english;
    }

    public Student() {
        this.name="";
        this.major="";
        this.number="0000";
        this.math=0;
        this.java=0;
        this.average=0;
        this.english=0;
    }

    public Student(String name, String major,String grade,String number, float math,float java,float english) {
        this.name = name;
        this.major=major;
        this.grade=grade;
        this.number=number;
        this.math=math;
        this.java=java;
        this.english=english;
        this.average=(this.english+this.java+this.math)/4;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", grade='" + grade + '\'' +
                ", number='" + number + '\'' +
                ", math=" + math +
                ", java=" + java +
                ", english=" + english +
                '}';
    }

    public static void randomCode(){
        Scanner sc=new Scanner(System.in);
        int sign=10;
        String arr="123456789abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
        char[] ch=arr.toCharArray();
        String randomCode="";
        for(int i=0;i<4;i++){
            Random r=new Random();
            randomCode=randomCode+ch[r.nextInt(59)];
        }
        System.out.println("------------------------------------------>>>> "+randomCode);
        System.out.print("------------------------------------------>>>> 请输入验证码：");
        String str=sc.next();
        if(str.equals(randomCode)){
            System.out.println("恭喜你 输入正确！");
        }
        else{
            sign=0;
        }
        while(sign==0){
            System.out.println("抱歉 输入错误！请重试：");
            String a=sc.next();
            if(a.equals(randomCode)){
                System.out.println("恭喜你 输入正确！");
                sign=1;
            }
            else{
                sign=0;
            }
        }

    }
}
