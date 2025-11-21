package com.trainingdiary;

import com.trainingdiary.models.User;
import com.trainingdiary.services.AuthService;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ ===");

        AuthService auth = new AuthService();

        // Тест регистрации
        auth.register("user1", "pass1");
        auth.register("user2", "pass2");

        // Тест входа
        boolean loginSuccess = auth.login("user1", "pass1");
        System.out.println("Вход user1: " + loginSuccess);

        boolean loginFail = auth.login("user1", "wrong");
        System.out.println("Вход с неправильным паролем: " + loginFail);

        System.out.println("Тестирование завершено!");
    }
}