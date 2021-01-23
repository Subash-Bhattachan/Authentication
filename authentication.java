
/*
 * This application authenticates and authorizes the employees of the zoo.
 * This also serves as an example to show and use the content of a text file.
 * Author: Subash Bhattachan
 * Date: August 19, 2018
 */


package authenticationsystem;

import java.util.Scanner; // enables the program to read input
import java.security.MessageDigest; // enables to output a fixed length hash value
import java.nio.file.*; // to access files, file attributes and file systems


public class AuthenticationSystem {
        
    // method to read text file as string 
    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
   
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in); // to create a scanner for user inputs
        String userInput;
        String userName;
        String passWord;

   
        System.out.println("Would you like to log out?");
        System.out.println("Press 'y' or 'Y' for yes and 'n' or 'N' for no: ");
        userInput = scnr.nextLine();
        
        // to create an infinte loop 
        while (true) {

            while (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n")) {

                System.out.println("Invalid response. Try again.");
                userInput = scnr.nextLine();
            }

            if (userInput.equalsIgnoreCase("y")) {
                System.out.println("Good Bye! Have a good one!");
                break; // to end the program completely
            }
            else {
                System.out.println("Okay! Let us get started.");
                
                System.out.println("Type your username:");
                userName = scnr.nextLine();
                System.out.println("Type your password:");
                //System.out.println(getPassword());
                passWord = scnr.nextLine();
                System.out.print("\n\n");
 
                int i; // attemptCounter

                for (i = 1; i <= 3; ++i) {
                    
                    // this section of code converts the entered password to hash code
                    String original = passWord;  
                    //password here replaces the actual password inputted by the user
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(original.getBytes());
                    byte[] digest = md.digest();
                    StringBuffer sb = new StringBuffer();
                    for (byte b : digest) {

                        sb.append(String.format("%02x", b & 0xff));
                    }
                    
                                       
                    //sb.toString() is used to compare password strings

                    if (sb.toString().equals("108de81c31bf9c622f76876b74e9285f")
                        && (userName.equals("griffin.keyes")) || 
                        sb.toString().equals("17b1b7d8a706696ed220bc414f729ad3")
                        && (userName.equals("donald.monkey"))) {
                        // now displaying the text file as string                       
                        String data = readFileAsString("zookeeper.txt");
                        System.out.println(data);
                        break; // to come out of for loop
                    }
                    
                    /*String data1 = readFileAsString("credentials.txt");
                    if (data1.contains(sb.toString()) && (userName.equals("donald.monkey"))) {
                    System.out.println("donald is zookeeper");
                    break;// this codes work too
                    }*/
                    
                    else if (sb.toString().equals("a584efafa8f9ea7fe5cf18442f32b07b")
                        && (userName.equals("bernie.gorilla")) ||
                        sb.toString().equals("3adea92111e6307f8f2aae4721e77900")
                        && (userName.equals("jerome.grizzlybear"))) {
                        
                        String data = readFileAsString("veterinarian.txt");
                        System.out.println(data);
                        break; // to come out of for loop
                    } 
                    
                    else if (sb.toString().equals("3e34baa4ee2ff767af8c120a496742b5")
                        && (userName.equals("rosario.dawson")) ||
                        sb.toString().equals("0d107d09f5bbe40cade3de5c71e9e9b7")
                        && (userName.equals("bruce.grizzlybear"))) {
                        
                        String data = readFileAsString("admin.txt");
                        System.out.println(data);
                        break; // to come out of for loop
                    } 
                    
                    else {
                        // if the attempts are less than three then user still gets chances
                        if (i < 3) {
                            System.out.println("Either your username or password is invalid.");
                            System.out.println("You have " + (3 - i) + " attempt(s) remaining. Try again.");
                            System.out.println("Type your username: ");
                            userName = scnr.nextLine();
                            System.out.println("Type your password: ");
                            passWord = scnr.nextLine();
                            System.out.print("\n\n");
                        } 
                        // or else the 
                        else {
                            System.out.print("The last try was an invalid as well.");
                            System.out.println("There is no attempt remaining now.");
                        }

                    }

                }
                //if attempts are more than three, the following codes will take place    
                if (i > 3) {
                    System.out.println("So you have exhausted all three attempts!");
                    System.out.println("Now exiting.");
                    System.out.println("");
                    break; //break will exit the program completely.
                } 
                else {
                    System.out.println("");
                }

            }
            System.out.println("Would you like to log out?");
            System.out.println("Press 'y' or 'Y' for yes and 'n' or 'N' for no: ");
            userInput = scnr.nextLine();
        }
    }
}
