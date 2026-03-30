## The Local Loop

A community based app where people can view community events in their area. Users can add, view, sort, and even delete events.

## Folder Structure
- `src`: java files
- `bin`: compiled files
- `lib`: the folder to maintain dependencies
- `events.txt`: events file for storing events (automatically generatred)

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
