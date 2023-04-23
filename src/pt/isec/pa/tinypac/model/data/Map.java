package pt.isec.pa.tinypac.model.data;


import pt.isec.pa.tinypac.model.data.Ghosts.Ghost;
import pt.isec.pa.utils.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Map class is responsible for
 * storing information about the walls, obstacles, and paths of the maze.
 */
public class Map {

    public record Position(int y, int x) {
    }

    int height, width;
    Maze maze;
    private Direction currentPacmanDirection;

    ArrayList<Ghost> ghosts = new ArrayList<Ghost>(4);
    private Pacman pacman;
    ArrayList<Wrap> wraps = new ArrayList<Wrap>(2);

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new Maze(height, width);
    }

    public void addElement(Organism organism, int y, int x) {
        maze.set(y, x, organism);
    }

    public void set(Organism organism, int y, int x) {
        maze.set(y, x, organism);
    }

    public MazeElement getLeftCell(Organism org) {
        Position p = getPositionOf(org);
        return (MazeElement) maze.get(p.y, p.x - 1);
    }

    public Organism getOrganism(int y, int x) {
        IMazeElement element = maze.get(y, x);
        if (element instanceof Organism organism)
            return organism;
        return null;
    }

    public <T extends Organism> List<T> findElementsOf(Class<T> type) {
        List<T> result = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Organism o = (Organism) maze.get(y, x);
                if (type.isInstance(o)) {
                    result.add(type.cast(o));
                }
            }
        }

        return result;
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    public Position getPositionOf(Organism organism) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                if (maze.get(y, x) == organism)
                    return new Position(y, x);
        return null;
    }

    public <T extends Organism> List<Position> getOrganismNeighbors(int y, int x, Class<T> type) {
        List<Position> lst = new ArrayList<>();
        for (int yd = -1; yd <= 1; yd++) {
            for (int xd = -1; xd <= 1; xd++) {
                if (yd != 0 || xd != 0) {
                    var organism = maze.get(y + yd, x + xd);
                    if (type.isInstance(organism)) {
                        lst.add(new Position(y + yd, x + xd));
                    }
                }
            }
        }
        return lst;
    }

    public List<Position> getAdjacentEmptyCells(int yo, int xo) {
        List<Position> lst = new ArrayList<>();
        for (int y = Math.max(0, yo - 1); y <= Math.min(height - 1, yo + 1); y++)
            for (int x = Math.max(0, xo - 1); x <= Math.min(width - 1, xo + 1); x++)
                if ((y != yo || x != xo) && maze.get(y, x) == null)
                    lst.add(new Position(y, x));
        return lst;
    }

    public void setCurrentPacmanDirection(Direction direction) {
        this.currentPacmanDirection = direction;
    }

    public boolean evolve() {
        List<Organism> lst = new ArrayList<>();
        this.pacman.setDirection(currentPacmanDirection);
        lst.add(this.pacman);

        for (var organism : lst)
            organism.evolve();
        return true;
    }

   /* public boolean onlyOneSpecies() {
        int nr_evolvers = 0,nr_virus = 0;

        for(int y = 0; y < height;y++)
            for(int x = 0;x < width; x++)
                if (maze.get(y,x) instanceof Organism organism) {
                    if (organism instanceof Evolver)
                        nr_evolvers++;
                    else if (organism instanceof Virus)
                        nr_virus++;
                }
        return (nr_evolvers==0 || nr_virus == 0);
    }*/

    public char[][] buildMap() {
        char[][] staticMaze = maze.getMaze();
        char[][] char_board = new char[staticMaze.length][staticMaze[0].length];

        for (int y = 0; y < staticMaze.length; y++) {
            for (int x = 0; x < staticMaze[y].length; x++) {
                if (staticMaze[y][x] == 'M') {
                    char_board[y][x] = ' ';
                } else {
                    char_board[y][x] = staticMaze[y][x];
                }
                //System.out.print(char_board[y][x]);
            }
            // System.out.println();
        }

        Map.Position p = pacman.getP();
        char_board[p.y()][p.x()] = 'M';

        return char_board;
    }

}
