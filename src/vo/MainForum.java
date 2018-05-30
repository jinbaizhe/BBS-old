package vo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * MainForum entity. @author MyEclipse Persistence Tools
 */

public class MainForum implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String info;
	private Timestamp createTime;
	private Set subForums = new HashSet(0);

	// Constructors

	/** default constructor */
	public MainForum() {
	}

	/** full constructor */
	public MainForum(String name, String info, Timestamp createTime, Set subForums) {
		this.name = name;
		this.info = info;
		this.createTime = createTime;
		this.subForums = subForums;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set getSubForums() {
		return this.subForums;
	}

	public void setSubForums(Set subForums) {
		this.subForums = subForums;
	}

}