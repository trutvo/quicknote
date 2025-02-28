package com.trustedshops.quicknote;

import com.trustedshops.quicknote.exception.NoteNotFoundException;
import com.trustedshops.quicknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("unittest")
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Test
    public void testNoteNotFound() {
        assertThrows(NoteNotFoundException.class, () -> noteService.getNoteBySlug("non-existing-slug"));
    }

    @Test
    public void testAddAndGetNote() {
        String slug = "existing-slug";
        noteService.addOrUpdateNote(slug, "existing-text");
        assertEquals(slug, noteService.getNoteBySlug(slug).getSlug());
    }

    @Test
    public void testDeleteNote() {
        String slug = "slug-to-delete";
        assertThrows(NoteNotFoundException.class, () -> noteService.deleteNoteBySlug(slug));
        noteService.addOrUpdateNote(slug, "existing-text");
        assertEquals(slug, noteService.getNoteBySlug(slug).getSlug());
        noteService.deleteNoteBySlug(slug);
        assertThrows(NoteNotFoundException.class, () -> noteService.getNoteBySlug(slug));
    }

    @Test
    public void testListNotes() {
        noteService.deleteAllNotes();
        noteService.addOrUpdateNote("1", "1");
        noteService.addOrUpdateNote("2", "2");
        noteService.addOrUpdateNote("3", "3");
        assertEquals(3, noteService.getNotes().size());
    }

    @Test
    public void testUpdateNote() {
        String slug = "slug-to-update";
        noteService.addOrUpdateNote(slug, "existing-text");
        assertEquals("existing-text", noteService.getNoteBySlug(slug).getText());
        noteService.addOrUpdateNote(slug, "updated-text");
        assertEquals("updated-text", noteService.getNoteBySlug(slug).getText());
    }
}
