package com.capgemini.addressbookmain;

import java.util.*;

 class AddressBooksMain {
	private static List<Address> addressList;
	private static Map<String, Address> addressMap;

	public AddressBooksMain() {
		addressList = new ArrayList<>();     
		addressMap = new HashMap<>();
	}

	private void AddContact(Address a) {
		addressList.add(a);
		addressMap.put(a.getFirst(), a);
	}

	private void EditContact(String name, String city, String state, String zip1, String phoneNum, String email) {
		Address ad = addressMap.get(name);
		if (ad == null) {
			System.out.println("\nSuch Person is not available !!\n");
		} else {
			ad.setCity(city);
			ad.setState(state);
			ad.setZip(zip1);
			ad.setPhoneNum(phoneNum);
			ad.setEmail(email);
			System.out.println("\nDetails After edit");
			System.out.println(ad);
		}
	}

	private void DeleteContact(String name) {
		boolean b = false;
		Address ad = addressMap.get(name);
		addressMap.remove(name);
		for (int i = 0; i < addressList.size(); i++) {
			if (addressList.get(i).getFirst().equals(name)) {
				addressList.remove(i);
			}
		}
		System.out.println("Contact deleted!!");
	}

	public static boolean checkForDuplicate(String name) {
		for (Address address : addressList) {
			if (address.getFirst().equals(name)) {
				System.out.println("Contact already exists!");
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AddressBooksMain addressBookObj = new AddressBooksMain();
		String ans = " ";
		do {
			System.out.println("Enter \n1 for new insertion: \n2 for edition: \n3 for deleteion: ");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("Welcome to Address Book System!!\n");
				System.out.println("Enter your first name: ");
				String first = sc.next();
				if (checkForDuplicate(first))
					continue;
				System.out.println("Enter your last name: ");
				String last = sc.next();
				System.out.println("Enter your city: ");
				String city = sc.next();
				System.out.println("Enter your state: ");
				String state = sc.next();
				System.out.println("Enter your zip: ");
				String zip = sc.next();
				System.out.println("Enter your phone number: ");
				String phoneNum = sc.next();
				System.out.println("Enter your email: ");
				String email = sc.next();
				Address addObj = new Address(first, last, city, state, zip, phoneNum, email);
				addressBookObj.AddContact(addObj);
				System.out.println("Contact added! \n");
				System.out.println(addObj);
				break;
			case 2:
				System.out.println("Enter first name whose address you want to edit: ");
				String name = sc.next();
				if (addressMap.containsKey(name)) {
					System.out.println("Enter your city: ");
					String city1 = sc.next();
					System.out.println("Enter your state: ");
					String state1 = sc.next();
					System.out.println("Enter your zip: ");
					String zip1 = sc.next();
					System.out.println("Enter your phone number: ");
					String phoneNum1 = sc.next();
					System.out.println("Enter your email: ");
					sc.nextLine();
					String email1 = sc.next();
					addressBookObj.EditContact(name, city1, state1, zip1, phoneNum1, email1);
				} else {
					System.out.println("Such contact is not available for edit.");
				}
				break;

			case 3:
				System.out.println("Enter the name whose details you want to delete:");
				String delName = sc.next();
				if (addressMap.containsKey(delName)) {
					addressBookObj.DeleteContact(delName);
					System.out.println("After deletion List size " + addressList.size());
					System.out.println("After deletion Map values " + addressMap.values());
				} else
					System.out.println("Such Contact is not available !");
				break;
			default:
				System.out.println("Invalid input!!");
			}
			System.out.println("\n Do you want more insertion, edition or deletion ?(YES or yes): ");
			ans = sc.next();
		} while (ans.equals("YES") || (ans.equals("yes")));
		System.out.println("List size: " + addressList.size());
		System.out.println("Map values: " + addressMap.values());
	}
}

class Address {

	private String first;
	private String last;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;
	private String email;

	public Address(String first, String last, String city, String state, String zip, String phoneNum, String email) {
		this.first = first;
		this.last = last;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Name: " + first + " " + last + "\ncity: " + city + "\nstate: " + state + "\nzip: " + zip
				+ "\nphoneNum: " + phoneNum + "\nemail: " + email;
	}
}
