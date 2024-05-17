package managers;

import java.awt.Graphics;
import java.util.ArrayList;

import entity.Plants.Plant;
import entity.Plants.PlantFactory;
import scenes.Playing;
import entity.Sun;

public class PlantsManager {
    private Playing playing;
	private ArrayList<Plant> plants = new ArrayList<>();
	private Sun sun;

	public PlantsManager(Playing playing) {
		this.playing = playing;
		sun = new Sun();
	}

	public void update() {
		// plant shoots or action
	}

	public void addPlant(String name, int x, int y) {
        Plant plantCreated = PlantFactory.CreatePlant(name, x, y);
        if (checkNonAquatic(plantCreated, x, y) && !checkPlants(x, y) && checkCost(plantCreated, sun)) {
                plants.add(plantCreated);
				sun.reduceSun(plantCreated.getCost());
        }
        
	}

    private boolean checkPlants(int x, int y) {
		boolean isExist = false;
		for (Plant plant : plants) {
			if (plant.getX() == x && plant.getY() == y && plant.getName().equals("LilyPad") == false) {
				isExist = true;
			}
		}
		return isExist;
	}

    private boolean checkNonAquatic(Plant plant, int x, int y) {
		boolean plantable = true;
		
		if (y >= 380 && y <= 470) {
			if (plant.getAquaStatus() == false) {
                plantable = false;
				for (Plant p : plants) {
					if (p.getX() == x && p.getY() == y) {
						if (p.getName().equals("LilyPad")) {
							// if (p.getAquaStatus() == false) {
								plantable = true;
							// }
						} 
					}
				}
			} 
		} else {
			if (plant.getAquaStatus() == true) {
                plantable = false;
			}
		}

		return plantable;
	}

	private boolean checkCost(Plant plant, Sun sun) {
		return (plant.getCost() <= sun.getSun());
	}

    public void clearPlants() {
		for (Plant p : plants) {
			if (p instanceof entity.Plants.Sunflower) {
				((entity.Plants.Sunflower) p).stopSunSF();
			}
		}
        plants.clear();
    }

	public void draw(Graphics g) {
		for (Plant p : plants)
			drawPlants(p, g);
	}

	private void drawPlants(Plant plant, Graphics g) {
        if (plant.getName().equals("LilyPad")) {
            g.drawImage(plant.getImage(), (int) plant.getX() - 15, (int) plant.getY() + 10, null);
        } else {
		    g.drawImage(plant.getImage(), (int) plant.getX() - 15, (int) plant.getY(), null); // work di x nya coba
        }
	}
    
}