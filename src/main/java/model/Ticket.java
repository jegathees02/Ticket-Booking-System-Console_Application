package main.java.model;

import java.text.SimpleDateFormat;
import java.util.*;

public class Ticket {
    private String ticket_id;
    private String user_id;
    private String event_id;
    private String seat_number;
    private Date event_date;
    private Date booking_date;

    

    public Ticket() {
    }

    public Ticket(String ticket_id, String user_id, String event_id, String seat_number, Date event_date,
            Date booking_date) {
        this.ticket_id = ticket_id;
        this.user_id = user_id;
        this.event_id = event_id;
        this.seat_number = seat_number;
        this.event_date = event_date;
        this.booking_date = booking_date;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public void displayTicketDetails() {
        System.out.println("Ticket ID: " + ticket_id);
        System.out.println("User ID: " + user_id);
        System.out.println("Event ID: " + event_id);
        // System.out.println("Ticket Type: " + ticket_type);
    }

    
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return "Ticket ID: " + ticket_id +
               "\nUser ID: " + user_id +
               "\nEvent ID: " + event_id +
               "\nSeat Number: " + seat_number +
               "\nEvent Date: " + dateFormat.format(event_date) +
               "\nBooking Date: " + dateFormat.format(booking_date) + "\n";
    }

    
}
