package com.trainingdiary;

import com.trainingdiary.services.AuthService;
import com.trainingdiary.models.User;
import com.trainingdiary.models.Training;
import com.trainingdiary.models.Exercise;
import com.trainingdiary.models.Note;
import com.trainingdiary.services.TrainingService;
import com.trainingdiary.services.NoteService;

import java.time.LocalDate;
import java.time.LocalTime;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("фул тесты всего проекта");

        testAuthService();
        testModels();
        testTrainingService();
        testNoteService();
        testNewFeatures();

        System.out.println("\nтесты завершены");
    }

    private static void testAuthService() {
        System.out.println("\n--- Тесты AuthService ---");
        AuthService auth = new AuthService();

        boolean test1 = auth.register("test_user", "password123");
        System.out.println("Тест 1 - Регистрация: " + (test1 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        boolean test2 = auth.register("test_user", "anotherpass");
        System.out.println("Тест 2 - Дубликат логина: " + (!test2 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        boolean test3 = auth.login("test_user", "password123");
        System.out.println("Тест 3 - Вход верный: " + (test3 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        boolean test4 = auth.login("test_user", "wrongpass");
        System.out.println("Тест 4 - Вход неверный: " + (!test4 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        if (test3) {
            User current = auth.getCurrentUser();
            System.out.println("Тест 5 - Текущий пользователь: " +
                    (current != null && current.getLogin().equals("test_user") ? "✅ УСПЕХ" : "❌ ОШИБКА"));
        }
        auth.logout();
        System.out.println("Тест 6 - Выход: " + (!auth.isLoggedIn() ? "✅ УСПЕХ" : "❌ ОШИБКА"));
    }

    private static void testModels() {
        System.out.println("\n--- Тесты моделей данных ---");
        User user = new User("john", "pass123");
        System.out.println("Тест 1 - Создание User: " +
                (user.getLogin().equals("john") ? "✅ УСПЕХ" : "❌ ОШИБКА"));
        Training training = new Training(LocalDate.now(), LocalTime.of(14, 30), "Силовая");
        System.out.println("Тест 2 - Создание Training с временем: " +
                (training.getType().equals("Силовая") && training.getTime().getHour() == 14 ?
                        "✅ УСПЕХ" : "❌ ОШИБКА"));
        Exercise exercise = new Exercise("Приседания", 10, 70.5);
        System.out.println("Тест 3 - Создание Exercise: " +
                (exercise.getName().equals("Приседания") && exercise.getWeight() == 70.5 ?
                        "✅ УСПЕХ" : "❌ ОШИБКА"));
        Note userNote = new Note(LocalDate.now(), "Тестовая заметка");
        System.out.println("Тест 4 - Создание Note пользователя: " +
                (userNote.getText().equals("Тестовая заметка") && !userNote.isTrainerRecommendation() ?
                        "✅ УСПЕХ" : "❌ ОШИБКА"));
        Note trainerNote = new Note(LocalDate.now(), "Рекомендация", "TRAINER_RECOMMENDATION");
        System.out.println("Тест 5 - Создание Note тренера: " +
                (trainerNote.isTrainerRecommendation() ? "✅ УСПЕХ" : "❌ ОШИБКА"));
        user.addTraining(training);
        System.out.println("Тест 6 - User.addTraining(): " +
                (user.getTrainings().size() == 1 ? "✅ УСПЕХ" : "❌ ОШИБКА"));
        training.addExercise(exercise);
        System.out.println("Тест 7 - Training.addExercise(): " +
                (training.getExercises().size() == 1 ? "✅ УСПЕХ" : "❌ ОШИБКА"));
        user.addNote(userNote);
        System.out.println("Тест 8 - User.addNote(): " +
                (user.getNotes().size() == 1 ? "✅ УСПЕХ" : "❌ ОШИБКА"));
    }

    private static void testTrainingService() {
        System.out.println("\n--- Тесты TrainingService ---");
        TrainingService service = new TrainingService();
        User user = new User("test", "pass");

        Training training = new Training(LocalDate.now(), "Кардио");
        service.addTraining(user, training);
        System.out.println("Тест 1 - addTraining(): " +
                (user.getTrainings().size() == 1 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        var trainings = service.getUserTrainings(user);
        System.out.println("Тест 2 - getUserTrainings(): " +
                (trainings.size() == 1 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        service.removeTraining(user, 0);
        System.out.println("Тест 3 - removeTraining() правильный индекс: " +
                (user.getTrainings().isEmpty() ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        service.removeTraining(user, 999);
        System.out.println("Тест 4 - removeTraining() неправильный индекс: ✅ УСПЕХ (без ошибок)");
    }

    private static void testNoteService() {
        System.out.println("\n--- Тесты NoteService ---");
        NoteService service = new NoteService();
        User user = new User("note_user", "pass");

        service.addNote(user, "Первая заметка");
        System.out.println("Тест 1 - addNote(): " +
                (user.getNotes().size() == 1 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        var notes = service.getUserNotes(user);
        System.out.println("Тест 2 - getUserNotes(): " +
                (notes.size() == 1 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        service.updateNote(user, 0, "Обновленная заметка");
        System.out.println("Тест 3 - updateNote(): " +
                (notes.get(0).getText().equals("Обновленная заметка") ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        service.deleteNote(user, 0);
        System.out.println("Тест 4 - deleteNote(): " +
                (user.getNotes().isEmpty() ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        service.updateNote(user, 999, "Текст");
        System.out.println("Тест 5 - updateNote() неправильный индекс: ✅ УСПЕХ (без ошибок)");
    }

    private static void testNewFeatures() {
        System.out.println("\n--- Тесты новых функций ---");

        User user = new User("client", "pass");
        Note trainerNote = new Note(LocalDate.now(), "Рекомендация", "TRAINER_RECOMMENDATION");
        Note userNote = new Note(LocalDate.now(), "Моя заметка");

        user.addNote(trainerNote);
        user.addNote(userNote);

        System.out.println("Тест 1 - Тип заметки тренера: " +
                (trainerNote.isTrainerRecommendation() ? "✅ УСПЕХ" : "❌ ОШИБКА"));
        System.out.println("Тест 2 - Тип заметки пользователя: " +
                (!userNote.isTrainerRecommendation() ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        Training training = new Training(LocalDate.now(), LocalTime.of(18, 0), "Вечерняя");
        System.out.println("Тест 3 - Время тренировки: " +
                (training.getTime().getHour() == 18 ? "✅ УСПЕХ" : "❌ ОШИБКА"));

        System.out.println("=== Тестирование завершено ===");
    }
}