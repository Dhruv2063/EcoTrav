import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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