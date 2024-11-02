import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class PlaneManagement {
    static int[][] planeseat;
    private static int row_number;
    private static int count_of_price;
    private static int ticket_price;

    public static void main(String[] args) {

        System.out.println("** WELCOME TO THE PLANE MANAGEMENT APPLICATION. **");

        planeseat = new int[4][];
        planeseat[0] = new int[14];
        planeseat[1] = new int[12];
        planeseat[2] = new int[12];
        planeseat[3] = new int[14];

        Ticket ticket2 = new Ticket(52);                                              //call ticket array
        Ticket[][] ticket3 = new Ticket[4][15];


        boolean running = true;
        while (running) {
            System.out.println("**************************************************");
            System.out.println("*                  MENU OPTIONS                  *");
            System.out.println("**************************************************");
            System.out.println("     1) Buy a seat.                               ");
            System.out.println("     2) Cancel a seat.                            ");
            System.out.println("     3) Find first available seat.                ");
            System.out.println("     4) Show seating plan.                        ");
            System.out.println("     5) Print tickets information and total sales.");
            System.out.println("     6) Search ticket.                            ");
            System.out.println("     0) Quit.                                     ");
            System.out.println("**************************************************");

            Scanner input = new Scanner(System.in);         //user option
            System.out.print("PLEASE SELECT AN OPTION - : ");
            int option = input.nextInt();

            switch (option) {
                case 1:
                    buy_seat(ticket3);                      //call buy_seat method
                    break;
                case 2:
                    cancel_seat(ticket3, ticket2);          //call cancel_seat method
                    break;
                case 3:
                    find_first_available();                 //call find_first_available method
                    break;
                case 4:
                    show_seating_plan();                    //call show_seating_plan method
                    break;
                case 5:
                    print_tickets_info(ticket3);            //call print_tickets_info method
                    break;
                case 6:
                    search_ticket(ticket3);                 //call search_ticket method
                    break;
                case 0:
                    running = false;
                    System.out.println("**** EXITING THE PROGRAMME. *****");
                    System.out.println("GOOD BYE. ! HAVE A NICE JOURNEY...");
                    break;
                default:
                    System.out.println("Option selected is not correct.");
            }
        }
    }

    //TASK-3
    public static void buy_seat(Ticket[][] ticket3) {
        Scanner input = new Scanner(System.in);
        System.out.print("ENTER ROW LETTER     : ");                                        //get row letter
        String row_letter = input.next();
        String[] roww = {"A", "B", "C", "D"};
        row_number = 0;
        int colomn_number;
        String seat;
        boolean valid = false;
        for (int j = 0; j < roww.length; j++) {
            if (roww[j].equals(row_letter)) {
                valid = true;
                break;
            }
        }
        if (valid) {
            for (int i = 0; i < roww.length; i++) {
                if (roww[i].equals(row_letter)) {                                           //check row letter
                    row_number = i;
                }
            }
            System.out.print("ENTER COLUMN NUMBER  : ");                                  //get colomn number
            colomn_number = input.nextInt();

            if (colomn_number > planeseat[row_number].length) {                             //check colomn number
                System.out.println("Invalid COLUMN number.");
                return;
            }
            if (planeseat[row_number][colomn_number - 1] == 1) {
                System.out.println("THIS SEAT SOLD.YOU SHOULD TO TRY ANOTHER SEAT");        //check seat
            } else {
                //person information
                Scanner inputt = new Scanner(System.in);
                System.out.print("ENTER YOUR NAME      :");
                String name = inputt.next();
                System.out.print("ENTER YOUR SURNAME   :");
                String surname = inputt.next();
                System.out.print("ENTER YOUR E-MAIL    :");
                String email = inputt.next();

                if (email.contains("@") && email.contains(".")) {                           //email validation
                } else {
                    System.out.println("INVALID EMAIL. PLEASE ENTER VALID EMAIL...!!!!!");
                    return;
                }
                planeseat[row_number][colomn_number - 1] = 1;
                System.out.println("YOUR BOOKING SUCCESFULL.....");
                seat = row_letter + colomn_number;
                System.out.println("YOUR BOOKED SEAT - " + seat);
                //call Person class
                person person1 = new person(name, surname, email);
                person1.setName(name);
                person1.setSurname(surname);
                person1.setEmail(email);
                //ticket informatioN
                if (colomn_number <= 5) {
                    ticket_price = 200;
                } else if (colomn_number >= 6 && colomn_number <= 9) {
                    ticket_price = 180;
                } else {
                    ticket_price = 150;
                }
                Ticket ticket1 = new Ticket(row_letter, colomn_number, ticket_price, person1);
                ticket1.setRow(row_letter);
                ticket1.setColomn(colomn_number);
                ticket1.setPrice(ticket_price);
                ticket1.setPassenger(person1);                                              //set the passenger

                ticket1.addTicket("* TICKET INFORMATION- " + ticket1.getRow() + " " + ticket1.getColomn() + " " + ticket1.getPrice()
                        + "   * PERSON INFORMATION-  " + person1.getName() + " " + person1.getSurname() + " " + person1.getEmail());

                ticket3[row_number][colomn_number] = ticket1;                               //tickets are in ticket2 array add to the ticket3 array
                count_of_price = count_of_price + ticket_price;
                Ticket.Save(seat + ".txt", ticket1);
            }
        } else {
            System.out.println("YOUR ROW LETTER INVALID. PLEASE ENTER VALID ROW LETTER");
        }
    }

    //TASK-4
    public static void cancel_seat(Ticket[][] ticket3, Ticket ticket2) {
        Scanner input = new Scanner(System.in);
        System.out.print("ENTER ROW LETTER     : ");        //get row letter
        String row_letter = input.next();
        String[] roww = {"A", "B", "C", "D"};
        int row_number = 0;
        int colomn_number;
        String seat;
        boolean valid = false;
        int i = 0;
        for (int j = 0; j < roww.length; j++) {            //row letter validation
            if (roww[j].equals(row_letter)) {
                valid = true;
                break;
            }
        }
        if (valid) {
            for (i = 0; i < roww.length; i++) {
                if (roww[i].equals(row_letter)) {           //check row letter
                    row_number = i;
                }
            }
            System.out.print("ENTER COLOMN NUMBER  : ");       //get colomn number
            colomn_number = input.nextInt();


            if (colomn_number > planeseat[row_number].length) {         //check colomn number
                System.out.println("INVALID COLOMN NUMBER.....!");
                return;
            }
            if (colomn_number <= 5) {
                ticket_price = 200;
            } else if (colomn_number >= 6 && colomn_number <= 9) {
                ticket_price = 180;
            } else {
                ticket_price = 150;
            }
            count_of_price = count_of_price - ticket_price;
            if (planeseat[row_number][colomn_number - 1] == 1) {
                planeseat[row_number][colomn_number - 1] = 0;           //check seat
                seat = row_letter + colomn_number;
                System.out.println("NOW CANCELLING YOUR SEAT - " + seat);
                for (int n = 0; n < ticket3.length; n++) {
                    for (int m = 0; m < ticket3[n].length; m++) {
                        if (ticket3[n][m] != null) {
                            ticket3[n][m] = null;
                            System.out.println("CANCELED YOUR TICKET.....!");
                            break;
                        }
                    }
                }
                Ticket.deleteFile(seat + ".txt");                      //call deleteFile method
            } else {
                System.out.println("PLEASE ENTER CORRECT SEAT POSISION");
            }
        } else {
            System.out.println("YOUR ROW LETTER INVALID. PLEASE ENTER VALID ROW LETTER");
        }
    }

    //TASK-5
    public static void find_first_available() {
        for (int i = 0; i < planeseat.length; i++) {
            for (int j = 0; j < planeseat[i].length; j++) {
                if (planeseat[i][j] == 0) {
                    System.out.println(i + 1 + " ROW " + (j + 1) + " COLOMN SEAT IS AVAILABLE");
                    break;
                }
            }
        }
    }

    //TASK-6
    public static void show_seating_plan() {
        System.out.println("THE SEATING PLAN.");
        System.out.println("AVAILABLE SEATS = O ");
        System.out.println("SOLD SEATS      = X ");
        for (int i = 0; i < planeseat.length; i++) {
            for (int j = 0; j < planeseat[i].length; j++) {
                if (planeseat[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    //TASK-10
    public static void print_tickets_info(Ticket[][] ticket3) {

        System.out.println("TICKET AND PERSON INFORMATION : ");
        int ticketnumber = 1;
        for (int i = 0; i < ticket3.length; i++) {
            for (int j = 0; j < ticket3[i].length; j++) {
                if (ticket3[i][j] != null) {
                    System.out.println("Ticket [" + ticketnumber + "] - ROW " + (i + 1) + " COLOMN " + j + ".");
                    Ticket ticket = ticket3[i][j];
                    if (ticket != null) {
                        ticket.printTicketperson();           // Print ticket and person information
                    }
                    ticketnumber++;
                }
            }
        }
        System.out.println("TICKET PRICE OF THE TICKETS= £" + count_of_price);

    }

    //TASK-11
    public static void search_ticket(Ticket[][] ticket3) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter row letter : ");        //get row letter
        String row_letter = input.next();
        String[] roww = {"A", "B", "C", "D"};
        row_number = 0;
        int colomn_number;
        String seat;
        boolean valid = false;
        int i = 0;
        for (int j = 0; j < roww.length; j++) {
            if (roww[j].equals(row_letter)) {
                valid = true;
                break;
            }
        }
        if (valid) {
            for (i = 0; i < roww.length; i++) {
                if (roww[i].equals(row_letter)) {           //check roe letter
                    row_number = i;
                }
            }
            System.out.print("Enter COLOMN number : ");       //get colomn number
            colomn_number = input.nextInt();

            if (colomn_number > planeseat[row_number].length) {     //check colomn number
                System.out.println("Invalid COLOMN number.");
                return;
            }
            Ticket ticket = ticket3[row_number][colomn_number];
            if (ticket != null) {
                System.out.println("Ticket and Person Information:");
                ticket.printTicketperson();                  // Print ticket and person information
            } else {
                System.out.println("This seat is available.");
            }
        } else {
            System.out.println("YOUR ROW LETTER INVALID. PLEASE ENTER VALID ROW LETTER");
        }
    }
}