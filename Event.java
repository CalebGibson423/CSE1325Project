package cse1325project;
import java.time.*;
import java.util.*;
import static cse1325project.Constants.*;

public class Event 
{
    private String name;
    private LocalDate date;
    private LocalTime time;
    private double duration;
    private ArrayList<String> type;         //Corporate & Business, Social & Personal, Community & Cultural, Educational & Academic, Sports & Recreational
    private String format;                  //In-person, Virtual, Hybrid
    private String organizer;
    private String password;
    private String location; 
    
    public Event(String name, LocalDate date, LocalTime time, double duration, ArrayList<String> type, String format, String organizer, String password, String location)
    {
        this.name = name;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.type = type;
        this.format = format;   
        this.organizer = organizer;
        this.password = password;
        this.location = location;
    }  
    
    //getters
    public String getName()
    {
        return name;
    }
    
    public LocalDate getDate()
    {
        return date;
    }

    public LocalTime getTime()
    {
        return time;
    }

    public double getDuration()
    {
        return duration;
    }
    
    public ArrayList<String> getTypes()
    {
        return type;
    }

    public String getFormat()
    {
        return format;
    }

    public String getOrganizer()
    {
        return organizer;
    }

    public String getPassword()
    {
        return password;
    }

    public String getLocation()
    {
        return location;
    }
    
    //setters
    public void setName(String newName)
    {
        name = newName;
    }
    
    public void setDate(LocalDate newDate)
    {
        date = newDate;
    }

    public void setMonth(LocalTime newTime)
    {
        time = newTime;
    }

    public void setDuration(double newDuration)
    {
        duration = newDuration;
    }  

    public void setTypes(ArrayList<String> newTypes)
    {
        type = newTypes;
    }

    public void setFormat(String newFormat)
    {
        format = newFormat;
    }  

    public void setHost(String newOrganizer)
    {
        organizer = newOrganizer;
    }

    public void setPassword(String newPassword)
    {
        password = newPassword;
    }

    public void setLocation(String newLocation)
    {
        location = newLocation;
    }

    public String toString()
    {
        String eventTypes = "";
        for(int i = 0; i < type.size(); i++)
        {
            eventTypes += type.get(i);
            if (i < type.size() - 1)
            {
                eventTypes += ", ";
            }
        }

        String eventInfo = (
            "Name: " + name + "\n" + 
            "Date: " + date.format(DATE_FORMATTER) + "\n" +
            "Time: " + time.format(TIME_FORMATTER) + "\n" +
            "Duration: " + duration + " hours\n" +
            "Type(s): " + eventTypes + "\n" +
            "Format: " + format + "\n" +
            "Organizer(s): " + "\n" +
            "Location: " + location + "\n");
        return eventInfo;
    }
}

