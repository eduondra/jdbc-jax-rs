package jdbc;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class Manager {
    static ArrayList<Animal> animals = new ArrayList<>();

    public ArrayList<Animal> getAnimal(){
        return animals;
    }
}
