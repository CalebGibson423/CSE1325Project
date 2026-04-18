package theLocalLoop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

//Valid Types and Formats
import theLocalLoop.Constants.ValidType;
import theLocalLoop.Constants.ValidFormat;

/**
 * The TestData class provides a method to load a sample list of events into a LinkedList. 
 * Useful for testing purposes.
 */
public class TestData {

    /**
     * Loads a sample list of events into the provided LinkedList for testing purposes. 
     * @param events
     * Current list of events.
     */
    public static void loadSampleEvents(LinkedList<Event> events){
        
    events.add(new Event(
        "Tech Meetup",
        LocalDate.of(2026, 4, 12),
        LocalTime.of(18, 0),
        2.0,
        new ArrayList<ValidType>() {{
            add(ValidType.READING);
            add(ValidType.DISCUSSION);
        }},
        ValidFormat.ONLINE,
        "DFW Tech Group",
        "pass123",
        "Zoom"
    ));

    events.add(new Event(
        "Morning Yoga Flow",
        LocalDate.of(2026, 4, 13),
        LocalTime.of(7, 30),
        1.5,
        new ArrayList<ValidType>() {{
            add(ValidType.FITNESS);
            add(ValidType.WELLNESS);
        }},
        ValidFormat.IN_PERSON,
        "Grand Prairie Parks Dept",
        "",
        "Central Park"
    ));

    events.add(new Event(
        "Startup Pitch Night",
        LocalDate.of(2026, 4, 14),
        LocalTime.of(19, 0),
        3.0,
        new ArrayList<ValidType>() {{
            add(ValidType.BUSINESS);
            add(ValidType.STARTUP);
        }},
        ValidFormat.IN_PERSON,
        "DFW Innovators",
        "pitchit",
        "Dallas Hub"
    ));

    events.add(new Event(
        "AI Workshop",
        LocalDate.of(2026, 4, 15),
        LocalTime.of(16, 0),
        2.5,
        new ArrayList<ValidType>() {{
            add(ValidType.TECHNOLOGY);
            add(ValidType.EDUCATION);
        }},
        ValidFormat.ONLINE,
        "AI Learning Group",
        "ai2026",
        "Google Meet"
    ));

    events.add(new Event(
        "Community Cleanup Day",
        LocalDate.of(2026, 4, 16),
        LocalTime.of(9, 0),
        4.0,
        new ArrayList<ValidType>() {{
            add(ValidType.COMMUNITY);
            add(ValidType.VOLUNTEERING);
        }},
        ValidFormat.IN_PERSON,
        "City Volunteers",
        "",
        "Main Street Park"
    ));

    events.add(new Event(
        "Gaming Tournament",
        LocalDate.of(2026, 4, 17),
        LocalTime.of(17, 0),
        5.0,
        new ArrayList<ValidType>() {{
            add(ValidType.GAMING);
            add(ValidType.ESPORTS);
        }},
        ValidFormat.ONLINE,
        "Texas Gamers",
        "ggwp",
        "Discord"
    ));

    events.add(new Event(
        "Book Club Meeting",
        LocalDate.of(2026, 4, 18),
        LocalTime.of(15, 0),
        1.5,
        new ArrayList<ValidType>() {{
            add(ValidType.READING);
            add(ValidType.DISCUSSION);
        }},
        ValidFormat.ONLINE,
        "Library Readers Group",
        "books",
        "Zoom"
    ));

    events.add(new Event(
        "Food Truck Festival",
        LocalDate.of(2026, 4, 19),
        LocalTime.of(12, 0),
        6.0,
        new ArrayList<ValidType>() {{
            add(ValidType.FOOD);
            add(ValidType.FESTIVAL);
        }},
        ValidFormat.IN_PERSON,
        "City Events",
        "yum2026",
        "Downtown Grand Prairie"
    ));

    events.add(new Event(
        "Hackathon Kickoff",
        LocalDate.of(2026, 4, 20),
        LocalTime.of(9, 0),
        8.0,
        new ArrayList<ValidType>() {{
            add(ValidType.CODING);
            add(ValidType.HACKATHON);
        }},
        ValidFormat.IN_PERSON,
        "DFW Hackers",
        "hack2026",
        "Innovation Lab"
    ));

    events.add(new Event(
        "Photography Workshop",
        LocalDate.of(2026, 4, 21),
        LocalTime.of(14, 0),
        2.0,
        new ArrayList<ValidType>() {{
            add(ValidType.ART);
            add(ValidType.PHOTOGRAPHY);
        }},
        ValidFormat.IN_PERSON,
        "Creative Lens Group",
        "photo",
        "Arts Center"
    ));

    events.add(new Event(
        "Cybersecurity Bootcamp",
        LocalDate.of(2026, 5, 3),
        LocalTime.of(10, 0),
        3.0,
        new ArrayList<ValidType>() {{
            add(ValidType.TECHNOLOGY);
            add(ValidType.EDUCATION);
        }},
        ValidFormat.ONLINE,
        "SecureNet Academy",
        "secure2026",
        "Online"));

    events.add(new Event(
        "Downtown Jazz Night",
        LocalDate.of(2026, 6, 14),
        LocalTime.of(19, 30),
        4.0,
        new ArrayList<ValidType>() {{
            add(ValidType.MUSIC);
            add(ValidType.SOCIAL);
        }},
        ValidFormat.IN_PERSON,
        "City Arts Council",
        "",
        "Downtown Amphitheater"));

    events.add(new Event(
        "Indie Game Showcase",
        LocalDate.of(2026, 7, 8),
        LocalTime.of(13, 0),
        5.0,
        new ArrayList<ValidType>() {{
            add(ValidType.GAMING);
            add(ValidType.ESPORTS);
        }},
        ValidFormat.ONLINE,
        "GameDev Collective",
        "indie2026",
        "Discord"));

    events.add(new Event(
        "Summer Coding Camp",
        LocalDate.of(2026, 6, 22),
        LocalTime.of(9, 0),
        6.0,
        new ArrayList<ValidType>() {{
            add(ValidType.CODING);
            add(ValidType.EDUCATION);
        }},
        ValidFormat.IN_PERSON,
        "CodeForward",
        "camp123",
        "UT Dallas Lab"));

    events.add(new Event(
        "Farmers Market Meetup",
        LocalDate.of(2026, 5, 17),
        LocalTime.of(8, 0),
        3.5,
        new ArrayList<ValidType>() {{
            add(ValidType.FOOD);
            add(ValidType.COMMUNITY);
        }},
        ValidFormat.IN_PERSON,
        "Local Growers Assoc",
        "",
        "Grand Prairie Market Square"));

    events.add(new Event(
        "AI & Robotics Expo",
        LocalDate.of(2026, 8, 2),
        LocalTime.of(11, 0),
        7.0,
        new ArrayList<ValidType>() {{
            add(ValidType.TECHNOLOGY);
            add(ValidType.STARTUP);
        }},
        ValidFormat.IN_PERSON,
        "FutureTech Expo",
        "robot2026",
        "Dallas Convention Center"));

    events.add(new Event(
        "Sunset Photography Walk",
        LocalDate.of(2026, 7, 19),
        LocalTime.of(18, 30),
        2.0,
        new ArrayList<ValidType>() {{
            add(ValidType.PHOTOGRAPHY);
            add(ValidType.ART);
        }},
        ValidFormat.IN_PERSON,
        "Creative Lens Group",
        "",
        "Lake Park Trail"));

    events.add(new Event(
        "Charity Run 5K",
        LocalDate.of(2026, 5, 10),
        LocalTime.of(7, 0),
        2.5,
        new ArrayList<ValidType>() {{
            add(ValidType.FITNESS);
            add(ValidType.VOLUNTEERING);
        }},
        ValidFormat.IN_PERSON,
        "RunForHope",
        "run2026",
        "City Stadium"));
    
    events.add(new Event(
        "Book Author Q&A",
        LocalDate.of(2026, 8, 15),
        LocalTime.of(16, 0),
        1.5,
        new ArrayList<ValidType>() {{
            add(ValidType.READING);
            add(ValidType.DISCUSSION);
        }},
        ValidFormat.ONLINE,
        "Readers Hub",
        "books",
        "Zoom"));

    events.add(new Event(
        "Cultural Food Fair",
        LocalDate.of(2026, 9, 5),
        LocalTime.of(12, 0),
        6.0,
        new ArrayList<ValidType>() {{
            add(ValidType.FOOD);
            add(ValidType.FESTIVAL);
            add(ValidType.CULTURE); 
        }},
        ValidFormat.IN_PERSON,
        "Global Eats Org",
        "taste2026",
        "Fairgrounds Park"));
    
    }
}
