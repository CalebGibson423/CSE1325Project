package theLocalLoop;
import java.time.format.DateTimeFormatter;

/**
 * Constants class for storing application-wide constants such as date and time formatters, valid tags, and valid formats.
 */
public class Constants {

    //Date and time formatters
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MM-yyyy");

    //valid types and formats
    public static final String[] validTypes = {"Art", "Business", "Fitness", "Social", "Community", "Social Work", "Service", "Military", "Holiday", "Personal"};
    public static final String[] validFormats = {"In person", "Online", "Hybrid"};

}
