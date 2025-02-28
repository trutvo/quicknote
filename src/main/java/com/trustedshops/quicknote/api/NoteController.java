package com.trustedshops.quicknote.api;

import com.trustedshops.quicknote.exception.NoteNotFoundException;
import com.trustedshops.quicknote.NoteRepository;
import com.trustedshops.quicknote.entity.Note;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/notes/{slug}")
    public Note getNoteBySlug(@PathVariable String slug) {
        return noteRepository.findBySlug(slug)
                .orElseThrow(() -> new NoteNotFoundException("note not found for slug: " + slug));
    }

    @PostMapping("/notes/{slug}")
    public void addOrUpdateNote(@PathVariable String slug, @RequestBody String text) {
        noteRepository.findBySlug(slug).ifPresentOrElse(
                note -> {
                    note.setText(text);
                    noteRepository.save(note);
                },
                () -> noteRepository.save(new Note(slug, text))
        );
    }

    @DeleteMapping("/notes/{slug}")
    public void deleteNoteBySlug(@PathVariable String slug) {
        noteRepository.findBySlug(slug).ifPresentOrElse(
                noteRepository::delete,
                () -> {
                    throw new NoteNotFoundException("note not found for slug: " + slug);
                }
        );
    }
}
