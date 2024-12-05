/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos.Pesados;

import com.mycompany.arbol_grafo_2024.Grafos.ControlMarcados;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author raul
 */
public class AlgritmoDikjstra<G extends Comparable<G>> {
    private final DiGrafoPesado<G> elGrafo;
    private final double[] costos;
    private final int[] predecesores;
    private final ControlMarcados controlMarcados;
    
    private static final double INFINITO = Double.MAX_VALUE;

    public AlgritmoDikjstra(DiGrafoPesado<G> elGrafo) {
        this.elGrafo = elGrafo;
        int nroVertices = elGrafo.cantidadDeVertices();
        this.costos = new double[nroVertices];
        this.predecesores = new int[nroVertices];
        controlMarcados = new ControlMarcados(nroVertices);
        
        Arrays.fill(costos, INFINITO);
        Arrays.fill(predecesores, -1);
    }

    private void ejecutarDikjstra(G verticeInicial) {
        int vertInicial = elGrafo.nroVertice(verticeInicial);
        costos[vertInicial] = 0;
                
        for (int i = 0; i < elGrafo.cantidadDeVertices(); i++) {
            int verticeActual = adyacenteConAristaMinima();
            if (verticeActual == -1)
                break;
            controlMarcados.marcarVertice(verticeActual);
            List<AdyacenteConPeso> adyacentes = elGrafo.listaDeAdyacencia.get(i);
            for (AdyacenteConPeso adyacente : adyacentes) {
                int vecino = adyacente.getIndiceVertice();
                double pesoArista = adyacente.getPeso();
                
                if (!controlMarcados.estaMarcadoVertice(vecino)) {
                    double nuevoCosto = costos[verticeActual] + pesoArista;
                    if (nuevoCosto < costos[vecino]) {
                        costos[vecino] = nuevoCosto;
                        predecesores[vecino] = verticeActual;
                    }
                }
            }
        }
    }
    
    private int adyacenteConAristaMinima() {
        double minimo = INFINITO;
        int indiceMinimo = -1;
        for (int i = 0; i < costos.length; i++) {
            if (!controlMarcados.estaMarcadoVertice(i) && costos[i] < minimo) {
                minimo = costos[i];
                indiceMinimo = i;
            }
        }
        return indiceMinimo;
    }
    
    
}

