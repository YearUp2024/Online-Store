package com.pluralsight;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static DisplayProducts displayProducts = new DisplayProducts();
    static DisplayCart displayCart = new DisplayCart();

    public static void main(String[] args) {
        Options();
    }

    public static void Options(){
        String userChoice = "";
        while(!userChoice.equalsIgnoreCase("E")) {
            System.out.println("Display [P]roducts");
            System.out.println("Display [C]art");
            System.out.println("[E]xit");
            System.out.print("Choose from the Options: ");
            userChoice = scanner.nextLine();

            if(userChoice.equalsIgnoreCase("P")){
                System.out.println("----------------");
                displayProducts.main();
            }
            if(userChoice.equalsIgnoreCase("C")){
                displayCart.main();
            }
            if(userChoice.equalsIgnoreCase("E")){
                System.out.println("You Exited");
            }
        }
    }
}