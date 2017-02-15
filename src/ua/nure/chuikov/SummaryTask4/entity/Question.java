package ua.nure.chuikov.SummaryTask4.entity;

public class Question {
    private int id;
    private String textOfQuestion;
    private int complixity;
    private int testId;

    public Question() {
	// TODO Auto-generated constructor stub
    }

    public Question(int questionId, String textOfQuestion, int complixity, int testId) {
	super();
	this.id = questionId;
	this.textOfQuestion = textOfQuestion;
	this.complixity = complixity;
	this.testId = testId;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTextOfQuestion() {
	return textOfQuestion;
    }

    public void setTextOfQuestion(String textOfQuestion) {
	this.textOfQuestion = textOfQuestion;
    }

    public int getComplixity() {
	return complixity;
    }

    public void setComplixity(int complixity) {
	this.complixity = complixity;
    }

    public int getTestId() {
	return testId;
    }

    public void setTestId(int testId) {
	this.testId = testId;
    }

    @Override
    public String toString() {
	return "Question [id=" + id + ", textOfQuestion=" + textOfQuestion + ", complixity=" + complixity + ", testId="
		+ testId + "]";
    }

}
