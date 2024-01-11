public class Mammal {
    
    private String name;
    private String sound;

    public Mammal(String name, String sound) {

        this.name = name;
	this.sound = sound;
	
    }

    public String getName() {
	return this.name;
    }

    public String getSound() {
	return this.sound;
    }

    public void speak() {
	System.out.print(getSound());
    }

    public boolean laysEggs() {
	return true;
    }

    public boolean livesInWater() {
	return true;
    }
}
