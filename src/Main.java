import java.io.*;
import java.util.*;

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
    private static int goalX;
    private static int goalY;
    private static int goalZ;
    private static int movingToX;
    private static int movingToY;
    private static int movingToZ;
    // set up a buffered reader for input (ty Jeff for template)
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int distanceTravelledInput;
        char directionInput;
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

        do {
            distanceTravelledInput = readInt();
            directionInput = readCharacter();
            if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell) {
                smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
            }
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
            for(int i = 0; i < distanceTravelledInput; i++) {
                switch (directionFacing) {
                    case 1:
                        currentX++;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 2:
                        currentX--;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 3:
                        currentY++;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 4:
                        currentY--;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 5:
                        currentZ++;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 6:
                        currentZ--;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                }
            }
            System.out.println("Direction facing: " + directionFacing);
            System.out.println("Top plane facing: " + topPlaneFacing);
            System.out.println("Coords: " + currentX + " " + currentY + " " + currentZ);
            fuckingTurn(directionFacing, topPlaneFacing, directionInput);
        } while(directionInput != 'E');
        System.out.println("Final Direction facing: " + directionFacing);
        System.out.println("Final Top plane facing: " + topPlaneFacing);
        System.out.println("Final coords: " + currentX + " " + currentY + " " + currentZ);
        System.out.println(roundToTwoDecimals(smallestDistanceFromShell));
        br.close();
    }
    public static double getDistanceFromShell(int currentX, int currentY, int currentZ) {
        return Math.min(checkEndPointDistance(currentX, currentY, currentZ), checkDistanceFromLine(currentX, currentY, currentZ));
    }

    public static double checkEndPointDistance(int currentX, int currentY, int currentZ) {
        double deltaX = goalX - currentX;
        double deltaY = goalY - currentY;
        double deltaZ = goalZ - currentZ;
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ,2));
    }

    public static double checkDistanceFromLine(int currentX, int currentY, int currentZ) {
        if (directionFacing == 1 || directionFacing == 2) {
            // we are moving in the X direction
            if ((goalX < movingToX && goalX > currentX) || (goalX > movingToX && goalX < currentX)) {
                double deltaY = goalY - currentY;
                double deltaZ = goalZ - currentZ;
                return Math.sqrt(Math.pow(deltaY, 2) + Math.pow(deltaZ, 2));
            } else {
                return 2147483646;
            }
        } else if (directionFacing == 3 || directionFacing == 4) {
            // we are moving in the Y direction
            if ((goalY < movingToY && goalY > currentY) || (goalY > movingToY && goalY < currentY)) {
                double deltaX = goalX - currentX;
                double deltaZ = goalZ - currentZ;
                return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
            } else {
                return 2147483646;
            }
        } else {
            // we are moving in the Z direction
            if ((goalZ < movingToZ && goalZ > currentZ) || (goalZ > movingToZ && goalZ < currentZ)) {
                double deltaX = goalX - currentX;
                double deltaY = goalY - currentY;
                return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
            } else {
                return 2147483646;
            }
        }
    }

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

    private static double roundToTwoDecimals(double input) {
        return Math.round(input * 100.0) / 100.0;
    }

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
                                directionFacing = 4;
                                topPlaneFacing = 2;
                                break;
                            case 'D':
                                directionFacing = 3;
                                topPlaneFacing = 1;
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

}