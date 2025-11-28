package com.trainingdiary.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Training {
    private LocalDate date;
    private String type;
    private List<Exercise> exercises;
    private String comment;

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

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}