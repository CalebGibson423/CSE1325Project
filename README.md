## The Local Loop

A community based app where people can view community events in their area. Users can add, view, sort, and even delete events.

## PlantUML Class Diagram

![TheLocalLoopClassDiagram](https://github.com/CalebGibson423/CSE1325Project/blob/main/UMLClassDiagram.png)

## JavaDoc Documentation

[Javadoc Index for theLocalLoop](https://calebgibson423.github.io/CSE1325Project/)

### To Run Javadoc, paste command in terminal:
   ```bash
   javadoc -d docs -sourcepath src theLocalLoop
   ```

## Folder Structure
- `docs`: Javadoc files
- `src`: java files
- `bin`: compiled files
- `lib`: the folder to maintain dependencies
- `events.txt`: events file for storing events (automatically generatred)

## Files 
- `Constants.java`: DateTime formatting and validTypes / validFormat for data validation.
- `Event.java`: Custom Event class with a constructor, getters and setters for each attribute, and toString methods.
- `EventManager.java`: Manages event operations, main logic of program.
- `FileManager.java`: Loads and Saves data to/from events.txt.
- `InputValidator.java`: Handles user input validation throughout the program.
- `Main.java`: Main program loop.
- `MenuManager.java`: Method for printing menus and handling main menu input.
- `Sort.java`: Sorting and Filtering method with a method for handling user selections.
- `TestData.java`: Predefined events for testing.
- `events.txt`: Where Events are stored and loaded from at the start/end of the program.

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
