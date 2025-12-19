package com.trainingdiary.services;

import com.trainingdiary.models.User;
import com.trainingdiary.models.Training;
import com.trainingdiary.models.Note; // ВАЖНО: добавлен импорт
import java.util.ArrayList;
import java.util.List;

public class TrainerService {

    public List<User> getAllClients(List<User> allUsers) {
        List<User> clients = new ArrayList<>();
        for (User user : allUsers) {
            if (!user.isTrainer()) {
                clients.add(user);
            }
        }
        return clients;
    }

    public User findUserByLogin(List<User> allUsers, String login) {
        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public List<Training> viewUserTrainings(User user) {
        return user.getTrainings();
    }

    public void addRecommendation(User trainer, User client, String recommendationText) {
        String fullText = "Рекомендация от тренера " + trainer.getLogin() +
                ": " + recommendationText;
        Note recommendation = new Note(
                java.time.LocalDate.now(),
                fullText,
                "TRAINER_RECOMMENDATION"
        );
        client.addNote(recommendation);
    }
}