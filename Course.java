import java.util.ArrayList;

public class Course {
    private String name;
    private String courseCode;
    private static ArrayList<Course> courseList = new ArrayList<Course>(); /// only temporary

    // EMMA: create ArrayList for evaluation object (must create evaluation class first)
    private ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
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
    public void addEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }
    // EMMA: also create a getter for evaluations ArrayList (like I have done for course) --> make sure it returns a copy of the ArrayList
    // EMMA: basically, copy and paste the getter I wrote for the courseList, but DO NOT make it static.
    public ArrayList<Evaluation> getEvaluations() {
        return new ArrayList<Evaluation>(evaluations);
    }
    public float calculateAverageScore() {
        if (evaluations.isEmpty()) {
            return 0; // Return 0 if there are no evaluations
        }

        float totalScore = 0;
        for (Evaluation evaluation : evaluations) {
            totalScore += evaluation.getEvaluationScore();
        }

        return totalScore / evaluations.size();
    }

}
