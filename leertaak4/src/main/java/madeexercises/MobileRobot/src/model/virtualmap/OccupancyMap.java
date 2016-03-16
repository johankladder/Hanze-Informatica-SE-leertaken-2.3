package madeexercises.MobileRobot.src.model.virtualmap;

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


import madeexercises.MobileRobot.src.model.environment.Environment;
import madeexercises.MobileRobot.src.model.environment.Position;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OccupancyMap {

	private final char UNKNOWN = 'n';
	private final char EMPTY = 'e';
	public final char OBSTACLE = 'o';
	private final char ROBOT = 'r';
	private final static char UNREACHABLE_UNKNOWN = 'u';

	private final int CELL_DIMENSION = 10;
	private final int MAP_WIDTH = 510;
	private final int MAP_HEIGHT = 460;

	private final char[][] grid;

	private final ArrayList<ActionListener> actionListenerList;
	private Environment environment;


	public OccupancyMap() {
		this.grid = new char[MAP_WIDTH / CELL_DIMENSION][MAP_HEIGHT / CELL_DIMENSION];

		for (int i = 0; i < MAP_WIDTH / CELL_DIMENSION; i++) {
			for (int j = 0; j < MAP_HEIGHT / CELL_DIMENSION; j++) {
				grid[i][j] = UNKNOWN;
			}
		}

		this.actionListenerList = new ArrayList<ActionListener>();


	}

	private void checkUnreachableUnknowns()
	{
		//Eerst moeten we het aantal cellen bepalen
		// Het aantal horizontale cellen is gelijk aan de breedte van het veld / de breedte van een cel
		// Het aantal verticale cellen is gelijk aan de hoogte van het veld / de hoogte van een cel
		int xCellSize = MAP_WIDTH / CELL_DIMENSION;
		int yCellSize = MAP_HEIGHT / CELL_DIMENSION;

		// Alle cellen doorlopen
		//Voor elke rij....
		for (int x = 0; x < xCellSize; x++)
		{
			//Elke kolom....
			for (int y = 0; y < yCellSize; y++)
			{
				//Alle cellen die UNKNOWN zijn op UNREACHABLE_UNKNOWN zetten
				if (grid[x][y] == UNKNOWN) grid[x][y] = UNREACHABLE_UNKNOWN;

				// Nou kijken of we er echt niet bij kunnen
				// Dit kan door te kijken of één van de cellen er om heen EMPTY zijn
				// Als dit zo is dan mag deze cel weer veranderd worden naar UNKNOWN
				if (grid[x][y] == UNREACHABLE_UNKNOWN)
				{
					if ((x > 0 && grid[x - 1][y] == EMPTY) || (y > 0 && grid[x][y - 1] == EMPTY) || (x < xCellSize - 1 && grid[x + 1][y] == EMPTY)
							|| (y < yCellSize - 1 && grid[x][y + 1] == EMPTY)) grid[x][y] = UNKNOWN;
				}
			}
		}
	}

	public Boolean isFullyDiscovered() {
		for (int x = 0; x < MAP_WIDTH / CELL_DIMENSION; x++) {
			for(int y = 0; y < MAP_HEIGHT / CELL_DIMENSION; y++) {
				if (grid[x][y] == UNKNOWN)
					return false;
			}
		}
		return true;
	}

	public void drawLaserScan(double position[], double measures[]) {
		double rx = Math.round(position[0] + 20.0 * Math.cos(Math.toRadians(position[2])));
		double ry = Math.round(position[1] + 20.0 * Math.sin(Math.toRadians(position[2])));
		int dir = (int) Math.round(position[2]);

		for (int i = 0; i < 360; i++) {
			int d = i - dir;
			while (d < 0)
				d += 360;
			while (d >= 360)
				d -= 360;
			double fx = Math.round(rx + measures[d] * Math.cos(Math.toRadians(i)));
			double fy = Math.round(ry + measures[d] * Math.sin(Math.toRadians(i)));

			if (measures[d] < 100) {
				drawLaserBeam(rx, ry, fx, fy, true);
			} else {
				drawLaserBeam(rx, ry, fx, fy, false);
			}

			checkUnreachableUnknowns();
		}

		//paint robot position on grid
		Position robotPos = environment.getRobot().getPlatform().getRobotPosition();
		//environment.getRobot().readPosition(robotPos);

		int robotX = (int) robotPos.getX() / CELL_DIMENSION;
		int robotY = (int) robotPos.getY() / CELL_DIMENSION;
		this.grid[robotX][robotY] = ROBOT;


		this.processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}


	/**
	 * This method allows other objects to register as ActionListeners.
	 *
	 * @param listener the ActionListener to add to the watchlist
	 */
	public void addActionListener(ActionListener listener) {
		this.actionListenerList.add(listener);
		processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

	}

	/**
	 * This method will remove the given ActionListener from the registered ActionListeners.
	 *
	 * @param listener the ActionListener to remove from the watchlist
	 */
	public void removeActionListener(ActionListener listener) {
		if (actionListenerList.contains(listener)) {
			actionListenerList.remove(listener);
		}
	}

	/**
	 * This method is intended to notify all ActionListeners of a event.
	 *
	 * @param event the event to be processed
	 */
	public void processEvent(ActionEvent event) {

		for (ActionListener listener : actionListenerList) {
			listener.actionPerformed(event);
		}
	}

	public int getCellDimension() {
		return CELL_DIMENSION;
	}

	public int getMapWidth() {
		return MAP_WIDTH;
	}

	public int getMapHeight() {
		return MAP_HEIGHT;
	}

	public char[][] getGrid() {
		return grid;
	}

	public char getUnknown() {
		return UNKNOWN;
	}

	public char getObstacle() {
		return OBSTACLE;
	}

	public char getEmpty() {
		return EMPTY;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	private void drawLaserBeam(double rx, double ry, double x, double y, boolean obstacle) {
		int rxi = (int) Math.ceil(rx / CELL_DIMENSION);
		int ryj = (int) Math.ceil(ry / CELL_DIMENSION);
		int xi = (int) Math.ceil(x / CELL_DIMENSION);
		int yj = (int) Math.ceil(y / CELL_DIMENSION);

		if (xi < 0 || yj < 0 || xi >= MAP_WIDTH / CELL_DIMENSION || yj >= MAP_HEIGHT / CELL_DIMENSION)
			return;

		if (obstacle) {
			grid[xi][yj] = OBSTACLE;
		} else if (grid[xi][yj] != OBSTACLE) {
			grid[xi][yj] = EMPTY;
		}

		int xmin = Math.min(rxi, xi);
		int xmax = Math.max(rxi, xi);
		int ymin = Math.min(ryj, yj);
		int ymax = Math.max(ryj, yj);

		if (rx == x) {
			for (int j = ymin; j <= ymax; j++) {
				if (grid[xmin][j] != OBSTACLE)
					grid[xmin][j] = EMPTY;
			}
		} else {
			double m = (y - ry) / (x - rx);
			double q = y - m * x;
			for (int i = xmin; i <= xmax; i++) {
				int h = (int) Math.ceil((m * (i * CELL_DIMENSION) + q) / CELL_DIMENSION);
				if (h >= ymin && h <= ymax) {
					if (grid[i][h] != OBSTACLE)
						grid[i][h] = EMPTY;

				}
			}
		}
	}

}
