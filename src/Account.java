import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private String name;
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<Activity> activityList = new ArrayList<>();
    private static ArrayList<Account> accountsList = new ArrayList<>();

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

    public ArrayList<Activity> getActivityList(){
        return new ArrayList<Activity>(activityList);
    }

    public void addCourse(Course course){
        courseList.add(course);
    }

    public void addActivity(Activity activity){
        activityList.add(activity);
    }

    public void bubbleSortCourseList() {
        int n = courseList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (courseList.get(j).getName().compareTo(courseList.get(j + 1).getName()) > 0) {
                    Course temp = courseList.get(j);
                    courseList.set(j, courseList.get(j + 1));
                    courseList.set(j + 1, temp);
                }
            }
        }
    }
}