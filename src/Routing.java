import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;
import java.util.*;
import java.io.*;


public class Routing {

    public class Vertex {
        HashMap<String, Integer> nbrs;

        public Vertex() {
            nbrs = new HashMap<>();
        }
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

    public void display_Map()
    {
        System.out.println("\t Electric Bus Route Map\n");
        ArrayList<String> keys = new ArrayList<>(vtx.keySet());

        for (String key : keys)
        {
            String str = key + "=>\n";
            Vertex vtr = vtx.get(key);
            ArrayList<String> vtrnbrs = new ArrayList<>(vtr.nbrs.keySet());

            for (String nbr : vtrnbrs)
            {
                str = str + "\t" + nbr + "\t";
                if (nbr.length() < 16)
                {
                    str = str + "\t";
                }
                if (nbr.length() < 8)
                {
                    str = str + "\t";
                }
                str = str + vtr.nbrs.get(nbr)+ "\n";
            }
            System.out.println(str);
        }
        System.out.println("\n");
    }

    public void display_Stations()
    {
        System.out.println("\n*********************\n");
        ArrayList<String> keys = new ArrayList<>(vtx.keySet());
        int i = 1;
        for (String key : keys)
        {
            System.out.println(i + ". " + key);
            i++;
        }
        System.out.println("\n*********************\n");
    }

