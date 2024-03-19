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
    private static int startingX;
    private static int startingY;
    private static int startingZ;
    private static int goalX;
    private static int goalY;
    private static int goalZ;
    private static int currentX;
    private static int currentY;
    private static int currentZ;
    // set up a buffered reader for input (ty Jeff for template)
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        int distanceTravelledInput = 0;
        char directionInput = '0';
        double smallestDistanceFromShell = 2147483646;

        // get starting position
        startingX = readInt();
        startingY = readInt();
        startingZ = readInt();

        // get goal position
        goalX = readInt();
        goalY = readInt();
        goalZ = readInt();

        System.out.println("Starting point: " + startingX + " " + startingY + " " + startingZ);
        System.out.println("Goal point: " + goalX + " " + goalY + " " + goalZ);

        // set current position to starting position
        currentX = startingX;
        currentY = startingY;
        currentZ = startingZ;

        do {
            distanceTravelledInput = readInt();
            directionInput = readCharacter();
            if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell) {
                smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
            }
            System.out.println("Distance traveled input: " + distanceTravelledInput + " Direction input: " + directionInput);
            System.out.println("Distance from shell: " + getDistanceFromShell(currentX, currentY, currentZ));
            System.out.println("Current smallestDistanceFromShell: " + smallestDistanceFromShell);
            System.out.println("Current XYZ: " + currentX + " " + currentY + " " + currentZ);
            for(int i = 0; i < distanceTravelledInput; i++) {
                System.out.println("Looping for loop");
                switch (directionFacing) {
                    case 1:
                        System.out.println("Case 1: Facing Positive X");
                        currentX++;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 2:
                        System.out.println("Case 2: Facing Negative X");
                        currentX--;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 3:
                        System.out.println("Case 3: Facing Positive Y");
                        currentY++;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 4:
                        System.out.println("Case 4: Facing Negative Y");
                        currentY--;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 5:
                        System.out.println("Case 5: Facing Positive Z");
                        currentZ++;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                    case 6:
                        System.out.println("Case 6: Facing Negative Z");
                        currentZ--;
                        if (getDistanceFromShell(currentX, currentY, currentZ) < smallestDistanceFromShell)
                            smallestDistanceFromShell = getDistanceFromShell(currentX, currentY, currentZ);
                        break;
                }
                if(getDistanceFromShell(currentX,currentY,currentZ) < smallestDistanceFromShell){
                    smallestDistanceFromShell = getDistanceFromShell(currentX,currentY,currentZ);
                }

            }
            fuckingTurn(directionFacing, topPlaneFacing, directionInput);
        } while(directionInput != 'E');
        System.out.println("FINAL smallest distance from shell: " + roundToTwoDecimals(smallestDistanceFromShell));
        br.close();
    }
    public static double getDistanceFromShell(int x, int y, int z) {
        return Math.sqrt(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) + Math.pow(z,2));
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