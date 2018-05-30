package vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Picture entity. @author MyEclipse Persistence Tools
 */

public class Picture implements java.io.Serializable {

	// Fields

	private Integer id;
	private String picture;
	private Timestamp uploadTime;
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Picture() {
	}

	/** full constructor */
	public Picture(String picture, Timestamp uploadTime, Set users) {
		this.picture = picture;
		this.uploadTime = uploadTime;
		this.users = users;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Timestamp getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}