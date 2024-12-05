/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos.No_pesados;

import com.mycompany.arbol_grafo_2024.Excepciones.AristaNoExisteException;
import com.mycompany.arbol_grafo_2024.Excepciones.AristaYaExisteException;
import com.mycompany.arbol_grafo_2024.Excepciones.VerticeNoExisteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author raul_
 */
public class Grafo <G extends Comparable<G>>{
    protected List<G> listaDeVertices;
    protected List<List<Integer>> listaDeAdyacencias;
    public static final int NRO_VERTICES_INVALIDO = -1;
    
    public Grafo() {
        this.listaDeVertices = new ArrayList<G>();
        this.listaDeAdyacencias = new ArrayList<>();
    }
    
    public Grafo(Iterable<G> vertices) {
        this();
        for (G verticeEnTurno : vertices)
            this.insertarVertice(verticeEnTurno);
    }
    
    public int nroVertice(G vertice) {
        for (int i = 0;  i < this.listaDeVertices.size(); i++) {
            G verticeEnTurno = this.listaDeVertices.get(i);
            if (vertice.compareTo(verticeEnTurno) == 0)
                return i;
        }
        return NRO_VERTICES_INVALIDO;
    }
    
    public boolean validarVertice(G vertice) {
        int nroVertice = this.nroVertice(vertice);
        if (nroVertice == NRO_VERTICES_INVALIDO)
            return false;
        return true;
    }
    
    public void insertarVertice(G vertice) {
        if (!validarVertice(vertice)) {
            this.listaDeVertices.add(vertice);
            this.listaDeAdyacencias.add(new ArrayList<>());
        }
    }
    
    public int cantidadDeVertice() {
        return this.listaDeVertices.size();
    }
    
    public int cantidadDeAristas() {
        int cantidadAristas = 0;
        int cantidadDeLazos = 0;
        for (int i = 0; i < this.listaDeVertices.size(); i++) {
            List<Integer> listaDeAdyacentesAlVertice = this.listaDeAdyacencias.get(i);
            for (Integer adyacente : listaDeAdyacentesAlVertice) {
                if (adyacente == i)
                    cantidadDeLazos++;
                else
                    cantidadAristas++;
            }
        }
        cantidadAristas = (cantidadAristas / 2) + cantidadDeLazos;
        return cantidadAristas;
    }
    
    public Iterable<G> getVertices() {
        return this.listaDeVertices;
    }
    
    public Iterable<G> getAdyacentesDelVertice(G vertice) {
        this.validarVertice(vertice);
        int nroDelVertice = this.nroVertice(vertice);
        List<Integer> adyacentesDelVerticePorNumero = listaDeAdyacencias.get(nroDelVertice);
        List<G> listaDeAdyacentesDelVertice = new ArrayList<>();
        for (Integer nroVerticeEnTurno : adyacentesDelVerticePorNumero)
            listaDeAdyacentesDelVertice.add(listaDeVertices.get(nroVerticeEnTurno));
        return listaDeAdyacentesDelVertice;
    }
    
    public boolean existeAdyacencia(G verticeOrigen, G verticeDestino) {
        if (validarVertice(verticeOrigen) && validarVertice(verticeDestino)) {
            int nroDelVerticeOrigen = this.nroVertice(verticeOrigen);
            int nroDelVerticeDestino = this.nroVertice(verticeDestino);
            List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(nroDelVerticeOrigen);
            return adyacentesDelVerticeOrigen.contains(nroDelVerticeDestino);
        }
        return false;
    }
    
    public void insertarArista(G verticeOrigen, G verticeDestino) throws AristaYaExisteException {
        if (existeAdyacencia(verticeOrigen, verticeDestino))
            throw new AristaYaExisteException();
        int nroDelVerticeOrigen = this.nroVertice(verticeOrigen);
        int nroDelVerticeDestino = this.nroVertice(verticeDestino);
        List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(nroDelVerticeOrigen);
        adyacentesDelOrigen.add(nroDelVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
        if (nroDelVerticeOrigen != nroDelVerticeDestino) {
            List<Integer> adyacentesDelDestino = this.listaDeAdyacencias.get(nroDelVerticeDestino);
            adyacentesDelDestino.add(nroDelVerticeOrigen);
            Collections.sort(adyacentesDelDestino);
        }
    }
    
    public void eliminarVertice(G verticeAEliminar) throws VerticeNoExisteException{
        if (validarVertice(verticeAEliminar)) {
            int nroDelVertice = this.nroVertice(verticeAEliminar);
            listaDeVertices.remove(nroDelVertice);
            listaDeAdyacencias.remove(nroDelVertice);
            for (List<Integer> adyacentesDeUnVertice : this.listaDeAdyacencias) {
                adyacentesDeUnVertice.remove(nroDelVertice);
                for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                    int nroAdyacenteEnTurno = adyacentesDeUnVertice.get(i);
                    if (nroAdyacenteEnTurno > nroDelVertice) {
                        nroAdyacenteEnTurno--;
                        adyacentesDeUnVertice.set(i, nroAdyacenteEnTurno);

                    }
                }
            }
        }
    }
    
    public void eliminarArista(G verticeOrigen, G verticeDestino) throws AristaNoExisteException {
        if (!existeAdyacencia(verticeOrigen, verticeDestino))
            throw new AristaNoExisteException();
        int nroVerticeOrigen = nroVertice(verticeOrigen);
        int nroVerticeDestino = nroVertice(verticeDestino);
        List<Integer> adyacentesDelOrigen = listaDeAdyacencias.get(nroVerticeOrigen);
        List<Integer> adyacentesDelDestino = listaDeAdyacencias.get(nroVerticeDestino);
        adyacentesDelOrigen.remove(nroVerticeDestino);
        adyacentesDelDestino.remove(nroVerticeOrigen);
    }
    
    public int gradoDelVertice(G vertice) {
        if (validarVertice(vertice)) {
            int nroDelVertice = this.nroVertice(vertice);
            return this.listaDeAdyacencias.get(nroDelVertice).size();
        }
        return 0;
    }
}
