import java.time.*;
import java.time.format.*;
public class Event 
{
    private String name;
    private int day;
    private int month;
    private int year;
    private double duration;
    private ArrayList<String> type;
    //Corporate & Business, Social & Personal, Community & Cultural, Educational & Academic, Sports & Recreational
    private String format;
    //In-person, Virtual, Hybrid
    
    public Event(String name, int day, int month, int year, double duration, ArrayList<String> type, String format)
    {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.duration = duration;
        this.type = type;
        this.format = format;   
    }  
    
    //getters
    public String getName()
    {
        return name;
    }
    
    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }
    
    public int getYear()
    {
        return year;    
    }

    public double getDuration()
    {
        return duration;
    }
    
    public ArrayList<String> getTypes()
    {
        return types;
    }

    public String getFormat()
    {
        return format;
    }
    
    //setters
    public void setName(String newName)
    {
        name = newName;
    }
    
    public void setDay(int newDay)
    {
        day = newDay;
    }

    public void setMonth(int newMonth)
    {
        month = newMonth;
    }
    
    public void setYear(int newYear)
    {
        year = newYear;  
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
}

