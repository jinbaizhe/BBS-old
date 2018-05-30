package vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Post entity. @author MyEclipse Persistence Tools
 */

public class Post implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private SubForum subForum;
	private String title;
	private String content;
	private Timestamp sendTime;
	private Timestamp updateTime;
	private Integer type;
	private Integer top;
	private Integer viewNum;
	private Set followposts = new HashSet(0);
	private Set collections = new HashSet(0);

	// Constructors

	/** default constructor */
	public Post() {
	}

	/** full constructor */
	public Post(User user, SubForum subForum, String title, String content, Timestamp sendTime, Timestamp updateTime,
			Integer type, Integer top, Integer viewNum, Set followposts, Set collections) {
		this.user = user;
		this.subForum = subForum;
		this.title = title;
		this.content = content;
		this.sendTime = sendTime;
		this.updateTime = updateTime;
		this.type = type;
		this.top = top;
		this.viewNum = viewNum;
		this.followposts = followposts;
		this.collections = collections;
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

	public SubForum getSubForum() {
		return this.subForum;
	}

	public void setSubForum(SubForum subForum) {
		this.subForum = subForum;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTop() {
		return this.top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getViewNum() {
		return this.viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Set getFollowposts() {
		return this.followposts;
	}

	public void setFollowposts(Set followposts) {
		this.followposts = followposts;
	}

	public Set getCollections() {
		return this.collections;
	}

	public void setCollections(Set collections) {
		this.collections = collections;
	}

}