package cinema;


import java.util.Scanner;




public class Cinema {
    public static int row, column,x,y;
    public static int ticketPrice =0;
    public static int currentIncome= 0;
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


        enterRowColumn();
        char[][] table = new char[row][column];
        initializeTable(table);
        menu(table);















    }

    private static int priceTicket(char [][] table) {
        int seats= row*column;
        if(seats < 60){
            ticketPrice = 10;
            getCurrentIncome(table);
            return ticketPrice;
        }
        if(seats > 60){
            if(row % 2 != 0) {
                if (x >= ((row / 2)+1)) {
                    ticketPrice= 8;
                    getCurrentIncome(table);
                    return ticketPrice;
                }
                if (x < ((row / 2)+1)) {
                    ticketPrice = 10;
                    getCurrentIncome(table);
                    return ticketPrice;
                }
            }

            if(row % 2 == 0) {
                if (x > ((row / 2))) {
                    ticketPrice = 8;
                    getCurrentIncome(table);
                    return ticketPrice;
                }
                if (x <= ((row / 2))) {
                    ticketPrice = 10;
                    getCurrentIncome(table);
                    return ticketPrice;
                }
            }
        }

        return 0;
    }

    private static void enterCoordinates(char[][] table) {
        try {
            System.out.println("Enter a row number:");
            x = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            y = scanner.nextInt();
            alreadyPurchased(table);
            table[x-1][y-1]= 'B';

        }catch (ArrayIndexOutOfBoundsException e){

            System.out.println("Wrong input!");

            enterCoordinates(table);
        }







    }

    private static void drawTable(char[][] table) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= column; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < row; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < column; j++) {
                System.out.print(table[i][j]);
                System.out.print(" ");

            }
            System.out.println();
        }
    }

    private static void initializeTable(char[][] table ) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                table[i][j] = 'S';
            }
        }
    }

    private static void enterRowColumn() {
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        column = scanner.nextInt();
    }

    private static void printMenu(){
        System.out.println();
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    private static void getCurrentIncome(char [][] table){

        currentIncome = ticketPrice + currentIncome;



    }

    private static void menu( char[][] table){

        printMenu();
        int menuNumber= scanner.nextInt();


        switch (menuNumber) {
            case 0:

                break;
            case 1:
                drawTable(table);
                menu( table);
                break;

            case 2:
                enterCoordinates( table);
                System.out.println("Ticket price: $"+priceTicket(table));
                menu( table);
                break;

            case 3:
                Statistics(table);
                menu(table);
                break;


        }

    }

    private static void Statistics(char[][] table){
        System.out.println("Number of purchased tickets: "+ numberOfTicket(table));
        System.out.format("Percentage: %.2f", percentage(table));
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome );
        getTotal();





    }

    private static int numberOfTicket(char[][] table){
        int count=0;
        for(int i=0; i< table.length ; i++){
            for(int j=0 ; j < table[i].length ; j++){
                if(table[i][j] == 'B')
                    count++;

            }
        }
        return count;
    }

    private static double percentage(char[][] table){
        double total = (double) (row*column);
        double ticket = (double) numberOfTicket(table);

        return  ( ticket / total ) *100;
    }


    private static void getTotal() {
        int total = 0;
        int seats = row * column;
        if (seats < 60) {
            total = seats * 10;
        }
        if (seats > 60) {
            total = ((row / 2) * 10) * column + (((row / 2)) * 8) * column;
            if (row % 2 != 0) {
                total = ((row / 2) * 10) * column + (((row / 2) + 1) * 8) * column;
            }

        }
        System.out.println("Total income: $" +total);

    }

    private static void alreadyPurchased(char[][] table){
        if(table[x-1][y-1]=='B'){
            System.out.println("That ticket has already been purchased!");
            enterCoordinates(table);
        }
    }


}

