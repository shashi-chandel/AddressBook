package com.capgemini.addressbookmain;

import java.util.Scanner;

public class AddressBookMain {
	public static void main(String[] args) {
		Address Obj = new Address();
		Obj.AddContact();
	}
}

class  Address {
	private static String first;
	private static String last;
	private static String city;
	private static String state;
	private static long zip;
	private static String phoneNum;
	private static String email;  
	
	public void CreateContact (String first, String last, String city, String state, long zip, String phoneNum, String email)  {
		System.out.println("Welcome to Address Book System!!");
		System.out.println("\nFirst name: " + first);
		System.out.println("Last name: " + last);
		System.out.println("City: " + city);
		System.out.println("State: " + state);
		System.out.println("Zip code: " + zip);
		System.out.println("Phone number: " + phoneNum);
		System.out.println("Email address: " + email);
		}
	public void AddContact () {
		Scanner sc=new Scanner(System.in);
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
		sc.nextLine();
		String email = sc.next();
		
		CreateContact(first,last,city,state,zip,phoneNum,email);
	}
}