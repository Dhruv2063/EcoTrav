import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.sql.SQLOutput;
import java.util.*;



public class Main {

    static void Create_Bus_Map(Main g)
{
    g.addVertex("ISBT");
    g.addVertex("Jolly Grant Airport");
    g.addVertex("Kargi Chowk");
    g.addVertex("Rispana Pull");
    g.addVertex("Clock Tower");
    g.addVertex("Jogiwala");
    g.addVertex("Mokhampur");
    g.addVertex("Ballupur Chowk");
    g.addVertex("IMA");
    g.addVertex("FRI");
    g.addVertex("Pacific Mall");
    g.addVertex("Prem Nagar");
    g.addVertex("Survey Chowk");
    g.addVertex("Rajpur Road");
    g.addVertex("Sai Mandir");
    g.addVertex("IT Park");
    g.addVertex("Railway Station");
    g.addVertex("Musoorie Diversion");
    g.addVertex("Saharanpur Chowk");
    g.addVertex("Prince Chowk");
    g.addVertex("DRDO");
    g.addVertex("Nanda Ki Chowki");
    g.addVertex("Balliwala Chowk");
    g.addVertex("Shimla Bypass");

    g.addEdge("ISBT", "Jolly Grant Airport", 8);
    g.addEdge("Jolly Grant Airport", "Kargi Chowk", 8);
    g.addEdge("Kargi Chowk", "Rispana Pull", 8);
    g.addEdge("Rispana Pull", "Clock Tower", 8);
    g.addEdge("Clock Tower", "Jogiwala", 8);
    g.addEdge("Jogiwala", "Mokhampur", 8);
    g.addEdge("Mokhampur", "Ballupur Chowk", 8);
    g.addEdge("Ballupur Chowk", "IMA", 8);
    g.addEdge("IMA", "FRI", 8);
    g.addEdge("FRI", "Pacific Mall", 8);
    g.addEdge("Pacific Mall", "Prem Nagar", 8);
    g.addEdge("Prem Nagar", "Survey Chowk", 8);
    g.addEdge("Survey Chowk", "Rajpur Road", 8);
    g.addEdge("Rajpur Road", "Sai Mandir", 8);
    g.addEdge("Sai Mandir", "IT Park", 8);
    g.addEdge("IT Park", "Railway Station", 8);
    g.addEdge("Railway Station", "Musoorie Diversion", 8);
    g.addEdge("Musoorie Diversion", "Saharanpur Chowk", 8);
    g.addEdge("Saharanpur Chowk", "Prince Chowk", 8);
    g.addEdge("Prince Chowk", "DRDO", 8);
    g.addEdge("DRDO", "Nanda Ki Chowki", 8);
    g.addEdge("Nanda Ki Chowki", "Balliwala Chowk", 8);
    g.addEdge("Balliwala Chowk", "Shimla Bypass", 8);
}

    public static void main(String[] args) throws IOException 
    {
        Main g = new Main();
        Create_Metro_Map(g);
=======
import java.util.*;


public class Routing {

    public class Vertex
    {
        HashMap<String, Integer> nbrs = new HashMap<>();
    }

    static HashMap<String, Vertex> vtx;

    public Routing()
    {
        vtx = new HashMap<>();
    }

    public int numVertex()
    {
        return this.vtx.size();
    }

    public boolean containsVertex(String vname)
    {
        return this.vtx.containsKey(vname);
    }

    public void addVertex(String vname)
    {
        Vertex vrt = new Vertex();
        vtx.put(vname, vrt);
    }

    public int numedges()
    {
        ArrayList<String> Keys = new ArrayList<>(vtx.keySet());
        int count = 0;

        for (String key : Keys)
        {
            Vertex vrt = vtx.get(key);
            count = count + vrt.nbrs.size();
        }

        return count / 2;
    }

    public boolean containsEdge(String vname1, String vname2)
    {
        Vertex vtx1 = vtx.get(vname1);
        Vertex vtx2 = vtx.get(vname2);

        if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2))
        {
            return false;
        }

        return true;
    }

    public void addEdge(String vname1, String vname2, int value)
    {
        Vertex vtx1 = vtx.get(vname1);
        Vertex vtx2 = vtx.get(vname2);

        if  (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vname2))
        {
            return;
        }

        vtx1.nbrs.put(vname2, value);
        vtx2.nbrs.put(vname1, value);
    }

    public static void main(String[] args) throws IOException {
>>>>>>> 099827470e3e4329a8c80da8b716484277d3729d

        System.out.println("\n\t\t\t***WELCOME TO THE ELECTRIC BUS MAP***");

        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        while(true)
        {
            System.out.println("\t\t\t~~~~LIST OF ACTIONS~~~~\n\n");
            System.out.println("1. LIST ALL THE STATIONS IN THE MAP");
            System.out.println("2. SHOW THE BUS MAP");
            System.out.println("3. GET SHORTEST DISTANCE FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
            System.out.println("4. GET SHORTEST TIME TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
            System.out.println("5. GET SHORTEST PATH (DISTANCE WISE) TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
            System.out.println("6. GET SHORTEST PATH (TIME WISE) TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
            System.out.println("7. EXIT THE MENU");
            System.out.print("\nENTER YOUR CHOICE FROM THE ABOVE LIST (1 to 7) : ");

            int choice = -1;
            try
            {
                choice = Integer.parseInt(inp.readLine());
            } catch(Exception e)
            {

            }
            if (choice == 7)
            {
                System.exit(0);
            }
            switch (choice)
            {
                case 1:

            }
        }

<<<<<<< HEAD


=======
>>>>>>> 099827470e3e4329a8c80da8b716484277d3729d
        }
    }