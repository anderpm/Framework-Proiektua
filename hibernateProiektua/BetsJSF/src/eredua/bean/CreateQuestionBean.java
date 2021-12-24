package eredua.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import eredua.domeinua.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean {
	
	private BLFacade facadeBL;
	private Date data;
	private List<Event> eventak;
	private Event event;
	private String question;
	private float minBet;
	
	public CreateQuestionBean() {
		this.facadeBL = FacadeBean.getBusinessLogic();
		this.eventak = new ArrayList<Event>();
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}	
	
	public List<Event> getEventak(){
		return eventak;
	}
	
	public void setEventak(List<Event> eventak) {
		this.eventak = eventak;
	}
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public float getMinBet() {
		return minBet;
	}

	public void setMinBet(float minBet) {
		this.minBet = minBet;
	}

	public void onDateSelect(SelectEvent event) {
		Date dataSelect = (Date)event.getObject();
		this.eventak.clear();
		this.eventak = facadeBL.getEvents(dataSelect);
		System.out.println(eventak.size());
	}
	
	public void create() {		
		if(this.event == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Gertaera bat aukeratu behar da"));
		}
		else if(this.question.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Galdera idatzi behar da"));
		}
		else if(this.minBet<0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Apustu minimoa 0 edo handiagoa izan behar da"));
		}
		else {
			try {
				facadeBL.createQuestion(event, question, minBet);
			} catch (EventFinished e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(event + " gertaera amaitu egin da"));
				e.printStackTrace();
			} catch (QuestionAlreadyExist e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Galdera existitzen da"));
				e.printStackTrace();
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Galdera ondo sortu da"));
			this.event = null;
			this.question = "";
			this.minBet = 0;
		}
	}

}
