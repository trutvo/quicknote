package com.trustedshops.quicknote.service;

import com.trustedshops.quicknote.NoteRepository;
import com.trustedshops.quicknote.entity.Note;
import com.trustedshops.quicknote.exception.NoteNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void deleteAllNotes() {
        noteRepository.deleteAll();
    }

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteBySlug(String slug) {
        return noteRepository.findBySlug(slug)
                .orElseThrow(() -> new NoteNotFoundException("note not found for slug: " + slug));
    }

    public void addOrUpdateNote(String slug, String text) {
        noteRepository.findBySlug(slug).ifPresentOrElse(
                note -> {
                    note.setText(text);
                    noteRepository.save(note);
                },
                () -> noteRepository.save(new Note(slug, text))
        );
    }

    public void deleteNoteBySlug(String slug) {
        noteRepository.findBySlug(slug).ifPresentOrElse(
                noteRepository::delete,
                () -> {
                    throw new NoteNotFoundException("note not found for slug: " + slug);
                }
        );
    }
}
