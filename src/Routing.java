import java.io.*;
import java.util.*;

public class Routing {

    static HashMap<String, HashMap<String, Integer>> busMap;

    public Routing() {
        busMap = new HashMap<>();
    }

    public void addStation(String stationName) {
        busMap.put(stationName, new HashMap<>());
    }

    public void addConnection(String source, String destination, int distance) {
        busMap.get(source).put(destination, distance);
        busMap.get(destination).put(source, distance);
    }

    public void displayStations() {
        System.out.println("List of Stations:");
        busMap.keySet().forEach(station -> System.out.println(station));
    }

    public void displayBusMap() {
        System.out.println("Electric Bus Route Map:");
        busMap.forEach((station, connections) -> {
            System.out.println(station + " =>");
            connections.forEach((destination, distance) ->
                    System.out.printf("\t%s (Distance: %d kilometers)%n", destination, distance)
            );
        });
    }

    public class Station {
        Map<String, Integer> connections;

        public Station() {
            connections = new HashMap<>();
        }
    }

    public int dijkstra(String source, String destination) {
        if (!busMap.containsKey(source) || !busMap.containsKey(destination)) {
            System.out.println("Invalid station names.");
            return -1;
        }

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousStations = new HashMap<>();
        Set<String> unvisited = new HashSet<>(busMap.keySet());

        for (String stationName : unvisited) {
            distances.put(stationName, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        while (!unvisited.isEmpty()) {
            String current = unvisited.stream()
                    .min(Comparator.comparingInt(distances::get))
                    .orElse(null);

            if (current == null) {
                System.out.println("No path found.");
                return -1;
            }

            unvisited.remove(current);

            busMap.get(current).forEach((neighbor, distance) -> {
                int newDistance = distances.get(current) + distance;
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    previousStations.put(neighbor, current);
                }
            });
        }

        return distances.get(destination);
    }


    public static void main(String[] args) throws IOException {
        Routing busNetwork = new Routing();
        busNetwork.addStation("ISBT");
        busNetwork.addStation("Jolly Grant Airport");
        busNetwork.addStation("Kargi Chowk");
        busNetwork.addStation("Rispana Pull");
        busNetwork.addStation("Clock Tower");
        busNetwork.addStation("Jogiwala");
        busNetwork.addStation("Mokhampur");
        busNetwork.addStation("Ballupur Chowk");
        busNetwork.addStation("IMA");
        busNetwork.addStation("FRI");
        busNetwork.addStation("Pacific Mall");
        busNetwork.addStation("Prem Nagar");
        busNetwork.addStation("Survey Chowk");
        busNetwork.addStation("Rajpur Road");
        busNetwork.addStation("Sai Mandir");
        busNetwork.addStation("IT Park");
        busNetwork.addStation("Railway Station");
        busNetwork.addStation("Musoorie Diversion");
        busNetwork.addStation("Saharanpur Chowk");
        busNetwork.addStation("Prince Chowk");
        busNetwork.addStation("Nanda Ki Chowki");
        busNetwork.addStation("Balliwala Chowk");
        busNetwork.addStation("Shimla Bypass");

        busNetwork.addConnection("ISBT", "Jolly Grant Airport", 29);
        busNetwork.addConnection("Jolly Grant Airport", "Kargi Chowk", 27);
        busNetwork.addConnection("Kargi Chowk", "Rispana Pull", 5);
        busNetwork.addConnection("Rispana Pull", "Clock Tower", 6);
        busNetwork.addConnection("Clock Tower", "Jogiwala", 7);
        busNetwork.addConnection("Jogiwala", "Mokhampur", 3);
        busNetwork.addConnection("Mokhampur", "Ballupur Chowk", 10);
        busNetwork.addConnection("Ballupur Chowk", "IMA", 4);
        busNetwork.addConnection("IMA", "FRI", 2);
        busNetwork.addConnection("FRI", "Pacific Mall", 12);
        busNetwork.addConnection("Pacific Mall", "Prem Nagar", 16);
        busNetwork.addConnection("Prem Nagar", "Survey Chowk", 10);
        busNetwork.addConnection("Survey Chowk", "Rajpur Road", 5);
        busNetwork.addConnection("Rajpur Road", "Sai Mandir", 7);
        busNetwork.addConnection("Sai Mandir", "IT Park", 10);
        busNetwork.addConnection("IT Park", "Railway Station", 8);
        busNetwork.addConnection("Railway Station", "Musoorie Diversion", 8);
        busNetwork.addConnection("Musoorie Diversion", "Saharanpur Chowk", 9);
        busNetwork.addConnection("Saharanpur Chowk", "Prince Chowk", 2);
        busNetwork.addConnection("Prince Chowk", "Nanda Ki Chowki", 10);
        busNetwork.addConnection("Nanda Ki Chowki", "Balliwala Chowk", 7);
        busNetwork.addConnection("Balliwala Chowk", "Shimla Bypass", 22);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\nElectric Bus Network Menu:");
            System.out.println("1. List all stations");
            System.out.println("2. Show bus map");
            System.out.println("3. Get shortest distance between stations");
            System.out.println("4. Exit");

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
                    int distance = busNetwork.dijkstra(source, destination);
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