package theLocalLoop;
import java.time.format.DateTimeFormatter;

/**
 * Constants class for storing application-wide constants such as date and time formatters, valid tags, and valid formats.
 */
public class Constants {


    /**
     * Private constructor to prevent instantiation.
     */
    private Constants() {
        // Prevent instantiation
    }

    /**
     * Public class that stores valid DateTimeFormatters
     */
    public static class DateTimeFormatters{
        
         /**
         * Private constructor to prevent instantiation.
         */
        private DateTimeFormatters() {
            // Prevent instantiation
        }

        /**
         * Public static final DateTimeFormatter for formatting dates in the pattern "MM-dd-yyyy"
         */
        public static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("MM-dd-yyyy");
        
        /**
         * Public static final DateTimeFormatter for formatting times in the pattern "HH:mm"
         */
        public static final DateTimeFormatter TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("HH:mm");  
        
        /**
         * Public static final DateTimeFormatter for formatting months and years in the pattern "MM-yyyy"
         */
        public static final DateTimeFormatter MONTH_YEAR_FORMATTER = 
            DateTimeFormatter.ofPattern("MM-yyyy");
    }

    /**
     * Public enum that stores valid Types, includes getDisplayName, and valid type methods.
     */
    public enum ValidType {
        /**Art related events (eg: Art Exhibition) */
        ART("Art"),
        /**Business related events (eg: Business Conference) */
        BUSINESS("Business"),
        /**Fitness related events (eg: Yoga Class) */
        CODING("Coding"),
        /**Culture related events (eg: Museum Visit) */
        CULTURE("Culture"),
        /**Community related events (eg: Town Hall Meeting) */
        COMMUNITY("Community"),
        /**Discussion related events (eg: Book Club) */
        DISCUSSION("Discussion"),
        /**Education related events (eg: Workshop) */
        EDUCATION("Education"),
        /**E-Sports related events (eg: Tournament) */
        ESPORTS("E-Sports"),
        /**Festival related events (eg: Music Festival) */
        FESTIVAL("Festival"),
        /**Fitness related events (eg: Fitness Class) */
        FITNESS("Fitness"),
        /**Food related events (eg: Cooking Class) */
        FOOD("Food"),
        /**Gaming related events (eg: Video Game Tournament) */
        GAMING("Gaming"),
        /**Hackathon events (eg: Coding Competition) */
        HACKATHON("Hackathon"),
        /**Music related events (eg: Concert) */
        MUSIC("Music"),
        /**Networking related events (eg: Casual Mixers ) */
        NETWORKING("Networking"),
        /**Photography related events (eg: Photo Walk) */
        PHOTOGRAPHY("Photography"),
        /**Reading related events (eg: Literary Discussion) */
        READING("Reading"),
        /**Social related events (eg: Happy Hour) */
        SOCIAL("Social"),
        /**Startup related events (eg: Pitch Competition) */
        STARTUP("Startup"),
        /**Technology related events (eg: Tech Talk) */
        TECHNOLOGY("Technology"),
        /**Volunteering related events (eg: Community Service) */
        VOLUNTEERING("Volunteering"),
        /**Welfare related events (eg: Support Group) */
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
        /**In person event */
        IN_PERSON("In person"),
        /**Online event */
        ONLINE("Online"),
        /**Event that is both in person and online */
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
