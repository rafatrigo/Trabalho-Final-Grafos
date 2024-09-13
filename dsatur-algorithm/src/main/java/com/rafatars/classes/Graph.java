package com.rafatars.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import lombok.Data;


/*
 * Monta o grafo baseado em uma tabela de adjacência
 * 
 * Ordena os vértices do maior grau para o menor
 */
@Data
public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;

    public Graph(String filePath) {

        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        
        File file = new File(filePath);
        Scanner scan;
        try {
            scan = new Scanner(file);

            int lineCount = 0;

            //if the vertices are already created
            boolean startVertices = false;

            //scan until the file has no more lines
            while (scan.hasNextLine()) {
                //get the line
                String line = scan.nextLine();

                //transform the line into a char array
                String[] lineCharacters = line.split(",");

                //create the vertices
                if(!startVertices){
                    for(int i = 0; i < lineCharacters.length; i++){

                        vertices.add(new Vertex("Vertex"+i));
                    }
                    startVertices = true;
                }

                //passtrough each character of the line
                for(int i = 0 + lineCount; i < lineCharacters.length; i++)
                {
                    String value = lineCharacters[i];

                    if(value.equals("1"))
                    {
                        Edge edge = new Edge();

                        //set as the origin the vertex matching the line of the adjacency table
                        edge.setOrigin(vertices.get(lineCount));
                        //set as the destination the vertex matching the column of the adjacency table
                        edge.setDestination(vertices.get(i));

                        //add the edge in the vertices
                        vertices.get(lineCount).getEdges().add(edge);
                        vertices.get(i).getEdges().add(edge);

                        //add the edge to the edges list
                        edges.add(edge);
                    }
                }


                lineCount++;

            }

            scan.close();

            //set the degrees of the vertices
            for (Vertex vertex : this.vertices) {
                vertex.calculateDegrees();
            }

            //order the vertices according to the degrees
            this.orderVertex();

        } catch (FileNotFoundException e) {
            System.out.println("Path not found!\nVerify the path and try again.\nPath: " + filePath);
        }
    }

    private void orderVertex()
    {
        ArrayList<Vertex> orderedVertices = new ArrayList<>();

        for(int i = 0; i < vertices.size(); i++)
        {
            int indexGreater = 0;

            for(int j = 0; j < this.vertices.size(); j++)
            {
                if(this.vertices.get(j).getDegrees() > this.vertices.get(indexGreater).getDegrees())
                {
                    indexGreater = j;
                }
            }

            orderedVertices.add(this.vertices.get(indexGreater));
            this.vertices.remove(indexGreater);
        }

        this.vertices.addAll(orderedVertices);
    }
}