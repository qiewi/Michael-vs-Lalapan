package managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import entity.Plants.Lilypad;
import entity.Plants.Plant;
import entity.Plants.PlantFactory;
import entity.Plants.Tallnut;
import entity.Zombies.Zombie;
import objects.Sun;
import scenes.Playing;

public class PlantsManager implements ManagersUI {
    private Playing playing;
	private Sun sun;

	private static ArrayList<Plant> plants = new ArrayList<>();
	
	public PlantsManager(Playing playing) {
		this.playing = playing;
		sun = new Sun();
	}

	public void update() {
		Iterator<Plant> iterator = plants.iterator();
        while (iterator.hasNext()) {
            Plant plant = iterator.next();
            if (plant.getHealth() <= 0) {
				plant.actionStop();
				iterator.remove();
			}
        }
	}

	public void addPlant(String name, int x, int y, int deckIndex) {
        Plant plantCreated = PlantFactory.CreatePlant(name, x, y);
        if (checkNonAquatic(plantCreated, x, y) && !checkPlants(x, y) && checkCost(plantCreated, sun)) {
                plants.add(plantCreated);
				sun.reduceSun(plantCreated.getCost());
				playing.getTopBar().makePlantCardsCooldown(deckIndex);
        } else {
			plantCreated.actionStop();
			plantCreated = null;
		}
        
	}

	public static Plant checkPlantsInPos(int x, int y) {
		Plant plant = null;
		for (Plant p : plants) {
			if (((int) p.getX() - 30 <= x && (int) p.getX() >= x)&& (int) p.getY() == y){
				plant = p;
			}
		}
		return plant;
	}

	public static void takeDamage(Plant plant, Zombie zombie) {
		for (Plant p : plants) {
			if (p.equals(plant)) {
				p.takeDamage(zombie);
			}
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
			}
			for (Plant p : plants) {
				if (p.getX() == x && p.getY() == y) {
					if (p.getName().equals("LilyPad")) {
						if (plant.getAquaStatus() == false) {
							plantable = true;
						} else {
							plantable = false;
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
			p.actionStop();
		}
        plants.clear();
    }

	public static void deletePlantsAt(int x, int y) {
		for (int i = 0; i < plants.size(); i++) {
			if (plants.get(i).getX() == x && plants.get(i).getY() == y) {
				plants.get(i).actionStop();
				plants.remove(i);
			}
		}
	}

	public void draw(Graphics g) {
		for (Plant p : plants)
			drawPlants(p, g);
	}

	private void drawPlants(Plant plant, Graphics g) {
        if (plant instanceof Lilypad) {
            g.drawImage(plant.getImage(), (int) plant.getX() - 15, (int) plant.getY() + 10, null);
        } else if (plant instanceof Tallnut) {
		    g.drawImage(plant.getImage(), (int) plant.getX() - 15, (int) plant.getY() - 45, null); // work di x nya coba
        } else {
		    g.drawImage(plant.getImage(), (int) plant.getX() - 15, (int) plant.getY() - 10, null); // work di x nya coba
        }
	}

	public static ArrayList<Plant> getPlants() {
		return plants;
	}
    
}