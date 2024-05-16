// package managers;

// import java.util.ArrayList;
// import java.util.Random;

// import entity.Plants.Plant;
// import entity.Plants.PlantFactory;
// import entity.Zombies.Zombie;
// import scenes.PlantsList;
// import scenes.Playing;

// public class PlantsManager {
//     private Playing playing;
// 	private ArrayList<Plant> plants = new ArrayList<>();

// 	public PlantsManager(Playing playing) {
// 		this.playing = playing;
// 	}

// 	public void update() {
// 		// plant shoots or action
// 	}

// 	public void addPlant(int x, int y) {
// 		if (checkPlants()) {
//         plantCreated = PlantFactory.CreatePlant(topBar.getPlantCardsButton(5).getName(), xArrow, yArrow);
//         if (checkPool(plantCreated)) 
//             PlantsList.add(plantCreated);

// 	}

// 	public void draw(Graphics g) {
// 		for (Zombie z : zombies)
// 			drawZombie(z, g);
// 	}

// 	private void drawZombie(Zombie z, Graphics g) {
// 		g.drawImage(z.getImage(), (int) z.getX(), (int) z.getY(), null);
// 	}
// }
