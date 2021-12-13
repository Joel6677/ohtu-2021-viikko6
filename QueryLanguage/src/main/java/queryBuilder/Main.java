
package queryBuilder;

import statistics.Player;
import statistics.PlayerReaderImpl;
import statistics.Statistics;
import statistics.matcher.Matcher;

/**
 *
 * @author Jolle
 */
public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("https://nhlstatisticsforohtu.herokuapp.com/players.txt"));

        QueryBuilder query = new QueryBuilder();
        Matcher m1 = query.playsIn("PHI")
                .hasAtLeast(10, "assists")
                .hasFewerThan(5, "goals").build();

        Matcher m2 = query.playsIn("EDM")
                .hasAtLeast(40, "points").build();

        Matcher m = query.oneOf(m1, m2).build();

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}