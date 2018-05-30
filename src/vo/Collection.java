package vo;

import java.sql.Timestamp;

/**
 * Collection entity. @author MyEclipse Persistence Tools
 */

public class Collection implements java.io.Serializable {

	// Fields

	private CollectionId id;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** minimal constructor */
	public Collection(CollectionId id) {
		this.id = id;
	}

	/** full constructor */
	public Collection(CollectionId id, Timestamp time) {
		this.id = id;
		this.time = time;
	}

	// Property accessors

	public CollectionId getId() {
		return this.id;
	}

	public void setId(CollectionId id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}