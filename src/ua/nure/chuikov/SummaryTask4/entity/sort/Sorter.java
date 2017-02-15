package ua.nure.chuikov.SummaryTask4.entity.sort;

import java.util.Comparator;

import ua.nure.chuikov.SummaryTask4.entity.UserTestResult;

public class Sorter {
    public static final Comparator<UserTestResult> LINE_CMP_NUMBER = new Comparator<UserTestResult>() {
	@Override
	public int compare(UserTestResult o1, UserTestResult o2) {
	    return o1.getResult() - o2.getResult();
	}
    };

}