    public boolean hasPath(String vname1, String vname2, HashMap<String, Boolean> processed)
    {
        if (containsEdge(vname1, vname2))
        {
            return true;
        }

        processed.put(vname1, true);

        Vertex vrt = vtx.get(vname2);
        ArrayList<String> nbrs = new ArrayList<>(vrt.nbrs.keySet());

        for (String nbr : nbrs)
        {
            if (!processed.containsKey(nbr))
            {
                if (hasPath(nbr, vname2, processed))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<String> get_Interchanges(String str)
    {
        ArrayList<String> arr = new ArrayList<>();
        String res[] = str.split(" ");
        arr.add(res[0]);
        int count = 0;
        for (int i=1; i< res.length-1; i++)
        {
            int index = res[i].indexOf('~');
            String s = res[i].substring(index+1);

            if (s.length()==2)
            {
                String prev = res[i-1].substring(res[i-1].indexOf('~')+1);
                String next = res[i+1].substring(res[i+1].indexOf('~')+1);

                if (prev.equals(next))
                {
                    arr.add(res[i]);
                }
                else
                {
                    arr.add(res[i]+" ==> "+res[i+1]);
                    i++;
                    count++;
                }
            }
            else
            {
                arr.add(res[i]);
            }
        }
        arr.add(Integer.toString(count));
        arr.add(res[res.length-1]);
        return arr;
    }

    private class DijkstraPair implements Comparable<DijkstraPair>
    {
        String vname;
        String psf;
        int cost;

        @Override
        public int compareTo(DijkstraPair o)
        {
            return o.cost - this.cost;
        }
    }

    public int dijkstra(String src, String des, boolean nan)
    {
        int val = 0;
        ArrayList<String> ans = new ArrayList<>();
        HashMap<String, DijkstraPair> map = new HashMap<>();

        Heap<DijkstraPair> heap = new Heap<>();

        for (String key : vtx.keySet())
        {
            DijkstraPair np = new DijkstraPair();
            np.vname = key;
            np.cost = Integer.MAX_VALUE;

            if (key.equals(src))
            {
                np.cost = 0;
                np.psf = key;
            }

            heap.add(np);
            map.put(key, np);
        }

        while (!heap.isEmpty())
        {
            DijkstraPair rp = heap.remove();

            if(rp.vname.equals(des))
            {
                val = rp.cost;
                break;
            }

            map.remove(rp.vname);
            ans.add(rp.vname);

            Vertex v = vtx.get(rp.vname);
            for (String nbr : v.nbrs.keySet())
            {
                if (map.containsKey(nbr))
                {
                    int oc = map.get(nbr).cost;
                    Vertex k = vtx.get(rp.vname);
                    int nc;
                    if(nan)
                    {
                        nc = rp.cost + k.nbrs.get(nbr);
                    }
                    else
                    {
                        nc = rp.cost + k.nbrs.get(nbr);
                    }

                    if (nc < oc)
                    {
                        DijkstraPair gp = map.get(nbr);
                        gp.psf = rp.psf + nbr;
                        gp.cost = nc;

                        heap.updatepriority(gp);
                    }
                }
            }
        }
        return val;
    }

    private class Pair
    {
        String vname;
        String psf;
        int min_dis;
        int min_time;
    }


    public String Get_Minimum_Distance(String src, String dst)
    {
        int min = Integer.MAX_VALUE;
        String ans = "";
        HashMap<String, Boolean> processed = new HashMap<>();
        LinkedList<Pair> stack = new LinkedList<>();

        Pair sp = new Pair();
        sp.vname = src;
        sp.psf = src + " ";
        sp.min_dis = 0;
        sp.min_time = 0;

        stack.addFirst(sp);

        while (!stack.isEmpty())
        {
            Pair rp = stack.removeFirst();

            if (processed.containsKey(rp.vname))
            {
                continue;
            }

            processed.put(rp.vname, true);

            if (rp.vname.equals(dst))
            {
                int temp = rp.min_dis;
                if (temp < min)
                {
                    ans = rp.psf;
                    min = temp;
                }
                continue;
            }
            Vertex rpvtx = vtx.get(rp.vname);
            ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

            for (String nbr : nbrs)
            {
                if (!processed.containsKey(nbr))
                {
                    Pair np = new Pair();
                    np.vname = nbr;
                    np.psf = rp.psf + nbr + " ";
                    np.min_dis = rp.min_dis + rpvtx.nbrs.get(nbr);
                    stack.addFirst(np);
                }
            }
        }
        ans = ans + Integer.toString(min);
        return ans;
    }

    static void Create_Bus_Map(@NotNull Routing g)
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
        g.addVertex("Nanda Ki Chowki");
        g.addVertex("Balliwala Chowk");
        g.addVertex("Shimla Bypass");

        g.addEdge("ISBT", "Jolly Grant Airport", 29);
        g.addEdge("Jolly Grant Airport", "Kargi Chowk", 27);
        g.addEdge("Kargi Chowk", "Rispana Pull", 5);
        g.addEdge("Rispana Pull", "Clock Tower", 6);
        g.addEdge("Clock Tower", "Jogiwala", 7);
        g.addEdge("Jogiwala", "Mokhampur", 3);
        g.addEdge("Mokhampur", "Ballupur Chowk", 10);
        g.addEdge("Ballupur Chowk", "IMA", 4);
        g.addEdge("IMA", "FRI", 2);
        g.addEdge("FRI", "Pacific Mall", 12);
        g.addEdge("Pacific Mall", "Prem Nagar", 16);
        g.addEdge("Prem Nagar", "Survey Chowk", 10);
        g.addEdge("Survey Chowk", "Rajpur Road", 5);
        g.addEdge("Rajpur Road", "Sai Mandir", 7);
        g.addEdge("Sai Mandir", "IT Park", 10);
        g.addEdge("IT Park", "Railway Station", 8);
        g.addEdge("Railway Station", "Musoorie Diversion", 8);
        g.addEdge("Musoorie Diversion", "Saharanpur Chowk", 9);
        g.addEdge("Saharanpur Chowk", "Prince Chowk", 2);
        g.addEdge("Prince Chowk", "Nanda Ki Chowki", 10);
        g.addEdge("Nanda Ki Chowki", "Balliwala Chowk", 7);
        g.addEdge("Balliwala Chowk", "Shimla Bypass", 22);
    }

    public static void main(String[] args) throws IOException {

        Routing g = new Routing();
        Create_Bus_Map(g);

        System.out.println("\n\t\t\t***WELCOME TO THE ELECTRIC BUS MAP***");

        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

        while(true)
        {
            System.out.println("\t\t\t~~~~LIST OF ACTIONS~~~~\n\n");
            System.out.println("1. LIST ALL THE STATIONS IN THE MAP");
            System.out.println("2. SHOW THE BUS MAP");
            System.out.println("3. GET SHORTEST PATH (DISTANCE WISE) TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
            System.out.println("4. GET SHORTEST PATH (TIME WISE) TO REACH FROM A 'SOURCE' STATION TO 'DESTINATION' STATION");
            System.out.println("5. EXIT THE MENU");
            System.out.print("\nENTER YOUR CHOICE FROM THE ABOVE LIST (1 to 5) : ");

            int choice = -1;
            try
            {
                choice = Integer.parseInt(inp.readLine());
            } catch(Exception e)
            {
            }
            if (choice == 5)
            {
                System.exit(0);
            }
            switch (choice)
            {
                case 1:
                    g.display_Stations();
                    break;

                case 2:
                    g.display_Map();
                    break;

                case 3:
                    System.out.println("Enter the Source and Destination Stations");
                    String s1 = inp.readLine();
                    String s2 = inp.readLine();

                    HashMap<String, Boolean> processed2 = new HashMap<>();
                    boolean vertexS1Missing = !g.containsVertex(s1);
                    boolean vertexS2Missing = !g.containsVertex(s2);
                    boolean noPathFromS1ToS2 = !g.hasPath(s1, s2, processed2);

                    if (vertexS1Missing || vertexS2Missing || noPathFromS1ToS2)
                    {
                        System.out.println("The Inputs are Invalid");
                    }
                    else
                    {
                        ArrayList<String> str = g.get_Interchanges(g.Get_Minimum_Distance(s1, s2));


                    }
            }
        }


        }
    }