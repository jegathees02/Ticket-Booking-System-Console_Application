package main.java.app;

import java.sql.SQLException;
import java.util.*;


import main.java.dao.EventDAO;
import main.java.dao.TicketDAO;
import main.java.dao.UserDAO;
import main.java.model.Event;
import main.java.model.Ticket;
import main.java.model.User;

interface inf{
  void login_initial();
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

class TicketBookingApplication implements inf{
  private static final EventDAO eventdao;
  private static final UserDAO userdao;
  private static final TicketDAO ticketdao;

  static{
    try{
      eventdao = new EventDAO();
      userdao = new UserDAO();
      ticketdao = new TicketDAO();
    } catch(SQLException e) {
      throw new RuntimeException("Error Initializing DAO classes. ",e);
    }
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
        // userSignup();
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
        // userSignup();
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

  //Admin Login
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
    System.out.println("Enter your username:");
    String admin_password = sc.nextLine();
    try{
      User user = userdao.getUserByUsernameAndPasswordAdmin(admin_username,admin_password);
        if(user != null) {
          System.out.println("Login Successful");
          // adminMenu();

        }
        else{
          System.out.println("Invalid username or password.");
        }
      
    }
    catch(Exception e) {
      System.out.println("Exception occured :"+e.getMessage());
    }
    sc.close();
  }
  public void signup(int n) {
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
    System.out.println("Enter your password:");
    String user_password = sc.nextLine();
    if(n == 1) {

    }
    else if(n ==2) {

    }
    else{
      System.out.println("Invalid Input");
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
          "***************************************************************** \n"+
          "*                                                               * \n"+
          "*            Welcome to Ticket Booking Application              * \n"+
          "*                                                               * \n"+
          "***************************************************************** \n"
        );
        // MainDescription md = new MainDescription();
        // md.welcomeMsg();
        // md.welcomeTitle();
        // md.welcomeDescription();
        TicketBookingApplication tbs = new TicketBookingApplication();

        //inital calling to select the admin or user.
        tbs.login_initial();

        
        
        


        sc.close();
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
      System.out.println("Select one");
      System.out.println("1:Show events");
      System.out.println("2:Cancel Ticket");
      System.out.println("3:Logout");
      System.out.println();
      int n = sc.nextInt();
      if(n == 1) {
        displayEventDetails();
      }else if(n == 2) {
        cancelTicket();

      }else if(n == 3) {
        
      }else{
        System.out.println("Invalid Input!!!");
        userMenu();
      }
      sc.close();
    }
    public void displayEventDetails() throws SQLException{
      Scanner sc = new Scanner(System.in);
      List<Event> event = eventdao.getAllEvents();
      if(event != null) {
        for(Event events : event) {
          System.out.println(events);
        }
      }
      System.out.println("Select One");
      System.out.println("1:Book Tickets");
      System.out.println("2:View Seats");
      System.out.println("3:Back");
      System.out.println("4:Home");
      System.out.println("5:Logout");
      int n = sc.nextInt();
      switch(n) {
        case 1 : System.out.print("Enter the event_id for which you need to book ticket:");
                  String e_id = sc.nextLine();
                  bookTicketForEvent(e_id);
                  break;
        case 2 : System.out.print("Enter the event_id you need to view the seats:");
                String ev_id = sc.nextLine();
                  viewSeatsForEvents(ev_id);
                  break;
        case 3 : userMenu();
                  break;
        case 4 : userMenu();
                  break;
        case 5 : login_initial();
                  break;
        default : System.out.println("Please Enter the correct value");
                  displayEventDetails();
      }
      sc.close();
    }
    public void bookTicketForEvent(String event_id) throws SQLException {
      Scanner sc = new Scanner(System.in);
      System.out.println("Select the type of seat:");
      System.out.println("1:Normal");
      System.out.println("2:VIP");
      int t_type = sc.nextInt();
      if(t_type == 1) {

      }
      else if(t_type == 2) {

      }
      else{
        System.out.println("Enter the correct value");
        bookTicketForEvent(event_id);
      }
      System.out.println("Ticket Booked Successfully");

      System.out.println("Redirecting...");
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      displayEventDetails();
      
    }
    public void cancelTicket() throws SQLException {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter your user_id");
      String us_id = sc.nextLine();
      System.out.println("Your registered events:");
      List<Ticket> ticket = ticketdao.getTicketsByUserId(us_id);
      if(ticket != null) {
        for(Ticket tickets : ticket){
          System.out.println(tickets);
        }
      }
      System.out.println("Select the event_id for which you need to cancel tickets");
      String ev_id = sc.nextLine();
      System.out.println("Cancelling your tickets");
      ticketdao.deleteTicketByEvent_idANDUser_id(ev_id,us_id);
      System.out.println("Ticket cancelled");
      sc.close();
    }

    public void viewSeatsForEvents(String event_id) throws SQLException {
      System.out.println("The total number of seats are:");
      int tot = ticketdao.getTicketByEvent_id(event_id);
      System.out.println(tot);
    }
}