import java.io.*;
import java.util.*;

/**
 * @author Mathis Luo & Robbie Muranaka
 * Date: April 1st, 2024
 * Solves the CCC '04 S4 - Space Turtle
 */
public class Main {

    // directionFacing ranges from 1 to 6
    // 1 is positive x direction | 2 is negative x direction
    // 3 is positive y direction | 4 is negative y direction
    // 5 is positive z direction | 6 is negative z direction
    private static byte directionFacing = 1;

    // topPlaneFacing ranges from 1 to 6
    // 1 is positive x direction | 2 is negative x direction
    // 3 is positive y direction | 4 is negative y direction
    // 5 is positive z direction | 6 is negative z direction
    private static byte topPlaneFacing = 5;
    // the coordinates of the goal
    private static int goalX;
    private static int goalY;
    private static int goalZ;
    private static int movingToX;
    private static int movingToY;
    private static int movingToZ;

    // set up a buffered reader for input
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        // declare variables for the input
        int distanceTravelledInput;
        char directionInput;
        // smallestDistanceFromShell is set to the largest possible value so that the first distance from the shell will always be smaller
        double smallestDistanceFromShell = 2147483646;

        // get starting position
        int startingX = readInt();
        int startingY = readInt();
        int startingZ = readInt();

        // get goal position
        goalX = readInt();
        goalY = readInt();
        goalZ = readInt();

        // set current position to starting position
        int currentX = startingX;
        int currentY = startingY;
        int currentZ = startingZ;

