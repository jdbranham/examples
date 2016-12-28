package com.example;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;

	@ManyToOne(cascade=CascadeType.MERGE)
	private User parent;
	@OneToMany(mappedBy = "parent", fetch=FetchType.EAGER)
	private Set<User> children = new HashSet<>();

	protected User() {
	}

	public User(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	public User(String firstName, String lastName, User parent) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.parent = parent;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public User getParent() {
		return parent;
	}

	public void setParent(User parent) {
		this.parent = parent;
	}

	public Set<User> getChildren() {
		return children;
	}

	public void setChildren(Set<User> children) {
		this.children = children;
	}
	
	public void addChild(User user){
		this.children.add(user);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", parent=" + parent + "]";
	}

}
