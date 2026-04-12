## The Local Loop

A community based app where people can view community events in their area. Users can add, view, sort, and even delete events.

## Folder Structure
- `src`: java files
- `bin`: compiled files
- `lib`: the folder to maintain dependencies
- `events.txt`: events file for storing events (automatically generatred)

## Files 
- `Constants.java`: DateTime formatting
- `Event.java`: Custom Event class with getters, setters, and toString methods
- `EventManager.java`: Manages event operations, main logic of program
- `FileManager.java`: Loads and Saves data to/from events.txt
- `Main.java`: Main program loop
- `MenuManager.java`: Method for printing menus and handling main menu input
- `Sort.java`: Sorting and Filtering method with a method for handling user selections
- `TestData.java`: Predefined events for testing
- `events.txt`: Where Events are stored and loaded from at the start/end of the program

## How to run and compile the project
You can use one of the following 3 methods:
### 1) Using the terminal
   - make sure you are in the project root directory
   - run the following commands:
   ```bash
   javac -d bin src/theLocalLoop/*.java
   ```
   ```bash
   java -cp bin theLocalLoop.Main
   ```

### 2) Using VSCode right-click
   - right click `Main.java` in src/theLocalLoop
   - click `run java`
     
### 3) Using the run button in main.java
   - open `Main.java` in src/theLocalLoop
   - just above public static void main(String[] args) find `Run | Debug` and click `Run`
