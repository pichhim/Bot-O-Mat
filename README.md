# Bot-O-Mat
Hi! This is a terminal-based app that allows users to generate Bots to do chores for them. Some features include adding additional chores, printing a leaderboard, creating multiple robots, and having tasks specific to each robot.

# Setup:
Option 1: Clone to Eclipse, navigate to RobotApp.java, and hit the "Start" button to compile and run the app.
Option 2: Clone to your chosen directory, navigate to the 'botomat' package in 'src', and compile with 'javac *.java'. Navigate back to 'src' and run 'java botomat.RobotApp'.

# *Brief* Design:
Bot interface is implemented by abstract class BotImpl. BotImpl has many child classes corresponding to the different robot types.
Task interface is implemented by TaskImpl class, which stores information about task description and eta.
BotCreator interface is implemented by BotCreatorImpl class, which takes in user input Strings (type, name) and composes a new bot of the given type.
RobotApp class contains the main app function, prompting user input about their desired actions.
