package com.capgemini.addressbookmain;

public class AddressBookMain {
	public static void main(String[] args) {
		Address Obj=new Address();
		Obj.CreateContact("Rahul", "Singh", "Ranchi", "Jharkhand", 856515, "8956565852", "rahul1304@gmail.com");	
		}
}
class Address {
	public void CreateContact(String first, String last, String city, String state, long zip, String phoneNum, String email) {
		System.out.println("Welcome to Address Book System!!\n");
		System.out.println("First name: " + first);
		System.out.println("Last name: " + last);
		System.out.println("City: " + city);
		System.out.println("State: " + state);
		System.out.println("Zip code: " + zip);
		System.out.println("Phone number: " + phoneNum);
		System.out.println("Email address: " + email);
	}
}