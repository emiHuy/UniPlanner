
import java.util.Date;

public class Evaluation {
    public String evaluationName;
    private float evaluationScore;
    private Date evaluationDate;
    public Evaluation(String evaluationName, float evaluationScore, Date evaluationDate) {
        this.evaluationName = evaluationName;
        this.evaluationScore = evaluationScore;
        this.evaluationDate = evaluationDate;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public float getEvaluationScore() {
        return evaluationScore;
    }

    public Date getEvaluationDate() {
        return evaluationDate;
    }
}

