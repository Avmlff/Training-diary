package com.trainingdiary.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Training {
    private LocalDate date;
    private LocalTime time;
    private String type;
    private List<Exercise> exercises;
    private String comment;

    public Training(LocalDate date, String type) {
        this.date = date;
        this.type = type;
        this.exercises = new ArrayList<>();
        this.time = LocalTime.of(10, 0);
    }

    public Training(LocalDate date, LocalTime time, String type) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.exercises = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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