package com.rafatars.classes;


import java.util.ArrayList;

import lombok.Data;

@Data
public class Dsatur {
    

    public void dsatur(Graph graph)
    {
        int colored = 0;

        //array de saturação dos
        int[] saturation = new int[graph.getVertices().size()];

        //colore o vertice de maior grau com a primeira cor
        graph.getVertices().get(0).setNumber(0);
        colored++;

        while(colored < graph.getVertices().size())
        {
            this.calculateSaturation(saturation, graph.getVertices());

            int nextVertexIndex = this.getNextVertexIndex(saturation, graph.getVertices());

            this.colorVertex(graph.getVertices().get(nextVertexIndex));

            colored++;
        }
    }
    
    private void calculateSaturation(int[] saturation, ArrayList<Vertex> vertices)
    {
        for(int i = 0; i < vertices.size(); i++)
        {

            //caso o vértice já esteja colorido atribui saturação -1
            if(vertices.get(i).getNumber() != -1)
            {
                saturation[i] = -1;

            }else{

                Vertex[] neighbours = vertices.get(i).getNeighbours();
    
                ArrayList<Integer> neighboursColors = new ArrayList<>();
    
                for (Vertex vertex : neighbours) {
                    //caso o numero não for -1, ou seja, está colorido
                    //caso seja uma cor que nenhum outro visinho tenha até o momento
                    if(vertex.getNumber() != -1 && !neighboursColors.contains(vertex.getNumber()))
                    {
                        neighboursColors.add(vertex.getNumber());
                    }
                }
    
                saturation[i] = neighboursColors.size();

            }
        }
    }

    private int getNextVertexIndex(int[] saturation, ArrayList<Vertex> vertices)
    {
        int greaterSaturationIndex = 0;

        for (int i = 0; i < saturation.length; i++) {
            
            if(saturation[i] > saturation[greaterSaturationIndex]){
                
                greaterSaturationIndex = i;
                
            }else if(saturation[i] == saturation[greaterSaturationIndex]){
                
                if(vertices.get(i).getDegrees() >= vertices.get(greaterSaturationIndex).getDegrees()){
                    
                    greaterSaturationIndex = i;

                }
            }
        }

        return greaterSaturationIndex;
    }

    private void colorVertex(Vertex vertex)
    {
        ArrayList<Integer> availableColors = new ArrayList<>();
        availableColors.add(0);
        availableColors.add(1);
        availableColors.add(2);
        availableColors.add(3);
        availableColors.add(4);
        availableColors.add(5);
        availableColors.add(6);

        for(Vertex neighbour : vertex.getNeighbours())
        {
            if(availableColors.contains(neighbour.getNumber())){
                
                for(int i = 0; i < availableColors.size(); i++)
                {
                    if(availableColors.get(i) == neighbour.getNumber()){
                        availableColors.remove(i);
                    }
                }
            }
        }

        if(availableColors.size() == 0){
            System.out.println("Deu ruim na hora de colorir o vértice!");
            return;
        }

        vertex.setNumber(availableColors.get(0));
    }
}
