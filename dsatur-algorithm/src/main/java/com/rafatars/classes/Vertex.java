package com.rafatars.classes;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Vertex {
    private ArrayList<Edge> edges;
    private String label;
    private int degrees;

    //cores
    private int number;

    public Vertex(int number, ArrayList<Edge> edges, String label) {
        this.number = number;
        calculateDegrees();
        this.edges = edges;
        this.label = label;
    }

    public Vertex(int number, String label) {
        this.number = number;
        this.edges = new ArrayList<>();
        this.degrees = 0;
        this.label = label;
    }

    public Vertex(String label) {
        this.number = -1;
        this.edges = new ArrayList<>();
        this.degrees = 0;
        this.label = label;
    }

    public void calculateDegrees()
    {
        this.degrees = this.getNeighbours().length;
    }

    public Vertex[] getNeighbours(){
        Vertex[] neighbours = new Vertex[this.edges.size()];

        for(int i = 0; i < this.edges.size(); i++){
            
            if(this.edges.get(i).getDestination().getLabel().equals(this.label)){
                neighbours[i] = this.edges.get(i).getOrigin();
            }else{
                neighbours[i] = this.edges.get(i).getDestination();
            }
            
        }

        return neighbours;
    }
}
