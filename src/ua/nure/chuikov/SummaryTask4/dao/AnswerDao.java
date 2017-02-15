/**
 * 
 */
package ua.nure.chuikov.SummaryTask4.dao;

import java.util.List;

import ua.nure.chuikov.SummaryTask4.entity.Answer;

/**
 * @author Yarik
 *
 */
public interface AnswerDao {
    public List<Answer> getAnswerByQuestionId(int testID);

    public void updateAnswer(int questionId, int answerNumber, Answer answer);

    public void addAnswerForQuestuinWithID(Answer answer);
    public List<Answer> getAllAnswers();
    public List<Answer> getAllAnswersByQuestionid(int i);
    
}
