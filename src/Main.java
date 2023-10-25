import java.sql.SQLOutput;
import java.util.*;


public class Main {

    class BusStation {
        private int id;
        private String name;

        public BusStation(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    class BusConnection {
        private BusStation source;
        private BusStation destination;
        private int distance;

        public BusConnection(BusStation source, BusStation destination, int distance) {
            this.source = source;
            this.destination = destination;
            this.distance = distance;
        }

        public BusStation getSource() {
            return source;
        }

        public BusStation getDestination() {
            return destination;
        }

        public int getDistance() {
            return distance;
        }
    }

    class ElectricBusNetwork {
        private Map<Integer, BusStation> stations;
        private List<BusConnection> connections;

        public ElectricBusNetwork() {
            stations = new HashMap<>();
            connections = new ArrayList<>();
        }

        public void addStation(BusStation station) {
            stations.put(station.getId(), station);
        }

        public void addConnection(BusConnection connection) {
            connections.add(connection);
        }

        public BusStation getStationById(int id) {
            return stations.get(id);
        }

        public List<BusConnection> getConnections() {
            return connections;
        }
    }

    public static void Create_Bus_Map(Main g) {

    }




    public static void main(String[] args) {

        Main g = new Main();
        Create_Bus_Map(g);

        System.out.println("\n\t\t\t***WELCOME TO THE ELECTRIC BUS MAP***");

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
        }

        ElectricBusNetwork busNetwork = new ElectricBusNetwork();

        // Adding bus stations
        BusStation station1 = new BusStation(1, "Prem Nagar");
        BusStation station2 = new BusStation(2, "Ballupur Chowk");
        BusStation station3 = new BusStation(3, "Clock Tower");

        busNetwork.addStation(station1);
        busNetwork.addStation(station2);
        busNetwork.addStation(station3);

        // Adding connections between stations
        BusConnection connection1 = new BusConnection(station1, station2, 5);
        BusConnection connection2 = new BusConnection(station2, station3, 8);

        busNetwork.addConnection(connection1);
        busNetwork.addConnection(connection2);

        // Retrieving information
        BusStation retrievedStation = busNetwork.getStationById(2);
        System.out.println("Retrieved Station: " + retrievedStation.getName());

        List<BusConnection> connections = busNetwork.getConnections();
        System.out.println("Connections:");
        for (BusConnection connection : connections) {
            System.out.println("From: " + connection.getSource().getName() +
                    " To: " + connection.getDestination().getName() +
                    " Distance: " + connection.getDistance() + " miles");
        }
    }
}