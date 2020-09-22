package botomat;

public class RadialBot extends BotImpl {
	public RadialBot(final String s) {
		super(s);
		this._capabilities.add("do the dishes");
		this._capabilities.add("take out the recycling");
		this._capabilities.add("mow the lawn");
		this._capabilities.add("give the dog a bath");
		this._capabilities.add("rake the leaves");
	}
}