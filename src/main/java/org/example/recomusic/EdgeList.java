package org.example.recomusic;

import lombok.Data;
import java.util.*;

@Data
public class EdgeList{
    private int limit;
    private ArrayList<Vertex> items;
    private EdgeCreator edgeCreator;

    public EdgeList(int limit, EdgeCreator comparator) {
        this.limit = limit;
        this.edgeCreator = comparator;
    }

    public void add(Vertex item, long noOfArtists, long noOfAlbums) {
        int i = 0;
        for (; edgeCreator.compare(item, items.get(i), noOfArtists, noOfAlbums) < 0; i++);
        items.add(i, item);
        if (items.size() > limit)
            items.remove(limit);
    }
}
