// Sean O'Dell, CSE 142, Spring 2015, Section BG
// Programming Assignment #4, 04/28/15
//
// This program's behavior is to read exam and homework scores and report your minimum overall 
// course grade.

import java.util.*;

public class Gradanator {
   public static final int PARTICIPATION_MAX = 30;
   public static void main(String[] args) {
      Scanner console = new Scanner(System.in);
      intro();
      double wMidtermScore = test(console, "Midterm: ");
      double wFinalExamScore = test(console, "Final: ");
      double wHomeworkScore = homework(console);
      determineGrade(console, wMidtermScore, wFinalExamScore,
                     wHomeworkScore);
   }
   
   // States the purpose of this program
   public static void intro() {
      System.out.println("This program reads exam/homework scores");
      System.out.println("and reports your overall course grade.");
      System.out.println();
   }
   
   // Takes user imput and determines weighted score and prints passed test type.
   public static double test(Scanner console, String testType) {
      System.out.println(testType);
      int total;
      System.out.print("Weight (0-100)? ");
      int weight = console.nextInt();
      System.out.print("Score earned? ");
      int score = console.nextInt();
      System.out.print("Were scores shifted (1=yes, 2=no)? ");
      int shift = console.nextInt();
      if (shift == 1) {
         System.out.print("Shift amount? ");
         total = score + console.nextInt();
      } else {
         total = score;
      }
      total = Math.min(total, 100);
      return calculateWeightedScore(total, 100, weight);
   }
   
   // Takes user imput and determines weighted homework score.
   public static double homework(Scanner console) {
      System.out.println("Homework:");
      int scoredPoints = 0;
      int maxAssignmentPoints = 0;
      System.out.print("Weight (0-100)? ");
      int weight = console.nextInt();
      System.out.print("Number of assignments? ");
      int assigned = console.nextInt();
      for (int i = 1; i <= assigned; i++) {
         System.out.print("Assignment " + i + " score and max? ");
         scoredPoints += console.nextInt();
         maxAssignmentPoints += console.nextInt();
      }
      System.out.print("How many sections did you attend? ");
      int attended = console.nextInt();
      int sectionPoints = Math.min(attended * 5, PARTICIPATION_MAX);
      System.out.println("Section points = " + sectionPoints + " / " + PARTICIPATION_MAX);
      int pointsPossible = PARTICIPATION_MAX + maxAssignmentPoints;
      int pointsEarned = Math.min(sectionPoints + scoredPoints, pointsPossible);
      return calculateWeightedScore(pointsEarned, pointsPossible, weight);
   }
   
   // Calculates weighted score given the points earned, total number of points and the weighting.
   public static double calculateWeightedScore(int pointsEarned, int pointsPossible, int weight) {
      System.out.println("Total points = " + pointsEarned + " / " + pointsPossible);
      double scoreW = (double) pointsEarned / pointsPossible * weight;
      System.out.println("Weighted score = " + Math.round(scoreW * 10) / 10.0 + " / " + weight);
      System.out.println();
      return scoreW;
   }
   
   // Determines overall precentage and lowest possible gpa given weighted midterm, final, and
   // homework scores.
   public static void determineGrade(Scanner console, double wMidtermScore,
                                     double wFinalExamScore, double wHomeworkScore) {
      double overallPercent = Math.round((wMidtermScore + wFinalExamScore + wHomeworkScore) * 10) /
                                         10.0;
      System.out.println("Overall percentage = " + overallPercent);
      double gpa;
      String message;
      if (overallPercent >= 85) {
         gpa = 3.0;
         message = "There is always room for improvement!";
      } else if (overallPercent >= 75) {
         gpa = 2.0;
         message = "This is not \"passing\".";
      } else if (overallPercent >= 60) {
         gpa = 0.7;
         message = "Ummmm...";
      } else {
         gpa = 0.0;
         message = "You want to go home, and re-think your life";
      }
      System.out.println("Your grade will be at least: " + gpa);
      System.out.println(message);
   }
}