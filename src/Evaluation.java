
import java.util.Date;

public class Evaluation {
    public String evaluationName;
    private double evaluationScore;
    private Date evaluationDate;

    public Evaluation(String evaluationName, double evaluationScore, Date evaluationDate) {
        this.evaluationName = evaluationName;
        this.evaluationScore = evaluationScore;
        this.evaluationDate = evaluationDate;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public double getEvaluationScore() {
        return evaluationScore;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }
}