package botomat;

import botomat.Bot;
import botomat.BotCreatorImpl;
import botomat.BotImpl;
import botomat.Task;
import botomat.TaskImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RobotApp {
	public static void main(String[] arrstring) throws InterruptedException {
		List<Bot> bots = new ArrayList<Bot>();
		List<Task> chores = RobotApp.initList();
		System.out.println("Hi! Welcome to the Bot-O-Mat.");
		System.out.println("Make yourself a Robot to do some of your pesky chores.");
		System.out.println("Here are a list of existing chores and their ETA:");
		for (Task task : chores) {
			System.out.println("     - " + task.getDescription() + ". eta: " + task.getETA() + " ms.");
		}
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		RobotApp.initialPrompt(scanner, bots, chores);
		while (!chores.isEmpty()) {
			System.out.println();
			RobotApp.secondaryPrompt(scanner, bots, chores);
		}
		System.out.println();
		System.out.println("Awesomesauce! You have completed all of your chores. Now give us our Bots back.");
	}

	// Initializes original list of tasks
	private static List<Task> initList() {
		List<Task> initTasks = new ArrayList<Task>();
		initTasks.add(new TaskImpl("do the dishes", 1000));
		initTasks.add(new TaskImpl("sweep the house", 3000));
		initTasks.add(new TaskImpl("do the laundry", 10000));
		initTasks.add(new TaskImpl("take out the recycling", 4000));
		initTasks.add(new TaskImpl("make a sammich", 7000));
		initTasks.add(new TaskImpl("mow the lawn", 20000));
		initTasks.add(new TaskImpl("rake the leaves", 18000));
		initTasks.add(new TaskImpl("give the dog a bath", 14500));
		initTasks.add(new TaskImpl("bake some cookies", 8000));
		initTasks.add(new TaskImpl("wash the car", 20000));
		return initTasks;
	}

	// Initial prompt asking to create first bot
	private static void initialPrompt(Scanner scanner, List<Bot> list, List<Task> list2) throws InterruptedException {
		System.out.println(
				"What type of robot would you like? (Options: Unipedal, Bipedal, Quadrupedal, Arachnid, Radial, Aeronautical)");
		String type = scanner.nextLine().toUpperCase();

		// Prompt type input until valid input is received
		while (!RobotApp.validateInput("types", type)) {
			System.out.println();
			System.out.println(
					"Sorry, we don't have that type of Robot, but will relay it to our customer feedback team!");
			System.out.println(
					"Please enter in one of these types: Unipedal, Bipedal, Quadrupedal, Arachnid, Radial, Aeronautical.");
			type = scanner.nextLine();
		}
		System.out.println("What would you like to name your " + type + " robot?");
		String name = scanner.nextLine();
		System.out.println();
		System.out.println("Spicy! You now have a " + type + " robot named " + name + ".");
		System.out.println();

		// Create new bot and delegate task
		BotCreatorImpl botCreatorImpl = new BotCreatorImpl(type, name);
		list.add(botCreatorImpl.getBot());
		botCreatorImpl.getBot().completeTask(list2);
	}

	// Prompts user to ask what they would like
	private static void secondaryPrompt(Scanner scanner, List<Bot> bots, List<Task> chores)
			throws InterruptedException {
		System.out.println(
				"You still have chores remaining. Please input a number for one of the commands listed below:");
		System.out.println("     - 0: Print out the remaining chores.");
		System.out.println("     - 1: Print a leaderboard for chores completed by my robots.");
		System.out.println("     - 2: Add a new task to the chores list.");
		System.out.println("     - 3: Make a new or existing bot do more chores.");
		String command = scanner.nextLine();
		while (!RobotApp.validateInput("commands", command)) {
			System.out.println();
			System.out.println("Sorry, that command is invalid. Please try again.");
			System.out.println("Please enter in one of these numbers:");
			System.out.println("     - 0: Print out the remaining chores.");
			System.out.println("     - 1: Print a score board for chores completed by my robots.");
			System.out.println("     - 2: Add a new task to the chores list.");
			System.out.println("     - 3: Make a new or existing bot do more chores.");
			command = scanner.nextLine();
		}
		System.out.println();
		RobotApp.secondaryPromptHandler(scanner, bots, chores, command);
	}

	// Completes additional actions based off of command input from secondaryPrompt
	private static void secondaryPromptHandler(Scanner scanner, List<Bot> bots, List<Task> chores, String command)
			throws InterruptedException {

		// Print remaining chores
		if (command.equals("0")) {
			System.out.println("Here are a list of existing chores and their ETA:");
			for (Task task : chores) {
				System.out.println("     - " + task.getDescription() + ". eta: " + task.getETA() + " ms.");
			}
		}

		// Print score board of chores done per bot
		else if (command.equals("1")) {
			System.out.println("Here is the chores score board:");
			for (Bot bot : bots) {
				System.out.println();
				System.out.println(bot.getClass().getSimpleName() + " " + bot.getName() + " has completed "
						+ bot.getCompletedTasks().size() + " chores!");
				System.out.print(bot.getClass().getSimpleName() + " " + bot.getName() + " has completed the tasks: ");
				for (int i = 0; i < bot.getCompletedTasks().size(); ++i) {
					if (i == bot.getCompletedTasks().size() - 1) {
						System.out.print(bot.getCompletedTasks().get(i).getDescription());
						continue;
					}
					System.out.print(bot.getCompletedTasks().get(i).getDescription() + ", ");
				}
			}
		}

		// Add new chore given a description and eta input from Scanner
		else if (command.equals("2")) {
			System.out.println("What is the description of the chore would you like to add?");
			String description = scanner.nextLine().toLowerCase();
			System.out.println("What is the ETA of the chore would you like to add?");
			int eta = scanner.nextInt();
			if (eta > 20000) {
				System.out.println(
						"That ETA is too long for our bots. They are efficient and will complete any task quicker than 20001ms.");
				eta = 20000;
			}
			Task newTask = new TaskImpl(description, eta);
			chores.add(newTask);
			System.out.println("Checking to see which bots are capable of the task: " + description);
			List<Bot> arrayList = new ArrayList<Bot>();
			for (int i = 0; i < bots.size() && i < 3; ++i) {
				Bot bot = bots.get((int) (Math.random() * (double) (bots.size() - 0) + 0.0));
				while (arrayList.contains(bot)) {
					bot = bots.get((int) (Math.random() * (double) (bots.size() - 0) + 0.0));
				}
				bot.addCapabilities(newTask);
				System.out.println(bot.getClass().getSimpleName() + " " + bot.getName() + " is capable of this task!");
			}
		}

		// Selects a bot to do the next round of chores
		// If bot with the input name and type does not exist, create a new bot
		else if (command.equals("3")) {
			// Take in and validate inputs
			System.out.println(
					"What is the type of the robot you want to do chores? (Options: Unipedal, Bipedal, Quadrupedal, Arachnid, Radial, Aeronautical)");
			String type = scanner.nextLine().toUpperCase();
			while (!RobotApp.validateInput("types", type)) {
				System.out.println();
				System.out.println(
						"Sorry, we don't have that type of Robot, but will relay it to our customer feedback team!");
				System.out.println(
						"Please enter in one of these types: Unipedal, Bipedal, Quadrupedal, Arachnid, Radial, Aeronautical.");
				type = scanner.nextLine();
			}
			System.out.println("What is the name of your " + type
					+ " robot? (or, for a new robot, what would you like to name the robot?)");
			String name = scanner.nextLine();
			Bot existingBot = null;

			// Search to find if bot with input name and type exists, creates new bot if
			// needed + makes bot complete some tasks
			for (Bot bot2 : bots) {
				if (!bot2.getName().toUpperCase().equals(name.toUpperCase())
						|| !bot2.getClass().getSimpleName().toUpperCase().equals(type.toUpperCase() + "BOT"))
					continue;
				existingBot = bot2;
				break;
			}
			if (existingBot != null) {
				System.out.println(type + "BOT " + name + " has already been created!");
				System.out.println();
				existingBot.completeTask(chores);
			} else {
				System.out.println(type + "BOT " + name + " has not been created! Creating new bot...");
				System.out.println();
				BotImpl botImpl = new BotCreatorImpl(type, name).getBot();
				bots.add(botImpl);
				botImpl.completeTask(chores);
			}
		}

		// Error: Invalid command was somehow input (but should not happen!)
		else {
			throw new IllegalArgumentException("The command is invalid.");
		}
	}

	// Checks if user input is valid: checker specifies what type of input is being validated
	// Command input from secondaryPrompt is checked by param 'command'
	private static boolean validateInput(String checker, String command) {
		if (checker.equals("types")) {
			String[] botTypes = new String[] { "UNIPEDAL", "AERONAUTICAL", "ARACHNID", "BIPEDAL", "QUADRUPEDAL",
					"RADIAL" };
			for (int i = 0; i < botTypes.length; ++i) {
				if (command.equals(botTypes[i])) {
					return true;
				}
			}
			return false;
		}
		if (checker.equals("commands")) {
			return command.equals("0") || command.equals("1") || command.equals("2") || command.equals("3");
		}
		throw new IllegalArgumentException("The prompt argument is invalid");
	}
}