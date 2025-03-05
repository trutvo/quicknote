package com.trustedshops.quicknote;

import com.trustedshops.quicknote.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findBySlug(String slug);
}
