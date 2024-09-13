package com.rafatars.classes;

import lombok.Data;

@Data
public class Edge {
    private Vertex origin;
    private Vertex destination;
    private int weight;
    private boolean directed;

    public Edge(Vertex origin, Vertex destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(Vertex origin, Vertex destination) {
        this.origin = origin;
        this.destination = destination;
        this.weight = 1;
    }

    public Edge() {
        this.origin = null;
        this.destination = null;
        this.weight = 1;
        this.directed = false;
    }
}