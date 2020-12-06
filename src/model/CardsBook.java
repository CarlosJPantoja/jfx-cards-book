package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class CardsBook{

	private User activeUser;
	
	private ArrayList<User> users;
	
	public CardsBook() {
		users = new ArrayList<User>();
		readSerialUsers();
	}
	
	public boolean verifyUsername(String username) {
		boolean exists = false;
		
		for(int i=0; i<users.size() && !exists; i++) {
			if(username.equals(users.get(i).getUsername())) {
				exists = true;
			}
		}
		
		return exists;
	}
	
	public boolean verifyCredentials(String username, String password) {
		boolean exists = false;
		boolean credentials = false;
		
		for(int i=0; i<users.size() && !exists; i++) {
			if(username.equals(users.get(i).getUsername())) {
				credentials = users.get(i).verifyPassword(password);
				if(credentials) {
					activeUser = users.get(i);
				}
				exists = true;
			}
		}
		
		return credentials;
	}
	
	private void writeSerialUsers() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/SerializableUsers.txt"));
			for(int i=0; i<users.size(); i++) {
				oos.writeObject(users.get(i));
			}
			oos.close();
		} catch(IOException e) {
			
		}
	}
	
	private void readSerialUsers() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/SerializableUsers.txt"));
			try {
				while(true) {
					User aux = (User)ois.readObject();
					users.add(aux);
				}
			} catch(EOFException e) {
				
			} catch (ClassNotFoundException e) {
				
			} 
			ois.close();
		}catch(IOException e) {
		} 
	}

	public void createAccount(String username, String password, String photo, String gender, LocalDate birthdate, 
			String phone, String email) {
		
		User user = new User(username, password, photo, gender, birthdate, phone, email);
		users.add(user);

		writeSerialUsers();
	}

	public User getActiveUser() {
		return activeUser;
	}
}
