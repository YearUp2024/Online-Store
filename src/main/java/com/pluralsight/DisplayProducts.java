package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This Class allows the users to Search for or Add new items into Products.csv fiel. 
 */
public class DisplayProducts {
    static Scanner scanner = new Scanner(System.in);

    /**
     * This is my Main method that is calling PromptUser();
     */
    public void main(){
        PromptUser();
    }

    /**
     * This method is going to prompt the users to choose from different options.
     */
    public static void PromptUser(){
        HashMap<String, String[]>products = loadProductFromCSV("products.csv");
        while(true) {
            System.out.println("[S]earch");
            System.out.println("[A]dd");
            System.out.println("Go [B]ack");
            System.out.print("Choose from the options: ");
            String userChoice = scanner.nextLine();

            if(userChoice.equalsIgnoreCase("S")){
                String result = searchProduct(products);
                System.out.println("------------------------------------");
                System.out.println(result);
                System.out.println("------------------------------------");
            }
            if(userChoice.equalsIgnoreCase("A")){
                Add();
            }
            if(userChoice.equalsIgnoreCase("B")){
               break;
            }
        }
    }

    /**
     * This method is going to loop over the Products.csv file and store all the items into a HashMap.
     * Which will allow the program to find items faster and easily.
     * @param fileName
     * @return
     */
    public static HashMap<String, String[]>loadProductFromCSV(String fileName){
        HashMap<String, String[]> product = new HashMap<>();

        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String readLine;
            while((readLine = bufferedReader.readLine()) != null){
                String[] data = readLine.split("\\|");

                if(data.length == 4){
                    String sku = data[0];
                    product.put(sku, new String[]{data[1], data[2], data[3]});
                }else{
                    System.out.println("Skipping Line" + readLine);
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return product;
    }

    /**
     * This method is going to loop over HashMap and see if it can find the product the user is looking for.
     * If the product is not found it will print a message and will allow the users to choose from different
     * options.
     * @param product
     * @return Product from the HashMap
     */
    public static String searchProduct(HashMap<String, String[]> product){
        System.out.print("Enter a product SKU: ");
        String productSKU = scanner.nextLine();

        if(product.containsKey(productSKU)){
            String[] productDetails = product.get(productSKU);
            return String.format("SKU: %s \nName: %s \nPrice: $%s \nDescription: %s",productSKU, productDetails[0], productDetails[1], productDetails[2]);
        }else{
            return "Product not found.";
        }
    }

    /**
     * This method allows the users to Add new items into the Products.csv file.
     * The user needs to enter Product SD, Name, Price, And Department.
     */
    public static void Add(){
        System.out.println("------------------You are Adding a new product into the list--------------");
        System.out.print("Enter product SDK: ");
        String addProductSDK = scanner.nextLine();

        System.out.print("Enter product Name: ");
        String addProductName = scanner.nextLine();

        System.out.print("Enter product Price: ");
        double addProductPrice = scanner. nextDouble();
        scanner.nextLine();

        System.out.print("Enter product Department: ");
        String addProductDepartment = scanner. nextLine();

        try{
            FileWriter fileWriter = new FileWriter("products.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.format("\n%s|%s|%.2f|%s\n", addProductSDK, addProductName, addProductPrice, addProductDepartment));
            bufferedWriter.close();
            System.out.println("Product Added successfully!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
