package 复数;
import java.math.*;
public class Complex {
    double a;
    double b;

    public Complex(double a, double b) {
        this.a = a;
        this.b=b;
    }

    public Complex(double a) {
        this.a= a;
        this.b=0;
    }
    public Complex(){
        this.a=0;
        this.b=0;
    }
    public double getRealPart(){
        return a;
    }
    public double getImaginePart(){
        return b;
    }
    public  void add(Complex c){
        double e=this.a+c.a;
        double f=this.b+c.b;
        System.out.println("2个复数相加之后 = "+e+"+"+f+"i");
    }
    public  void substract(Complex c){
        double e=this.a-c.a;
        double f=this.b-c.b;
        System.out.println("2个复数相减之后 = "+e+"+"+f+"i");
    }
    public  void multiply(Complex c){
        double e=(this.a*c.a-this.b*c.b);
        double f=(this.b*c.a+this.a*c.b);
        System.out.println("2个复数相乘之后 = "+e+"+"+f+"i");
    }
    public   void divide(Complex c){
        double e=(this.a*c.a+this.b*c.b)/(c.a*c.a+c.b*c.b);
        double f=(this.b*c.a-this.a*c.b)/(c.a*c.a+this.b*this.b);
        System.out.println("2个复数相除之后 = "+e+"+"+f+"i");
    }
    public  void abs(){
        System.out.println(this.a+"+"+this.b+"i"+"的绝对值="+Math.sqrt(this.a*this.a+this.b*this.b));
    }

    @Override
    public String toString() {
        if(this.b!=0){
        return this.a+"+"+this.b+"i";}
        else{
            return "a";
        }
    }

    @Override
    public boolean equals(Object obj) {
        Complex c=(Complex)obj;
        if(this.a==c.a&&this.b==c.b){
            return true;
        }
        else {
            return false;
        }
    }
}
