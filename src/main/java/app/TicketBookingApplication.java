package main.java.app;

import java.util.*;

class TicketBookingApplication{

  public void login_initial() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Select the options below:");
    System.out.println("1:User");
    System.out.println("2:Admin");
    int login_selection = sc.nextInt();
    if(login_selection == 1) {
      System.out.println("1:Login");
      System.out.println("2:Signup");
      int sel = sc.nextInt();
    }
    else if(login_selection == 2){
      System.out.println("1:Login");
      System.out.println("2:Signup");
      int sel = sc.nextInt();

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
    System.out.println("Enter your username:");
    String user_password = sc.nextLine();

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
}