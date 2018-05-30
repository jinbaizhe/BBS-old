package vo;

import java.sql.Timestamp;

/**
 * Followpost entity. @author MyEclipse Persistence Tools
 */

public class Followpost implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Post post;
	private String content;
	private Timestamp sendTime;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public Followpost() {
	}

	/** full constructor */
	public Followpost(User user, Post post, String content, Timestamp sendTime, Timestamp updateTime) {
		this.user = user;
		this.post = post;
		this.content = content;
		this.sendTime = sendTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}