package main.java.dao;

import main.java.db.DBConnector;
import main.java.model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAO {
    private Connection connection;

    public EventDAO() throws SQLException {
        connection = DBConnector.getConnection();
    }

    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM event";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setEvent_id(resultSet.getString("event_id"));
                event.setEvent_name(resultSet.getString("event_name"));
                event.setEvent_type(resultSet.getString("event_type"));
                event.setEvent_date(resultSet.getDate("event_date"));
                event.setEvent_venue(resultSet.getString("event_venue"));
                event.setCapacity(resultSet.getInt("capacity"));
                event.setTot_normal(resultSet.getInt("tot_normal"));
                event.setTot_vip(resultSet.getInt("tot_vip"));
                events.add(event);
            }
        }
        return events;
    }

    public Event getEventById(String eventId) throws SQLException {
        String query = "SELECT * FROM event WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, eventId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Event event = new Event();
                    event.setEvent_id(resultSet.getString("event_id"));
                    event.setEvent_name(resultSet.getString("event_name"));
                    event.setEvent_type(resultSet.getString("event_type"));
                    event.setEvent_date(resultSet.getDate("event_date"));
                    event.setEvent_venue(resultSet.getString("event_venue"));
                    event.setCapacity(resultSet.getInt("capacity"));
                    event.setTot_normal(resultSet.getInt("tot_normal"));
                    event.setTot_vip(resultSet.getInt("tot_vip")); // Add this line to retrieve tot_vip
                    return event;
                }
            }
        }
        return null; // Event with the given ID not found
    }
    

    public void insertEvent(Event event) throws SQLException {
        String query = "INSERT INTO event (event_id, event_name, event_type, event_date, event_venue, capacity, tot_normal, tot_vip) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getEvent_id());
            statement.setString(2, event.getEvent_name());
            statement.setString(3, event.getEvent_type());
            statement.setDate(4, new java.sql.Date(event.getEvent_date().getTime()));
            statement.setString(5, event.getEvent_venue());
            statement.setInt(6, event.getCapacity());
            statement.setInt(7, event.getTot_normal());
            statement.setInt(8, event.getTot_vip());
            statement.executeUpdate();
        }
    }

    public void updateEvent(Event event) throws SQLException {
        String query = "UPDATE event SET event_name = ?, event_type = ?, event_date = ?, event_venue = ?, " +
                "capacity = ?, tot_normal = ?, tot_vip = ? WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getEvent_name());
            statement.setString(2, event.getEvent_type());
            statement.setDate(3, new java.sql.Date(event.getEvent_date().getTime()));
            statement.setString(4, event.getEvent_venue());
            statement.setInt(5, event.getCapacity());
            statement.setInt(6, event.getTot_normal());
            statement.setInt(7, event.getTot_vip());
            statement.setString(8, event.getEvent_id());
            statement.executeUpdate();
        }
    }
    

    public void deleteEvent(String eventId) throws SQLException {
        String query = "DELETE FROM event WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, eventId);
            statement.executeUpdate();
        }
    }
}
