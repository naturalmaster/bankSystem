package bean;

import java.util.Date;

public class Account {
    private int aId;
    private String name;
    private String passWord;

    /**
     * 只能为“男” 或者 “女”
     */
    private String sex;

    /**
     * 身份证号
     */
    private String code;
    private int openMoney;
    private Date openDate;
    private int balance;


    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (sex.equals("男") || sex.equals("女")){
            this.sex = sex;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOpenMoney() {
        return openMoney;
    }

    public void setOpenMoney(int openMoney) {
        this.openMoney = openMoney;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
