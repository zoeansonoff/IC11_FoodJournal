import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PaleoFood[] journal = new PaleoFood[10];
        int count = 0;
        String name;
        int calories, type, cookingTemp, option;
        boolean organic;
        File binaryFile = new File("FoodJournal.dat");
        Scanner keyboard = new Scanner(System.in);


        System.out.println("~~~~~~~~~Welcome to the Paleo Food Journal!~~~~~~~~~");
        if (binaryFile.exists() && binaryFile.length() > 0) {
            //try to read contents from binary file => array
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                journal = (PaleoFood[]) fileReader.readObject();
                //determine accurate count
                for (int i = 0; i < journal.length; i++) {
                    if (journal[i] != null) {
                        count++;
                        System.out.println(journal[i]);
                    }
                    else
                        break;

                }
                for (PaleoFood pf : journal) {
                    if (pf != null) {
                        count++;
                        System.out.println(pf);
                    }
                    else
                        break;
                }
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        if (count == 0)
            System.out.println("[No food eaten. You must be hungry.]");

        do{
            System.out.println("***** Options Menu *****");
            System.out.println("Enter (1) to record a meat");
            System.out.println("Enter (2) to record a produce");
            System.out.println("Enter (3) to quit");
            option = keyboard.nextInt();
            //Clear out extra \n
            keyboard.nextLine();

            switch (option) {
                case 1: //record meat, 2 is produce 3 is quit
                    try {
                        System.out.print("What is the name of the meat eaten?");
                        name = keyboard.nextLine();
                        System.out.print("How many calories was it?");
                        calories = keyboard.nextInt();
                        System.out.print("Enter (1) for mammal/bird or (2) for seafood: ");
                        type = keyboard.nextInt();
                        //check for mystery meat exception
                        if (type != 1 && type != 2)
                            throw new MysteryMeatException();
                        System.out.print("Enter the cooking temperature in Fahrenheit: ");
                        cookingTemp = keyboard.nextInt();
                        //new meat object added to array
                        journal[count++] = new Meat(name, calories, type, cookingTemp);
                    }
                    catch (MysteryMeatException e)
                    {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("What is the name of the produce eaten?");
                        name = keyboard.nextLine();
                        System.out.print("How many calories was it?");
                        calories = keyboard.nextInt();
                        System.out.print("Enter (1) for organic or (2) for non-organic: ");
                        type = keyboard.nextInt();
                        // Clear the buffer
                        keyboard.nextLine();
                        // Check for valid type
                        if (type != 1 && type != 2)
                          throw new IllegalArgumentException("Invalid produce type.");

                        System.out.print("Enter the amount of carbs in grams: ");
                        int carbs = keyboard.nextInt();
                        // Clear the buffer
                        keyboard.nextLine();
                        // Create produce object and add to array
                        journal[count++] = new Produce(name, calories, type, carbs);
                    } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("~~~Food Recorded in Journal~~~");
                    for (int i = 0; i < count; i++) {
                        System.out.println(journal[i]);

                    }
                    System.out.println();
                    int total = totalCalories(journal, count);
                    System.out.println("Total calories consumed   = " + total);
                    System.out.println("Average calories consumed = " + (double) total / count);
                    System.out.println("Food with the most calories = " + foodWithMostCalories(journal, count));
                    break;
            }

        } while (option != 3);
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(journal);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public static int totalCalories(PaleoFood[] journal, int count)
    {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += journal[i].getCalories();

        }
        return total;
    }
    public static PaleoFood foodWithMostCalories(PaleoFood[] journal, int count)
    {
        int max = Integer.MIN_VALUE;
        PaleoFood maxFood = null;
        for (int i = 0; i < count; i++) {
            if (journal[i].getCalories() > max) {
                max = journal[i].getCalories();
                maxFood = journal[i];
            }
        }
        return maxFood;
    }
}
