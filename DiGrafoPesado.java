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
public class DiGrafoPesado <G extends Comparable<G>> extends GrafoPesado<G>{

    public DiGrafoPesado() {
        super();
    }

    @Override
    public void insertarArista(G verticeOrigen, G verticeDestino, double peso) {
        // Insertar una arista dirigida de verticeOrigen a verticeDestino con un peso específico
        if (validarVertice(verticeOrigen) && validarVertice(verticeDestino)) {
            int indiceOrigen = nroVertice(verticeOrigen);
            int indiceDestino = nroVertice(verticeDestino);
            List<AdyacenteConPeso> adyacentesOrigen = listaDeAdyacencia.get(indiceOrigen);

            AdyacenteConPeso nuevaArista = new AdyacenteConPeso(indiceDestino, peso);
            if (!adyacentesOrigen.contains(nuevaArista)) {
                adyacentesOrigen.add(nuevaArista);
            }
        }
    }

    @Override
    public void eliminarArista(G verticeOrigen, G verticeDestino) {
        // Eliminar únicamente la arista dirigida de verticeOrigen a verticeDestino
        if (validarVertice(verticeOrigen) && validarVertice(verticeDestino)) {
            int indiceOrigen = nroVertice(verticeOrigen);
            int indiceDestino = nroVertice(verticeDestino);
            List<AdyacenteConPeso> adyacentesOrigen = listaDeAdyacencia.get(indiceOrigen);

            adyacentesOrigen.removeIf(adyacente -> adyacente.getIndiceVertice() == indiceDestino);
        }
    }

    @Override
    public int cantidadDeAristas() {
        // Contar las aristas considerando que son dirigidas
        int cantidadAristas = 0;
        for (List<AdyacenteConPeso> adyacentes : listaDeAdyacencia) {
            cantidadAristas += adyacentes.size();
        }
        return cantidadAristas;
    }

    public int gradoSalidaDelVertice(G vertice) {
        // Grado de salida: cuántas aristas salen de este vértice
        if (validarVertice(vertice)) {
            int indiceVertice = nroVertice(vertice);
            return listaDeAdyacencia.get(indiceVertice).size();
        }
        return 0;
    }

    public int gradoEntradaDelVertice(G vertice) {
        // Grado de entrada: cuántas aristas apuntan hacia este vértice
        if (validarVertice(vertice)) {
            int indiceVertice = nroVertice(vertice);
            int gradoEntrada = 0;
            for (List<AdyacenteConPeso> adyacentes : listaDeAdyacencia) {
                for (AdyacenteConPeso adyacente : adyacentes) {
                    if (adyacente.getIndiceVertice() == indiceVertice) {
                        gradoEntrada++;
                    }
                }
            }
            return gradoEntrada;
        }
        return 0;
    }

    @Override
    public int gradoDelVertice(G vertice) {
        // Grado total: suma del grado de entrada y salida
        return gradoEntradaDelVertice(vertice) + gradoSalidaDelVertice(vertice);
    }
}