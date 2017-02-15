/**
 * 
 */
package ua.nure.chuikov.SummaryTask4.dao;

import java.sql.SQLException;
import java.util.List;

import ua.nure.chuikov.SummaryTask4.entity.Test;

/**
 * @author Yarik
 *
 */
public interface TestDao {
    public Test getTestById(int id);//Executed
    public List<Test> getTestBySubject(int subjectId) throws SQLException;//Executed
    public Test getTestByName(String name) throws SQLException;//Executed
    public List<Test> getAllTest();//Executed
    public void updateTest(int test, Test t);
    public int getTestByAmountOfQuestions(int testid) throws SQLException;//Executed
    public int getTestByComplixity(int testID);
    public int createTest(Test newTest);
    public Test getFullTest(int id);
    
    public void removeTestById(int id); 
}
