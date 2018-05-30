package vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private Picture picture;
	private String username;
	private String password;
	private String info;
	private String sex;
	private String email;
	private Integer status;
	private Integer type;
	private Integer level;
	private Timestamp registerTime;
	private String activeKey;
	private Set followposts = new HashSet(0);
	private Set posts = new HashSet(0);
	private Set messages = new HashSet(0);
	private Set logs = new HashSet(0);
	private Set collections = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Picture picture, String username, String password, String info, String sex, String email,
			Integer status, Integer type, Integer level, Timestamp registerTime, String activeKey, Set followposts,
			Set posts, Set messages, Set logs, Set collections) {
		this.picture = picture;
		this.username = username;
		this.password = password;
		this.info = info;
		this.sex = sex;
		this.email = email;
		this.status = status;
		this.type = type;
		this.level = level;
		this.registerTime = registerTime;
		this.activeKey = activeKey;
		this.followposts = followposts;
		this.posts = posts;
		this.messages = messages;
		this.logs = logs;
		this.collections = collections;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Picture getPicture() {
		return this.picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Timestamp getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public String getActiveKey() {
		return this.activeKey;
	}

	public void setActiveKey(String activeKey) {
		this.activeKey = activeKey;
	}

	public Set getFollowposts() {
		return this.followposts;
	}

	public void setFollowposts(Set followposts) {
		this.followposts = followposts;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

	public Set getLogs() {
		return this.logs;
	}

	public void setLogs(Set logs) {
		this.logs = logs;
	}

	public Set getCollections() {
		return this.collections;
	}

	public void setCollections(Set collections) {
		this.collections = collections;
	}

}