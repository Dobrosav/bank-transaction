package database.entities;

public class Client {
	private int id;
	private String name;
	private String lastName;
	private String Street;
	private int HouseNumber;
	private int country;
	private String dateofbirth;
	private int postalCode;
	private int premium;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public int getHouseNumber() {
		return HouseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		HouseNumber = houseNumber;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public int getPremium() {
		return premium;
	}

	public void setPremium(int premium) {
		this.premium = premium;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + ":" + name + ":" + lastName + ":" + Street + ":" + HouseNumber + ":" + dateofbirth+":"+premium;
	}

}
