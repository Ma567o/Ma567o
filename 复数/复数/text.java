package 复数;
import java.util.Scanner;
public class text {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个复数的实部：");
        double a = sc.nextDouble();
        System.out.println("请输入第一个复数的虚部：");
        double b = sc.nextDouble();
        Complex c1=new Complex(a,b);
        System.out.println("第一个复数为：" + a + "+" + b + "i");
        System.out.println("请输入第二个复数的实部：");
        double c = sc.nextDouble();
        System.out.println("请输入第二个复数的虚部：");
        double d = sc.nextDouble();
        Complex c2=new Complex(c,d);
        System.out.println("第二个复数为：" + c + "+" + d + "i");
        c1.add(c2);
        c1.substract(c2);
        c1.multiply(c2);
        c1.divide(c2);
        c1.abs();
        c2.abs();
        c1.equals(c2);
        c1.toString();
        c2.toString();

    }
}
