package main.java.app;

import java.sql.SQLException;
import java.util.*;

import main.java.dao.AdminDAO;
import main.java.dao.EventDAO;
import main.java.dao.TicketDAO;
import main.java.dao.UserDAO;
import main.java.model.Admin;
import main.java.model.Event;
import main.java.model.Ticket;
import main.java.model.User;

interface inf{
  void login_initial();
}

abstract class abs{
  abstract void userMenu() throws SQLException;
  abstract void adminMenu() throws SQLException;
}

// class MainHeading{
//   public void welcomeMsg() {
//     System.out.println("Welcome to Ticket Booking System");
//   }
// }
// class MainTitle extends MainHeading{
//   public void welcomeTitle() {
//     System.out.println("Best application for ticket booking");
//   }
// }
// class MainDescription extends MainTitle{
//   public void welcomeDescription() {
//     System.out.println("Enjoy your events by using our application.");
//   }
// }

class TicketBookingApplication extends abs implements inf{
  private  EventDAO eventdao;
  private  UserDAO userdao;
  private  TicketDAO ticketdao;

  public TicketBookingApplication() {
    Scanner scanner = new Scanner(System.in);
    try {
        eventdao = new EventDAO();
        ticketdao = new TicketDAO();
    } catch (SQLException e) {
        System.err.println("Error connecting to the database. Please check your database connection.");
    }
    scanner.close();
}

  public void login_initial() {     // completed
    Scanner sc = new Scanner(System.in);
    System.out.println("Select the options below:");
    System.out.println("1:User");
    System.out.println("2:Admin");
    int login_selection = sc.nextInt();
    if(login_selection == 1) {
      System.out.println("1:Login");
      System.out.println("2:Signup");
      int sel = sc.nextInt();
      if(sel == 1) {
        userLogin();
      }
      else if(sel == 2) {
        userSignup();
      }
      else{
        System.out.println("Select the correct number");
        login_initial();
      }
    }
    else if(login_selection == 2){
      System.out.println("1:Login");
      System.out.println("2:Signup");
      int sel = sc.nextInt();
      if(sel == 1) {
        adminLogin();
      }
      else if(sel == 2) {
        adminSignup();
      }
      else{
        System.out.println("Select the correct number");
        login_initial();
      }
    }
    else{
      System.out.println("Invalid Input.Try again!!!");
      login_initial();
    }

    sc.close();
  }

  public void adminLogin() {
    Scanner sc = new Scanner(System.in);
    printStar(5);
    printDash(5);
    System.out.print("Welcome to Admin Login Page");
    printDash(5);
    printStar(5);
    System.out.println();
    System.out.println("Enter Your Login details here:");
    System.out.println("Enter your username:");
    String admin_username = sc.nextLine();
    System.out.println("Enter your password:");
    String admin_password = sc.nextLine();
    
    try {
        // Instantiate AdminDAO to perform database operations
        AdminDAO adminDAO = new AdminDAO();

        // Check if the admin with the given username and password exists in the database
        Admin admin = adminDAO.getAdminByUsernameAndPassword(admin_username, admin_password);

        if (admin != null) {
            System.out.println("Login Successful");
            adminMenu();
            // Call the adminMenu() method here or perform other admin-specific actions

        } else {
            System.out.println("Invalid username or password.");
        }

    } catch (SQLException e) {
        System.out.println("Exception occurred: " + e.getMessage());
    }
    sc.close();
}
  public void userSignup() {
    Scanner sc = new Scanner(System.in);
    printStar(5);
    printDash(5);
    System.out.print("Welcome to Sign-Up Page");
    printDash(5);
    printStar(5);
    System.out.println("Enter your User Id:");
    String user_id = sc.nextLine();
    System.out.println("Enter your Name:");
    String user_name = sc.nextLine();
    System.out.println("Enter your Email:");
    String user_email = sc.nextLine();
    System.out.println("Enter your Phone Number");
    String phone = sc.nextLine();
    System.out.println("Enter your password:");
    String user_password = sc.nextLine();
    // Create a User object with the provided details
    User newUser = new User(user_id, user_name, user_email, phone, user_password);

    try {
        // Instantiate UserDAO to perform database operations
        UserDAO userDAO = new UserDAO();

        // Insert the new user into the database
        userDAO.insertUser(newUser);

        System.out.println("User sign-up successful!");
        userMenu();
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred during user sign-up. Please try again.");
    }




    sc.close();
  }
  public void adminSignup() {
    Scanner sc = new Scanner(System.in);
    printStar(5);
    printDash(5);
    System.out.print("Welcome to Admin Sign-Up Page");
    printDash(5);
    printStar(5);
    System.out.println("Enter your Admin Id:");
    String admin_id = sc.nextLine();
    System.out.println("Enter your Name:");
    String user_name = sc.nextLine();
    System.out.println("Enter your Email:");
    String user_email = sc.nextLine();
    System.out.println("Enter your Phone Number");
    String phone = sc.nextLine();
    System.out.println("Enter your password:");
    String user_password = sc.nextLine();
    // Create an Admin object with the provided details
    Admin newAdmin = new Admin(admin_id, user_name, user_email,phone, user_password);

    try {
        // Instantiate AdminDAO to perform database operations
        AdminDAO adminDAO = new AdminDAO();

        // Insert the new admin into the database
        adminDAO.insertAdmin(newAdmin);

        System.out.println("Admin sign-up successful!");
        adminMenu();
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred during admin sign-up. Please try again.");
    }
    
    sc.close();
  }
  //User Login
  public void userLogin() {
    Scanner sc = new Scanner(System.in);
    printStar(5);
    printDash(5);
    System.out.print("Welcome to User Login Page");
    printDash(5);
    printStar(5);
    System.out.println();
    System.out.println("Enter Your Login details here:");
    System.out.println("Enter your username:");
    String user_username = sc.nextLine();
    System.out.println("Enter your Password:");
    String user_password = sc.nextLine();
    try{
      User user = userdao.getUserByUsernameAndPasswordUser(user_username,user_password);
        if(user != null) {
          System.out.println("Login Successful");
          // adminMenu();
          userMenu();

        }
        else{
          System.out.println("Invalid username or password.");
        }
    } catch(Exception e) {
      System.out.println("Exception Occurred."+e.getMessage());
    }
    sc.close();
  }