        // loop until direction input is E (end)
        do {
            // get input for distance travelled and direction
            distanceTravelledInput = readInt();
            directionInput = readCharacter();
            // update the smallest distance from shell
            if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell) {
                smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
            }
            // set the moving to coordinates depending on the direction the ship is facing
            switch (directionFacing) {
                case 1:
                    movingToX = currentX + distanceTravelledInput;
                    break;
                case 2:
                    movingToX = currentX - distanceTravelledInput;
                    break;
                case 3:
                    movingToY = currentY + distanceTravelledInput;
                    break;
                case 4:
                    movingToY = currentY - distanceTravelledInput;
                    break;
                case 5:
                    movingToZ = currentZ + distanceTravelledInput;
                    break;
                case 6:
                    movingToZ = currentZ - distanceTravelledInput;
                    break;
            }
            // move the ship one unit at a time and update the smallest distance from shell if necessary each time
            for(int i = 0; i < distanceTravelledInput; i++) {
                // switch between 6 possible facing directions
                switch (directionFacing) {
                    case 1:
                        currentX++;
                        // update the smallest distance from shell if necessary
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 2:
                        currentX--;
                        // update the smallest distance from shell if necessary
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 3:
                        currentY++;
                        // update the smallest distance from shell if necessary
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 4:
                        currentY--;
                        // update the smallest distance from shell if necessary
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 5:
                        currentZ++;
                        // update the smallest distance from shell if necessary
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 6:
                        currentZ--;
                        // update the smallest distance from shell if necessary
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                }
            }
            // turn the ship after it has moved
            fuckingTurn(directionFacing, topPlaneFacing, directionInput);
        } while(directionInput != 'E');
        // print answer
        System.out.println(roundToTwoDecimals(smallestDistanceFromShell));
        br.close();
    }

    /**
     * Get the shortest distance this input
     */
    public static double getDistanceFromShell(int currentX, int currentY, int currentZ) {
        return Math.min(checkEndPointDistance(currentX, currentY, currentZ), checkDistanceFromLine(currentX, currentY, currentZ));
    }

    /**
     * This method gets the distance from the current position to the goal using the formula for
     * calculating the Euclidean distance in 3D space.
     */
    public static double checkEndPointDistance(int currentX, int currentY, int currentZ) {
        double deltaX = goalX - currentX;
        double deltaY = goalY - currentY;
        double deltaZ = goalZ - currentZ;
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ,2));
    }

    /**
     * This method checks the shortest distance between the line that you are moving along and the goal
     */
    public static double checkDistanceFromLine(int currentX, int currentY, int currentZ) {
        if (directionFacing == 1 || directionFacing == 2) {
            // we are moving in the X direction
            // check if the goal is within movement range
            if ((goalX < movingToX && goalX > currentX) || (goalX > movingToX && goalX < currentX)) {
                double deltaY = goalY - currentY;
                double deltaZ = goalZ - currentZ;
                return Math.sqrt(Math.pow(deltaY, 2) + Math.pow(deltaZ, 2));
            } else {
                return 2147483646;
            }
        } else if (directionFacing == 3 || directionFacing == 4) {
            // we are moving in the Y direction
            // check if the goal is within movement range
            if ((goalY < movingToY && goalY > currentY) || (goalY > movingToY && goalY < currentY)) {
                double deltaX = goalX - currentX;
                double deltaZ = goalZ - currentZ;
                return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
            } else {
                return 2147483646;
            }
        } else {
            // we are moving in the Z direction
            // check if the goal is within movement range
            if ((goalZ < movingToZ && goalZ > currentZ) || (goalZ > movingToZ && goalZ < currentZ)) {
                double deltaX = goalX - currentX;
                double deltaY = goalY - currentY;
                return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
            } else {
                return 2147483646;
            }
        }
    }

    /**
     * This method rounds the input to 2 decimal places.
     */
    private static double roundToTwoDecimals(double input) {
        return Math.round(input * 100.0) / 100.0;
    }

    /**
     * This method takes in the current heading of the ship, the direction the top plane of the ship is facing,
     * and the direction the ship is to turn. It then updates the directionFacing and topPlaneFacing variables.
     */
    public static void fuckingTurn(int noseFacing, int topFacing, char turnDirection) {
        // switch between 6 possible facing directions
        switch(noseFacing) {
            // ship is facing in the positive x direction
            case 1:
                // switch between 4 possible top plane facing directions
                switch (topFacing) {
                    // top plane is facing the positive y direction
                    case 3:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 6;
                                break;
                            case 'R':
                                directionFacing = 5;
                                break;
                            case 'U':
                                directionFacing = 3;
                                topPlaneFacing = 2;
                                break;
                            case 'D':
                                directionFacing = 4;
                                topPlaneFacing = 1;
                                break;
                        }
                        break;
                    // top plane is facing negative y direction
                    case 4:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 5;
                                break;
                            case 'R':
                                directionFacing = 6;
                                break;
                            case 'U':
                                directionFacing = 4;
                                topPlaneFacing = 2;
                                break;
                            case 'D':
                                directionFacing = 3;
                                topPlaneFacing = 1;
                                break;
                        }
                        break;
                    // top plane is facing positive z direction
                    case 5:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 3;
                                break;
                            case 'R':
                                directionFacing = 4;
                                break;
                            case 'U':
                                directionFacing = 5;
                                topPlaneFacing = 2;
                                break;
                            case 'D':
                                directionFacing = 6;
                                topPlaneFacing = 1;
                                break;
                        }
                        break;
                    // top plane is facing negative z direction
                    case 6:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 4;
                                break;
                            case 'R':
                                directionFacing = 3;
                                break;
                            case 'U':
                                directionFacing = 6;
                                topPlaneFacing = 2;
                                break;
                            case 'D':
                                directionFacing = 5;
                                topPlaneFacing = 1;
                                break;
                        }
                        break;
                }
                break;
            // ship is facing in the negative x direction
            case 2:
                // switch between 4 possible top plane facing directions
                switch (topFacing) {
                    // top plane is facing the positive y direction
                    case 3:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 5;
                                break;
                            case 'R':
                                directionFacing = 6;
                                break;
                            case 'U':
                                directionFacing = 3;
                                topPlaneFacing = 1;
                                break;
                            case 'D':
                                directionFacing = 4;
                                topPlaneFacing = 2;
                                break;
                        }
                        break;
                    // top plane is facing negative y direction
                    case 4:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 6;
                                break;
                            case 'R':
                                directionFacing = 5;
                                break;
                            case 'U':
                                directionFacing = 4;
                                topPlaneFacing = 1;
                                break;
                            case 'D':
                                directionFacing = 3;
                                topPlaneFacing = 2;
                                break;
                        }
                        break;
                    // top plane is facing positive z direction
                    case 5:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 4;
                                break;
                            case 'R':
                                directionFacing = 3;
                                break;
                            case 'U':
                                directionFacing = 5;
                                topPlaneFacing = 1;
                                break;
                            case 'D':
                                directionFacing = 6;
                                topPlaneFacing = 2;
                                break;
                        }
                        break;
                    // top plane is facing negative z direction
                    case 6:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 3;
                                break;
                            case 'R':
                                directionFacing = 4;
                                break;
                            case 'U':
                                directionFacing = 6;
                                topPlaneFacing = 1;
                                break;
                            case 'D':
                                directionFacing = 5;
                                topPlaneFacing = 2;
                                break;
                        }
                        break;
                }
                break;
            // ship is facing in the positive y direction
            case 3:
                // switch between 4 possible top plane facing directions
                switch (topFacing) {
                    // top plane is facing the positive x direction
                    case 1:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 5;
                                break;
                            case 'R':
                                directionFacing = 6;
                                break;
                            case 'U':
                                directionFacing = 1;
                                topPlaneFacing = 4;
                                break;
                            case 'D':
                                directionFacing = 2;
                                topPlaneFacing = 3;
                                break;
                        }
                        break;
                    // top plane is facing negative x direction
                    case 2:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 6;
                                break;
                            case 'R':
                                directionFacing = 5;
                                break;
                            case 'U':
                                directionFacing = 2;
                                topPlaneFacing = 4;
                                break;
                            case 'D':
                                directionFacing = 1;
                                topPlaneFacing = 3;
                                break;
                        }
                        break;
                    // top plane is facing positive z direction
                    case 5:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 2;
                                break;
                            case 'R':
                                directionFacing = 1;
                                break;
                            case 'U':
                                directionFacing = 5;
                                topPlaneFacing = 4;
                                break;
                            case 'D':
                                directionFacing = 6;
                                topPlaneFacing = 3;
                                break;
                        }
                        break;
                    // top plane is facing negative z direction
                    case 6:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 1;
                                break;
                            case 'R':
                                directionFacing = 2;
                                break;
                            case 'U':
                                directionFacing = 6;
                                topPlaneFacing = 4;
                                break;
                            case 'D':
                                directionFacing = 5;
                                topPlaneFacing = 3;
                                break;
                        }
                        break;
                }
                break;
            // ship is facing in the negative y direction
            case 4:
                // switch between 4 possible top plane facing directions
                switch (topFacing) {
                    // top plane is facing the positive x direction
                    case 1:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 6;
                                break;
                            case 'R':
                                directionFacing = 5;
                                break;
                            case 'U':
                                directionFacing = 1;
                                topPlaneFacing = 3;
                                break;
                            case 'D':
                                directionFacing = 2;
                                topPlaneFacing = 4;
                                break;
                        }
                        break;
                    // top plane is facing negative x direction
                    case 2:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 5;
                                break;
                            case 'R':
                                directionFacing = 6;
                                break;
                            case 'U':
                                directionFacing = 2;
                                topPlaneFacing = 3;
                                break;
                            case 'D':
                                directionFacing = 1;
                                topPlaneFacing = 4;
                                break;
                        }
                        break;
                    // top plane is facing positive z direction
                    case 5:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 1;
                                break;
                            case 'R':
                                directionFacing = 2;
                                break;
                            case 'U':
                                directionFacing = 5;
                                topPlaneFacing = 3;
                                break;
                            case 'D':
                                directionFacing = 6;
                                topPlaneFacing = 4;
                                break;
                        }
                        break;
                    // top plane is facing negative z direction
                    case 6:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 2;
                                break;
                            case 'R':
                                directionFacing = 1;
                                break;
                            case 'U':
                                directionFacing = 6;
                                topPlaneFacing = 3;
                                break;
                            case 'D':
                                directionFacing = 5;
                                topPlaneFacing = 4;
                                break;
                        }
                        break;
                }
                break;
            // ship is facing in the positive z direction
            case 5:
                // switch between 4 possible top plane facing directions
                switch (topFacing) {
                    // top plane is facing the positive x direction
                    case 1:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 4;
                                break;
                            case 'R':
                                directionFacing = 3;
                                break;
                            case 'U':
                                directionFacing = 1;
                                topPlaneFacing = 6;
                                break;
                            case 'D':
                                directionFacing = 2;
                                topPlaneFacing = 5;
                                break;
                        }
                        break;
                    // top plane is facing negative x direction
                    case 2:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 3;
                                break;
                            case 'R':
                                directionFacing = 4;
                                break;
                            case 'U':
                                directionFacing = 2;
                                topPlaneFacing = 6;
                                break;
                            case 'D':
                                directionFacing = 1;
                                topPlaneFacing = 5;
                                break;
                        }
                        break;
                    // top plane is facing positive y direction
                    case 3:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 1;
                                break;
                            case 'R':
                                directionFacing = 2;
                                break;
                            case 'U':
                                directionFacing = 3;
                                topPlaneFacing = 6;
                                break;
                            case 'D':
                                directionFacing = 4;
                                topPlaneFacing = 5;
                                break;
                        }
                        break;
                    // top plane is facing negative y direction
                    case 4:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 2;
                                break;
                            case 'R':
                                directionFacing = 1;
                                break;
                            case 'U':
                                directionFacing = 4;
                                topPlaneFacing = 6;
                                break;
                            case 'D':
                                directionFacing = 3;
                                topPlaneFacing = 5;
                                break;
                        }
                        break;
                }
                break;
            // ship is facing in the negative z direction
            case 6:
                // switch between 4 possible top plane facing directions
                switch (topFacing) {
                    // top plane is facing the positive x direction
                    case 1:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 3;
                                break;
                            case 'R':
                                directionFacing = 4;
                                break;
                            case 'U':
                                directionFacing = 1;
                                topPlaneFacing = 5;
                                break;
                            case 'D':
                                directionFacing = 2;
                                topPlaneFacing = 6;
                                break;
                        }
                        break;
                    // top plane is facing negative x direction
                    case 2:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 4;
                                break;
                            case 'R':
                                directionFacing = 3;
                                break;
                            case 'U':
                                directionFacing = 2;
                                topPlaneFacing = 5;
                                break;
                            case 'D':
                                directionFacing = 1;
                                topPlaneFacing = 6;
                                break;
                        }
                        break;
                    // top plane is facing positive y direction
                    case 3:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 2;
                                break;
                            case 'R':
                                directionFacing = 1;
                                break;
                            case 'U':
                                directionFacing = 3;
                                topPlaneFacing = 5;
                                break;
                            case 'D':
                                directionFacing = 4;
                                topPlaneFacing = 6;
                                break;
                        }
                        break;
                    // top plane is facing negative y direction
                    case 4:
                        // turn depending on requested turn direction
                        switch (turnDirection) {
                            case 'L':
                                directionFacing = 1;
                                break;
                            case 'R':
                                directionFacing = 2;
                                break;
                            case 'U':
                                directionFacing = 4;
                                topPlaneFacing = 5;
                                break;
                            case 'D':
                                directionFacing = 3;
                                topPlaneFacing = 6;
                                break;
                                }
                        break;
                }
                break;
        }

    }

    // The next three methods are template code for using buffered reader
    private static String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine().trim());
        return st.nextToken();
    }
    private static int readInt() throws IOException {
        return Integer.parseInt(next());
    }
    private static char readCharacter() throws IOException {
        return next().charAt(0);
    }

}