package madeexercises.MobileRobot.src.model.robot;


import madeexercises.MobileRobot.src.model.virtualmap.OccupancyMap;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Title    :   The Mobile Robot Explorer Simulation Environment v2.0
 * Copyright:   GNU General Public License as published by the Free Software Foundation
 * Company  :   Hanze University of Applied Sciences
 *
 * @author Dustin Meijer        (2012)
 * @author Alexander Jeurissen  (2012)
 * @author Davide Brugali       (2002)
 * @version 2.0
 */

public class MobileRobotAI implements Runnable {

    private final OccupancyMap map;
    private final MobileRobot robot;

    /*
    Helper fields: Need to be removed when exercise is completed
     */
    private final double stepSize = 8; // Step-size the robot takes when going forward -> Crucial for narrow corners // FIXME: Do steps as far as it can see
    private final int extraClicksBeforeCorner = 4; // Additional steps for passing trough corner // FIXME: Remove this one when knowing width and height of robot
    private final int extraClicksAfterCorner = 3; // Additional steps for passing after corner // FIXME: Remove this one when knowing width and height of robot
    private int extraClicks = 0; // FIXME: Remove this one when knowing width and height of robot

    private boolean corner = false;


    /*
    Configuration:
     */

    private static boolean rightHanded = true;
    private boolean first = true; // Status flag for initialising algorithm (Only for turning the robot the left or right)

    public MobileRobotAI(MobileRobot robot, OccupancyMap map) {
        this.map = map;
        this.robot = robot;
    }

    /**
     * In this method the gui.controller sends commands to the robot and its devices.
     * At the moment all the commands are hardcoded.
     * The exercise is to let the gui.controller make intelligent decisions based on
     * what has been discovered so far. This information is contained in the OccupancyMap.
     */
    public void run() {
        String result;
        boolean running = true;
        double position[] = new double[3];
        double measures[] = new double[360];
        double sonarMeasure[] = new double[360];

        while (running) {
            if (isFullyDiscovered()) {
                running = false;
                robot.quit();
            }
            try {

                PipedInputStream pipeIn = new PipedInputStream();
                BufferedReader input = new BufferedReader(new InputStreamReader(pipeIn));
                PrintWriter output = new PrintWriter(new PipedOutputStream(pipeIn), true);

                robot.setOutput(output);

                robot.sendCommand("R1.GETPOS");
                result = input.readLine();
                double[] foundPositions = parsePosition(result, position);

                robot.sendCommand("L1.SCAN");
                result = input.readLine();
                double foundMeasures[] = parseMeasures(result, measures);
                map.drawLaserScan(position, measures);

                robot.sendCommand("S1.SCAN");
                result = input.readLine();
                double scanSonar[] = parseMeasures(result, sonarMeasure);


                checkUnreachableUnknowns();


                double wallRight = foundMeasures[90];
                double wallForward = foundMeasures[0];
                double wallLeft = foundMeasures[270];

                double rotationWall;

                if (!rightHanded) {
                    rotationWall = wallLeft;
                } else {
                    rotationWall = wallRight;
                }


                // Begin logic:
                if (first) {
//                    // The robot first needs to turn to the side of the desired algorithm!
//                    robot.sendCommand(getBeginRotationString()); // FIXME: Doesn't work when robot has other default spawning degree
//                    // -> Can be fixed by first calling that robot need to be on degree 0
//                    input.readLine();

                    first = false; // Set status-flag

                } else if (rotationWall > 50 || corner) {

                    // FIXME: The code with (**) notation is not as clean as it can get.
                    if (extraClicks < extraClicksBeforeCorner) {
                        robot.sendCommand("P1.MOVEFW " + stepSize); // **
                        input.readLine(); // **
                        extraClicks++; // **
                    } else {
                        robot.sendCommand(getBeginRotationString());
                        input.readLine();

                        for (int xClicks = 0; xClicks < extraClicksAfterCorner; xClicks++) {
                            robot.sendCommand("P1.MOVEFW " + stepSize); // **
                            input.readLine(); // **
                        }

                        extraClicks = 0; //**
                        corner = false;
                    }

                } else if (wallForward > stepSize) {

                    // TODO: Stepsize is always to small, needs to be the maximum it can see, minus the wall-spacing

                    double a = measures[90];
                    double c = 0;
                    boolean hit = false;

                    for (int cValue = 89; cValue > 0; cValue--) {
                        if (!hit) {
                            double testc = measures[cValue];
                            System.out.println(cValue + "  | " + testc);
                            if (testc >= 100) {
                                hit = true;
                            } else {
                                if ((testc - c) > 10 && c != 0) {
                                    hit = true;
                                    corner = true;

                                } else {
                                    c = testc;
                                }
                            }
                        }

                    }

                    c = Math.pow(c, 2);
                    a = Math.pow(a, 2);

                    double b = stepSize;

                    if (c > a) {
                        b = Math.sqrt((c - a));
                    }


                    System.out.println("-----------------" + rotationWall);
                    robot.sendCommand("P1.MOVEFW " + b);
                    input.readLine();

                } else {
                    robot.sendCommand(getInvertedRotation());
                    input.readLine();
                }

            } catch (IOException ioe) {
                System.err.println("execution stopped");
                running = false;
            }
        }

    }

