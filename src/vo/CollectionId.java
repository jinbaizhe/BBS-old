package vo;

/**
 * CollectionId entity. @author MyEclipse Persistence Tools
 */

public class CollectionId implements java.io.Serializable {

	// Fields

	private User user;
	private Post post;

	// Constructors

	/** default constructor */
	public CollectionId() {
	}

	/** full constructor */
	public CollectionId(User user, Post post) {
		this.user = user;
		this.post = post;
	}

	// Property accessors

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CollectionId))
			return false;
		CollectionId castOther = (CollectionId) other;

		return ((this.getUser() == castOther.getUser()) || (this.getUser() != null && castOther.getUser() != null
				&& this.getUser().equals(castOther.getUser())))
				&& ((this.getPost() == castOther.getPost()) || (this.getPost() != null && castOther.getPost() != null
						&& this.getPost().equals(castOther.getPost())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUser() == null ? 0 : this.getUser().hashCode());
		result = 37 * result + (getPost() == null ? 0 : this.getPost().hashCode());
		return result;
	}

}