import java.util.ArrayList;

public class Course {
    private String name;
    private String courseCode;
    private static ArrayList<Course> courseList = new ArrayList<Course>(); /// only temporary

    public Course(String aName, String aCode){
        name = aName;
        courseCode = aCode;
        courseList.add(this);
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return courseCode;
    }

    public static ArrayList<Course> getCourseList(){
        return new ArrayList<Course>(courseList);
    }
}