  public TicketBookingApplication(Scanner scanner) {
    try {
        eventdao = new EventDAO();
        userdao = new UserDAO(); // Initialize the userdao object
        ticketdao = new TicketDAO();
    } catch (SQLException e) {
        System.err.println("Error connecting to the database. Please check your database connection.");
    }
}
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println(
            "***************************************************************** \n" +
            "*                                                               * \n" +
            "*            Welcome to Ticket Booking Application              * \n" +
            "*                                                               * \n" +
            "***************************************************************** \n"
    );
    TicketBookingApplication tbs = new TicketBookingApplication(scanner);

    tbs.login_initial();

    scanner.close();
}

    public void printStar(int n) {
      for(int i=0;i<n;i++) System.out.print("*");
    }
    public void printDash(int n) {
      for(int i=0;i<n;i++) System.out.print("-");
    }

    public void userMenu() throws SQLException {
      Scanner sc = new Scanner(System.in);
      printStar(8);
      System.out.print("User Menu");
      printStar(8);
      System.out.println();
      System.out.println("Select the below");
      System.out.println("1:Show events");
      System.out.println("2:Cancel Ticket");
      System.out.println("3:Logout");
      System.out.println("4:View Ticket Details");
      System.out.println();
      int n = sc.nextInt();
      if(n == 1) {
        displayEventDetails();
      }else if(n == 2) {
        cancelTicketForEvent();

      }else if(n == 3) {
        login_initial();
      }else if(n==4){
        viewTicketDetails();
      }else{
        System.out.println("Invalid Input!!!");
        userMenu();
      }
      sc.close();
    }

    public void displayEventDetails() throws SQLException {
      Scanner sc = new Scanner(System.in);
      List<Event> events = eventdao.getAllEvents();
  
      if (events != null && !events.isEmpty()) {
          for (Event event : events) {
              System.out.println(event);
          }
      } else {
          System.out.println("No events available.");
      }
  
      System.out.println("Select One");
      System.out.println("1: Book Tickets");
      System.out.println("2: View Seats");
      System.out.println("3: Back");
      System.out.println("4: Home");
      System.out.println("5: Logout");
      System.out.println("6:View Ticket Details");
  
      int choice = sc.nextInt();
  
      switch (choice) {
          case 1:
              System.out.print("Enter the event_id for which you need to book tickets: ");
              sc.nextLine(); // Consume the newline left by nextInt()
              String event_id = sc.nextLine();
              bookTicketForEvent(event_id);
              break;
          case 2:
              System.out.print("Enter the event_id you need to view the seats: ");
              sc.nextLine(); // Consume the newline left by nextInt()
              String ev_id = sc.nextLine();
              viewSeatsForEvents(ev_id);
              break;
          case 3:
              userMenu();
              break;
          case 4:
              userMenu();
              break;
          case 5:
              login_initial();
              break;
          case 6:
              viewTicketDetails();
              break;
          default:
              System.out.println("Please enter the correct value.");
              displayEventDetails();
      }
  
      sc.close();
  }

  public String generateTicketId() {
    // You can use UUID to generate a unique ticket ID
    return UUID.randomUUID().toString();
  }
  public void bookTicketForEvent(String event_id) throws SQLException {
    Scanner sc = new Scanner(System.in);
    
    Event event = eventdao.getEventById(event_id);

    if (event == null) {
        System.out.println("Event with event_id " + event_id + " not found.");
        return;
    }

    System.out.println("Event Details:");
    System.out.println(event);

    System.out.println("Select the type of seat:");
    System.out.println("1: Normal");
    System.out.println("2: VIP");
    int t_type = sc.nextInt();
    sc.nextLine();

    int totalTickets = 0;
    String ticketType = "";
    System.out.println("Enter the User Id");
    String user_id = sc.nextLine();

    if (t_type == 1) {
        totalTickets = event.getTot_normal();
        ticketType = "Normal";
    } else if (t_type == 2) {
        totalTickets = event.getTot_vip();
        ticketType = "VIP";
    } else {
        System.out.println("Invalid choice!");
        return;
    }

    if (totalTickets > 0) {
        System.out.println("Number of available " + ticketType + " tickets: " + totalTickets);
        System.out.println("Enter the number of " + ticketType + " tickets to book:");
        int numTickets = sc.nextInt();

        if (numTickets <= totalTickets) {
            // Proceed with booking tickets
            System.out.println("Booking " + numTickets + " " + ticketType + " tickets for event " + event_id + "...");

            // Implement the booking logic here
            // For example, create and insert ticket objects in the database
            for (int i = 0; i < numTickets; i++) {
                Ticket ticket = new Ticket();
                ticket.setTicket_id(generateTicketId()); // You need to implement this method to generate a unique ticket ID
                ticket.setEvent_id(event_id);
                ticket.setUser_id(user_id); // Replace this with the actual user ID of the logged-in user
                ticket.setSeat_number("A" + (i + 1)); // You can adjust the seat numbering based on your requirements
                ticket.setEvent_date(event.getEvent_date());
                ticket.setBooking_date(new Date());
                
                try {
                    ticketdao.insertTicket(ticket); // Insert the ticket into the database
                } catch (SQLException e) {
                    System.out.println("Error occurred while booking the ticket. Please try again.");
                    return;
                }
            }

            // After booking, update the available tickets in the event object and database
            if (t_type == 1) {
                event.setTot_normal(totalTickets - numTickets);
            } else {
                event.setTot_vip(totalTickets - numTickets);
            }

            // Update the event details in the database
            try {
                eventdao.updateEvent(event);
            } catch (SQLException e) {
                System.out.println("Error occurred while updating the event details. Please try again.");
                return;
            }

            System.out.println("Ticket(s) booked successfully!");
        } else {
            System.out.println("Sorry, only " + totalTickets + " " + ticketType + " tickets available. Seats full!");
        }
    } else {
        System.out.println("No " + ticketType + " tickets available. Seats full!");
    }
}


  
public void cancelTicketForEvent() throws SQLException{
  Scanner scanner = new Scanner(System.in);
  System.out.println("Enter your Event ID:");
  String event_id = scanner.nextLine();
  System.out.println("Enter your User ID:");
  String userId = scanner.nextLine();

  List<Ticket> userTickets = null;
  try {
      // Get all tickets for the given event and user
      userTickets = ticketdao.getTicketsByEventIdAndUserId(event_id, userId);
  } catch (SQLException e) {
      System.out.println("Error occurred while fetching tickets. Please try again.");
      return;
  }

  if (userTickets != null && !userTickets.isEmpty()) {
      System.out.println("Are you sure you want to cancel your ticket(s) for this event? (yes/no)");
      String confirmation = scanner.nextLine();

      if (confirmation.equalsIgnoreCase("yes")) {
          // Delete all the tickets from the database
          try {
              for (Ticket ticket : userTickets) {
                  ticketdao.deleteTicket(ticket.getTicket_id());
              }
          } catch (SQLException e) {
              System.out.println("Error occurred while canceling the ticket(s). Please try again.");
              return;
          }

          // Update the event capacity and available tickets in the database
          Event event = eventdao.getEventById(event_id);
          int remainingSeats = ticketdao.getTicketByEvent_id(event_id);

          // Update the correct ticket type count based on the event type
          if ("Normal".equalsIgnoreCase(event.getEvent_type())) {
              event.setTot_normal(remainingSeats);
          } else if ("VIP".equalsIgnoreCase(event.getEvent_type())) {
              event.setTot_vip(remainingSeats);
          }

          // Update the event details in the database
          try {
              eventdao.updateEvent(event);
          } catch (SQLException e) {
              System.out.println("Error occurred while updating the event details. Please try again.");
              return;
          }

          System.out.println("Ticket(s) canceled successfully!");
      } else {
          System.out.println("Ticket cancellation canceled.");
      }
  } else {
      System.out.println("You do not have a ticket for this event.");
  }
}


    public void viewSeatsForEvents(String event_id) throws SQLException {
      System.out.println("The total number of seats are:");
      int tot = ticketdao.getTicketByEvent_id(event_id);
      System.out.println(tot);
    }


    //admin controls
    public void adminMenu() throws SQLException {
      Scanner sc = new Scanner(System.in);
      printStar(8);
      System.out.print("Admin Menu");
      printStar(8);
      System.out.println();
      System.out.println("Select one");
      System.out.println("1:Add events");
      System.out.println("2:Cancel Event");
      System.out.println("3:Logout");
      System.out.println("4:View Event");
      System.out.println();
      int n = sc.nextInt();
      if(n == 1) {
        addEventDetails();
      }else if(n == 2) {
        cancelEvent();

      }else if(n == 3) {
        login_initial();
      }else if(n == 4) {
        viewAllEvents();
      }else{
        System.out.println("Invalid Input!!!");
        adminMenu();
      }
      sc.close();
    }
    public void addEventDetails() throws SQLException {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter the details to add the event:");
      System.out.println("Enter the event ID:");
      String e_id = sc.nextLine();
      System.out.println("Enter the event name:");
      String e_name = sc.nextLine();
      System.out.println("Enter the event type:");
      String e_type = sc.nextLine();
      System.out.println("Enter the event Date(YYYY-MM-DD)");
      String e_date = sc.nextLine();
      System.out.println("Enter the event venue");
      String e_venue = sc.nextLine();
      System.out.println("Enter the total normal tickets:");
      int tot_n = sc.nextInt();
      System.out.println("Enter the total vip tickets:");
      int tot_vip = sc.nextInt();

      Event newEvent = new Event(e_id, e_name, e_type, java.sql.Date.valueOf(e_date), e_venue, tot_n + tot_vip, tot_n, tot_vip);

    try {
        // Instantiate EventDAO to perform database operations
        EventDAO eventDAO = new EventDAO();

        // Insert the new event into the database
        eventDAO.insertEvent(newEvent);

        System.out.println("Event added successfully!");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while adding the event. Please try again.");
    }
    adminMenu();

      sc.close();

    }
    
