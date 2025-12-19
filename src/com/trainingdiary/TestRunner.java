package com.trainingdiary;

import com.trainingdiary.services.AuthService;
import com.trainingdiary.models.User;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("тесты дневника тренировок");

        AuthService auth = new AuthService();
        int passedTests = 0;
        int totalTests = 6;

        System.out.print("Тест 1 - Регистрация пользователя: ");
        boolean test1 = auth.register("user1", "pass1");
        if (test1) {
            System.out.println("УСПЕХ");
            passedTests++;
        } else {
            System.out.println("ОШИБКА");
        }

        System.out.print("Тест 2 - Дубликат логина: ");
        boolean test2 = auth.register("user1", "pass2");
        if (!test2) {
            System.out.println("УСПЕХ (правильно отказал)");
            passedTests++;
        } else {
            System.out.println("ОШИБКА");
        }

        System.out.print("Тест 3 - Вход с правильным паролем: ");
        boolean test3 = auth.login("user1", "pass1");
        if (test3) {
            System.out.println("УСПЕХ");
            passedTests++;
        } else {
            System.out.println("ОШИБКА");
        }

        System.out.print("Тест 4 - Вход с неправильным паролем: ");
        boolean test4 = auth.login("user1", "wrong");
        if (!test4) {
            System.out.println("УСПЕХ (правильно отказал)");
            passedTests++;
        } else {
            System.out.println("ОШИБКА");
        }

        System.out.print("Тест 5 - Регистрация тренера: ");
        boolean test5 = auth.registerTrainer("тренер2", "pass", "TRAINER123");
        if (test5) {
            System.out.println("УСПЕХ");
            passedTests++;
        } else {
            System.out.println("ОШИБКА");
        }

        System.out.print("Тест 6 - Регистрация тренера с неверным кодом: ");
        boolean test6 = auth.registerTrainer("тренер3", "pass", "WRONGCODE");
        if (!test6) {
            System.out.println("УСПЕХ (правильно отказал)");
            passedTests++;
        } else {
            System.out.println("ОШИБКА");
        }

        System.out.println("=======================================");
        System.out.println("Пройдено тестов: " + passedTests + " из " + totalTests);
        System.out.println("тесты завершены");
    }
}