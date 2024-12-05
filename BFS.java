/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos;

import com.mycompany.arbol_grafo_2024.Grafos.No_pesados.Grafo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author raul_
 */
public class BFS<G extends Comparable<G>> {
    private final Grafo<G> elGrafo;
    private final ControlMarcados controlMarcados;
    private final List<G> recorrido;

    public BFS(Grafo<G> unGrafo, G verticeDePartida) {
        this.elGrafo = unGrafo;
        controlMarcados = new ControlMarcados(elGrafo.cantidadDeVertice());
        recorrido = new ArrayList<>();
        ejecutarBFS(verticeDePartida);
    }

    public void ejecutarBFS(G verticeEnTurno) {
        elGrafo.validarVertice(verticeEnTurno);
        Queue<G> colaDeVertices = new LinkedList<>();
        colaDeVertices.add(verticeEnTurno);
        controlMarcados.marcarVertice(elGrafo.nroVertice(verticeEnTurno));
        while(!colaDeVertices.isEmpty()) {
            G vertice = colaDeVertices.poll();
            recorrido.add(vertice);
            Iterable<G> adyacentesDelVertice = elGrafo.getAdyacentesDelVertice(vertice);
            for (G adyacente : adyacentesDelVertice) {
                int nroDelAdyacente = elGrafo.nroVertice(adyacente);
                if (!controlMarcados.estaMarcadoVertice(nroDelAdyacente)) {
                    colaDeVertices.add(adyacente);
                    controlMarcados.marcarVertice(nroDelAdyacente);
                }
            }
        }
    }
    
    public List<G> getRecorrido() {
        return recorrido;
    }
    
    
    public boolean seVisitoVertice(G vertice) {
        elGrafo.validarVertice(vertice);
        int nroDeVertice = elGrafo.nroVertice(vertice);
        return controlMarcados.estaMarcadoVertice(nroDeVertice);
    }
    
    public boolean seVisitoTodosLosVertices() {
        return controlMarcados.estanTodosVerticesMarcados();
    }
}
