public class person {
    private String name;
    private String surname;
    private String email;
    public person(String N,String SN,String EM){
        this.name=N;
        this.surname=SN;
        this.email=EM;
    }
    public void setName(String namee){this.name=namee;}
    public void setSurname(String surnamee){this.surname=surnamee;}
    public void setEmail(String emaill){this.email=emaill;}
    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public String getEmail(){return this.email;}
    public void printPerson() {
        System.out.println("                      * Name   : " + getName());
        System.out.println("                      * Surname: " + getSurname());
        System.out.println("                      * Email  : " + getEmail());
    }


}
