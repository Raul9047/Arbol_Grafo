    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.arbol_grafo_2024.Grafos.Pesados;

    /**
     *
     * @author raul
     */

    public class AdyacenteConPeso implements Comparable<AdyacenteConPeso>{
        private int indiceVertice;
        private double peso;

        @Override
        public int compareTo(AdyacenteConPeso vertice) {
            Integer esteVertice = indiceVertice;
            Integer elOtroVertice = vertice.indiceVertice;
            return esteVertice.compareTo(elOtroVertice);
        }

        public AdyacenteConPeso(int indiceVertice) {
            this.indiceVertice = indiceVertice;
        }

        public AdyacenteConPeso(int indiceVertice, double peso) {
            this.indiceVertice = indiceVertice;
            this.peso = peso;
        }

        public int getIndiceVertice() {
            return indiceVertice;
        }

        public double getPeso() {
            return peso;
        }

        public void setIndiceVertice(int indiceVertice) {
            this.indiceVertice = indiceVertice;
        }

        public void setPeso(double peso) {
            this.peso = peso;
        }

        @Override
        public int hashCode() {
            int hast = 3;
            hast = 67 * hast + this.indiceVertice;
            return hast;
        }

        @Override
        public boolean equals(Object otro) {
            if (otro == null)
                return false;
            if (getClass() != otro.getClass())
                return false;
            AdyacenteConPeso other = (AdyacenteConPeso)otro;
            return this.indiceVertice == other.indiceVertice;
        }
    }
