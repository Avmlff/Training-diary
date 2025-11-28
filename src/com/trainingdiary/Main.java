package com.trainingdiary;

import com.trainingdiary.models.Training;
import com.trainingdiary.models.Exercise;
import com.trainingdiary.models.User;
import com.trainingdiary.models.Note;
import com.trainingdiary.services.AuthService;
import com.trainingdiary.services.TrainingService;
import com.trainingdiary.services.NoteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static AuthService authService = new AuthService();
    private static TrainingService trainingService = new TrainingService();
    private static NoteService noteService = new NoteService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== ДНЕВНИК ТРЕНИРОВОК ===");
        runApplication();
    }

    private static void runApplication() {
        while (true) {
            if (!authService.isLoggedIn()) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private static void showAuthMenu() {
        System.out.println("\n1. Регистрация");
        System.out.println("2. Вход");
        System.out.println("3. Выход");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> System.exit(0);
            default -> System.out.println("Неверный выбор!");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("Добро пожаловать, " + authService.getCurrentUser().getLogin());
        System.out.println("1. Мои тренировки");
        System.out.println("2. Добавить тренировку");
        System.out.println("3. Удалить тренировку");
        System.out.println("4. Добавить комментарий к тренировке");
        System.out.println("5. Мои заметки");
        System.out.println("6. Добавить заметку");
        System.out.println("7. Выйти из аккаунта");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> showTrainings();
            case 2 -> addTraining();
            case 3 -> deleteTraining();
            case 4 -> addCommentToTraining();
            case 5 -> showNotes();
            case 6 -> addNote();
            case 7 -> authService.logout();
            default -> System.out.println("Неверный выбор!");
        }
    }

    private static void register() {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (authService.register(login, password)) {
            System.out.println("Регистрация успешна!");
        } else {
            System.out.println("Пользователь с таким логином уже существует!");
        }
    }

    private static void login() {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (authService.login(login, password)) {
            System.out.println("Вход выполнен успешно!");
        } else {
            System.out.println("Неверный логин или пароль!");
        }
    }

    private static void showTrainings() {
        User currentUser = authService.getCurrentUser();
        List<Training> trainings = trainingService.getUserTrainings(currentUser);

        if (trainings.isEmpty()) {
            System.out.println("У вас пока нет тренировок.");
            return;
        }

        System.out.println("\n=== ВАШИ ТРЕНИРОВКИ ===");
        for (int i = 0; i < trainings.size(); i++) {
            Training training = trainings.get(i);
            System.out.println((i + 1) + ". " + training.getDate() + " - " + training.getType());

            // Показываем комментарий если есть
            if (training.getComment() != null && !training.getComment().isEmpty()) {
                System.out.println("   Комментарий: " + training.getComment());
            }

            System.out.println("   Упражнений: " + training.getExercises().size());

            // Показываем упражнения
            for (Exercise exercise : training.getExercises()) {
                System.out.println("   - " + exercise.getName() + ": " +
                        exercise.getReps() + " повт. × " + exercise.getWeight() + " кг");
            }
            System.out.println();
        }
    }

    private static void addTraining() {
        System.out.print("Введите дату тренировки (гггг-мм-дд): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        System.out.print("Введите тип тренировки: ");
        String type = scanner.nextLine();

        Training training = new Training(date, type);

        // Добавление упражнений
        while (true) {
            System.out.print("Добавить упражнение? (да/нет): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("да")) break;

            System.out.print("Название упражнения: ");
            String name = scanner.nextLine();
            System.out.print("Количество повторений: ");
            int reps = scanner.nextInt();
            System.out.print("Вес (кг): ");
            double weight = scanner.nextDouble();
            scanner.nextLine();

            Exercise exercise = new Exercise(name, reps, weight);
            training.addExercise(exercise);
            System.out.println("Упражнение добавлено!");
        }

        trainingService.addTraining(authService.getCurrentUser(), training);
        System.out.println("Тренировка добавлена!");
    }

    private static void deleteTraining() {
        User currentUser = authService.getCurrentUser();
        List<Training> trainings = trainingService.getUserTrainings(currentUser);

        if (trainings.isEmpty()) {
            System.out.println("Нет тренировок для удаления.");
            return;
        }

        showTrainings();
        System.out.print("Введите номер тренировки для удаления: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < trainings.size()) {
            trainingService.removeTraining(currentUser, index);
            System.out.println("Тренировка удалена!");
        } else {
            System.out.println("Неверный номер тренировки!");
        }
    }

    // ДОБАВИЛ: Добавление комментария к тренировке
    private static void addCommentToTraining() {
        User currentUser = authService.getCurrentUser();
        List<Training> trainings = trainingService.getUserTrainings(currentUser);

        if (trainings.isEmpty()) {
            System.out.println("Нет тренировок для добавления комментария.");
            return;
        }

        showTrainings();
        System.out.print("Введите номер тренировки для комментария: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < trainings.size()) {
            System.out.print("Введите комментарий: ");
            String comment = scanner.nextLine();

            Training training = trainings.get(index);
            training.setComment(comment);
            System.out.println("Комментарий добавлен!");
        } else {
            System.out.println("Неверный номер тренировки!");
        }
    }

    // ДОБАВИЛ: Просмотр заметок
    private static void showNotes() {
        User currentUser = authService.getCurrentUser();
        List<Note> notes = noteService.getUserNotes(currentUser);

        if (notes.isEmpty()) {
            System.out.println("У вас пока нет заметок.");
            return;
        }

        System.out.println("\n=== ВАШИ ЗАМЕТКИ ===");
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            System.out.println((i + 1) + ". " + note.getDate() + ": " + note.getText());
        }
    }

    // ДОБАВИЛ: Добавление заметки
    private static void addNote() {
        System.out.print("Введите текст заметки: ");
        String text = scanner.nextLine();

        noteService.addNote(authService.getCurrentUser(), text);
        System.out.println("Заметка добавлена!");
    }
}