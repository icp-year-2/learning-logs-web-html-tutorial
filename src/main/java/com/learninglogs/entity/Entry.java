package com.learninglogs.entity;

import java.sql.Timestamp;

/**
 * Entry entity — represents a learning note under a Topic.
 * Maps to the `entries` table in the database.
 *
 * This file is complete from Week 2. No changes needed.
 */
public class Entry {

    private int id;
    private String text;
    private int topicId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Entry(String text, int topicId) {
        this.text = text;
        this.topicId = topicId;
    }

    public Entry(int id, String text, int topicId,
                 Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.text = text;
        this.topicId = topicId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() { return id; }
    public String getText() { return text; }
    public int getTopicId() { return topicId; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setText(String text) { this.text = text; }

    @Override
    public String toString() {
        return "[" + id + "] " + text + " (Topic ID: " + topicId + ", Created: " + createdAt + ")";
    }
}
