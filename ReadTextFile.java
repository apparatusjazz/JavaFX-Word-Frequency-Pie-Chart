package sample;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadTextFile {

    private static Scanner input;
    public static void openFile(String file) {
        try {
            input = new Scanner(new File(file));
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String readFile() {
        String s = "";
        while(input.hasNext()) {
            s += input.nextLine();
            //System.out.println(s);
        }
        return s;
    }
    public static void closeFile() {
        if (input != null) {
            input.close();
        }
    }
}
