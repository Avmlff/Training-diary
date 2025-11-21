package com.trainingdiary.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Training {
    private LocalDate date;
    private String type;
    private List<Exercise> exercises;

    public Training(LocalDate date, String type) {
        this.date = date;
        this.type = type;
        this.exercises = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}