/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos.No_pesados;

import com.mycompany.arbol_grafo_2024.Grafos.Conexos.DebilmenteConexo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author raul
 */
public class OrdenamientoTopologico<G extends Comparable<G>> {
    private DiGrafo<G> miGrafo;

    public OrdenamientoTopologico(DiGrafo<G> miGrafo) {
        this.miGrafo = miGrafo;
    }
    
    public List<G> ordenamiento () {
        Warshall<G> verificar = new Warshall(miGrafo);
        DebilmenteConexo debilmente = new DebilmenteConexo(miGrafo);
        if (verificar.hayCiclos() || !debilmente.esDebilmenteConexoDiGrafo())
            throw new IllegalArgumentException("No se puede este Ordenamiento");
        List<G> ordenado = new ArrayList<>();
        int [] gradosDeEntrada = new int [miGrafo.cantidadDeVertice()];
        for (int i = 0; i < miGrafo.cantidadDeVertice(); i++) {
            G verticeTurno = miGrafo.listaDeVertices.get(i);
            gradosDeEntrada[i]  = miGrafo.gradoDeEntradaDeUnVertice(verticeTurno);
        }
        Queue<G>  colaDeVertices = new LinkedList<>();
        for (int i = 0; i < miGrafo.cantidadDeVertice(); i++) {
            if (gradosDeEntrada[i] == 0) {
                G verticeEnTurno = miGrafo.listaDeVertices.get(i);
                colaDeVertices.offer(verticeEnTurno);
            }
        }
        while(!colaDeVertices.isEmpty()) {
            G verticeEnTurno = colaDeVertices.poll();
            ordenado.add(verticeEnTurno);
            for (G verticeSiguiente : miGrafo.getAdyacentesDelVertice(verticeEnTurno)) {
                int nroVerticeSiguiente = miGrafo.nroVertice(verticeSiguiente);
                gradosDeEntrada[nroVerticeSiguiente]--;
                if (gradosDeEntrada[nroVerticeSiguiente] == 0)
                    colaDeVertices.offer(verticeSiguiente);
            }
        }
        return ordenado;
    }
}
