import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class StatsCalculator {
    private static final int [] BIN_EDGES = {1, 2, 4, 6, 8, 10, 12, 14};
    private final GameStats stats;


    public StatsCalculator(GameStats stats){
        this.stats = stats;
    }


    public ArrayList<String> updateResults(){


        ArrayList<String> results = new ArrayList<>();


        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++){
            final int lowerBound = BIN_EDGES[binIndex];
            int numGames = 0;


            if(binIndex == BIN_EDGES.length-1){
                // last bin
                // Sum all the results from lowerBound on up
                for(int numGuesses=lowerBound; numGuesses<stats.maxNumGuesses(); numGuesses++){
                    numGames += stats.numGames(numGuesses);
                }
            }
            else{
                int upperBound = BIN_EDGES[binIndex+1];
                for(int numGuesses=lowerBound; numGuesses <= upperBound; numGuesses++) {
                    numGames += stats.numGames(numGuesses);
                }
            }


            results.add(Integer.toString(numGames));
        }
        return results;
    }




}

