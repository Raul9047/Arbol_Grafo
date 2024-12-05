/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos.Pesados;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raul
 */
public class GrafoPesado <G extends Comparable<G>> {
    protected List<G> listaDeVertices;
    protected List<List<AdyacenteConPeso>> listaDeAdyacencia;
    public static final int NRO_VERTICES_INVALIDO = -1;    

    public GrafoPesado() {
        listaDeVertices = new ArrayList<G>();
        listaDeAdyacencia = new ArrayList<>();
    }

    public int nroVertice(G vertice) {
        for (int i = 0; i < listaDeVertices.size(); i++) {
            G verticeEnTurno = listaDeVertices.get(i);
            if (vertice.compareTo(verticeEnTurno) == 0)
                return i;
        }
        return NRO_VERTICES_INVALIDO;
    }
    
    public boolean validarVertice(G vertice) {
        return (nroVertice(vertice) != NRO_VERTICES_INVALIDO);
    }
    
    public void insertarVertice(G vertice) {
        if (!validarVertice(vertice)) {
            listaDeVertices.add(vertice);
            listaDeAdyacencia.add(new ArrayList<>());
        }
    }
    
    public void insertarArista(G verticeOrigen, G verticeDestino, double peso) {
        if (validarVertice(verticeOrigen) && validarVertice(verticeDestino)) {
            int verOrigen = nroVertice(verticeOrigen);
            int verDestino = nroVertice(verticeDestino);
            List<AdyacenteConPeso> adyacentesOrigen = listaDeAdyacencia.get(verOrigen);

            AdyacenteConPeso arista = new AdyacenteConPeso(verDestino, peso);
            if (!adyacentesOrigen.contains(arista)) {
                adyacentesOrigen.add(arista);
            }

            if (verOrigen != verDestino) {      // No agregar dos veces si es un lazo
                List<AdyacenteConPeso> adyacentesDestino = listaDeAdyacencia.get(verDestino);
                AdyacenteConPeso aristaInversa = new AdyacenteConPeso(verOrigen, peso);
                if (!adyacentesDestino.contains(aristaInversa)) {
                    adyacentesDestino.add(aristaInversa);
                }
            }
        }
    }
    
    public boolean existeAdyacencia(G verticeOrigen, G verticeDestino) {
        if (validarVertice(verticeOrigen) && validarVertice(verticeDestino)) {
            int nroDelVerticeOrigen = this.nroVertice(verticeOrigen);
            int nroDelVerticeDestino = this.nroVertice(verticeDestino);
            List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencia.get(nroDelVerticeOrigen);
            return adyacentesDelVerticeOrigen.contains(nroDelVerticeDestino);
        }
        return false;
    }    
    
    public List<AdyacenteConPeso> adyacentesDelVertice(G vertice) {
        List<AdyacenteConPeso> adyacentes = new ArrayList<>();
        if (validarVertice(vertice)) {
            int nroVertice = nroVertice(vertice);
            for (AdyacenteConPeso adyacente : listaDeAdyacencia.get(nroVertice)) {
                adyacentes.add(adyacente);
            }
        }
        return adyacentes;
    }
    
    public int cantidadDeVertices() {
        return listaDeVertices.size();
    }
    
    public int cantidadDeAristas() {
        int cantAristas = 0;
        int cantLazos = 0;
        for (int i = 0; i < listaDeAdyacencia.size(); i++) {
            List<AdyacenteConPeso> adyacentes = listaDeAdyacencia.get(i);
            for (AdyacenteConPeso adyacenteEnTurno : adyacentes) {
                if (adyacenteEnTurno.getIndiceVertice() == i)
                    cantLazos++;
                else
                    cantAristas++;
            }
        }
        return (cantAristas/2 + cantLazos);
    }
    
    public void eliminarArista(G verticeOrigen, G verticeDestino) {
        if (existeAdyacencia(verticeOrigen, verticeDestino)) {
            int nroVerticeOrigen = nroVertice(verticeOrigen);
            int nroVerticeDestino = nroVertice(verticeDestino);
            
            //  eliminar la arista el origen al destino
            List<AdyacenteConPeso> adyacentesDelOrigen = listaDeAdyacencia.get(nroVerticeOrigen);
            adyacentesDelOrigen.remove(nroVerticeDestino);
            
            if (nroVerticeOrigen != nroVerticeDestino) {
                List<AdyacenteConPeso> adyacentesDelDestino = listaDeAdyacencia.get(nroVerticeDestino);
                adyacentesDelDestino.remove(nroVerticeOrigen);
            }
        }
    }    
    
    public void eliminarVertice(G verticeAEliminar) {
        if (validarVertice(verticeAEliminar)) {
            int nroDelVertice = this.nroVertice(verticeAEliminar);
            
            //  eliminar el vertice de las listas principales
            listaDeVertices.remove(nroDelVertice);
            listaDeAdyacencia.remove(nroDelVertice);
            
            //  actualiza las referencias en las lineas de adyacencias
            for (List<AdyacenteConPeso> adyacentesDeUnVertice : this.listaDeAdyacencia) {
                adyacentesDeUnVertice.remove(nroDelVertice);
                
                //  actualizar indices mayores al vertice eliminado
                for (AdyacenteConPeso adyacente : adyacentesDeUnVertice) {
                    if (adyacente.getIndiceVertice() > nroDelVertice) {
                        adyacente.setIndiceVertice(adyacente.getIndiceVertice() -1);

                    }
                }
            }
        }
    }    
    
    public int gradoDelVertice(G vertice) {
        if (validarVertice(vertice)) {
            int nroDelVertice = this.nroVertice(vertice);
            return this.listaDeAdyacencia.get(nroDelVertice).size();
        }
        return 0;
    }
}


