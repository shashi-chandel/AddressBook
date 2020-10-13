package com.capgemini.addressbookmain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

class AddressBookMain {
	
	Scanner sc = new Scanner(System.in);
	public List<Contact> addressList;
	private Map<String, Contact> addressBookMap;
	private Map<String, Contact> addressBookByCity;
	private Map<String, Contact> addressBookByState;

	public AddressBookMain() {
		addressList = new ArrayList<Contact>();
		addressBookMap = new HashMap<String, Contact>();
		addressBookByCity = new TreeMap<String, Contact>();
		addressBookByState = new TreeMap<String, Contact>();
	}

	public List<Contact> getAddressBookList() {
		return addressList;
	}

	public Map<String, Contact> getAddressBookMap() {
		return addressBookMap;
	}
	
	public Map<String, Contact> getAddressBookByCity() {
		return addressBookByCity;
	}

	public Map<String, Contact> getAddressBookByState() {
		return addressBookByState;
	}

	private void addContactPerson(Contact c) {
		addressList.add(c);
		addressBookMap.put(c.getFirstName(), c);
		System.out.println("\nContact Added\n");
	}

	private void editContactPerson(String firstName) {
		Contact cp = addressBookMap.get(firstName);
		if (cp == null) {
			System.out.println("\nNo such Person available !!\n");
		} else {
			System.out.println("Here is the Person Details to be edited " + cp);
			System.out.println(
					"Enter Updated Contact details of person in format: Address, City, State, Zip, Phone, email");
			String[] details = new String[6];
			for (int i = 0; i <= 5; i++) {
				details[i] = sc.next();
			}
			cp.setAddress(details[0]);
			cp.setCity(details[1]);
			cp.setState(details[2]);
			cp.setZip(details[3]);
			cp.setPhone(details[4]);
			cp.setEmail(details[5]);
			System.out.println("Updated Contact Details :");
			System.out.println(cp);
		}
	}

	public void deleteContactPerson(String name) {
		boolean b2 = false;
		Contact cp = addressBookMap.get(name);
		addressBookMap.remove(name);
		for (int i = 0; i < addressList.size(); i++) {
			if (addressList.get(i).getFirstName().equals(name)) {
				addressList.remove(i);
			}
		}
		System.out.println("Contact Deleted !!!");
	}