public void cancelEvent() throws SQLException {
  Scanner sc = new Scanner(System.in);
  System.out.println("Listing all events:");
  try {
      // Instantiate EventDAO to perform database operations
      EventDAO eventDAO = new EventDAO();

      // Retrieve all events from the database
      List<Event> events = eventDAO.getAllEvents();

      // Display all events to the user
      for (Event event : events) {
          System.out.println(event);
      }

      System.out.println();
      System.out.println("Enter the event ID of the event you want to cancel:");
      String event_id = sc.nextLine();

      // Check if the event with the given event_id exists in the database
      Event eventToCancel = eventDAO.getEventById(event_id);
      if (eventToCancel != null) {
          // Delete the event from the database
          eventDAO.deleteEvent(event_id);
          System.out.println("Event with ID " + event_id + " has been canceled successfully!");
      } else {
          System.out.println("Event with ID " + event_id + " does not exist. Please enter a valid event ID.");
      }

  } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Error occurred while canceling the event. Please try again.");
  }
  adminMenu();
  sc.close();
}

public void viewTicketDetails() throws SQLException {
  Scanner scanner = new Scanner(System.in);
  System.out.println("1: View tickets by User ID");
  System.out.println("2: View tickets by User ID and Event ID");
  int choice = scanner.nextInt();
  scanner.nextLine(); // Consume the newline left by nextInt()

  switch (choice) {
      case 1:
          System.out.println("Enter your User ID:");
          String userId = scanner.nextLine();
          List<Ticket> userTickets = ticketdao.FindTicket(userId);
          displayTickets(userTickets);
          break;
      case 2:
          System.out.println("Enter your User ID:");
          String user_id = scanner.nextLine();
          System.out.println("Enter the Event ID:");
          String event_id = scanner.nextLine();
          List<Ticket> matchingTickets = ticketdao.FindTicket(user_id, event_id);
          displayTickets(matchingTickets);
          break;
      default:
          System.out.println("Invalid choice!");
  }
  scanner.close();
}
private void displayTickets(List<Ticket> tickets) {
  if (tickets != null && !tickets.isEmpty()) {
      for (Ticket ticket : tickets) {
          // Assuming Ticket class has a displayTicketDetails() method
          ticket.displayTicketDetails();
          System.out.println("---------------------");
      }
  } else {
      System.out.println("No tickets available for the specified criteria.");
  }
}

// Add this method in the TicketBookingApplication class
public void viewAllEvents() throws SQLException {
  List<Event> events = eventdao.getAllEvents();

  if (events != null && !events.isEmpty()) {
      System.out.println("All Events:");
      for (Event event : events) {
          System.out.println(event);
          System.out.println("---------------------");
      }
  } else {
      System.out.println("No events available.");
  }
}

}