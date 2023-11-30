package com.ferreteria.Modelo;

public class ResumenVentas {

    private double precioTotal;
    private int totalVentas;

    public ResumenVentas(double precioTotal, int totalVentas) {
        this.precioTotal = precioTotal;
        this.totalVentas = totalVentas;
    }

    public ResumenVentas() {
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }
}
