package botomat;

public class BotCreatorImpl implements BotCreator {
	private BotImpl _bot;

	public BotCreatorImpl(final String type, final String name) {
		this._bot = this.createBot(type.toUpperCase(), name);
	}

	private BotImpl createBot(String type, String name) {
		switch (type) {
		case "UNIPEDAL": {
			return new UnipedalBot(name);
		}
		case "AERONAUTICAL": {
			return new AeronauticalBot(name);
		}
		case "ARACHNID": {
			return new ArachnidBot(name);
		}
		case "BIPEDAL": {
			return new BipedalBot(name);
		}
		case "QUADRUPEDAL": {
			return new QuadrupedalBot(name);
		}
		case "RADIAL": {
			return new RadialBot(name);
		}
		default: {
			throw new IllegalArgumentException("The robot type does not exist");
		}
		}
	}

	@Override
	public BotImpl getBot() {
		return this._bot;
	}
}