	/**
	 * UC7
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkForDuplicateName(String name) {
		for (Contact cd : addressList) {
			if (cd.getFirstName().equals(name)) {
				System.out.println("A Person with this name already exist !!\n");
				return true;
			}
		}
		return false;
	}

	public void maintainAddressBook() {

		while (true) {
			System.out.println("\n1. Add Contact Details");
			System.out.println("\n2. Edit Contact Details");
			System.out.println("\n3. Delete Contact Details");
			System.out.println("\n4. Exit");
			System.out.println("\nEnter your choice");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				while (true) {
					System.out.println("Enter Contact details of the person\n");
					String[] details = new String[8];
					System.out.println("Enter First Name :");
					details[0] = sc.next();
					if (checkForDuplicateName(details[0]))
						continue;
					System.out.println("Enter Last Name :");
					details[1] = sc.next();
					System.out.println("Enter Address :");
					details[2] = sc.next();
					System.out.println("Enter City :");
					details[3] = sc.next();
					System.out.println("Enter State :");
					details[4] = sc.next();
					System.out.println("Enter Zip :");
					details[5] = sc.next();
					System.out.println("Enter Phone :");
					details[6] = sc.next();
					System.out.println("Enter e-mail :");
					details[7] = sc.next();
					Contact c = new Contact(details[0], details[1], details[2], details[3], details[4], details[5],
							details[6], details[7]);

					addContactPerson(c);
					break;
				}
				break;
			case 2:
					if (addressList.size() == 0) {
						System.out.println("No Contacts Available !!! \n Please add some ");
						break;
					}
					System.out.println("Enter the first name to edit the contact details");
					String name = sc.next();
					editContactPerson(name);
				break;
			case 3:
					if (addressList.size() == 0) {
						System.out.println("No Contacts Available !!! \n Please add some ");
						break;
					}
					System.out.println("Enter the first name to delete the contact details");
					String dname = sc.next();
					if (addressBookMap.containsKey(dname))
						deleteContactPerson(dname);
					else
						System.out.println("No such Contact available !");
				break;
			default:
				break;
			}
			if (choice == 4)
				break;
			else
				System.out.println("\nEnter option");
		}
	}
}
public class AddressBookManagement {

	public Map<String, AddressBookMain> addressBookManagement = new TreeMap<String, AddressBookMain>();
	public Map<String, List<Contact>> personsByState = new TreeMap<String, List<Contact>>();
	public Map<String, List<Contact>> personsByCity = new TreeMap<String, List<Contact>>();
	
	public void showAddressBooks() {

		System.out.println("\nList of Address Books Added: \n");
		addressBookManagement.forEach((k, v) -> System.out.println(k + " " + v.addressList + "\n"));
	}
	
	public void countPerson() {
		personsByCity = new TreeMap<String, List<Contact>>();
		createMapForCity();
		System.out.println("Cities");
		personsByCity.forEach((k, v) -> {
			System.out.print("\n" + k);
			System.out.print("  " + v.stream().count());
		});
		personsByState = new TreeMap<String, List<Contact>>();
		createMapForState();
		System.out.println("\nStates");
		personsByState.forEach((k, v) -> {
			System.out.print("\n" + k);
			System.out.print("  " + v.stream().count());
		});

	}
	
	public void searchPerson(String searchForPerson) {
		Predicate<Contact> search = n -> n.getFirstName().equals(searchForPerson) ? true : false;
		Consumer<Contact> display = n -> System.out.println(n);
		addressBookManagement.forEach((k, v) -> {
			v.getAddressBookList().stream().filter(search).forEach(display);
		});
	}

	private void createMapForCity() {
		addressBookManagement.forEach((k, v) -> {
			v.getAddressBookByCity().forEach((k1, v1) -> {
				if (personsByCity.containsKey(k1)) {
					List<Contact> list = personsByCity.get(k1);
					list.add(v1);
				} else {
					List<Contact> list = new ArrayList<Contact>();
					list.add(v1);
					personsByCity.put(k1, list);
				}
			});
		});
	}
	private void createMapForState() {
		addressBookManagement.forEach((k, v) -> {
			v.getAddressBookByCity().forEach((k1, v1) -> {
				if (personsByState.containsKey(k1)) {
					List<Contact> list = personsByState.get(k1);
					list.add(v1);
				} else {
					List<Contact> list = new ArrayList<Contact>();
					list.add(v1);
					personsByState.put(k1, list);
				}
			});
		});
	}

	private void searchPersonByCityOrState(String searchIn) {

		if (searchIn.equalsIgnoreCase("city")) {
			personsByCity = new TreeMap<String, List<Contact>>();
			createMapForCity();
			personsByCity.forEach((k, v) -> {
				System.out.println(k);
				v.stream().forEach(n -> System.out.println(n));
			});

		} else if (searchIn.equalsIgnoreCase("state")) {
			personsByState = new TreeMap<String, List<Contact>>();
			createMapForState();
			personsByState.forEach((k, v) -> {
				System.out.println(k);
				v.stream().forEach(n -> System.out.println(n));
			});
		} else
			System.out.println("Wrong Input");
	}
	
	public void sort() {
		List<Contact> list = new ArrayList<Contact>();
		addressBookManagement.values().forEach(n -> n.getAddressBookList().forEach(n1 -> list.add(n1)));
		Collections.sort(list, (Contact c1 , Contact c2)-> c1.getFirstName().compareTo(c2.getFirstName()));
		list.forEach(n->System.out.println(n));
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		AddressBookManagement a = new AddressBookManagement();
		System.out.println("\n***** Welcome to Address Book Program *****\n");

		while (true) {
			System.out.println("\n1. Add a new Address Book");
			System.out.println("\n2. Search person across all address books");
			System.out.println("\n3. Show names of Address Books");
			System.out.println("\n4. Show persons by city or state");
			System.out.println("\n5. Show Count of persons by city or state");
			System.out.println("\n6. Sort By First Name");
			System.out.println("\n7. Exit");
			System.out.println("\nEnter your choice");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				while (true) {
					AddressBookMain m = new AddressBookMain();
					System.out.println("Enter name of the Address Book");
					String name = sc.next();
					if (a.addressBookManagement.containsKey(name)) {
						System.out.println("\nAddress Book already exists !!!\n");
						continue;
					} else {
						a.addressBookManagement.put(name, m);
						System.out.println("\n In Address Book : " + name);
						m.maintainAddressBook();
						break;
					}
				}
				break;

			case 2:
				System.out.println("Enter city or state to search a person");
				String searchIn = sc.next();
				a.searchPerson(searchIn);
				break;

			case 3:
				a.showAddressBooks();
				break;

			case 4:
				System.out.println("Enter city or state");
				String searchName = sc.next();
				a.searchPersonByCityOrState(searchName);
				break;

			case 5:
				System.out.println("Showing Count of Persons by City and State");
				a.countPerson();
				break;
				
			case 6:
				a.sort();
				break;

			default:
				break;
			}

			if (choice == 7)
				break;
			else
				System.out.println("\nEnter option");
		}
		System.out.println("\nThank You !!!");
		sc.close();
	}
}

class Contact {

	private String first;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;

	public Contact(String firstName, String lastName, String address, String city, String state, String zip,
			String phone, String email) {
		this.first = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	public String getFirstName() {
		return first;
	}

	public void setFirstName(String firstName) {
		this.first = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object o) {
		Contact c = (Contact) o;
		return c.first.equals(this.first);
	}

	public String toString() {
		return "\n Name " + first + " " + lastName + "\n Address " + address + "\n city " + city + "\n state " + state
				+ "\n zip " + zip + "\n Phone " + phone + "\n email " + email;
	}
}