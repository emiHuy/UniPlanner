import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FileOperations {
    public static void saveData(){
        saveAccountsList();
        saveAccountData();
    }

    private static void saveAccountData(){
        // Make account-specific file name
        String fileName = HomeScreen.userAccount.getUsername() + ".txt";

        int year;
        int month;
        int day;

        try{
            File file = new File(fileName);
            FileWriter fileWrite = new FileWriter(file, false);

            BufferedWriter buffWrite = new BufferedWriter(fileWrite);

            for(Map.Entry<String,String> entry: HomeScreen.userAccount.getCalendarData().entrySet()){
                buffWrite.write(entry.getKey() + "|,|" + entry.getValue()+"|.|");
                }
            buffWrite.newLine();

            for(Course course: HomeScreen.userAccount.getCourseList()){
                buffWrite.write(course.getName() + ", " + course.getCode() + ": ");
                for(Evaluation evaluation: course.getEvaluations()){
                    // Get necessary parts of date to write in "yyyy-MM-dd" to text file
                    year = evaluation.getEvaluationDate().getYear() + 1900;
                    month = evaluation.getEvaluationDate().getMonth() + 1;
                    day = evaluation.getEvaluationDate().getDate();
                    // Write to text file
                    buffWrite.write(evaluation.getEvaluationName() + ", " + year + "-" + month + "-" + day + ", " + evaluation.getEvaluationScore() + ";");
                }
                buffWrite.newLine();
            }
            buffWrite.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveAccountsList(){
        try{
            File file = new File("accounts.txt");
            FileWriter fileWrite = new FileWriter(file, false);

            BufferedWriter buffWrite = new BufferedWriter(fileWrite);
            for(Account account: Account.getAccountsList()){
                // Write accounts in accountsList to text file
                buffWrite.write(account.getUsername() + ", " + account.getPassword() + ", " + account.getName());
                buffWrite.newLine();
            }
            buffWrite.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readAccountsList(){
        try{
            File file = new File("accounts.txt");
            FileReader fileRead = new FileReader(file);

            BufferedReader buffRead = new BufferedReader(fileRead);
            String line;
            String[] accountInfo;
            // Read data from accounts.txt file, line by line
            while((line = buffRead.readLine()) != null){
                // Split lines into 3 parts to get username, password, and name
                accountInfo = line.split(", ");
                // Create new Account object with the data above
                new Account(accountInfo[0], accountInfo[1], accountInfo[2]);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readAccountData(Account userAccount){
        String fileName = userAccount.getUsername() + ".txt";

        try{
            File file = new File(fileName);
            FileReader fileRead = new FileReader(file);

            BufferedReader buffRead = new BufferedReader(fileRead);

            String line;
            String[] lineSplit;
            String[] courseInfo;
            String[] courseEvaluations;

            line = buffRead.readLine();
            readCalendarData(line, userAccount);

            // Read data from user-specific text file, line by line
            while((line = buffRead.readLine()) != null){
                lineSplit = line.split(": ");
                // Split line to retrieve course data and data for course evaluations, if applicable
                courseInfo = new String[]{lineSplit[0]};
                if(lineSplit.length == 1){
                    readCourseInfo(courseInfo, null, userAccount);
                }
                else{
                    courseEvaluations = new String[]{lineSplit[1]};
                    readCourseInfo(courseInfo, courseEvaluations, userAccount);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readCourseInfo(String[] courseInfo, String[] courseEvaluations, Account userAccount){
        // Split string into parts to retrieve necessary course data
        String [] courseInfoSplit = courseInfo[0].split(", ");
        String courseName = courseInfoSplit[0];
        String courseCode = courseInfoSplit[1];
        // Create Course object with the necessary course data
        Course course = new Course(courseName, courseCode);
        userAccount.addCourse(course);

        // If there are any course evaluations, call method to read course evaluation data
        if(courseEvaluations != null){
            readEvaluations(course, courseEvaluations);
        }
    }

    private static void readEvaluations(Course course, String[] courseEvaluations){
        // Split string into individual evaluations
        String[] evaluationsSplit = courseEvaluations[0].split(";");

        // Initialize local variables
        String[] evaluationInfo;
        int count = 0;
        double mark;
        String name;
        Date date;

        for(String evaluation: evaluationsSplit){
            // Split string into parts to retrieve evaluation data
            evaluationInfo = evaluationsSplit[count++].split(", ");
            name = evaluationInfo[0];
            date = readEvalDate(evaluationInfo[1]);
            mark = Double.parseDouble(evaluationInfo[2]);
            // With evaluation data, create a new Evaluation object
            Evaluation eval = new Evaluation(name, mark, date);
            course.addEvaluation(eval);
        }
    }

    private static Date readEvalDate(String dateAsString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
            // Convert data in string format to date format
            date = dateFormat.parse(dateAsString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        // Return date in date format
        return date;
    }

    private static void readCalendarData(String line, Account userAccount){
        Map<String, String> calendarData = new HashMap<>();
        String[] entryList = line.split("\\|\\.\\|");
        String[] splitEntry;
        String entryName;
        String entryText;
        int count = 0;
        for(String entry: entryList){
            splitEntry = entryList[count++].split("\\|\\,\\|");
            if(splitEntry.length == 1){
                entryName = splitEntry[0];
                calendarData.put(entryName, "");
            }
            else{
                entryName = splitEntry[0];
                entryText = splitEntry[1];
                calendarData.put(entryName, entryText);
            }
        }
        userAccount.updateCalendarData(calendarData);
    }
}