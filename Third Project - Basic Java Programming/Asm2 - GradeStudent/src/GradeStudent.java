/* import packages */
import java.util.*;
import java.lang.Math;

public class GradeStudent {
    //declare global variable
    public static final Scanner input = new Scanner(System.in);
    public static double midTermWeightScore, finalTermWeightScore, homeworkWeightScore;
    //main
    public static void main(String[] args) {

        begin();
        System.out.println();
        int midTermWeight = midTerm();
        int finalTermWeight = finalTerm();
        int homeworkWeight = homework();
        //validate weight numbers (midterm, final and homework)
        while (midTermWeight + finalTermWeight + homeworkWeight != 100) {
            System.out.println("Please enter appropriate value (total 3 weight must be equal to 100)!");
            System.out.println();

            midTermWeight = midTerm();
            System.out.println();
            finalTermWeight = finalTerm();
            System.out.println();
            homeworkWeight = homework();
            System.out.println();
        }

        report();
    }
    //begin method: print introduction
    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade");
    }
    //midTerm method: calculate midterm score
    public static int midTerm() {
        int totalPoint, shiftAmount;

        System.out.println("Midterm: ");
        System.out.print("Weight (0 - 100)? ");
        int midTermWeight = input.nextInt();

        System.out.print("Score earned? ");
        int scoreEarned = input.nextInt();
        //loop for validating value yes or no
        while (true) {
            System.out.print("Were score shifted (1=yes, 2=no)? ");
            int scoreShifted = input.nextInt();
            if (scoreShifted == 1 || scoreShifted == 2) {
                if (scoreShifted == 1) {
                    System.out.print("Shift amount? ");
                    shiftAmount = input.nextInt();
                } else {
                    shiftAmount = 0;
                }
                break;
            } else {
                System.out.println("Please enter 1 or 2!");
            }
        }

        totalPoint = scoreEarned + shiftAmount;
        //validating total points
        if (totalPoint > 100) {
            totalPoint = 100;
        }
        System.out.println("Total points = " + totalPoint + " / 100");

        //calculate weighted score
        midTermWeightScore = (double) Math.round((totalPoint / 100.0 * midTermWeight) * 10) / 10;
        System.out.println("Weighted score = " + midTermWeightScore + " / " + midTermWeight);
        System.out.println();
        return midTermWeight;
    }
    //finalTerm method: calculate final score
    public static int finalTerm() {
        int totalPoint, shiftAmount;

        System.out.println("Final: ");
        System.out.print("Weight (0 - 100)? ");
        int finalTermWeight = input.nextInt();

        System.out.print("Score earned? ");
        int scoreEarned = input.nextInt();
        //loop for validating yes or no
        while (true) {
            System.out.print("Were score shifted (1=yes, 2=no)? ");
            int scoreShifted = input.nextInt();
            if (scoreShifted == 1 || scoreShifted == 2) {
                if (scoreShifted == 1) {
                    System.out.print("Shift amount? ");
                    shiftAmount = input.nextInt();
                } else {
                    shiftAmount = 0;
                }
                break;
            } else {
                System.out.println("Please enter 1 or 2!");
            }
        }

        totalPoint = scoreEarned + shiftAmount;
        //validating total points
        if (totalPoint > 100) {
            totalPoint = 100;
        }
        System.out.println("Total points = " + totalPoint + " / 100");

        //calculate weighted score
        finalTermWeightScore = (double) Math.round((totalPoint / 100.0 * finalTermWeight) * 10) / 10;
        System.out.println("Weighted score = " + finalTermWeightScore + " / " + finalTermWeight);
        System.out.println();
        return finalTermWeight;
    }
    //homework method: calculate homework score
    public static int homework() {
        System.out.println("Homework: ");
        System.out.print("Weight (0 - 100)? ");
        int homeworkWeight = input.nextInt();

        System.out.print("Number of assignments? ");
        int numAssign = input.nextInt();
        int[][] scoreAndMax = new int[numAssign][2]; //create a 2D array to store input score and max values

        for (int i = 0; i < numAssign; i++) {
            System.out.print("Assignment " + (i+1) + " score and max? ");
            int score = input.nextInt();
            int max = input.nextInt();
            //loop for validating input score and max score
            while (score > max) {
                System.out.println("Please enter again (score input must not be higher than max score input)!");

                System.out.print("Assignment " + (i+1) + " score and max? ");
                score = input.nextInt();
                max = input.nextInt();
            }
            scoreAndMax[i][0] = score; //assign input score value to 2D array
            scoreAndMax[i][1] = max; //assign input max value to 2D array
        }

        System.out.print("How many sections did you attend? ");
        int numAttend = input.nextInt();
        int sectionPoint = numAttend * 5;
        int maxAttend = 30;
        //validate section point
        if (sectionPoint > maxAttend) {
            sectionPoint = maxAttend;
        }
        System.out.println("Section points = " + sectionPoint + " / " + maxAttend);

        //calculate total score and total max
        int totalScore = 0;
        int maxScore = 0;
        for (int[] score : scoreAndMax) {
            totalScore += score[0];
            maxScore += score[1];
        }
        //validate total score and total max
        if (totalScore > 150) {
            totalScore = 150;
        }
        if (maxScore > 150) {
            maxScore = 150;
        }
        int total = totalScore + sectionPoint;
        int totalMax = maxScore + maxAttend;
        System.out.println("Total points = " + total + " / " + totalMax);

        //calculate weighted score
        homeworkWeightScore = (double) Math.round(((double) total / totalMax) * homeworkWeight * 10) / 10;
        System.out.println("Weighted score = " + homeworkWeightScore + " / " + homeworkWeight);
        System.out.println();
        return homeworkWeight;
    }
    //report method : print statistics to console
    public static void report() {
        double totalGrade = (double) Math.round((midTermWeightScore + finalTermWeightScore + homeworkWeightScore) * 10) / 10;
        double minGPA;
        //assign minGPA values ranked by total grades
        if (totalGrade >= 85) {
            minGPA = 3.0;
        } else if (totalGrade <= 84.99 && totalGrade >= 75) {
            minGPA = 2.0;
        } else if (totalGrade <= 74.99 && totalGrade >= 60) {
            minGPA = 0.7;
        } else {
            minGPA = 0.0;
        }
        System.out.println("Overall percentage = " + totalGrade);
        System.out.println("Your grade will be at least : " + minGPA);

        /* ranked score alphabetically by minGPA and print to console */
        if (minGPA == 3.0) {
            System.out.println("Congratulations! You get grade A. Keep it up!");
        } else if (minGPA == 2.0) {
            System.out.println("You get grade B. Try harder to gain better next semester!");
        } else if (minGPA == 0.7) {
            System.out.println("You get grade C. Need to concentrate more!");
        } else {
            System.out.println("Too bad! You get grade D. Focus more on your study!");
        }
    }
}
