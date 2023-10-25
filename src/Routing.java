import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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



        }
    }