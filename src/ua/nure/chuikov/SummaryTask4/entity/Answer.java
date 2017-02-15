package ua.nure.chuikov.SummaryTask4.entity;

public class Answer {
    private int id;
    private int qustionId;
    private int answerNumber;
    private String textOfAnswer;
    private Boolean flag;

    public Answer() {
	// TODO Auto-generated constructor stub
    }

    public Answer(int questionId, int answerNumber, String textOfAnswer, Boolean flag) {
	super();
	this.qustionId = questionId;
	this.answerNumber = answerNumber;
	this.textOfAnswer = textOfAnswer;
	this.flag = flag;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getQustionId() {
	return qustionId;
    }

    public void setQustionId(int qustionId) {
	this.qustionId = qustionId;
    }

    public int getAnswerNumber() {
	return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
	this.answerNumber = answerNumber;
    }

    public String getTextOfAnswer() {
	return textOfAnswer;
    }

    public void setTextOfAnswer(String textOfAnswer) {
	this.textOfAnswer = textOfAnswer;
    }

    public Boolean getFlag() {
	return flag;
    }

    public void setFlag(Boolean flag) {
	this.flag = flag;
    }

    @Override
    public String toString() {
	return "Answer [id=" + id + ", qustionId=" + qustionId + ", answerNumber=" + answerNumber + ", testOfAnswer="
		+ textOfAnswer + ", flag=" + flag + "]";
    }

}
