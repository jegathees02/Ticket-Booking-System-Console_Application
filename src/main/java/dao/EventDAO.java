package main.java.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.db.DBConnector;
import main.java.model.Event;

public class EventDAO {
    private Connection connection;

    public EventDAO() throws SQLException {
        connection = DBConnector.getConnection();
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setEvent_id(resultSet.getString("event_id"));
                event.setEvent_name(resultSet.getString("event_name"));
                event.setEvent_date(resultSet.getDate("event_date"));
                event.setEvent_venue(resultSet.getString("venue"));
                event.setCapacity(resultSet.getInt("capacity"));
                events.add(event);
            }
        }
        return events;
    }

    public Event getEventById(int eventId) throws SQLException {
        String query = "SELECT * FROM events WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, eventId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Event event = new Event();
                    event.setEvent_id(resultSet.getString("event_id"));
                    event.setEvent_name(resultSet.getString("event_name"));
                    event.setEvent_date(resultSet.getDate("event_date"));
                    event.setEvent_venue(resultSet.getString("venue"));
                    event.setCapacity(resultSet.getInt("capacity"));
                    return event;
                }
            }
        }
        return null; // Event with the given ID not found
    }

    public void insertEvent(Event event) throws SQLException {
        String query = "INSERT INTO events (event_name, event_date, venue, capacity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getEvent_name());
            statement.setDate(2, new java.sql.Date(event.getEvent_date().getTime()));
            statement.setString(3, event.getEvent_venue());
            statement.setInt(4, event.getCapacity());
            statement.executeUpdate();
        }
    }

    public void updateEvent(Event event) throws SQLException {
        String query = "UPDATE events SET event_name = ?, event_date = ?, venue = ?, capacity = ? WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getEvent_name());
            statement.setDate(2, new java.sql.Date(event.getEvent_date().getTime()));
            statement.setString(3, event.getEvent_venue());
            statement.setInt(4, event.getCapacity());
            statement.setString(5, event.getEvent_id());
            statement.executeUpdate();
        }
    }

    public void deleteEvent(int eventId) throws SQLException {
        String query = "DELETE FROM events WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, eventId);
            statement.executeUpdate();
        }
    }
}
