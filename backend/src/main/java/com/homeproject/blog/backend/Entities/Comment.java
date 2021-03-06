package com.homeproject.blog.backend.entities;

import java.util.Comparator;

public class Comment {
    private Long id;
    private Author author;
    private String text;
    private String createdOn;
    private String updatedOn;

    public static class CommentIDComparator implements Comparator<Comment> {
        private int sign = 1;

        public CommentIDComparator(boolean ascending) {
            if (!ascending) {
                sign = -1;
            }
        }

        @Override
        public int compare(Comment comment1, Comment comment2) {
           return comment1.id.compareTo(comment2.id) * sign;
        }
    }

    public Comment(Long id, Author author, String text, String createdOn, String updatedOn) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }
}
