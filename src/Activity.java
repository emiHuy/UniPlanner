import java.util.Date;

public class Activity {
    private String name;
    private Date date;

    public Activity(String name, Date date){
        this.name = name;
        this.date = date;
    }

    public String getName(){
        return name;
    }

    public Date getDate(){
        return date;
    }
}
