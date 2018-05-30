package vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Game entity. @author MyEclipse Persistence Tools
 */

public class Game implements java.io.Serializable {

	// Fields

	private Integer id;
	private String home;
	private String away;
	private Date date;
	private String url;
	private String note;
	private Set gameLinks = new HashSet(0);

	// Constructors

	/** default constructor */
	public Game() {
	}

	/** full constructor */
	public Game(String home, String away, Date date, String url, String note, Set gameLinks) {
		this.home = home;
		this.away = away;
		this.date = date;
		this.url = url;
		this.note = note;
		this.gameLinks = gameLinks;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHome() {
		return this.home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return this.away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getGameLinks() {
		return this.gameLinks;
	}

	public void setGameLinks(Set gameLinks) {
		this.gameLinks = gameLinks;
	}

}