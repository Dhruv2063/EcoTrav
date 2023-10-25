import java.util.ArrayList;
import java.util.HashMap;

public class Heap<T extends Comparable<T>>
{
    ArrayList<T> data = new ArrayList<>();
    HashMap<T, Integer> map = new HashMap<>();

    public void add(T item)
    {
        data.add(item);
        map.put(item, this.data.size() - 1);
        unheapify(data.size() - 1);
    }

    private void unheapify(int ci)
    {
        int pt = (ci - 1) / 2;
        if (isLarger(data.get(ci), data.get(pt)) > 0)
        {
            swap(pt, ci);
            unheapify(pt);
        }
    }

    public void downheapify(int pt)
    {
        int lci = 2 * pt + 1;
        int rci = 2 * pt + 2;
        int mini = pt;

        if (lci < this.data.size() && isLarger(data.get(lci), data.get(mini)) > 0)
        {
            mini = lci;
        }

        if (rci < this.data.size() && isLarger(data.get(rci), data.get(mini)) > 0)
        {
            mini = rci;
        }

        if (mini != pt)
        {
            swap(mini, pt);
            downheapify(mini);
        }

    }

    public T get()
    {
        return this.data.get(0);
    }

    private void swap(int i, int j)
    {
        T ith = data.get(i);
        T jth = data.get(j);

        data.set(i, jth);
        data.set(j, ith);
        map.put(ith, j);
        map.put(jth, i);
    }

    public void display()
    {
        System.out.println(data);
    }

    public int size()
    {
        return this.data.size();
    }

    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    public int isLarger(T t, T o)
    {
        return t.compareTo(o);
    }

    public T remove()
    {
        swap(0, this.data.size() - 1);
        T rm = this.data.remove(this.data.size() - 1);
        downheapify(0);

        map.remove(rm);
        return rm;
    }

    public void updatepriority(T pair)
    {
        int index = map.get(pair);
        unheapify(index);
    }
}