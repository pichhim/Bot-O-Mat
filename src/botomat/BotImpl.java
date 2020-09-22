package botomat;

import botomat.Bot;
import botomat.Task;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BotImpl implements Bot {
	private String _name;
	protected HashSet<String> _capabilities;
	protected List<Task> _completedTasks;

	protected BotImpl(String name) {
		this._name = name;
		this._capabilities = new HashSet<String>();
		this._completedTasks = new ArrayList<Task>();
	}

	@Override
	public void completeTask(List<Task> tasks) throws InterruptedException {
		List<Task> assigned = this.randomTasks(tasks);
		System.out.println("Your robot has been randomly assigned the tasks:");
		for (Task task : assigned) {
			System.out.println("     - " + task.getDescription() + ". eta: " + task.getETA() + "ms");
		}
		for (Task task : assigned) {
			System.out.println();
			System.out.println("Your bot is attempting to " + task.getDescription() + ". eta: " + task.getETA() + "ms");
			if (this._capabilities.contains(task.getDescription())) {
				task.setStatus(Task.Status.IN_PROGRESS);
				TimeUnit.MILLISECONDS.sleep(task.getETA());
				task.setStatus(Task.Status.COMPLETED);
				tasks.remove(task);
				this._completedTasks.add(task);
				System.out.println("Nice! " + this.getClass().getSimpleName() + " " + this.getName()
						+ " has completed the task: " + task.getDescription());
				continue;
			}
			System.out.println("Oh no! " + this.getClass().getSimpleName() + " " + this.getName()
					+ " is incapable of doing the task: " + task.getDescription());
		}
	}

	@Override
	public String getName() {
		return this._name;
	}

	@Override
	public List<Task> getCompletedTasks() {
		return this._completedTasks;
	}

	@Override
	public void addCapabilities(Task task) {
		this._capabilities.add(task.getDescription());
	}

	protected List<Task> randomTasks(List<Task> tasks) {
		List<Task> assigned = new ArrayList<Task>();
		for (int i = 0; i < 5 && i < tasks.size(); ++i) {
			Task task = tasks.get((int) (Math.random() * (double) (tasks.size() - 0) + 0.0));
			while (assigned.contains(task)) {
				task = tasks.get((int) (Math.random() * (double) (tasks.size() - 0) + 0.0));
			}
			assigned.add(task);
		}
		return assigned;
	}
}