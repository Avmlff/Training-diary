package com.trainingdiary.services;

import com.trainingdiary.models.Training;
import com.trainingdiary.models.Exercise;
import com.trainingdiary.models.User;
import java.time.LocalDate;
import java.util.List;

public class AnalyticsService {

    public int getTrainingsCount(User user, LocalDate startDate, LocalDate endDate) {
        int count = 0;
        for (Training training : user.getTrainings()) {
            if (!training.getDate().isBefore(startDate) && !training.getDate().isAfter(endDate)) {
                count++;
            }
        }
        return count;
    }

    public void showWeightProgress(User user, String exerciseName) {
        System.out.println("=== ПРОГРЕСС ПО УПРАЖНЕНИЮ: " + exerciseName + " ===");
        for (Training training : user.getTrainings()) {
            for (Exercise exercise : training.getExercises()) {
                if (exercise.getName().equalsIgnoreCase(exerciseName)) {
                    System.out.println(training.getDate() + ": " + exercise.getWeight() + "кг");
                }
            }
        }
    }

    public void showRepsProgress(User user, String exerciseName) {
        System.out.println("=== ПРОГРЕСС ПО ПОВТОРЕНИЯМ: " + exerciseName + " ===");
        for (Training training : user.getTrainings()) {
            for (Exercise exercise : training.getExercises()) {
                if (exercise.getName().equalsIgnoreCase(exerciseName)) {
                    System.out.println(training.getDate() + ": " + exercise.getReps() + " повт.");
                }
            }
        }
    }

    public int getTotalTrainingsCount(User user) {
        return user.getTrainings().size();
    }
}