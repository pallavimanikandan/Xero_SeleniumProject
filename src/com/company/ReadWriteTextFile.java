package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadWriteTextFile {
    static FileWriter FW = null;
    static BufferedWriter BW = null;
    static FileReader FR = null;
    static BufferedReader BR = null;
    static StringBuffer Text = null;

    public static void writeTextFile(String filePath) throws IOException {

        // String TestFile = "C:\\Pallavi\\Selenium-Utility\\SalesForceLoginData.txt";
        try {
            File FC = new File(filePath);
            if(!FC.exists()){
                FC.createNewFile();
            }

            //Writing In to file.
            FW = new FileWriter(filePath);
            BW = new BufferedWriter(FW);
            BW.write("Field Description,Values"); //Writing In To File.
            BW.newLine();
            BW.write("UserName,pallavinarasimman@gmail.com"); //Writing In To File.
            BW.newLine();
            BW.write("Password,MyOwnCompany2019"); //Writing In To File.
            BW.newLine();
            BW.write("URL,https://www.xero.com/us/");
            BW.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Reading from file..
    public static String[][] readTextFile(String filePath) throws IOException

    {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)));
        long lineCount = Files.lines(Paths.get(filePath)).count();  // Finding no.of rows
        int rows = (int) lineCount;
        int columns = sc.nextLine().trim().split(",").length; // Finding no.of columns
        String [][] myData = new String[rows][columns];
// Reading the file
        while(sc.hasNextLine()) {
            for (int i=0; i<myData.length-1; i++)   // myData.length-1; because 1st line is header skipping the 1st line
            {
                String[] line = sc.nextLine().trim().split(",");
                for (int j=0; j<line.length; j++) {
                    myData[i][j] = line[j];
                }
            }
        }
        return myData;
    }



    public static void main(String args[]) throws IOException {
        String file_Path= "C:\\Pallavi\\Selenium-Utility\\XeroDataFile.txt";
        //Writing the File
        writeTextFile(file_Path);
       //Reading the File
        String[][] data = readTextFile(file_Path);

        System.out.println("1st Line: " + data[0][0] + " is " + data[0][1]);
        System.out.println("2nd Line: " + data[1][0] + " is " + data[1][1]);
        System.out.println("3rd Line: " + data[2][0] + " is " + data[2][1]);

    }
}

