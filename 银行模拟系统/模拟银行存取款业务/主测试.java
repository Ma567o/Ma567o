package 模拟银行存取款业务;
import java.util.Scanner;
import java.util.*;
public class 主测试 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String flag="是";
        System.out.println("-------------------欢迎来到银行存取业务管理系统------------------");
        randomCode();
            Account[] arr = createArr();
            Map<String, Account> map = createMap(arr);
            Set<String> keySet = map.keySet();
            System.out.println("请输入你的银行卡账号：");
            String id = sc.next();
            judge(arr, map, keySet, id);
            while(flag.equals("是")) {
                System.out.println("存款请输入1：");
                System.out.println("取款请输入2：");
                System.out.println("查看余额请输入3：");
                System.out.println("修改个人密码请输入4：");
                System.out.println("退出系统请输入0：");
                int sign = sc.nextInt();
                if (sign == 1) {
                    int isornot=0;
                    do {
                        System.out.println("请输入你要存入的款：");
                        double deposit = sc.nextDouble();
                        map.get(id).money = map.get(id).money + deposit;
                        System.out.println("恭喜你 存款成功！");
                        System.out.println("剩余金额：" + map.get(id).money + "，是否继续存款（‘1’：结束，‘2’：继续）：");
                        isornot=sc.nextInt();
                      }  while (isornot==2);

                }

                if (sign == 2) {
                    int isornot = 0;
                    do {
                        System.out.println("输入取款金额：");
                        double cash = sc.nextDouble();
                        if (map.get(id).money >= cash) {
                            map.get(id).money = map.get(id).money - cash;
                        } else {
                            System.out.println("目前余额：" + map.get(id).money + "无法满足您的取款需求！");
                            continue;
                        }
                        System.out.println("剩余金额：" + map.get(id).money + "，是否继续取款（‘1’：结束，‘2’：继续）：");
                        isornot = sc.nextInt();
                        if (isornot == 1) {
                            break;
                        }
                    } while (map.get(id).money > 0);
                    System.out.println("取款结束！");
                }
                if (sign == 3) {
                    System.out.println("你的账户余额为：" + map.get(id).money);
                }
                if (sign == 4) {
                    System.out.println("请输入修改之后的密码：");
                    String p = sc.next();
                    map.get(id).password = p;
                    System.out.println("请再次确认修改之后的密码：");
                    if (sc.next().equals(p)) {
                        System.out.println("恭喜你 密码修改成功！");
                    }
                    int fla=10;
                    System.out.println("请输入你的新银行卡密码：");
                    String password=sc.next();
                    for(String str:keySet){
                        if(id.equals(str)&&password.equals(map.get(str).password)){
                            System.out.println("----------------------恭喜你 登录成功！！！！！--------------------------");
                            fla=1;
                            break;
                        }
                        else{
                            fla=0;
                        }
                    }
                    while(fla==0){
                        System.out.println("抱歉 密码错误！请重新输入：");
                        String str=sc.next();
                        for(String obj:keySet){
                            if(id.equals(obj)&&str.equals(map.get(obj).password)){
                                System.out.println("恭喜你 登录成功！！！！！");
                                fla=1;
                                break;
                            }
                            else{
                                fla=0;
                            }
                        }
                    }
                }
                if(sign==5){
                    break;
                }
                System.out.println("还需要继续使用吗？（是/否）：");
                flag = sc.next();
            }
    }
    public static Account[] createArr(){
        Account[] arr=new Account[100];
        for(int i=0;i<arr.length;i++){
            arr[i]=new Account();
        }
        arr[0]=new Account("马俊一","123456",1000);
        arr[1]=new Account("刘星辰","112233",100);
        arr[2]=new Account("薛仁杰","789",50000);
        return arr;
    }
    public  static Map<String,Account> createMap(Account[] arr){
        Map<String,Account> map=new HashMap<>();
        map.put("411321200404113610",arr[0]);
        map.put("411321200504897899",arr[1]);
        map.put("411321205589755465",arr[2]);
        return map;
    }
    public static void judge(Account[] arr,Map<String,Account> map,Set<String>keySet,String id){
        Scanner sc=new Scanner(System.in);
        int flag=10;
        System.out.println("请输入你的银行卡密码：");
        String password=sc.next();
        for(String str:keySet){
            if(id.equals(str)&&password.equals(map.get(str).password)){
                System.out.println("----------------------恭喜你 登录成功！！！！！--------------------------");
                flag=1;
                break;
            }
            else{
                flag=0;
            }
        }
        while(flag==0){
            System.out.println("抱歉 密码错误！请重新输入：");
            String str=sc.next();
            for(String obj:keySet){
                if(id.equals(obj)&&str.equals(map.get(obj).password)){
                    System.out.println("恭喜你 登录成功！！！！！");
                    flag=1;
                    break;
                }
                else{
                    flag=0;
                }
            }
        }
    }
    public static void randomCode(){
        Scanner sc=new Scanner(System.in);
        int sign=10;
        String arr="123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] ch=arr.toCharArray();
        String randomCode="";
        for(int i=0;i<4;i++){
            Random r=new Random();
            randomCode=randomCode+ch[r.nextInt(61)];
        }
        System.out.println(randomCode);
        System.out.println("请输入验证码：");
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
