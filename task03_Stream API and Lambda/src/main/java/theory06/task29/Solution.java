package theory06.task29;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

/**
 * There is a LogEntry class for monitoring user activity on your site. The class has three fields:
 * <p>
 * created: Date - the date of creating log entry
 * login: String - a user login
 * url: String - a url that the user clicked
 * <p>
 * The class have getters for all fields with the corresponding names (getCreated(), getLogin(), getUrl()).
 * <p>
 * Write a collector that calculates how many times was clicked each url by users. The collector will be applied to a stream of log entries for creating a map: url -> click count.
 * <p>
 * The class LogEntry will be available during testing. You can create your own classes for local testing.
 * <p>
 * Important. You should write only the collector in any valid formats but without ; on the end.
 * It will be passed as an argument to the collect() method of a stream as shown below.
 * <p>
 * List<LogEntry> logs = ...
 * Map<String, Long> clickCount =
 * logs.stream()
 * .collect(...your_collector_will_be_passed_here...);
 * <p>
 * Examples of the valid solution formats: Collectors.reducing(...) or reducing(...).
 */

public class Solution {
    private static List<LogEntry> logs;

    static {
        LogEntry log1 = new LogEntry(new Date(), "login@login", "https://loginUrl.com");
        LogEntry log2 = new LogEntry(new Date(), "login@login", "https://someUrl.com");
        LogEntry log3 = new LogEntry(new Date(), "login@login", "https://someUrl.com");
        LogEntry log4 = new LogEntry(new Date(), "login@login", "https://anotherUrl.com");

        logs = Arrays.asList(log1, log2, log3, log4);
    }

    public static void main(String[] args) {
        Map<String, Long> clickCount = logs.stream()
                .collect(Collectors.groupingBy(LogEntry::getUrl, counting()));

        clickCount.forEach((k,v)-> System.out.println(k + " : " + v));
    }
}
