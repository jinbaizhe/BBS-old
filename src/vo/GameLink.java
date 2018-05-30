package vo;

/**
 * GameLink entity. @author MyEclipse Persistence Tools
 */

public class GameLink implements java.io.Serializable {

	// Fields

	private Integer id;
	private Game game;
	private String info;
	private Integer orderNum;
	private String note;

	// Constructors

	/** default constructor */
	public GameLink() {
	}

	/** full constructor */
	public GameLink(Game game, String info, Integer orderNum, String note) {
		this.game = game;
		this.info = info;
		this.orderNum = orderNum;
		this.note = note;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}