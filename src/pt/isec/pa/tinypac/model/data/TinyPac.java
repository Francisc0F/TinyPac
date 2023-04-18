package pt.isec.pa.tinypac.model.data;

import pt.isec.pa.tinypac.model.data.Ghosts.Ghost;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TinyPac {
    private int level;
    private List<Ghost> ghosts;
    Maze maze;
    public TinyPac()  {
        level = 1;
        ghosts = new ArrayList<>();

        try{
            loadMap();
        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    private void loadMap() throws IOException {
        File file = new File(
                "level01.txt");

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        int y = 0;
        while ((st = br.readLine()) != null){
            for (int x = 0; x < st.length(); x++) {
                this.maze.set(y, x, );
            }
        }

    }



    public int getCurrentlevel() {
        return 0;
    }
}
