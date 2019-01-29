package rechard.learn.data;

public class Account {

    private long key;
    private String accountName;
    private String accountNo;
    private String ccy;
    private int paymentType;  // 1.payment 2.payroll 3.fix deposit
    private int userKy;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getUserKy() {
        return userKy;
    }

    public void setUserKy(int userKy) {
        this.userKy = userKy;
    }
}
