package pojo;

public class Account {
   private int accountNo;
   private String name;
   private String accType;
   private double balance;
   
   public Account() {
	
   }

   public int getAccountNo() {
	return accountNo;
   }

   public void setAccountNo(int accountNo) {
	this.accountNo = accountNo;
   }

   public String getName() {
	return name;
   }

   public void setName(String name) {
	this.name = name;
   }

   public String getAccType() {
	return accType;
   }

   public void setAccType(String accType) {
	this.accType = accType;
   }

   public double getBalance() {
	return balance;
   }

   public void setBalance(double balance) {
	this.balance = balance;
   }

   @Override
   public String toString() {
	return "Account [accountNo=" + accountNo + ", name=" + name + ", accType=" + accType + ", balance=" + balance + "]";
   }
   
   
   
   
}
