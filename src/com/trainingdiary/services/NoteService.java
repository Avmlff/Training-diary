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

    public void updateNote(User user, int index, String newText) {
        if (index >= 0 && index < user.getNotes().size()) {
            Note note = user.getNotes().get(index);
            if (!note.isTrainerRecommendation()) {
                note.setText(newText);
            }
        }
    }

    public void deleteNote(User user, int index) {
        if (index >= 0 && index < user.getNotes().size()) {
            Note note = user.getNotes().get(index);
            if (!note.isTrainerRecommendation()) {
                user.getNotes().remove(index);
            }
        }
    }
}