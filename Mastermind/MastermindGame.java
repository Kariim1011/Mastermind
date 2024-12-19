import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Objects;
import java.util.Scanner;



class Game
{
    static String userGuess;
    public final static String colors = "RGBYWO";
    public static ArrayList<Character> listAns = new ArrayList<Character>();
    public ArrayList<Character> nails = new ArrayList<Character>();
    static Random random = new Random();
    
    public static void makeCode()
    {
        for (int i = 0; i < 4; ++i)
        {
            char c = colors.charAt(random.nextInt(colors.length())); 
            listAns.add(c);
        }
    }
    
    public void setUserGuess()
    {
        userGuess = GameHelper.CheckInput();
    }
    
    public String getUserGuess()
    {
        return userGuess;
    }
}

class GameHelper
{
    static Scanner in = new Scanner(System.in);
    private static String userGuess;
    public ArrayList<Character> InputArraylist = new ArrayList();
    public ArrayList<Character> coloneListAns = new ArrayList(Game.listAns);
    Game game = new Game();
    
    public static boolean trueInput(String userGuess)
    {
        boolean flag = true;
        for (int i = 0; i < userGuess.length(); ++i)
        {
            int pos = Game.colors.indexOf(userGuess.charAt(i));
            if (pos == -1)
            {
                flag = false;
                return flag;
            }
        }
        return flag;
    }
    
    public static String CheckInput()
    {
        for(;;)
        { 
            System.out.print("Enter the colors : ");
            userGuess = in.next().toUpperCase();
            if (userGuess.length() == 4 && trueInput(userGuess))
                break;
            else
                System.out.println("invalid");
        }
        return userGuess;
    }
    
    public void makeInputArraylist(String input)
    {
        for (int i = 0; i < input.length(); ++i)
            this.InputArraylist.add(input.charAt(i));
    }
    
    public void checkColor(String input)
    {
        for (int i = 0; i < InputArraylist.size(); ++i)
        {
            if (Objects.equals(InputArraylist.get(i), coloneListAns.get(i)))
            {
                game.nails.add('B');
                coloneListAns.set(i, '0');
                this.InputArraylist.set(i, '1');
            }
        }
        
        for (int i = 0; i < InputArraylist.size(); ++i)
        {
            for (int j = 0; j < game.listAns.size(); ++j)
            {
                if (Objects.equals(InputArraylist.get(i), coloneListAns.get(j)))
                {
                    game.nails.add('W');
                    coloneListAns.set(j, '0');
                    break;
                }
            }
        }    
    }
    
    public void printNail()
    {
        System.out.print("The number if Black = " + Collections.frequency(game.nails, 'B'));
        System.out.println("      The number if White = " + Collections.frequency(game.nails, 'W'));
    }
    
    public static void printColors()
    {
        for (int i = 0; i < Game.colors.length(); ++i)
        {
            if (i == 0)
                System.out.print("[");
            System.out.print(Game.colors.charAt(i));
            if (i != Game.colors.length()-1)
                System.out.print(", ");
        }
            System.out.print("]");
    }
}

public class MastermindGame
{   
    public static void main(String[] args)
    {
        int cnt = 10;
        boolean flag = true;
        Game game = new Game();
        Game.makeCode();
        System.out.print("Avaliable colors is ");
        GameHelper.printColors();
        System.out.println();
        while (cnt > 0)
        {
            GameHelper helper = new GameHelper();
            System.out.print(cnt + ": ");
            game.setUserGuess();
            helper.makeInputArraylist(game.getUserGuess());
            helper.checkColor(game.getUserGuess());
            helper.printNail();
            int counter = Collections.frequency(helper.InputArraylist, '1');
            if (counter == 4)
            {
                System.out.println("YOU WIN");
                flag = false;
                break;       
            }
            cnt--;
        }
        if (flag == true)
            System.out.println("YOU LOSE");
    }
}
