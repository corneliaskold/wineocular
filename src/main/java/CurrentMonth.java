import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

import static java.time.Month.*;

public class CurrentMonth {

    HashMap<Month, String> englishToSwedish = new HashMap<>();

    public CurrentMonth() {
        addToHashMap();
    }

    // Stores english-swedish translations in the hashMap
    public void addToHashMap() {
        englishToSwedish.put(JANUARY, "januari");
        englishToSwedish.put(FEBRUARY, "februari");
        englishToSwedish.put(MARCH, "mars");
        englishToSwedish.put(APRIL, "april");
        englishToSwedish.put(MAY, "maj");
        englishToSwedish.put(JUNE, "juni");
        englishToSwedish.put(JULY, "juli");
        englishToSwedish.put(AUGUST, "augusti");
        englishToSwedish.put(SEPTEMBER, "september");
        englishToSwedish.put(OCTOBER, "oktober");
        englishToSwedish.put(NOVEMBER, "november");
        englishToSwedish.put(DECEMBER, "december");
    }

    // Returns the current month as a string in swedish
    public String getCurrentMonth() {
        Month month = LocalDate.now().getMonth();
        return englishToSwedish.get(month);
    }

}
