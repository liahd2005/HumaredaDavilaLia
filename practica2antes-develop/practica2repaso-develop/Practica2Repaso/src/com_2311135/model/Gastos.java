/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com_2311135.model;


public class Gastos {
    private int id;
    private String descripcion;
    private String categoria;
    private String fechaGasto;
    private double monto;

    public Gastos(Builder builder) {
        this.id= builder.id;
        this.descripcion = builder.descripcion;
        this.categoria = builder.categoria;
        this.fechaGasto = builder.fechaGasto;
        this.monto = builder.monto;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id= id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(String fecha) {
        this.fechaGasto = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    
    

    

    @Override
    public String toString() {
        return "Gastos{" + ", id"+ id +", descripcion=" + descripcion + ", categoria=" + categoria + ", fechaGasto=" + fechaGasto + ", monto=" + monto + '}';
    }
    
    public static class Builder {
        private int id;
        private String descripcion;
        private String categoria;
        private double monto;
        private String fechaGasto;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder setCategoria(String categoria) {
            this.categoria = categoria;
            return this;
        }

        public Builder setMonto(double monto) {
            this.monto = monto;
            return this;
        }

        public Builder setFechaGasto(String fechaGasto) {
            this.fechaGasto = fechaGasto;
            return this;
        }

        public Gastos build() {
            return new Gastos(this);
        }
    }
}
    
    
    