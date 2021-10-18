package com.hoangnd.noteyuumi.data.entity;

public class Note {
    private int id;
    private String title, content;
    private int completed;

    public Note(int id, String title, String content, int completed) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
