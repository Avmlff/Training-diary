package com.trainingdiary.services;

import com.trainingdiary.models.Training;
import com.trainingdiary.models.Exercise;
import com.trainingdiary.models.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingService {

    public void addTraining(User user, Training training) {
        user.addTraining(training);
    }

    public void removeTraining(User user, int index) {
        user.removeTraining(index);
    }

    public List<Training> getUserTrainings(User user) {
        return user.getTrainings();
    }

    public void updateTraining(User user, int index, LocalDate newDate, String newType) {
        if (index >= 0 && index < user.getTrainings().size()) {
            Training training = user.getTrainings().get(index);
            training.setDate(newDate);
            training.setType(newType);
        }
    }

    public List<Training> findTrainingsByDate(User user, LocalDate date) {
        List<Training> result = new ArrayList<>();
        for (Training training : user.getTrainings()) {
            if (training.getDate().equals(date)) {
                result.add(training);
            }
        }
        return result;
    }

    public List<Training> findTrainingsByExercise(User user, String exerciseName) {
        List<Training> result = new ArrayList<>();
        for (Training training : user.getTrainings()) {
            for (Exercise exercise : training.getExercises()) {
                if (exercise.getName().equalsIgnoreCase(exerciseName)) {
                    result.add(training);
                    break;
                }
            }
        }
        return result;
    }

    public void addExerciseToTraining(User user, int trainingIndex, Exercise exercise) {
        if (trainingIndex >= 0 && trainingIndex < user.getTrainings().size()) {
            Training training = user.getTrainings().get(trainingIndex);
            training.addExercise(exercise);
        }
    }

    public void updateExercise(User user, int trainingIndex, int exerciseIndex, String newName, int newReps, double newWeight) {
        if (trainingIndex >= 0 && trainingIndex < user.getTrainings().size()) {
            Training training = user.getTrainings().get(trainingIndex);
            if (exerciseIndex >= 0 && exerciseIndex < training.getExercises().size()) {
                Exercise exercise = training.getExercises().get(exerciseIndex);
                exercise.setName(newName);
                exercise.setReps(newReps);
                exercise.setWeight(newWeight);
            }
        }
    }
}