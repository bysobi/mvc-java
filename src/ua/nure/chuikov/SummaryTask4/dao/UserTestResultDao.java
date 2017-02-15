package ua.nure.chuikov.SummaryTask4.dao;

import java.util.List;

import ua.nure.chuikov.SummaryTask4.entity.UserTestResult;

public interface UserTestResultDao {
    
    public int gettestMaxREsult(int i);
    public List<UserTestResult> getAll();
}
