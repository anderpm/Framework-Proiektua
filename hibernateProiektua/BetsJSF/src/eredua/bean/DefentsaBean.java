package eredua.bean;

import java.util.ArrayList;
import java.util.List;

import businessLogic.BLFacade;
import eredua.domeinua.Question;

public class DefentsaBean {

	private BLFacade facadeBL;
	private List<Question> questions;
	private Question question;
	
	public DefentsaBean() {
		this.facadeBL = FacadeBean.getBusinessLogic();
		this.questions = new ArrayList<Question>();
	}

	public BLFacade getFacadeBL() {
		return facadeBL;
	}

	public void setFacadeBL(BLFacade facadeBL) {
		this.facadeBL = facadeBL;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question getQuestion() {
		this.questions = facadeBL.getAllQuestions();
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public void hustu() {
		this.questions = null;
		this.question = null;
	}
		
}