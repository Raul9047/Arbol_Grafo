/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos.Pesados;

import java.util.List;

/**
 *
 * @author raul
 */
public class FloydWarshall<G extends Comparable<G>> {
    private final GrafoPesado<G> elGrafo;
    private final double [][] distancia;
    private final int [][] predecesores;
    
    private static final double INFINITO = Double.MAX_VALUE;

    public FloydWarshall(GrafoPesado<G> elGrafo) {
        this.elGrafo = elGrafo;
        int cantidadDeVertices = elGrafo.cantidadDeVertices();
        distancia = new double[cantidadDeVertices][cantidadDeVertices];
        predecesores = new int[cantidadDeVertices][cantidadDeVertices];
        inicializarMatrices ();
    }
    
    private void inicializarMatrices() {
        int nroVertices = elGrafo.cantidadDeVertices();
        for (int i = 0; i < nroVertices; i++) {
            for (int j = 0; j < nroVertices; j++) {
                if (i == j) {
                    distancia[i][j] = 0;
                    predecesores[i][j] = i;
                }
                else
                {
                    List<AdyacenteConPeso> adyacentes = elGrafo.listaDeAdyacencia.get(i);
                    AdyacenteConPeso arista = buscarAdyacente(adyacentes, j);     //  colocar el peso de cada arista
                    if (arista != null) {
                        distancia[i][j] = arista.getPeso();
                        predecesores[i][j] = i;
                    }
                    else {
                        distancia[i][j] = INFINITO;
                        predecesores[i][j] = -1;
                    }
                }
            }
        }
    }

    private AdyacenteConPeso buscarAdyacente(List<AdyacenteConPeso> adyacentes, int indiceDestino) {
        for (AdyacenteConPeso adyacente :adyacentes) {
            if (adyacente.getIndiceVertice() == indiceDestino) {
                return adyacente;
            }
        }
        return null;
    }
    
    
    public void ejecutarFloydWarshall() {
        int nroVertices = elGrafo.cantidadDeVertices();
        for (int k = 0; k < nroVertices; k++) {
            for (int i = 0; i < nroVertices; i++) {
                for (int j = 0; j < nroVertices; j++) {
                    if (distancia[i][k] != INFINITO && distancia[k][j] != INFINITO) {
                        if (distancia[i][j] > (distancia[i][k] + distancia[k][j])) {
                            distancia[i][j] = distancia[i][k] + distancia[k][j];
                            predecesores[i][j] = predecesores[k][j];
                        }
                    }
                }
            }
        }
    }

    public double obtenerDistanciaMinima(G verticeOrigen, G verticeDestino) {
        int nroVerticeOrigen = elGrafo.nroVertice(verticeOrigen);
        int nroVerticeDestino = elGrafo.nroVertice(verticeDestino);
        return distancia[nroVerticeOrigen][nroVerticeDestino];
    }
    
}
