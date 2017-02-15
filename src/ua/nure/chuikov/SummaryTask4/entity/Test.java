package ua.nure.chuikov.SummaryTask4.entity;

import java.sql.Time;

public class Test {
    private int id;
    private String subjectName;
    private String testName;
    private Time timeToPass;
    private int complexity;

    public Test(String subjectName, String testName, String timeToPass) {
	super();
	this.subjectName = subjectName;
	this.testName = testName;
	this.timeToPass = Time.valueOf(timeToPass);
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTestName() {
	return testName;
    }

    public void setTestName(String testName) {
	this.testName = testName;
    }

    public Time getTimeToPass() {
	return timeToPass;
    }

    public void setTimeToPass(Time time) {
	this.timeToPass = time;
    }

    public String getSubjectName() {
	return subjectName;
    }

    public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
    }

    public int getComplexity() {
	return complexity;
    }

    public void setComplexity(int complexity) {
	this.complexity = complexity;
    }

    public Test() {
    }

    @Override
    public String toString() {
	return "Test [id=" + id + ", subjectName=" + subjectName + ", testName=" + testName + ", timeToPass="
		+ timeToPass + "]";
    }

}
