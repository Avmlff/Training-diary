package com.trainingdiary.models;

public class Exercise {
    private String name;
    private int reps;
    private double weight;

    public Exercise(String name, int reps, double weight) {
        this.name = name;
        this.reps = reps;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }
}