package com.trustedshops.quicknote.api;

import com.trustedshops.quicknote.entity.Note;
import com.trustedshops.quicknote.service.NoteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("/notes/{slug}")
    public Note getNoteBySlug(@PathVariable String slug) {
        return noteService.getNoteBySlug(slug);
    }

    @PostMapping("/notes/{slug}")
    public void addOrUpdateNote(@PathVariable String slug, @RequestBody String text) {
        noteService.addOrUpdateNote(slug, text);
    }

    @DeleteMapping("/notes/{slug}")
    public void deleteNoteBySlug(@PathVariable String slug) {
        noteService.deleteNoteBySlug(slug);
    }
}
