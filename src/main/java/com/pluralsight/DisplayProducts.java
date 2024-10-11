package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class DisplayProducts {
    static Scanner scanner = new Scanner(System.in);

    public void main(){
        PromptUser();
    }

    public static void PromptUser(){
        HashMap<String, String[]>products = loadProductFromCSV("products.csv");
        while(true) {
            System.out.println("[S]earch");
            System.out.println("[A]dd");
            System.out.println("Go [B]ack");
            System.out.println("Choose from the options: ");
            String userChoice = scanner.nextLine();

            if(userChoice.equalsIgnoreCase("S")){
                String result = searchProduct(products);
                System.out.println(result);
            }
            if(userChoice.equalsIgnoreCase("A")){
                Add();
            }
            if(userChoice.equalsIgnoreCase("B")){
               break;
            }
        }
    }

    public static HashMap<String, String[]>loadProductFromCSV(String fileName){
        HashMap<String, String[]> product = new HashMap<>();

        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String readLine;
            while((readLine = bufferedReader.readLine()) != null){
                String[] data = readLine.split("\\|");
                String sku = data[0];
                product.put(sku, new String[]{data[1], data[2], data[3]});
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return product;
    }

    public static String searchProduct(HashMap<String, String[]> product){
        System.out.println("Enter a product SKU: ");
        String productSKU = scanner.nextLine();

        if(product.containsKey(productSKU)){
            String[] productDetails = product.get(productSKU);
            return String.format("SKU: %s \nName: %s \nPrice: %s \nDescription: %s",productSKU, productDetails[0], productDetails[1], productDetails[2]);
        }else{
            return "Product not found.";
        }
    }

    public static String Add(){
        System.out.println("Enter product Name you like to add: ");
        String addProductName = scanner.nextLine();
        return addProductName;
    }
}
