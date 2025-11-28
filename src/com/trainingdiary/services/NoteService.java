package com.trainingdiary.services;

import com.trainingdiary.models.Note;
import com.trainingdiary.models.User;
import java.time.LocalDate;
import java.util.List;

public class NoteService {

    public void addNote(User user, String text) {
        Note note = new Note(LocalDate.now(), text);
        user.addNote(note);
    }

    public List<Note> getUserNotes(User user) {
        return user.getNotes();
    }
}