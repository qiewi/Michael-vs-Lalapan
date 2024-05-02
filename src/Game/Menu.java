package Game;
import java.util.List;

public class Menu{

    public void Start(){

    }

    public void Help(){

    }

    public void Plants_list(List<String> Plants){
        for (String Tanaman : Plants){
            System.out.println(Tanaman);
        }
    }

    public void Zombies_List(List<String> Zombies){
        for (String Zombie : Zombies){
            System.out.println(Zombie);
        }
    }

    public void Exit(){

    }
}