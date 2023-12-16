/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import modelo.pojo.Mensaje;
import modelo.pojo.Paciente;

/**
 *
 * @author andre
 */
public class PacienteValidator {
    private static boolean isNullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }

    public static Mensaje isValid(Paciente paciente) {

        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");

        if (isNullOrEmpty(paciente.getNombre())) {
            response.setMensaje("Nombre");
        }

        if (isNullOrEmpty(paciente.getApellidoPaterno())) {
            response.setMensaje("Apellido Paterno");
        }

        if (isNullOrEmpty(paciente.getApellidoMaterno())) {
            response.setMensaje("Apellido Materno");
        }

        if (isNullOrEmpty(paciente.getFechaNacimiento())) {
            response.setMensaje("Fecha de Nacimiento");
        }

        if (isNullOrEmpty(paciente.getSexo())) {
            response.setMensaje("Sexo");
        }

        if (isNullOrEmpty("" + paciente.getPeso())) {
            response.setMensaje("Peso");
        }

        if (isNullOrEmpty("" + paciente.getEstatura())) {
            response.setMensaje("Estatura");
        }

        if (isNullOrEmpty("" + paciente.getTallaInicial())) {
            response.setMensaje("Talla Inicial");
        }

        if (isNullOrEmpty(paciente.getEmail())) {
            response.setMensaje("Email");
        }

        if (isNullOrEmpty("" + paciente.getIdMedico())) {
            response.setMensaje("ID Medico");
        }

        if (!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() + " no puede ser vacio");
        }

        return response;
    }
}
