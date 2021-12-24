package eredua.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import eredua.domeinua.User;

public class LogInBean {
	
	private BLFacade facadeBL;
	private String eposta;
	private String izena;
	private String pasahitza;
	private User user;
	
	public LogInBean() {
		this.facadeBL = FacadeBean.getBusinessLogic();
	}
	
	public BLFacade getFacadeBL() {
		return facadeBL;
	}
	
	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
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
	
	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String logeatu() {
		User u = facadeBL.logeatu(eposta, pasahitza);
		try {
			if(u.getIzena() != null) {
				System.out.println("Erabiltzailea ondo logeatu da");
				this.setIzena(u.getIzena());
				this.setUser(u);
				return "ok";
			}
			else {
				return "error";	
			}
		}
		catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ezin izan da saioa hasi"));
			return "error";
		}
	}

	public String mezua() {
		return this.user.getIzena() + "moduan logeatuta zaude, ateratzeko 'Back' botoia sakatu";
	}
}
