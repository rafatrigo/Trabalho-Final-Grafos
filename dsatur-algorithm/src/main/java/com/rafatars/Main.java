package com.rafatars;

import com.rafatars.classes.Graph;
import com.rafatars.classes.Vertex;
import com.rafatars.classes.Dsatur;

public class Main {
    public static void main(String[] args) {
        String graphPath = "dsatur-algorithm/src/main/resources/v4-disconexo.txt";

        Graph graph = new Graph(graphPath);

        Dsatur dsatur = new Dsatur();

        dsatur.dsatur(graph);

        for(Vertex vertex : graph.getVertices())
        {
            System.out.println(vertex.getLabel() + " color: " + vertex.getNumber());
        }
    }
}