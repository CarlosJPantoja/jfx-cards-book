package model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable{
	
	private static final long serialVersionUID = 2L;
	
	private String username;
	private String password;
	private String photo;
	private String gender;
	private LocalDate birthdate;
	private String phone;
	private String email;
	
	public User(String username, String password, String photo, String gender, LocalDate birthdate, String phone, 
			String email) {
		
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.gender = gender;
		this.birthdate = birthdate;
		this.phone = phone;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public boolean verifyPassword(String pass) {
		return pass.equals(password);
	}

	public String getPhoto() {
		return photo;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public String getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}
	
	
	
}
