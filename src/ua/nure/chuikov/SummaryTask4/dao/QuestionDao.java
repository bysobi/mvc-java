package ua.nure.chuikov.SummaryTask4.dao;

import java.util.List;

import ua.nure.chuikov.SummaryTask4.entity.Question;

public interface QuestionDao {
    public int addQuestion(Question quest);// Executed
    public void removeQuestionById(int questionId);

    // public void setComplexity();

    public void updateQuestion(int id, Question quest);

    public Question getQuestionByText(String textOfQuestion);
    public List<Question> getAllQuestionByTestId(int id);// Executed
    // public int getComplexity(int testid);

}
