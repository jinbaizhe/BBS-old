package vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * SubForum entity. @author MyEclipse Persistence Tools
 */

public class SubForum implements java.io.Serializable {

	// Fields

	private Integer id;
	private MainForum mainForum;
	private String name;
	private String info;
	private Timestamp createTime;
	private Set posts = new HashSet(0);

	// Constructors

	/** default constructor */
	public SubForum() {
	}

	/** full constructor */
	public SubForum(MainForum mainForum, String name, String info, Timestamp createTime, Set posts) {
		this.mainForum = mainForum;
		this.name = name;
		this.info = info;
		this.createTime = createTime;
		this.posts = posts;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MainForum getMainForum() {
		return this.mainForum;
	}

	public void setMainForum(MainForum mainForum) {
		this.mainForum = mainForum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

}