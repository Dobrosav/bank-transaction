package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.entities.Account;
import database.entities.Client;
import database.entities.Currency;

public class Funcionalities {
	private static String connString = "jdbc:sqlite:C:/Users/vd180005d/Desktop/bank.db";
	public static ArrayList<Currency> getCurrencies(){
		ArrayList<Currency> l = new ArrayList<Currency>();

		try (Connection con = DriverManager.getConnection(connString); Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM currencies");
			while (rs.next()) {
				Currency c=new Currency();
				c.setCode(rs.getInt(1));
				c.setName(rs.getString(2));
				l.add(c);
			}
			rs.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return l;
	}
	public static ArrayList<Client> getAllClients() {
		ArrayList<Client> l = new ArrayList<Client>();

		try (Connection con = DriverManager.getConnection(connString); Statement stmt = con.createStatement();) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM clients");
			while (rs.next()) {
				Client c = new Client();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setStreet(rs.getString(4));
				c.setHouseNumber(rs.getInt(5));
				c.setCountry(rs.getInt(6));
				c.setDateofbirth(rs.getString(7));
				c.setPostalCode(rs.getInt(8));
				c.setPremium(rs.getInt(9));
				l.add(c);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	public static Client getClientById(int id) {
		Client c = new Client();
		try (Connection con = DriverManager.getConnection(connString);
				PreparedStatement stmt = con.prepareStatement("SELECT * FROM clients WHERE id=?");) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setLastName(rs.getString(3));
				c.setStreet(rs.getString(4));
				c.setHouseNumber(rs.getInt(5));
				c.setCountry(rs.getInt(6));
				c.setDateofbirth(rs.getString(7));
				c.setPostalCode(rs.getInt(8));
				c.setPremium(rs.getInt(9));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public static boolean updateClientAddress(int id, String street, int houseNumber, int postalCode) {
		try (Connection con = DriverManager.getConnection(connString);
				PreparedStatement stmt = con
						.prepareStatement("UPDATE clients SET street=?, HouseNumber=?, postalcode=? where id=?")) {
			stmt.setString(1, street);
			stmt.setInt(2, houseNumber);
			stmt.setInt(3, postalCode);
			stmt.setInt(4, id);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static ArrayList<Account> getAllAccountOfClient(int id) {
		ArrayList<Account> l = new ArrayList<Account>();
		try (Connection con = DriverManager.getConnection(connString);
				PreparedStatement stmt = con.prepareStatement("select * from accounts where user=?")) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Account c = new Account();
				c.setAccountNumber(rs.getString(1));
				c.setCurrency(rs.getInt(2));
				c.setType(rs.getString(3));
				c.setLimit(rs.getInt(4));
				c.setFee(rs.getInt(5));
				c.setPaymentcredit(rs.getInt(6));
				c.setAmmount(rs.getInt(7));
				c.setUser(rs.getInt(8));
				l.add(c);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	public static ArrayList<Account> getAllAccountOfClientByCurrencyCode(int id, int code) {
		ArrayList<Account> l = new ArrayList<Account>();
		try (Connection con = DriverManager.getConnection(connString);
				PreparedStatement stmt = con.prepareStatement("select * from accounts where user=? and currency=?")) {
			stmt.setInt(1, id);
			stmt.setInt(2, code);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Account c = new Account();
				c.setAccountNumber(rs.getString(1));
				c.setCurrency(rs.getInt(2));
				c.setType(rs.getString(3));
				c.setLimit(rs.getInt(4));
				c.setFee(rs.getInt(5));
				c.setPaymentcredit(rs.getInt(6));
				c.setAmmount(rs.getInt(7));
				c.setUser(rs.getInt(8));
				l.add(c);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	public static void transfer(int idReceiver, int idSender, int ammount, int currencyCode) throws TransferException{
		ArrayList<Account> lar=getAllAccountOfClientByCurrencyCode(idReceiver, currencyCode);
		if(lar.size()==0)
			throw new TransferException("Receiver have not account for this currency");
		ArrayList<Account> las=getAllAccountOfClientByCurrencyCode(idSender, currencyCode);
		if(las.size()==0)
			throw new TransferException("Sender have not account for this currency");
		Client cs=getClientById(idSender);
		Client cr=getClientById(idReceiver);
		int ammounts=ammount;
		if(cs.getPremium()!=1)
			ammounts+=ammount*0.01;
		if(las.get(0).getAmmount()-ammounts<0)
			throw new TransferException("Sender have not fund");
		if(lar.get(0).getCurrency()!=las.get(0).getCurrency())
			throw new TransferException("Currencies is not same");
		try (Connection con = DriverManager.getConnection(connString);
				PreparedStatement stmt = con.prepareStatement("UPDATE accounts SET ammount=ammount-? where user=? and AccounNumber=?"); 
				PreparedStatement stmt1=con.prepareStatement("UPDATE accounts SET ammount=ammount+? where user=? and AccounNumber=?")) {
				stmt.setInt(1, ammounts);
				stmt.setInt(2, cs.getId());
				stmt.setString(3, las.get(0).getAccountNumber());
				stmt.executeUpdate();
				stmt1.setInt(1, ammount);
				stmt1.setInt(2, cr.getId());
				stmt1.setString(3, lar.get(0).getAccountNumber());
				stmt1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Connection conn = null; try { // db parameters // String url =
		 * "jdbc:sqlite:C:/sqlite/db/chinook.db"; // create a connection to the database
		 * conn = DriverManager.getConnection(connString);
		 * 
		 * System.out.println("Connection to SQLite has been established.");
		 * 
		 * } catch (SQLException e) { System.out.println(e.getMessage()); }
		 */
		ArrayList<Client> l = getAllClients();
		for (Client c : l)
			System.out.println(c);
	//	updateClientAddress(1, "Urbana Street", 1, 60007);
		//System.out.println(getClientById(1));
		ArrayList<Account> al= getAllAccountOfClient(1);
		for (Account c : al)
			System.out.println(c);
		ArrayList<Currency> lc=getCurrencies();
		for(Currency c:lc)
			System.out.println(c);;
//		 try {
			//transfer(2, 1, 2000, 3);
//		} catch (TransferException e) {
	//		// TODO Auto-generated catch block
		//	System.out.println(e.getMessage());
			
	//	}
	}
}