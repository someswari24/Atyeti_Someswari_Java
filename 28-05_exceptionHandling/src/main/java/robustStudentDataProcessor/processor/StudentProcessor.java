package robustStudentDataProcessor.processor;

import robustStudentDataProcessor.exception.IndexException;
import robustStudentDataProcessor.exception.InvalidStudentDataException;
import robustStudentDataProcessor.exception.ValueException;
import robustStudentDataProcessor.model.Student;

public class StudentProcessor {
    public static Student studentData(String line,int lineNumber){
        String[] split = line.split(",");
        if (split.length<5){
            throw new IndexException("missing some fields");
        }

        String studentId=split[0].trim();
        String name=split[1].trim();

        if(studentId.isEmpty()||name.isEmpty()){
            throw new InvalidStudentDataException("data is missing");
        }

        double[] scores=new double[3];
        for (int i=0;i<3;i++) {
            try {
                scores[i] = Double.parseDouble(split[i + 2].trim());
            } catch (NumberFormatException e) {
                throw new ValueException("Invalid score value - " + split[i + 2].trim());
            }

            if (scores[i]<0 || scores[i]>100) {
                throw new InvalidStudentDataException("Score out of valid range (0-100): " + scores[i]);
            }
        }

        return new Student(studentId,name,scores);
    }

}
