/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos.No_pesados;

/**
 *
 * @author raul
 */
public class Warshall<G extends Comparable<G>> {
    private Grafo<G> miGrafo;
    private int [][] matriz;
    
    public Warshall(Grafo<G> unGrafo) {
        this.miGrafo = unGrafo;
        this.matriz = new int[miGrafo.cantidadDeVertice()][miGrafo.cantidadDeVertice()];
        for (int i = 0; i < miGrafo.cantidadDeVertice(); i++) {
            for (int j = 0; j < miGrafo.cantidadDeVertice(); j++) 
                matriz[i][j] = 0;
        }
        ejecutarWarshall();
    }
    
    private void ejecutarWarshall() {
        int nroVertices = miGrafo.cantidadDeVertice();
        for (int i = 0; i < nroVertices; i++) {
            for (int j = 0; j < nroVertices; j++) {
                matriz[i][j] = 0;
            }
        }
        
        for (int i = 0; i < nroVertices ; i++) {
            G vertice = miGrafo.listaDeVertices.get(i);
            Iterable<G> lista = miGrafo.getAdyacentesDelVertice(vertice);
            for (G elemento : lista) {
                int nroVertice = miGrafo.nroVertice(elemento);
                matriz[i][nroVertice] = 1;
            }
        }
        
        for (int k = 0; k < nroVertices; k++) {
            for (int i = 0; i < nroVertices; i++) {
                for (int j = 0; j < nroVertices; j++)
                    matriz[i][j] = matriz[i][j] | (matriz[i][k] & matriz[k][j]);
            }
        }
    }
    
    public boolean existeCamino(G origen, G destino) {
        int verticeOrigen = miGrafo.nroVertice(origen);
        int verticeDestino = miGrafo.nroVertice(destino);
        if (verticeOrigen == Grafo.NRO_VERTICES_INVALIDO || verticeDestino == Grafo.NRO_VERTICES_INVALIDO)
            throw new IllegalArgumentException("Uno o ambos vertices no existen");
        return matriz[verticeOrigen][verticeDestino] == 1;
    }
    
    public boolean hayCiclos() {
        int nroVertices = miGrafo.cantidadDeVertice();
        for (int i = 0; i < nroVertices; i++) {
            if (matriz[nroVertices][nroVertices] == 1)
                return true;
        }
        return false;
    }
}
