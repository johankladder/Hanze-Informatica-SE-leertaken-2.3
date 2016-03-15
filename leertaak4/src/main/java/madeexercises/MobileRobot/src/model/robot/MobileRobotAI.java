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

    private final int stepSize = 10; // Step-size the robot takes when going forward -> Crucial for narrow corners
    private final int extraClicksBeforeCorner = 3; // Additional steps for passing trough corner // FIXME: Remove this one when knowing width and height of robot
    private final int extraClicksAfterCorner = 3; // Additional steps for passing after corner // FIXME: Remove this one when knowing width and height of robot
    private final int minimalSpacingRightWall = 50; // FIXME: Remove this one when knowing width and height of robot
    private int extraClicks = 0; // FIXME: Remove this one when knowing width and height of robot

    private boolean first = true; // Status flag for initialising algorithm

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
        while (running) {
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

                double wallRight = foundMeasures[90];
                double wallForward = foundMeasures[0];

                // Begin logic:
                if (first) {
                    // Because this is a right-handed wall following algorithm
                    // the robot first need to go to the right side of a path
                    robot.sendCommand("P1.ROTATERIGHT 90"); // FIXME: Doesn't work when robot has other default spawning degree
                    input.readLine();
                    robot.sendCommand("P1.MOVEFW " + wallForward); // Let the robot moves toward the right-handed wall
                    input.readLine();
                    robot.sendCommand("P1.ROTATELEFT 90"); // Rotates the robot back to original state (face up)
                    input.readLine();
                    first = false; // Set status-flag

                } else if (wallRight > minimalSpacingRightWall) {
                    // FIXME: The code with (**) notation is not as clean as it can get.
                    if (extraClicks < extraClicksBeforeCorner) {
                        robot.sendCommand("P1.MOVEFW " + stepSize); // **
                        input.readLine(); // **
                        extraClicks++;
                    } else {
                        robot.sendCommand("P1.ROTATERIGHT 90");
                        input.readLine();

                        for (int xClicks = 0; xClicks < extraClicksAfterCorner; xClicks++) {
                            robot.sendCommand("P1.MOVEFW " + stepSize); // **
                            input.readLine(); // **
                        }

                        extraClicks = 0;
                    }

                } else if (wallForward > stepSize) {
                    robot.sendCommand("P1.MOVEFW " + stepSize);
                    input.readLine();
                } else {
                    robot.sendCommand("P1.ROTATELEFT 90");
                    input.readLine();
                }

            } catch (IOException ioe) {
                System.err.println("execution stopped");
                running = false;
            }
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


}
