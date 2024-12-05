/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbol_grafo_2024.Grafos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raul_
 */
public class ControlMarcados {
    private final List<Boolean> listaDeMarcados;

    public ControlMarcados(int nroVertice) {
        this.listaDeMarcados = new ArrayList<>();
        for (int i = 0; i < nroVertice; i++) {
            listaDeMarcados.add(Boolean.FALSE);
        }
    }
    
    public void descarmarTodosVertices() {
        for (int i = 0; i < listaDeMarcados.size(); i++) {
            listaDeMarcados.add(Boolean.FALSE);
        }
    }

    public boolean estaMarcadoVertice(int nroVertice) {
        return listaDeMarcados.get(nroVertice);
    }

    public void marcarVertice(int nroVertice) {
        listaDeMarcados.set(nroVertice, true);
    }

    public void desmarcarVertice(int nroVertice) {
        listaDeMarcados.set(nroVertice, false);
    }

    public boolean estanTodosVerticesMarcados() {
        return listaDeMarcados.contains(Boolean.FALSE);
    }
}

