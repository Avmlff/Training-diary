package com.trainingdiary.models;

import java.time.LocalDate;

public class Note {
    private LocalDate date;
    private String text;

    public Note(LocalDate date, String text) {
        this.date = date;
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}