import java.util.ArrayList;

public class Course {
    private String name;
    private String courseCode;
    private static ArrayList<Course> courseList = new ArrayList<Course>(); /// only temporary

    // EMMA: create ArrayList for evaluation object (must create evaluation class first)
    // EMMA: -> reference line above (except DO NOT make ArrayList static), put class name in between <>

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

    // EMMA: create method that updates ArrayList for evaluations
    // EMMA: this method must be public, take in evaluation object as argument and add it to list (it's like a one-line method)

    // EMMA: also create a getter for evaluations ArrayList (like I have done for course) --> make sure it returns a copy of the ArrayList
    // EMMA: basically, copy and paste the getter I wrote for the courseList, but DO NOT make it static
}
