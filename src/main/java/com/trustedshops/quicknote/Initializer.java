package com.trustedshops.quicknote;

import com.trustedshops.quicknote.entity.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public class Initializer {
    private static final Logger LOG
            = Logger.getLogger(String.valueOf(Initializer.class));

    private final NoteRepository noteRepository;

    public Initializer(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PostConstruct
    public void init() {
        LOG.info("Quicknote application initialized");
        noteRepository.findBySlug("root").ifPresentOrElse(
                note -> LOG.info("Root note already exists"),
                () -> {
                    LOG.info("Creating root note");
                    noteRepository.save(new Note("root", "This is the root note ..."));
                }
        );
    }
}
