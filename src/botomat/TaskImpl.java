package botomat;

public class TaskImpl implements Task {
	private String _description;
	private int _eta;
	private Status _status;

	public TaskImpl(final String description, final int eta) {
		this._description = description;
		this._eta = eta;
		this._status = Status.INCOMPLETE;
	}

	@Override
	public String getDescription() {
		return this._description;
	}

	@Override
	public int getETA() {
		return this._eta;
	}

	@Override
	public Status getStatus() {
		return this._status;
	}

	@Override
	public void setStatus(final Status status) {
		this._status = status;
	}
}