    public static void setRightWallFollowing(boolean status) {
        rightHanded = status;
    }

    public String getBeginRotationString() {
        if (rightHanded) {
            return "P1.ROTATERIGHT 90";
        } else {
            return "P1.ROTATELEFT 90";
        }
    }

    public String getInvertedRotation() {
        if (!rightHanded) {
            return "P1.ROTATERIGHT 90";
        } else {
            return "P1.ROTATELEFT 90";
        }
    }

    private double[] parsePosition(String value, double position[]) {
        int indexInit;
        int indexEnd;
        String parameter;

        indexInit = value.indexOf("X=");
        parameter = value.substring(indexInit + 2);
        indexEnd = parameter.indexOf(' ');
        position[0] = Double.parseDouble(parameter.substring(0, indexEnd));

        indexInit = value.indexOf("Y=");
        parameter = value.substring(indexInit + 2);
        indexEnd = parameter.indexOf(' ');
        position[1] = Double.parseDouble(parameter.substring(0, indexEnd));

        indexInit = value.indexOf("DIR=");
        parameter = value.substring(indexInit + 4);
        position[2] = Double.parseDouble(parameter);

        return position;
    }

    private double[] parseMeasures(String value, double measures[]) {
        for (int i = 0; i < 360; i++) {
            measures[i] = 100.0;
        }

        if (value.length() >= 5) {
            value = value.substring(5);  // removes the "SCAN " keyword

            StringTokenizer tokenizer = new StringTokenizer(value, " ");

            double distance;
            int direction;
            while (tokenizer.hasMoreTokens()) {
                distance = Double.parseDouble(tokenizer.nextToken().substring(2));
                direction = (int) Math.round(Math.toDegrees(Double.parseDouble(tokenizer.nextToken().substring(2))));
                if (direction == 360) {
                    direction = 0;
                }
                measures[direction] = distance;
            }

            // TODO: Opdracht 4
            // Print all the distances for every degree
            for (int i = 0; i < measures.length; i++) {
                double object = measures[i];

                if (object < 100) {
                    System.out.print("Obstacle: ");
                }
                System.out.print(i + "=" + object + "| ");
            }
            System.out.print("\n"); // Clears the line
        }

        return measures;
    }

    private void checkUnreachableUnknowns() {
        //Eerst moeten we het aantal cellen bepalen
        // Het aantal horizontale cellen is gelijk aan de breedte van het veld / de breedte van een cel
        // Het aantal verticale cellen is gelijk aan de hoogte van het veld / de hoogte van een cel
        int xCellSize = map.getMapWidth() / map.getCellDimension();
        int yCellSize = map.getMapHeight() / map.getCellDimension();

        char[][] grid = map.getGrid();

        // Alle cellen doorlopen
        //Voor elke rij....
        for (int x = 0; x < xCellSize; x++) {
            //Elke kolom....
            for (int y = 0; y < yCellSize; y++) {
                //Alle cellen die UNKNOWN zijn op UNREACHABLE_UNKNOWN zetten
                if (grid[x][y] == map.getUnknown()) grid[x][y] = map.getUnreachableUnknown();

                // Nou kijken of we er echt niet bij kunnen
                // Dit kan door te kijken of ��n van de cellen er om heen EMPTY zijn
                // Als dit zo is dan mag deze cel weer veranderd worden naar UNKNOWN
                if (grid[x][y] == map.getUnreachableUnknown()) {
                    if ((x > 0 && grid[x - 1][y] == map.getEmpty()) || (y > 0 && grid[x][y - 1] == map.getEmpty()) || (x < xCellSize - 1 && grid[x + 1][y] == map.getEmpty())
                            || (y < yCellSize - 1 && grid[x][y + 1] == map.getEmpty())) grid[x][y] = map.getUnknown();
                }
            }
        }
    }

    public Boolean isFullyDiscovered() {
        char[][] grid = map.getGrid();
        for (int x = 0; x < map.getMapWidth() / map.getCellDimension(); x++) {
            for (int y = 0; y < map.getMapHeight() / map.getCellDimension(); y++) {
                if (grid[x][y] == map.getUnknown())
                    return false;
            }
        }
        return true;
    }


}
