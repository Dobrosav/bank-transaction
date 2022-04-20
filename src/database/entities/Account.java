package database.entities;

public class Account {
	private String accountNumber;
	private int currency;
	private String type;
	private int limit;
	private int fee;
	private int paymentcredit;
	private int ammount;
	private int user;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getPaymentcredit() {
		return paymentcredit;
	}

	public void setPaymentcredit(int paymentcredit) {
		this.paymentcredit = paymentcredit;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return accountNumber + ":" + ammount + ":" + currency + ":" + "userid" + ":" + user;
	}

}
