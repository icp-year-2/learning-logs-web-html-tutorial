package com.learninglogs.dao;

import com.learninglogs.entity.Topic;
import java.util.ArrayList;

/**
 * Topic DAO Interface — defines database operations for topics.
 * Complete from Week 2. No changes needed.
 */
public interface TopicDao {
    boolean insertTopic(Topic topic);
    ArrayList<Topic> fetchAllTopics();
    Topic findTopicByName(String name);
}
