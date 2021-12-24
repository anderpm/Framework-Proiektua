package eredua.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import eredua.domeinua.Event;
import eredua.domeinua.Question;

public class FindQuestionBean {

	private BLFacade facadeBL;
	private Date data;
	private List<Event> eventak;
	private Event event;
	private List<Question> questions;
	private Question question;
	
	
	public FindQuestionBean() {
		this.facadeBL = FacadeBean.getBusinessLogic();
		this.eventak = new ArrayList<Event>();
		this.questions = new ArrayList<Question>();
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

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void onDateSelect(SelectEvent event) {
		Date dataSelect = (Date)event.getObject();
		this.eventak = null;
		this.eventak = facadeBL.getEvents(dataSelect);
	}
	
	public void onEventSelect(SelectEvent event) {
		Event eventSelect = (Event)event.getObject();
		this.questions = null;
		this.questions = eventSelect.getQuestions();
	}

	public void hustu() {
		this.data = null;
		this.eventak = null;
		this.event = null;
		this.questions = null;
		this.question = null;
	}
}
