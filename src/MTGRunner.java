import java.util.Scanner;
public class MTGRunner
{
        public static void main(String[] args)
        {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter card name: ");
            String name = s.nextLine();
            System.out.print("Enter card set: ");
            String set = s.nextLine();

            MTGAPI api = new MTGAPI();
            api.getCardName(name, set);
        }
}
