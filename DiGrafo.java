/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos.No_pesados;

import com.mycompany.arbol_grafo_2024.Excepciones.AristaNoExisteException;
import com.mycompany.arbol_grafo_2024.Excepciones.AristaYaExisteException;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author raul_
 */
public class DiGrafo <G extends Comparable<G>> extends Grafo<G>{

    public DiGrafo() {
        super();
    }

    public DiGrafo(Iterable<G> vertices) {
        super(vertices);
    }

    @Override
    public int gradoDelVertice(G vertice) {
        throw new UnsupportedOperationException("Operación No Soportada En Este Tipo De Grafos");
    }
    
    public int gradoDeSalidaDeUnVertice(G vertice) {
        return super.gradoDelVertice(vertice);
    }
    
    public int gradoDeEntradaDeUnVertice(G vertice) {
        super.validarVertice(vertice);
        int nroDelVertice = nroVertice(vertice);
        int cantidadDeLlegada = 0;
        
        return cantidadDeLlegada;
    }
    

    @Override
    public void eliminarArista(G verticeOrigen, G verticeDestino) throws AristaNoExisteException {
        if (this.existeAdyacencia(verticeOrigen, verticeDestino))
            throw new AristaNoExisteException();
        int nroVerticeOrigen = nroVertice(verticeOrigen);
        int nroVerticeDestino = nroVertice(verticeDestino);
        List<Integer> adyacenteDelOrigen = listaDeAdyacencias.get(nroVerticeOrigen);
        adyacenteDelOrigen.remove(nroVerticeDestino);
    }

    @Override
    public void insertarArista(G verticeOrigen, G verticeDestino) throws AristaYaExisteException {
        this.validarVertice(verticeOrigen);
        this.validarVertice(verticeDestino);
        if (super.existeAdyacencia(verticeOrigen, verticeDestino))
            throw new AristaYaExisteException();
        int nroVerticeOrigen = nroVertice(verticeOrigen);
        int nroVerticeDestino = nroVertice(verticeDestino);
        List<Integer> adyacentesDelOrigen = super.listaDeAdyacencias.get(nroVerticeOrigen);
        adyacentesDelOrigen.add(nroVerticeDestino);
        Collections.sort(adyacentesDelOrigen);
    }

    @Override
    public int cantidadDeAristas() {
        int cantidadAristas = 0;
        int cantidadDeLazos = 0;
        for (int i = 0; i < listaDeVertices.size(); i++) {
            List<Integer> listaDeAdyacencias = this.listaDeAdyacencias.get(i);
            for (Integer adyacente : listaDeAdyacencias) {
                if (adyacente == i)
                    cantidadDeLazos++;
                else
                    cantidadAristas++;
            }
        }
        cantidadAristas += cantidadDeLazos;
        return cantidadAristas;
    }
}
