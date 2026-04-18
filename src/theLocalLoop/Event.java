package theLocalLoop;
import java.time.*;
import java.util.*;

//Valid Types and Formats
import theLocalLoop.Constants.ValidType;
import theLocalLoop.Constants.ValidFormat;

//DateTime Formatters
import static theLocalLoop.Constants.DateTimeFormatters.dateFormatter;
import static theLocalLoop.Constants.DateTimeFormatters.timeFormatter;

/**
 * Event class representing a single event in the application. <br>
 * Contains attributes such as name, date, time, duration, type(s), format, organizer(s), password, and location. <br>
 * Constructor initializes all attributes, with getters and setters for each. <br>
 * Includes a toString method for displaying event details and a toSummaryString method for displaying a brief summary of the event. <br>
 */
public class Event 
{
    private String name;
    private LocalDate date;
    private LocalTime time;
    private double duration;
    private ArrayList<ValidType> types;         //Art, Business, Fitness, Social, Community, Social Work, Service, Military, Holiday, Personal
    private ValidFormat format;                 //In-person, Virtual, Hybrid
    private String organizer;
    private String password;
    private String location; 
    
    /**
     * Constructor for Event class, initializes all attributes.
     * @param name
     * String representing the name of the event.
     * @param date
     * LocalDate object representing the date of the event.
     * @param time
     * LocalTime object representing the time of the event.
     * @param duration
     * double representing the duration of the event in hours.
     * @param type
     * ArrayList of ValidTypes representing the type(s) of the event ("Social, Educational, Coding, ect...").
     * @param format
     * ValidFormat representing the format of the event ("In-person", "Virtual", or "Hybrid").
     * @param organizer
     * String representing the organizer(s) of the event.
     * @param password
     * String representing the password for editing the event (If applicable).
     * @param location
     * String representing the location of the event (If applicable).
     */
    public Event(String name, LocalDate date, LocalTime time, double duration, ArrayList<ValidType> types, ValidFormat format, String organizer, String password, String location)
    {
        this.name = name;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.types = types;
        this.format = format;   
        this.organizer = organizer;
        this.password = password;
        this.location = location;
    }  
    
    //--- getters ---

    /**
     * Getter for the name of the event.
     * @return
     * String representing the name of the event.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Getter for the date of the event.
     * @return
     * LocalDate object representing the date of the event.
     */
    public LocalDate getDate()
    {
        return date;
    }

    /**
     * Getter for the time of the event.
     * @return
     * LocalTime object representing the time of the event.
     */
    public LocalTime getTime()
    {
        return time;
    }

    /**
     * Getter for the duration of the event.
     * @return
     * double representing the duration of the event, in hours.
     */
    public double getDuration()
    {
        return duration;
    }
    
    /**
     * Getter for the type(s) of the event.
     * @return
     * ArrayList of ValidTypes representing the type(s) of the event 
     */
    public ArrayList<ValidType> getTypes()
    {
        return types;
    }

    /**
     * Getter for the format of the event.
     * @return
     * ValidFormat representing the format of the event 
     */
    public ValidFormat getFormat()
    {
        return format;
    }

    /**
     * Getter for the organizer(s) of the event.
     * @return
     * String representing the organizer(s) of the event.
     */
    public String getOrganizer()
    {
        return organizer;
    }

    /**
     * Getter for the password for editing the event.
     * @return
     * String representing the password for editing the event (If applicable).
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Getter for the location of the event.
     * @return
     * String representing the location of the event.
     */
    public String getLocation()
    {
        return location;
    }
    
    //--- setters ---

    /**
     * Setter for the name of the event.
     * @param newName
     * String representing the new name of the event.
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * Setter for the date of the event.
     * @param newDate
     * LocalDate object representing the new date of the event.
     */
    public void setDate(LocalDate newDate)
    {
        date = newDate;
    }

    /**
     * Setter for the time of the event.
     * @param newTime
     * LocalTime object representing the new time of the event.
     */
    public void setTime(LocalTime newTime)
    {
        time = newTime;
    }

    /**
     * Setter for the duration of the event.
     * @param newDuration
     * double representing the new duration of the event, in hours.
     */
    public void setDuration(double newDuration)
    {
        duration = newDuration;
    }  

    /**
     * Setter for the type(s) of the event.
     * @param newTypes
     * ArrayList of ValidTypes representing the new type(s) of the event 
     */
    public void setTypes(ArrayList<ValidType> newTypes)
    {
        types = newTypes;
    }

    /**
     * Setter for the format of the event.
     * @param newFormat
     * ValidFormat representing the new format of the event 
     */
    public void setFormat(ValidFormat newFormat)
    {
        format = newFormat;
    }  

    /**
     * Setter for the organizer(s) of the event.
     * @param newOrganizer
     * String representing the new organizer(s) of the event.
     */
    public void setOrganizer(String newOrganizer)
    {
        organizer = newOrganizer;
    }

    /**
     * Setter for the password for editing the event.
     * @param newPassword
     * String representing the new password for editing the event.
     */
    public void setPassword(String newPassword)
    {
        password = newPassword;
    }

    /**
     * Setter for the location of the event.
     * @param newLocation
     * String representing the new location of the event.
     */
    public void setLocation(String newLocation)
    {
        location = newLocation;
    }

    @Override
    /**
     * Returns a string representation of the event.
     * @return
     * String representing the event.
     */
    public String toString()
    {
        String eventTypes = "";

        //check if types is empty
        if (types != null && !types.isEmpty()) {
            for(int i = 0; i < types.size(); i++) {
                eventTypes += types.get(i).getDisplayName();
                if (i < types.size() - 1)
                {
                    eventTypes += ", ";
                }
            }
        } else {
            eventTypes = "None";
        }

        String formatName = "";

        if(format != null){
            formatName = format.getDisplayName();
        } else {
            formatName = "none";
        }

        String eventInfo = (
            "Name: " + name + "\n" + 
            "Date: " + date.format(dateFormatter) + "\n" +
            "Time: " + time.format(timeFormatter) + "\n" +
            "Duration: " + duration + " hours\n" +
            "Type(s): " + eventTypes + "\n" +
            "Format: " + formatName + "\n" +
            "Organizer(s): " + organizer + "\n" +
            "Location: " + location + "\n");
        return eventInfo;
    }

    /**
     * Returns a summary string representation of the event.
     * @return
     * String representing a summary of the event, including only the name, date, time, duration, format, organizer(s), and location.
     */
    public String toSummaryString(){
        String eventSummary = (
            name + " on " + date.format(dateFormatter) + " at " + time.format(timeFormatter) + " for " + duration + " hours\n" +
            "Format: " + format.getDisplayName() + "\n" +
            "Organizer(s): " + organizer + "\n" +
            "Location: " + location + "\n");
        return eventSummary;
    }
}
