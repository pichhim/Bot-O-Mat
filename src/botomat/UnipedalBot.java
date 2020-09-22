package botomat;

public class UnipedalBot extends BotImpl {
	public UnipedalBot(final String s) {
		super(s);
		this._capabilities.add("do the dishes");
		this._capabilities.add("take out the recycling");
		this._capabilities.add("bake some cookies");
	}
}