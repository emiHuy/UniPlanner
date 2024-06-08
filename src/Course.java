
public class Course {
    private String name;
    private String courseCode;

    public Course(String aName, String aCode){
        name = aName;
        courseCode = aCode;
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return courseCode;
    }
}
