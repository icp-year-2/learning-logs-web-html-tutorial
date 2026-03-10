package com.learninglogs.dao;

import com.learninglogs.entity.Topic;
import com.learninglogs.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Topic DAO Implementation — JDBC operations for topics.
 * Complete from Week 2. No changes needed.
 */
public class TopicDaoImpl implements TopicDao {

    @Override
    public boolean insertTopic(Topic topic) {
        if (findTopicByName(topic.getName()) != null) {
            System.out.println("Topic already exists: " + topic.getName());
            return false;
        }

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO topics (name) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, topic.getName());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting topic: " + e.getMessage());
            return false;
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
    }

    @Override
    public ArrayList<Topic> fetchAllTopics() {
        ArrayList<Topic> topics = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM topics";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
                topics.add(topic);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching topics: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return topics;
    }

    @Override
    public Topic findTopicByName(String name) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM topics WHERE LOWER(name) = LOWER(?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Topic(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding topic: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }
}
