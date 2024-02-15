import java.io.*;
import java.util.Scanner;

public class Main {

    private static int startingX;
    private static int startingY;
    private static int startingZ;
    private static int goalX;
    private static int goalY;
    private static int goalZ;
    public static void main(String[] args) throws IOException {
        // set up a buffered reader for input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int distanceTravelledInput = 0;
        char directionInput = '0';
        double shellDis = 0;

        String startingInput = br.readLine();
        // Split the input string into an array of strings
        String[] startingPoint = startingInput.split(" ");
        // Convert the strings to numbers (assuming they are integers)
        startingX = Integer.parseInt(startingPoint[0]);
        startingY = Integer.parseInt(startingPoint[1]);
        startingZ = Integer.parseInt(startingPoint[2]);

        String goalInput = br.readLine();
        // Split the input string into an array of strings
        String[] goalPoint = goalInput.split(" ");

        // Convert the strings to numbers (assuming they are integers)
        goalX = Integer.parseInt(goalPoint[0]);
        goalY = Integer.parseInt(goalPoint[1]);
        goalZ = Integer.parseInt(goalPoint[2]);


        do{
            distanceTravelledInput = br.read();

        }
        while(directionInput != 'E');

        br.close();
    }
    public static double disFromShell(int x, int y, int z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)+Math.pow(z,2));
    }
}