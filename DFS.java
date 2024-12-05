/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos;

import com.mycompany.arbol_grafo_2024.Grafos.No_pesados.Grafo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raul_
 */
public class DFS <G extends Comparable<G>> {
    private final Grafo<G> elGrafo;
    private final ControlMarcados controlMarcados;
    private final List<G> recorrido;
    
    public DFS(Grafo<G> unGrafo, G verticePartida) {
        elGrafo = unGrafo;
        controlMarcados = new ControlMarcados(elGrafo.cantidadDeVertice());
        recorrido = new ArrayList<>();
        ejecutarDFS(verticePartida);
    }

    private void ejecutarDFS(G verticeEnTurno) {
        elGrafo.validarVertice(verticeEnTurno);
        controlMarcados.marcarVertice(elGrafo.nroVertice(verticeEnTurno));
        recorrido.add(verticeEnTurno);
        Iterable<G> adyacentesDelVerice = elGrafo.getAdyacentesDelVertice(verticeEnTurno);
        for (G adyacente : adyacentesDelVerice) {
            int nroDelAdyacente = elGrafo.nroVertice(adyacente);
            if (!controlMarcados.estaMarcadoVertice(nroDelAdyacente)) {
                ejecutarDFS(adyacente);
            }
        }
    }
    
    public List<G> getRecorrido() {
        return recorrido;
    }
    
    public boolean seVisitoVertice(G vertice) {
        elGrafo.validarVertice(vertice);
        int nroDelVertice = elGrafo.nroVertice(vertice);
        return controlMarcados.estaMarcadoVertice(nroDelVertice);
    }
    
    public boolean seVisitoTodosLosVertices() {
        return controlMarcados.estanTodosVerticesMarcados();
    }
}
