package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */

    public static void main(String[] args) {
        String dirPath = "\\data2";
        String fileName = "";
        System.out.println("Welcome to your shopping cart");

        File newDirectory = new File(dirPath);
        if (newDirectory.exists()) {
            System.out.println("Directory already exists");
        }else {
            newDirectory.mkdir();
        }

        System.out.println("Welcome to your shopping cart");

        List<String> cartItems = new ArrayList<String>();

        Console cons = System.console();
        String input = "";

        while (!input.equals("q")) {
            input = cons.readLine("What do you want to perform? (Enter q to quit)");

            switch (input) {
                case "help":
                    displayMessage("'List' to show  a list of items in a shopping cart");                    
                    displayMessage("'Login <name>' to access your shopping cart");                    
                    displayMessage("'add <item>' to add item in your shopping cart shopping cart");                    
                    displayMessage("'delete <item>' to remove item in your list");                    
                    displayMessage("'q' to exit the program");                    
                    break;
                
                case "List":
                    if (cartItems.size() > 0) {
                        for (String item : cartItems) {
                            System.out.println(item);
                        } 
                    } else {
                        displayMessage("Your cart is empty");
                    }
                    break;
                case "users":
                    break;
            
                default:
                    break;
            }

            if (input.startsWith("add")) {
                input = input.replace(',', ' ');

                String strValue = "";
                Scanner scanner = new Scanner(input.substring(4));

                while (scanner.hasNext(input)) {
                    strValue = scanner.next();
                    cartItems.add(strValue);
                }
            }
        }

    }

    private static void displayMessage(String message) {
    }
}
