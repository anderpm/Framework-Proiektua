package eredua.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import eredua.domeinua.User;

public class RegisterBean {

	private BLFacade facadeBL;
	private String izena;
	private String eposta;
	private String pasahitza;
	private String pasahitza2;
	
	public RegisterBean() {
		this.facadeBL = FacadeBean.getBusinessLogic();
	}

	public BLFacade getFacadeBL() {
		return facadeBL;
	}

	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
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

	public String getPasahitza2() {
		return pasahitza2;
	}

	public void setPasahitza2(String pasahitza2) {
		this.pasahitza2 = pasahitza2;
	}
	
	public void erregistratu() {
		if(this.eposta.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Posta elektronikoa jarri behar da"));
		}
		else if(this.izena.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Izena jarri behar da"));
		}
		else if(this.pasahitza.equals("") || this.pasahitza2.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pasahitza jarri behar da"));
		}
		else if(!this.pasahitza.equals(this.pasahitza2)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pasahitzak ez datoz bat"));
		}
		else {
			User user = new User(this.izena, this.eposta, this.pasahitza);
			facadeBL.erregistratu(user);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ondo erregistratu zara"));
		}
		
	}
	
}