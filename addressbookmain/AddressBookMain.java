package com.capgemini.addressbookmain;

import java.util.*;

public class AddressBookMain {
			private List<Address> addressList;
			private Map<String, Address> addressMap;
		
			public AddressBookMain() {
				addressList = new ArrayList<>();
				addressMap = new HashMap<>();
			}
		
			private void AddContact(Address a) {
				addressList.add(a);
				addressMap.put(a.getFirst(), a);
			}
		
			private void EditContact(String name, String city, String state, long zip, String phoneNum, String email) {
				Address ad = addressMap.get(name);
				ad.setCity(city);
				ad.setState(state);
				ad.setZip(zip);
				ad.setPhoneNum(phoneNum);
				ad.setEmail(email);
				System.out.println("Details After edit");
				System.out.println(ad);
			}
			
			private void DeleteContact(String name) {
				boolean b = false;
				Address ad = addressMap.get(name);
				addressMap.remove(name);
				for (int i=0; i<addressList.size(); i++) {
					if(addressList.get(i).getFirst().equals(name)) {
						addressList.remove(i);
					}
				}
				System.out.println("Contact deleted!!");
			}
		

	public static void main(String[] args) {
				Scanner sc = new Scanner(System.in);
				AddressBookMain addressBookObj = new AddressBookMain();
				System.out.println("Welcome to Address Book System!!\n");
				System.out.println("Enter your first name: ");
				String first = sc.nextLine();
				System.out.println("Enter your last name: ");
				String last = sc.nextLine();
				System.out.println("Enter your city: ");
				String city = sc.nextLine();
				System.out.println("Enter your state: ");
				String state = sc.nextLine();
				System.out.println("Enter your zip: ");
				long zip = sc.nextLong();
				System.out.println("Enter your phone number: ");
				String phoneNum = sc.next();
				System.out.println("Enter your email: ");
				String email = sc.next();
				Address addObj = new Address(first, last, city, state, zip, phoneNum, email);
				addressBookObj.AddContact(addObj);
				System.out.println("Enter 1 for edit and 2 for delete: ");
				int ch=sc.nextInt();
				switch (ch) {
				case 1:
					System.out.println("Enter first name whose address you want to edit: ");
					String name = sc.next();
					System.out.println("Enter your city: ");
					String city1 = sc.next();
					System.out.println("Enter your state: ");
					String state1 = sc.next();
					System.out.println("Enter your zip: ");
					long zip1 = sc.nextLong();
					System.out.println("Enter your phone number: ");
					String phoneNum1 = sc.next();
					System.out.println("Enter your email: ");
					sc.nextLine();
					String email1 = sc.next();
					addressBookObj.EditContact(name, city1, state1, zip1, phoneNum1, email1);
					break;
				
				case 2:
					System.out.println("Enter the name whose details you want to delete:");
					String delName = sc.nextLine();
					addressBookObj.DeleteContact(delName);
					System.out.println("After deletion List size " + addressBookObj.addressList.size());
					System.out.println("After deletion Map values " + addressBookObj.addressMap.values());
					break;
				default:
					System.out.println("Invalid input!!");
				} 
				
			}
		}

class Address {

			private String first;
			private String last;
			private String city;
			private String state;
			private long zip;
			private String phoneNum;
			private String email;
		
			public Address(String first, String last, String city, String state, long zip, String phoneNum, String email) {
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
		
			public long getZip() {
				return zip;
			}
		
			public void setZip(long zip) {
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
