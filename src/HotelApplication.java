
import java.util.Scanner;
/**
 * @author-VipparlaSreeram
 */
public class HotelApplication {

    public static void main(String[] args) {
        boolean Run_The_Program = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (Run_The_Program) {
                try {
                    MainMenu.Menu_Options();
                    int selection = Integer.parseInt(scanner.nextLine());
                    Run_The_Program = MainMenu.executeOption(scanner, selection);
                } catch (Exception ex) {
                    System.out.println("Please enter a number between 1 and 5");
                }
            }
        } catch (Exception ex) {
            System.out.println("\nError - Exiting program...\n");
        }
    }

}
