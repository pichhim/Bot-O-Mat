package botomat;

import java.util.List;

public interface Bot {
	public void completeTask(List<Task> tasks) throws InterruptedException;

	public String getName();

	public List<Task> getCompletedTasks();

	public void addCapabilities(Task capable);
}