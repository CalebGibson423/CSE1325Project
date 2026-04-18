package theLocalLoop;
import java.time.format.DateTimeFormatter;

/**
 * Constants class for storing application-wide constants such as date and time formatters, valid tags, and valid formats.
 */
public class Constants {

    /**
     * Public class that stores valid DateTimeFormatters
     */
    public static class DateTimeFormatters{
        
        public static final DateTimeFormatter dateFormatter = 
            DateTimeFormatter.ofPattern("MM-dd-yyyy");
        
        public static final DateTimeFormatter timeFormatter = 
            DateTimeFormatter.ofPattern("HH:mm");  
        
        public static final DateTimeFormatter monthYearFormatter = 
            DateTimeFormatter.ofPattern("MM-yyyy");
    }

    /**
     * Public enum that stores valid Types, includes getDisplayName, and valid type methods.
     */
    public enum ValidType {
        ART("Art"),
        BUSINESS("Business"),
        CODING("Coding"),
        CULTURE("Culture"),
        COMMUNITY("Community"),
        DISCUSSION("Discussion"),
        EDUCATION("Education"),
        ESPORTS("E-Sports"),
        FESTIVAL("Festival"),
        FITNESS("Fitness"),
        FOOD("Food"),
        GAMING("Gaming"),
        HACKATHON("Hackathon"),
        MUSIC("Music"),
        NETWORKING("Networking"),
        PHOTOGRAPHY("Photography"),
        READING("Reading"),
        SOCIAL("Social"),
        STARTUP("Startup"),
        TECHNOLOGY("Technology"),
        VOLUNTEERING("Volunteering"),
        WELLNESS("Wellness");
    
        private final String displayName;

        /**
         * Enum constructor for validTypes
         * @param displayName
         * String representing the displayName of a validType
         */
        ValidType(String displayName) {
            this.displayName = displayName;
        }

        /**
         * Getter for the displayName of a validType
         * @return
         * Returns a string displayName of the validType
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * Method of validating if a string is a validType by iterating through validType enum
         * @param input
         * String to compare to validTypes
         * @return
         * Returns the type if it is valid, otherwise throws an exception
         */
        public static ValidType fromString(String input) {

            for (ValidType type : ValidType.values()) {
                if (type.displayName.equalsIgnoreCase(input)) {
                    return type;
                }
            }

            throw new IllegalArgumentException("Invalid type: " + input);
        }
    }

    /**
     * Public enum for storing the validFormats of an Event, includes an enum constructor and getter for displayName
     */
    public enum ValidFormat {
        IN_PERSON("In-person"),
        ONLINE("Online"),
        HYBRID("Hybrid");

        private final String displayName;

        /**
         * Enum constructor for validFormat
         * @param displayName
         * String representing the displayName of a validFormat
         */
        ValidFormat(String displayName) {
            this.displayName = displayName;
        }

        /**
         * Getter for the displayName of a validFormat
         * @return
         * Returns a string displayName of the validFormat
         */
        public String getDisplayName() {
            return displayName;
        }
    }
}
