package org.example.recomusic;

import lombok.Data;
import java.util.*;

@Data
public class EdgeList<E>{
    private int limit;
    private ArrayList<E> items;
    private Comparator<E> comparator;

    public EdgeList(int limit, Comparator<E> comparator) {
        this.limit = limit;
        this.comparator = comparator;
    }

    public void add(E item) {
        int i = 0;
        for (; comparator.compare(item, items.get(i)) < 0; i++);
        items.add(i, item);
        if (items.size() > limit)
            items.remove(limit);
    }
}
