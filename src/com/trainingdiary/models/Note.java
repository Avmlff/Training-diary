package com.trainingdiary.models;

import java.time.LocalDate;

public class Note {
    private LocalDate date;
    private String text;
    private String type;

    public Note(LocalDate date, String text) {
        this.date = date;
        this.text = text;
        this.type = "USER";
    }

    public Note(LocalDate date, String text, String type) {
        this.date = date;
        this.text = text;
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isTrainerRecommendation() {
        return "TRAINER_RECOMMENDATION".equals(type);
    }
}