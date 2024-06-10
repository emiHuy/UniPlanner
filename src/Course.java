import java.util.ArrayList;

public class Course {
    private String name;
    private String courseCode;
    private static ArrayList<Course> courseList = new ArrayList<Course>(); /// only temporary
    private ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();

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

    public void addEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }

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
