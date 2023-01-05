import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Parsing {

    public static void main(String[] args) {

        final String url = "https://www.nba.com/stats/players/traditional";

        try {
            final Document document = Jsoup.connect(url).get();

            table = document.select("")

            //for (Element row : document.select("table.Crom_table__p1iZz tr")) {
            //    final String playerName = row.select("td:nth-of-type(4)").text();
            //    System.out.println(playerName);
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
