/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos.Pesados;

import com.mycompany.arbol_grafo_2024.Grafos.ControlMarcados;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author raul
 */
public class AlgoritmoPrim<G extends Comparable<G>> {
    private final GrafoPesado<G> elGrafo;
    private final ControlMarcados controlMarcado;

    public AlgoritmoPrim(GrafoPesado<G> grafo) {
        this.elGrafo = grafo;
        this.controlMarcado = new ControlMarcados(elGrafo.cantidadDeVertices());
    }
    
    public List<AdyacenteConPeso> calcularPrim(G verticeInicial) {
        List<AdyacenteConPeso> recorrido = new ArrayList<>();
        if (elGrafo.validarVertice(verticeInicial)) {
            int nroVertices = elGrafo.cantidadDeVertices();
            Queue<AdyacenteConPeso> colaDeVertices = new LinkedList<>();    //cola prioridad
            
            controlMarcado.marcarVertice(elGrafo.nroVertice(verticeInicial));   //marcar el verice inicial
            agregarAdyacenteALaCola(verticeInicial, colaDeVertices);    //agregar el vertice inicial
            
            while (!colaDeVertices.isEmpty()) {
                AdyacenteConPeso aristaMinima = obtenerAristaMenorPeso(colaDeVertices);
                G verticeEnTurno = elGrafo.listaDeVertices.get(aristaMinima.getIndiceVertice());
                colaDeVertices.remove(aristaMinima);
                
                int indiceDestino = aristaMinima.getIndiceVertice();
                if (!controlMarcado.estaMarcadoVertice(indiceDestino)) {
                    controlMarcado.marcarVertice(indiceDestino);
                    recorrido.add(aristaMinima);
                    agregarAdyacenteALaCola(verticeEnTurno, colaDeVertices);
                }
            }
        }
        return recorrido;
    }
    
    private void agregarAdyacenteALaCola(G vertice, Queue<AdyacenteConPeso> colaDeVertices) {
        Iterable<AdyacenteConPeso> adyacentes = elGrafo.adyacentesDelVertice(vertice);
        for (AdyacenteConPeso adyacente : adyacentes) {
            if (!controlMarcado.estaMarcadoVertice(adyacente.getIndiceVertice()))
                colaDeVertices.add(adyacente);
        }
    }

    private AdyacenteConPeso obtenerAristaMenorPeso(Queue<AdyacenteConPeso> colaDeVertices) {
        AdyacenteConPeso aristaMinima = null;
        for (AdyacenteConPeso arista : colaDeVertices) {
            if (aristaMinima == null || arista.getPeso() < aristaMinima.getPeso()) {
                aristaMinima = arista;
            }
        }
        return aristaMinima;
    }
    
}
