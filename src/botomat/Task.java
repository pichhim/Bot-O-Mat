package botomat;

public interface Task {
	public String getDescription();

	public int getETA();

	public Status getStatus();

	public void setStatus(final Status p0);

	public enum Status {
		INCOMPLETE, IN_PROGRESS, COMPLETED;
	}
}