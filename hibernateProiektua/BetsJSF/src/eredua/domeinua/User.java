package eredua.domeinua;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userID;
	
	private String izena;
	
	private String eposta;
	
	private String pasahitza;
	
	public User() {
		super();
	}
	
	public User(String izena, String eposta, String pasahitza) {
		this.izena = izena;
		this.eposta = eposta;
		this.pasahitza = pasahitza;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getEposta() {
		return eposta;
	}

	public void setEposta(String eposta) {
		this.eposta = eposta;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	
}