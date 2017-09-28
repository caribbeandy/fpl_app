import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.io.FileNotFoundException;


public class FplOptimizer {

    private static class Player {
        public String name;

        // Constraints
        public int team_code;
        public double now_cost;
        public int total_points; 
        public int position;

        public Player(int team, int cost, int points, int pos) {
            team_code = team;
            now_cost = cost;
            total_points = points;
            position = pos;
        }
    }

    public static int teamCodeIndex=-1, nowCostIndex=-1, totalPointsIndex=-1, positionIndex=-1;

    public static List<Player> optimizePoints(List<Player> players) {
        return null;
    }

    public static int optimizePoints(List<Player> players, int i) {
        return 0;
    }

    private static List<Player> parseFile(File file) {

        List<Player> res = new ArrayList<>();

        Scanner fileReader;
        StringBuilder sb = new StringBuilder();

        try {
            fileReader = new Scanner(file, "UTF-8");

            // Parse header
            String line = fileReader.next();
            String [] split = line.split(",");

            for(int i=0; i<split.length; i++) {
                String s = split[i];
                if (s.equals("team_code")) teamCodeIndex = i;
                else if (s.equals("now_cost")) nowCostIndex = i;
                else if (s.equals("total_points")) totalPointsIndex = i;
                else if (s.equals("element_type")) positionIndex = i;
            }

            if (teamCodeIndex==-1 || nowCostIndex==-1 || totalPointsIndex==-1 || positionIndex==-1){
                return res;
            }

            while(fileReader.hasNext()) {
                line = fileReader.next();
                split = line.split("\\s*,\\s*");
                for(String s: split) {
                    System.out.print(s + " ");
                }
                System.out.println();
                System.out.println();
                res.add(new Player(
                            Integer.valueOf(split[teamCodeIndex]),
                            Integer.valueOf(split[nowCostIndex]),
                            Integer.valueOf(split[totalPointsIndex]),
                            Integer.valueOf(split[positionIndex])
                            ));
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return res;
    }

    public static void main(String [] args) {
        List<Player> players = parseFile(new File("fpl_2017_2018_data_raw.csv"));
    }


}
