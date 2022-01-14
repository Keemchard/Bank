//HEY IT'S ME BOSSKCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC

package zzzzzzzzzzzzzz;

import java.util.Scanner;
	
public class BankMain {
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);

//---------------------------------logic for sign in and logging in------------------------------------------------
		
		
		//SIGN UP--------------------------------------------------
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Create account!");
		System.out.println("-----------------------------------------------------------------------------------\n \n");
		
		Bank bank;
		do {
			System.out.print("Enter your Username: ");
			String userName = scan.nextLine();
			System.out.print("Enter you Pin Number: " );
			String pin = scan.nextLine();
			bank = new Bank(userName, pin);
			
			System.out.println("Username: " + bank.getUserName());
			System.out.println("Pin number: " + bank.getPin() + "\n");
			
		}while(bank.getUserName().equals("Invalid Username") || bank.getPin().equals("Invalid Pin"));

		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Hello " + bank.getUserName() + ", You have succesfully Created ad Account!");
		System.out.println("-----------------------------------------------------------------------------------\n \n");
		
		//LOG IN-------------------------------------------------
		System.out.println("Please Enter your Credential Below to Log in");
		
		String msg;
		do {
			System.out.print("Enter your Username: ");
			String userName = scan.nextLine();
			System.out.print("Enter your Pin: ");
			String pin = scan.nextLine();
			
			if(userName.equals(bank.getUserName()) && pin.equals(bank.getPin())) {
				msg = "Log In succesful";
				System.out.println(msg);
			}else {
				msg = "Either your Username or Pin is Incorrect";
				System.out.println(msg + "\n");
			}
			
		}while(msg.equals("Either your Username or Pin is Incorrect"));
		
		process();
		
	 
	}//end of main method
	
	public static void process() {
		Scanner scan = new Scanner(System.in);
		
		Bank bank = new Bank();
		
		
		String choose;
		double depositAmount;
		double withdrawAmount;
		String stopKey;
		do {
			
			System.out.println("\n-----------------------------------------------------------------------------------");
			System.out.println("Please type 1 for Deposit \n"
					+ "Type 2 for Withdraw \n"
					+ "And please type 3 if you want to exit the transaction \n"
					+ "You can also type 'stop' to exit the transaction");
			System.out.println("-----------------------------------------------------------------------------------\n \n");
			
			
			do {
				System.out.print("Type here: ");
				choose = scan.next();
				stopKey = choose;
				//DEPOSIT
				if(choose.equals("1")) {
					do {
						System.out.println("Wallet Amount: " + bank.getWalletAmount() + " Pesos");
						System.out.println("Minimum amount to be deposited is 100.00 Pesos \n");
						System.out.print("Enter Amount to be deposited: ");
						depositAmount = scan.nextDouble();
						bank.setDepositAmount(depositAmount);
						bank.setWalletAmount(bank.getWalletAmount() + bank.getDepositAmount());
						System.out.println("\n" + "YOUR BANK WALLET ACCOUNT BALANCE: " + bank.getWalletAmount() + " PESOS");
						
					}while(bank.getDepositAmount() == 0.0);	
					System.out.println("Thank You! \n");
				}
				//WITHDRAW
				else if(choose.equals("2")) {
					do {
						System.out.println("Wallet Amount: " + bank.getWalletAmount() + " Pesos");
						System.out.println("Minimum amount to be withdrawn is 100.00 Pesos \n");
						System.out.print("Enter Amount to be withdrawn: ");
						withdrawAmount = scan.nextDouble();
						bank.setWithdrawAmount(withdrawAmount);
						bank.setWalletAmount(bank.getWalletAmount() - bank.getWithdrawAmount());
						System.out.println("\n" + "YOUR BANK WALLET ACCOUNT BALANCE: " + bank.getWalletAmount() + " PESOS");
						 
					}while(bank.getWithdrawAmount() == 0.0);
					System.out.println("Thank You! \n");
				}
				//EXIT
				else if(choose.equals("3")) {
					System.exit(0);
				}
			}while(!choose.equals("1") && !choose.equals("2") && !choose.equals("3"));
			
		}while(!stopKey.equals("stop"));
		
	}
	
}//end of main class



class Bank {
	private String userName;
	private String pin;
	private double depositAmount;
	private double withdrawAmount;
	private double walletAmount = 0.0;
	String msg;
	double minimumAmount = 100.00;
	
	public Bank(String userName, String pin, double depositAmount, double withdrawAmount ) {
		this.setUserName(userName);
		this.setPin(pin);
		this.setDepositAmount(depositAmount);
		this.setWithdrawAmount(withdrawAmount);
	}
	public Bank(String userName, String pin ) {
		this.setUserName(userName);
		this.setPin(pin);
	}
	public Bank(double depositAmount, double withdrawAmount ) {
		this.setDepositAmount(depositAmount);
		this.setWithdrawAmount(withdrawAmount);
	}
	public Bank() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if(isValidUser(userName)) {
			this.userName = userName;
		}else {
			this.userName = "Invalid Username";
		}
		
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		if(isValidPin(pin)) {
			this.pin = pin;
		}else {
			this.pin = "Invalid Pin";
		}
		
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		if(isValidDeposit(depositAmount)) {
			this.depositAmount = depositAmount;
		}else {
			this.depositAmount = 0.0;
		}
		
	}

	public double getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(double withdrawAmount) {
		if(isValidWithdraw(withdrawAmount)) {
			this.withdrawAmount = withdrawAmount;
		}else {
			this.withdrawAmount = 0.0;
		}
		
	}

	public double getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(double walletAmount) {
		this.walletAmount = walletAmount;
	}
	
	
	public boolean isValidDeposit(double depositAmount) {
		return(depositAmount >= this.minimumAmount);
	}
	public boolean isValidWithdraw(double withdrawAmount) {
		return(withdrawAmount >= this.minimumAmount && withdrawAmount <= this.getWalletAmount());
	}
	
	
	public static boolean isValidPin(String pin) {
		int numCount = 0;
		int letterCount = 0;
		int unwantedCharCount = 0;
		
		for(int i = 0; i < pin.length(); i++) {
			if(containLetter(pin.charAt(i))) {
				letterCount++;
			}
			if(containNumber(pin.charAt(i))) {
				numCount++;
			}
			if(containUnwantedChar(pin.charAt(i))) {
				unwantedCharCount++;
			}
		}
		
		return(numCount == 4 && letterCount == 0 && unwantedCharCount == 0);
		
	}

	
//	-----------------------isValidUser Method Start-----------------------------
	public static boolean isValidUser(String userName) {
		int numCount = 0;
		int letterCount = 0;
		int unwantedCharCount = 0;
		
		char charUserName [] = userName.toCharArray();
		for(int i = 0 ; i < userName.length(); i++) {
			if(containLetter(charUserName[i])) {
				letterCount++;
			}
			if(containNumber(charUserName[i])) {
				numCount++;
			}
			if(containUnwantedChar(charUserName[i])) {
				unwantedCharCount++;
			}
		}
		
		
		return(letterCount >= 3 && numCount == 0 && unwantedCharCount == 0);
			
	}

	
// ----------------------Needed Method for verification sign up----------------------
	public static boolean containNumber(char input) {
		return(input >= '0' && input <= '9');
	}
	public static boolean containLetter(char input) {
		return((input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z'));
	}
	public static boolean containUnwantedChar(char input) {
		return(input >= ':' && input <= '@');
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
}







