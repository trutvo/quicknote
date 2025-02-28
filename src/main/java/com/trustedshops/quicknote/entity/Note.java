package com.trustedshops.quicknote.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Table(name = "note", uniqueConstraints = { @UniqueConstraint(columnNames = { "slug" }) })
public class Note{

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String slug;
    private String text;


    public Note() {
    }

    public Note(String slug, String text) {
        this.slug = slug;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id) && Objects.equals(slug, note.slug) && Objects.equals(text, note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slug, text);
    }
}
