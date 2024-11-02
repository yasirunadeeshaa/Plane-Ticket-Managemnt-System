import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Ticket {
    private String row;
    private int colomn;
    private int price;
    private person passenger;
    private static String[] tickets;
    private static int num_ticket=0;

    public Ticket(String row, int seat, int price, person pas) {
        this.row = row;
        this.colomn = seat;
        this.price = price;
        this.passenger = pas;
    }

    public void setRow(String row) {this.row = row;}

    public void setColomn(int colomn) {this.colomn = colomn;}

    public void setPrice(int price) {this.price = price;}

    public String getRow() {return this.row;}

    public int getColomn() {return this.colomn;}

    public int getPrice() {return this.price;}

    public person getPassenger() {return passenger;}
    public Ticket(int ticketss){
        tickets=new String[ticketss];
    }
    public void setPassenger(person pas){
        this.passenger=pas;
    }

    public void printTicketperson() {
        System.out.println("  TICKET INFORMATION :");
        System.out.println("                      * Row    : " + getRow());
        System.out.println("                      * colomn : " + getColomn());
        System.out.println("                      * Price  : " + getPrice());
        System.out.println("  PERSON INFORMATION :");
        passenger.printPerson();
    }

    public void addTicket(String ticket) {
        if (num_ticket == tickets.length) {
            System.out.println("Queue is full. Ticket not added.");
            return;
        }

        tickets[num_ticket]= ticket;
        num_ticket++;
    }
    public static void Save(String filename,Ticket ticket){
        try{
            File file=new File(filename);
            boolean file_created=file.createNewFile();
            if(file_created){
                System.out.println("FILE CREATED     : "+ file.getName());
                try {
                    FileWriter filee = new FileWriter(filename, true); // Appending to the file
                    PrintWriter writer = new PrintWriter(filee);

                    // ticket information
                    writer.println("** TICKET INFORMATION **");
                    writer.println("     * ROW          : "+ticket.getRow());
                    writer.println("     * COLOMN       : "+ticket.getColomn());
                    writer.println("     * TICKET PRICE : "+ticket.getPrice());

                    // person information
                    person person = ticket.getPassenger();
                    writer.println("** PERSON INFORMATION ** ");
                    writer.println("     * NAME         : "+person.getName());
                    writer.println("     * SURNAME      : "+person.getSurname());
                    writer.println("     * EMAIL        : "+person.getEmail());

                    writer.close();
                    System.out.println("FILE " + filename + " updated with ticket information.");
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
            }
            else{
                if(file.exists()){
                    System.out.println("File already axists....");
                }
                else{
                    System.out.println("Error while creating file : " + file.getName());
                }
            }
        }
        catch(IOException e){
            System.out.println("error");
        }
    }
    public static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()){
            file.delete();
            System.out.println(filename+" file deleted.");
        }
        else{
            System.out.println("File does not exist.");
        }
    }

}
