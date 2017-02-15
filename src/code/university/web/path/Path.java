package code.university.web.path;

public class Path {
    public static final String PAGE_WELCOME = "WEB-INF/jsp/welcome.jsp";
    public static final String PAGE_LOGIN = "WEB-INF/jsp/login.jsp";
    public static final String PAGE_REGISTRATION = "WEB-INF/jsp/registration.jsp";
    public static final String PAGE_INVALID_COMMAND = "WEB-INF/jsp/error.jsp";
    public static final String PAGE_ERROR = "WEB-INF/jsp/error.jsp";
    public static final String PAGE_LIST_USERS = "WEB-INF/jsp/admin/list_users.jsp";
    public static final String PAGE_EDIT_USER = "WEB-INF/jsp/admin/edit_user.jsp";
    public static final String PAGE_TESTS_LIST = "WEB-INF/jsp/admin/tests_list.jsp";
    public static final String PAGE_EDIT_TEST = "WEB-INF/jsp/admin/edit_test.jsp";
    public static final String PAGE_NEW_TEST = "WEB-INF/jsp/admin/new_test.jsp";
    public static final String PAGE_ADDING_QUESTOIN_AND_ANSWERS = "WEB-INF/jsp/admin/adding_questions.jsp";
    public static final String PAGE_PASSING_TEST = "WEB-INF/jsp/user/show_question_and_answers.jsp";
    public static final String PAGE_USERS_TESTS_LIST = "WEB-INF/jsp/user/users_tests_list.jsp";
    public static final String PAGE_USER_INFO = "WEB-INF/jsp/user/users_info_and_test_info.jsp";
    public static final String PAGE_TES_INFO = "WEB-INF/jsp/user/users_info_and_test_info.jsp";
    
    public static final String PAGE_RESULT_TESTS_LIST = "WEB-INF/jsp/admin/results_tests_list.jsp";
    
    
    
    public static final String COMMAND_LIST_TESTS = "controller?command=listTest";
    public static final String COMMAND_LIST_USERS = "controller?command=listUsers";
    public static final String COMMAND_QUESTIONS_LISTS = "controller?command=passingTest";
    public static final String COMMAND_NOT_ACTIVE = "controller?command=notActive";
    public static final String COMMAND_ACCESS_DENIED = "controller?command=noaccess";
	
}
