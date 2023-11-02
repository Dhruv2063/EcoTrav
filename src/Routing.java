import java.io.*;
import java.util.*;

public class Routing
{

    static HashMap<String, HashMap<String, Integer>> busMap;

    public Routing()
    {
        busMap = new HashMap<>();
    }

    public void addStation(String stationName)
    {
        busMap.put(stationName, new HashMap<>());
    }

    public void addConnection(String source, String destination, int distance)
    {
        if (!busMap.containsKey(source) || !busMap.containsKey(destination))
        {
            System.out.println("Invalid station names.");
            return;
        }
        busMap.get(source).put(destination, distance);
        busMap.get(destination).put(source, distance);
    }

    public void displayStations()
    {
        System.out.println("List of Stations:");
        busMap.keySet().forEach(station -> System.out.println(station));
    }

    public void displayBusMap()
    {
        System.out.println("Electric Bus Route Map:");
        busMap.forEach((station, connections) -> {
            System.out.println(station + " =>");
            connections.forEach((destination, distance) ->
                    System.out.printf("\t%s (Distance: %d kilometers)%n", destination, distance)
            );
        });
    }

    public int path(String source, String destination)
    {
        if (!busMap.containsKey(source) || !busMap.containsKey(destination)) {
            System.out.println("Invalid station names.");
            return -1;
        }

        Map<String, Integer> distances = new HashMap<>();
        Set<String> unvisited = new HashSet<>(busMap.keySet());

        for (String stationName : unvisited)
        {
            distances.put(stationName, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        while (!unvisited.isEmpty())
        {
            String current = unvisited.stream()
                    .min(Comparator.comparingInt(distances::get))
                    .orElse(null);

            if (current == null)
            {
                System.out.println("No path found.");
                return -1;
            }

            unvisited.remove(current);

            busMap.get(current).forEach((neighbor, distance) -> {
                int newDistance = distances.get(current) + distance;
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                }
            });
        }
        return distances.get(destination);
    }

    public static void main(String[] args) throws IOException
    {
        Routing busNetwork = new Routing();
        busNetwork.addStation("ISBT");
        busNetwork.addStation("Jolly Grant Airport");
        busNetwork.addStation("Kargi Chowk");
        busNetwork.addStation("Rispana Pull");
        busNetwork.addStation("Clock Tower");
        busNetwork.addStation("Jogiwala");
        busNetwork.addStation("Ballupur Chowk");
        busNetwork.addStation("IMA");
        busNetwork.addStation("FRI");
        busNetwork.addStation("Pacific Mall");
        busNetwork.addStation("Prem Nagar");
        busNetwork.addStation("Survey Chowk");
        busNetwork.addStation("Sai Mandir");
        busNetwork.addStation("IT Park");
        busNetwork.addStation("Railway Station");
        busNetwork.addStation("Musoorie Diversion");
        busNetwork.addStation("Balliwala Chowk");

        busNetwork.addConnection("ISBT", "Prem Nagar", 9);
        busNetwork.addConnection("ISBT", "Kargi Chowk", 3);
        busNetwork.addConnection("ISBT", "Balliwala Chowk", 6);
        busNetwork.addConnection("Prem Nagar", "IMA", 2);
        busNetwork.addConnection("IMA", "FRI", 2);
        busNetwork.addConnection("IMA", "Balliwala Chowk", 3);
        busNetwork.addConnection("FRI", "Ballupur Chowk", 2);
        busNetwork.addConnection("FRI", "Balliwala Chowk", 2);
        busNetwork.addConnection("Ballupur Chowk", "Balliwala Chowk", 2);
        busNetwork.addConnection("Ballupur Chowk", "Clock Tower", 4);
        busNetwork.addConnection("Balliwala Chowk", "Sai Mandir", 2);
        busNetwork.addConnection("Clock Tower", "Survey Chowk", 2);
        busNetwork.addConnection("Clock Tower", "Pacific Mall", 6);
        busNetwork.addConnection("Clock Tower", "IT Park", 8);
        busNetwork.addConnection("Clock Tower", "Sai Mandir", 2);
        busNetwork.addConnection("Clock Tower", "Railway Station", 2);
        busNetwork.addConnection("Clock Tower", "Rispana Pull", 5);
        busNetwork.addConnection("Musoorie Diversion", "Pacific Mall", 1);
        busNetwork.addConnection("Musoorie Diversion", "IT Park", 3);
        busNetwork.addConnection("IT Park", "Survey Chowk", 6);
        busNetwork.addConnection("Survey Chowk", "Rispana Pull", 5);
        busNetwork.addConnection("Sai Mandir", "Railway Station", 2);
        busNetwork.addConnection("Railway Station", "Kargi Chowk", 4);
        busNetwork.addConnection("Railway Station", "Rispana Pull", 4);
        busNetwork.addConnection("Kargi Chowk", "Rispana Pull", 4);
        busNetwork.addConnection("Rispana Pull", "Jogiwala", 2);
        busNetwork.addConnection("Jogiwala", "Jolly Grant Airport", 22);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\nElectric Bus Network Menu:");
            System.out.println("1. List all stations");
            System.out.println("2. Show bus map");
            System.out.println("3. Get shortest distance between stations");
            System.out.println("4. Exit");
            System.out.print("\nENTER YOUR CHOICE FROM THE ABOVE LIST (1 to 4) : ");

            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    busNetwork.displayStations();
                    break;
                case 2:
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    busNetwork.displayBusMap();
                    break;
                case 3:
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.print("Enter source station: ");
                    String source = reader.readLine();
                    System.out.print("Enter destination station: ");
                    String destination = reader.readLine();
                    int distance = busNetwork.path(source, destination);
                    System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    if (distance != -1) {
                        System.out.println("Shortest distance: " + distance + " Kilometer");
                        }
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}