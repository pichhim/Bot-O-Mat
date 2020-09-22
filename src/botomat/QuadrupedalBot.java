package botomat;

public class QuadrupedalBot extends BotImpl {
	public QuadrupedalBot(final String s) {
		super(s);
		this._capabilities.add("do the dishes");
		this._capabilities.add("sweep the house");
		this._capabilities.add("mow the lawn");
		this._capabilities.add("bake some cookies");
		this._capabilities.add("rake the leaves");
		this._capabilities.add("wash the car");
	}
}