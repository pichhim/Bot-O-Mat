package botomat;

public class ArachnidBot extends BotImpl {
	public ArachnidBot(String name) {
		super(name);
		this._capabilities.add("do the dishes");
		this._capabilities.add("sweep the house");
		this._capabilities.add("take out the recycling");
		this._capabilities.add("rake the leaves");
		this._capabilities.add("make a sammich");
		this._capabilities.add("wash the car");
	}
}