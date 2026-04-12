package theLocalLoop;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class TestData {

    public static void loadSampleEvents(LinkedList<Event> events){
        
    events.add(new Event(
        "Tech Meetup",
        LocalDate.of(2026, 4, 12),
        LocalTime.of(18, 0),
        2.0,
        new ArrayList<>(Arrays.asList("coding", "networking")),
        "Virtual",
        "DFW Tech Group",
        "pass123",
        "Zoom"
    ));

    events.add(new Event(
        "Morning Yoga Flow",
        LocalDate.of(2026, 4, 13),
        LocalTime.of(7, 30),
        1.5,
        new ArrayList<>(Arrays.asList("fitness", "wellness")),
        "In person",
        "Grand Prairie Parks Dept",
        "",
        "Central Park"
    ));

    events.add(new Event(
        "Startup Pitch Night",
        LocalDate.of(2026, 4, 14),
        LocalTime.of(19, 0),
        3.0,
        new ArrayList<>(Arrays.asList("business", "startup")),
        "In person",
        "DFW Innovators",
        "pitchit",
        "Dallas Hub"
    ));

    events.add(new Event(
        "AI Workshop",
        LocalDate.of(2026, 4, 15),
        LocalTime.of(16, 0),
        2.5,
        new ArrayList<>(Arrays.asList("technology", "education")),
        "Virtual",
        "AI Learning Group",
        "ai2026",
        "Google Meet"
    ));

    events.add(new Event(
        "Community Cleanup Day",
        LocalDate.of(2026, 4, 16),
        LocalTime.of(9, 0),
        4.0,
        new ArrayList<>(Arrays.asList("community", "volunteering")),
        "In person",
        "City Volunteers",
        "",
        "Main Street Park"
    ));

    events.add(new Event(
        "Gaming Tournament",
        LocalDate.of(2026, 4, 17),
        LocalTime.of(17, 0),
        5.0,
        new ArrayList<>(Arrays.asList("gaming", "esports")),
        "Virtual",
        "Texas Gamers",
        "ggwp",
        "Discord"
    ));

    events.add(new Event(
        "Book Club Meeting",
        LocalDate.of(2026, 4, 18),
        LocalTime.of(15, 0),
        1.5,
        new ArrayList<>(Arrays.asList("reading", "discussion")),
        "Virtual",
        "Library Readers Group",
        "books",
        "Zoom"
    ));

    events.add(new Event(
        "Food Truck Festival",
        LocalDate.of(2026, 4, 19),
        LocalTime.of(12, 0),
        6.0,
        new ArrayList<>(Arrays.asList("food", "festival")),
        "In person",
        "City Events",
        "yum2026",
        "Downtown Grand Prairie"
    ));

    events.add(new Event(
        "Hackathon Kickoff",
        LocalDate.of(2026, 4, 20),
        LocalTime.of(9, 0),
        8.0,
        new ArrayList<>(Arrays.asList("coding", "hackathon")),
        "In person",
        "DFW Hackers",
        "hack2026",
        "Innovation Lab"
    ));

    events.add(new Event(
        "Photography Workshop",
        LocalDate.of(2026, 4, 21),
        LocalTime.of(14, 0),
        2.0,
        new ArrayList<>(Arrays.asList("art", "photography")),
        "In person",
        "Creative Lens Group",
        "photo",
        "Arts Center"
    ));
}
}
