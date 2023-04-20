package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.Ghosts.Ghost;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TinyPac {
    private int level;
    private List<Ghost> ghosts;
    Maze maze;

    public TinyPac() {
        level = 1;
        ghosts = new ArrayList<>();
        maze = new Maze(31, 29);
      /*  try{
            loadMap("level01.txt");
        }catch (IOException ex){
            System.out.println(ex);
        }*/
    }

    public void loadMap(String filename) throws IOException {
        File file = new File(filename);

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        int y = 0;
        while ((st = br.readLine()) != null) {
            for (int x = 0; x < st.length(); x++) {
                MazeElement ele = new MazeElement(st.charAt(x));
                this.maze.set(y, x, ele);
            }
            y++;
        }
    }

    public MazeElement getMazeItem(int x, int y) {
        return (MazeElement) maze.get(y, x);
    }

    public char[][] getMaze() {
        return maze.getMaze();
    }

    public int getCurrentlevel() {
        return 0;
    }
}
