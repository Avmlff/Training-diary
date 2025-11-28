package com.trainingdiary.services;

import com.trainingdiary.models.Training;
import com.trainingdiary.models.User;
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
}