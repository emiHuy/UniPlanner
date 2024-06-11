import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private String username;
    private String password;
    private String name;
    private ArrayList<Course> courseList = new ArrayList<>();
    private static ArrayList<Account> accountsList = new ArrayList<>();
    private static Map<String, String> calendarData = new HashMap<>();


    public Account(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
        accountsList.add(this);
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public static ArrayList<Account> getAccountsList(){
        return new ArrayList<Account>(accountsList);
    }

    public ArrayList<Course> getCourseList(){
        return new ArrayList<Course>(courseList);
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public void updateCalendarData(Map<String, String> saveData){
        calendarData = saveData;
    }

    public Map<String, String> getCalendarData(){
        return calendarData;
    }
}
