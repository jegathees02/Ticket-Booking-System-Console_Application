package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.db.DBConnector;
import main.java.model.Ticket;

public class TicketDAO {
    private Connection connection;

    public TicketDAO() throws SQLException {
        connection = DBConnector.getConnection();
    }

    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicket_id(resultSet.getString("ticket_id"));
                ticket.setEvent_id(resultSet.getString("event_id"));
                ticket.setUser_id(resultSet.getString("user_id"));
                ticket.setSeat_number(resultSet.getString("seat_number"));
                ticket.setEvent_date(resultSet.getDate("event_date"));
                ticket.setBooking_date(resultSet.getDate("booking_date"));
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public Ticket getTicketById(String ticketId) throws SQLException {
        String query = "SELECT * FROM tickets WHERE ticket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticketId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setTicket_id(resultSet.getString("ticket_id"));
                    ticket.setEvent_id(resultSet.getString("event_id"));
                    ticket.setUser_id(resultSet.getString("user_id"));
                    ticket.setSeat_number(resultSet.getString("seat_number"));
                    ticket.setEvent_date(resultSet.getDate("event_date"));
                    ticket.setBooking_date(resultSet.getDate("booking_date"));
                    return ticket;
                }
            }
        }
        return null; // Ticket with the given ID not found
    }

    public List<Ticket> getTicketsByUserId(String userId) throws SQLException {
        List<Ticket> userTickets = new ArrayList<>();
        String query = "SELECT * FROM tickets WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setTicket_id(resultSet.getString("ticket_id"));
                    ticket.setEvent_id(resultSet.getString("event_id"));
                    ticket.setUser_id(resultSet.getString("user_id"));
                    ticket.setSeat_number(resultSet.getString("seat_number"));
                    ticket.setEvent_date(resultSet.getDate("event_date"));
                    ticket.setBooking_date(resultSet.getDate("booking_date"));
                    userTickets.add(ticket);
                }
            }
        }
        return userTickets;
    }

    public List<Ticket> getTicketsByEventId(String eventId) throws SQLException {
        List<Ticket> eventTickets = new ArrayList<>();
        String query = "SELECT * FROM tickets WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, eventId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setTicket_id(resultSet.getString("ticket_id"));
                    ticket.setEvent_id(resultSet.getString("event_id"));
                    ticket.setUser_id(resultSet.getString("user_id"));
                    ticket.setSeat_number(resultSet.getString("seat_number"));
                    ticket.setEvent_date(resultSet.getDate("event_date"));
                    ticket.setBooking_date(resultSet.getDate("booking_date"));
                    eventTickets.add(ticket);
                }
            }
        }
        return eventTickets;
    }

    public void insertTicket(Ticket ticket) throws SQLException {
        String query = "INSERT INTO tickets (ticket_id, event_id, user_id, seat_number, event_date, booking_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticket.getTicket_id());
            statement.setString(2, ticket.getEvent_id());
            statement.setString(3, ticket.getUser_id());
            statement.setString(4, ticket.getSeat_number());
            statement.setDate(5, new java.sql.Date(ticket.getEvent_date().getTime()));
            statement.setDate(6, new java.sql.Date(ticket.getBooking_date().getTime()));
            statement.executeUpdate();
        }
    }

    public void updateTicket(Ticket ticket) throws SQLException {
        String query = "UPDATE tickets SET event_id = ?, user_id = ?, seat_number = ?, event_date = ?, booking_date = ? WHERE ticket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticket.getEvent_id());
            statement.setString(2, ticket.getUser_id());
            statement.setString(3, ticket.getSeat_number());
            statement.setDate(4, new java.sql.Date(ticket.getEvent_date().getTime()));
            statement.setDate(5, new java.sql.Date(ticket.getBooking_date().getTime()));
            statement.setString(6, ticket.getTicket_id());
            statement.executeUpdate();
        }
    }

    public void deleteTicket(String ticketId) throws SQLException {
        String query = "DELETE FROM tickets WHERE ticket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticketId);
            statement.executeUpdate();
        }
    }
    public void deleteTicketByUser_id(String ticketId) throws SQLException {
        String query = "DELETE FROM tickets WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticketId);
            statement.executeUpdate();
        }
    }
    public void deleteTicketByEvent_id(String ticketId) throws SQLException {
        String query = "DELETE FROM tickets WHERE event_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticketId);
            statement.executeUpdate();
        }
    }
     public void deleteTicketByEvent_idANDUser_id(String ticketId,String user_id) throws SQLException {
        String query = "DELETE FROM tickets WHERE event_id = ? and user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ticketId);
            statement.setString(2,user_id);
            statement.executeUpdate();
        }
    }
    public int getTicketByEvent_id(String event_id) throws SQLException {
        String ticketCountQuery = "SELECT COUNT(*) FROM ticket WHERE event_id = ?";
        String capacityQuery = "SELECT capacity FROM event WHERE event_id = ?";
        int ticketCount = 0;
        int eventCapacity = 0;
        try (PreparedStatement ticketCountStatement = connection.prepareStatement(ticketCountQuery);
             PreparedStatement capacityStatement = connection.prepareStatement(capacityQuery)) {
            ticketCountStatement.setString(1, event_id);
            capacityStatement.setString(1, event_id);
            try (ResultSet ticketCountResult = ticketCountStatement.executeQuery()) {
                if (ticketCountResult.next()) {
                    ticketCount = ticketCountResult.getInt(1);
                }
            }
            try (ResultSet capacityResult = capacityStatement.executeQuery()) {
                if (capacityResult.next()) {
                    eventCapacity = capacityResult.getInt("capacity");
                }
            }
        }
        int remainingSeats = eventCapacity - ticketCount;
        return remainingSeats;
    }
    
}
