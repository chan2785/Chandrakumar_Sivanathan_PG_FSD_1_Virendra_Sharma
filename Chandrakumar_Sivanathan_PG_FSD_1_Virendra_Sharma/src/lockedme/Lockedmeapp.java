package lockedme;

//Import all libraries 
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//Declare the Directory and File 
public class Lockedmeapp {

  static String DIREC;
  File folder_name;
 

//If file doesn't exist, create the main folder 
public Lockedmeapp() {
    DIREC = System.getProperty("user.dir");
    folder_name = new File(DIREC+"/Appfiles"); 
    if (!folder_name.exists())
         folder_name.mkdirs();
      System.out.println("DIRECTORY : "+ folder_name.getAbsolutePath());
  }
 

//Application main screen  

private static final  
		String WelcomeScreen = String.format("************************************************************\n"
				+ "*********** Welcome to Lockers.com************************** \n" + "** This application was developed by Chandra Sivanathan*****\n"
				+ "************************************************************\n");
		
		 private static final String MAIN_MENU_PROMPT =
          "\nMAIN MENU - Select any of the following: \n"+
                  "1 -> List files in directory \n"+
                  "2 -> Add, Delete or Search\n"+
                  "3 -> Exit Program";

       private static final String SECONDARY_MENU_PROMPT =
          "   \nSelect any of the following: \n"+
                  "   1 -> Add a file\n"+
                  "   2 -> Delete a file\n"+
                  "   3 -> Search a file\n"+
                  "   4 -> GoBack";
					
		void showMainMenu() {
      System.out.println(MAIN_MENU_PROMPT);
      try{
          Scanner scanner = new Scanner(System.in);
          int option = scanner.nextInt();
          switch (option){
              case 1 :  
                  DisplayFiles();
                  showMainMenu();
					break;
         
              case 2 : 
                  showSecondaryMenu();
                  break;
              case 3 :  
                  System.out.println("Thank You for using our Application - Have an Wonderful Day ! ");
                  System.exit(0);
					break;
              default: 
				System.out.println("Please enter only 1 or 2 or 3 ");
				
				showMainMenu();
          }
      }
      catch (Exception e){
          System.out.println("select a valid option 1, 2 or 3");
          showMainMenu();
      }
  }

  void showSecondaryMenu() {
      System.out.println(SECONDARY_MENU_PROMPT);
      try{
    	  Scanner scanner = new Scanner(System.in);
          int input = scanner.nextInt();
      
         

          switch (input){
              case 1 : {
                  System.out.print(" Please Enter the File name to be added : ");
                  String filename = scanner.next().trim().toLowerCase();
                  addFile(filename);
                  break;
              }
              case 2 : {
                  System.out.print("Please Enter the File name to be Deleted : ");
                  String filename = scanner.next().trim();
                  deleteFile(filename);
                  break;
              }
              case 3 : {
                  System.out.print("Please Enter the File name to be searched : ");
                  String filename = scanner.next().trim();
                  searchFile(filename);
                  break;
              }
              case 4 : {
                  System.out.println("Going Back to MAIN menu");
                  showMainMenu();
                  break;
              }
              default : System.out.println("Please enter 1, 2, 3 or 4 ONLY");
          }
          showSecondaryMenu();
      }
      catch (Exception e){
          System.out.println("Please enter 1, 2, 3 or 4 ONLY");
          showSecondaryMenu();
      }
  }

  void DisplayFiles() {
      if (folder_name.list().length==0)
          System.out.println("The folder is empty");
      else {
          String[] list = folder_name.list();
          System.out.println("List of files in Directory  '"+ folder_name +"' are listed below in ascending order:");
          Arrays.sort(list);
          for (String str:list) {
			  
			  System.out.println(str);
				
          }
      }
  }

  void addFile(String filename) throws IOException {
      File filepath = new File(folder_name +"/"+filename);
      String[] list = folder_name.list();
      for (String file: list) {
          if (filename.equalsIgnoreCase(file)) {
              System.out.println("File " + filename + " already exists at " + folder_name);
              return;
          }
      }
      filepath.createNewFile();
      System.out.println("File "+filename+" added to "+ folder_name);
  }

  void deleteFile(String filename) {
      File filepath = new File(folder_name +"/"+filename);
      String[] list = folder_name.list();
      for (String file: list) {
          if (filename.equals(file) && filepath.delete()) {
              System.out.println("File " + filename + " deleted from " + folder_name);
              return;
          }
      }
      System.out.println("Delete Operation failed. FILE NOT FOUND");
  }

  void searchFile(String filename) {
      String[] list = folder_name.list();
      for (String file: list) {
          if (filename.equals(file)) {
              System.out.println("FOUND : File " + filename + " exists at " + folder_name);
              return;
          }
      }
      System.out.println("File NOT found (FNF)");
  }

  public static void main(String[] args) {
      System.out.println(WelcomeScreen);
      Lockedmeapp menu = new Lockedmeapp();
      menu.showMainMenu();
  }
}