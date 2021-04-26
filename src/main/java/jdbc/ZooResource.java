package jdbc;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

@Produces(MediaType.APPLICATION_JSON)
@Path("zoo")
public class ZooResource {

    @Inject
    private Manager manager;

    @GET
    public Response getAll() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/c3zoo", "root", ""
        );

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT Z.id, Z.jmeno, D.nazev, Z.vaha, Z.narozen FROM Zvirata AS Z JOIN Druhy AS D ON (Z.druh = D.id) LIMIT 100");

        while(result.next()){
            Animal animal = new Animal();
            String id = result.getString("id");
            animal.setId(id);
            String name = result.getString("jmeno");
            animal.setName(name);
            String type = result.getString("nazev");
            animal.setSpecies(type);
            String weight = result.getString("vaha");
            animal.setWeight(weight);
            String dateOfBirth = result.getString("narozen");
            animal.setBirth(dateOfBirth);
            manager.animals.add(animal);

        }

        connection.close();

        return Response.ok(manager.getAnimal()).build();
    }
}
