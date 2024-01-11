public class Human extends Mammal {


    public Human() {
        super("Human", "I'll take a grande latte with a double-shot of espresso");
    }

    public Human(String name, String sound) {
	super(name, sound);
    }

    @Override
    public boolean laysEggs() {
	return false;
    }

    @Override
    public boolean livesInWater() {
	return false;
    }
}
