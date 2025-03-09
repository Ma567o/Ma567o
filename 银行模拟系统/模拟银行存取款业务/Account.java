package 模拟银行存取款业务;

public class Account {
    String userName;
    String password;
    double money;
    public Account(){
        this.userName="userName";
        this.money=0;
        this.password="password";

    }

    public Account(String userName,String password,double money) {
        this.userName = userName;
        this.password=password;
        this.money=money;
    }

}
