/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojo;

/**
 *
 * @author andre
 */
public class Alimento {
    private int idPaciente;
    private String alimento;
    private int caloriasPorPorcion;
    private int cantidad;
    private String porcion;

    public Alimento() {
    }

    public Alimento(int idPaciente, String alimento, int caloriasPorPorcion, int cantidad, String porcion) {
        this.idPaciente = idPaciente;
        this.alimento = alimento;
        this.caloriasPorPorcion = caloriasPorPorcion;
        this.cantidad = cantidad;
        this.porcion = porcion;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public int getCaloriasPorPorcion() {
        return caloriasPorPorcion;
    }

    public void setCaloriasPorPorcion(int caloriasPorPorcion) {
        this.caloriasPorPorcion = caloriasPorPorcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPorcion() {
        return porcion;
    }

    public void setPorcion(String porcion) {
        this.porcion = porcion;
    }
}
