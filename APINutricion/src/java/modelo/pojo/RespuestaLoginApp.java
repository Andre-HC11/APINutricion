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
public class RespuestaLoginApp {
    private boolean error;
    private String contenido;
    private Paciente pacienteSesion;

    public RespuestaLoginApp() {
    }

    public RespuestaLoginApp(boolean error, String contenido, Paciente pacienteSesion) {
        this.error = error;
        this.contenido = contenido;
        this.pacienteSesion = pacienteSesion;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Paciente getPacienteSesion() {
        return pacienteSesion;
    }

    public void setPacienteSesion(Paciente pacienteSesion) {
        this.pacienteSesion = pacienteSesion;
    }
}
