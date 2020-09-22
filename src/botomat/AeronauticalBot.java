package botomat;

public class AeronauticalBot extends BotImpl {
	public AeronauticalBot(String name) {
		super(name);
		this._capabilities.add("do the dishes");
		this._capabilities.add("sweep the house");
		this._capabilities.add("do the laundry");
		this._capabilities.add("take out the recycling");
		this._capabilities.add("mow the lawn");
		this._capabilities.add("give the dog a bath");
		this._capabilities.add("rake the leaves");
		this._capabilities.add("wash the car");
	}
}