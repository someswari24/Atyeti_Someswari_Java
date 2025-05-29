package robustStudentDataProcessor.model;

import java.util.Arrays;

public class Student {
    private String studentId;
    private String name;
    private double[] scores;

    public Student(String studentId, String name, double[] scores) {
        this.studentId = studentId;
        this.name = name;
        this.scores = scores;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double[] getScores() {
        return scores;
    }

    public double calculateAverage() {
        double total = 0;
        for (double score:scores) {
            total+=score;
        }
        return total/scores.length;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", scores=" + Arrays.toString(scores) +
                '}';
    }
}
