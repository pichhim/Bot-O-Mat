package botomat;

public class BipedalBot extends BotImpl {
	public BipedalBot(String name) {
		super(name);
		this._capabilities.add("do the dishes");
		this._capabilities.add("give the dog a bath");
		this._capabilities.add("bake some cookies");
		this._capabilities.add("sweep the house");
		this._capabilities.add("wash the car");
		this._capabilities.add("make a sammich");
		this._capabilities.add("mow the lawn");
	}
}