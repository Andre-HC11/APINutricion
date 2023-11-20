/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import modelo.pojo.Domicilio;
import modelo.pojo.Mensaje;

/**
 *
 * @author andre
 */
public class DomicilioValidator {
    private static boolean isNullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }
    
    public static Mensaje isValid(Domicilio domicilio){
        Mensaje response = new Mensaje();
        response.setError(false);
        response.setMensaje("OK");
        
        if (isNullOrEmpty(domicilio.getCalle())) {
            response.setMensaje("Calle");
        }
        
        if (isNullOrEmpty(domicilio.getNumero())) {
            response.setMensaje("Número");
        }
        
        if (isNullOrEmpty(domicilio.getColonia())) {
            response.setMensaje("Colonia");
        }
        
        if (isNullOrEmpty(domicilio.getCodigoPostal())) {
            response.setMensaje("Código Postal");
        }
        
        if (!response.getMensaje().equals("OK")) {
            response.setError(true);
            response.setMensaje(response.getMensaje() + " no puede ser vacio");
        }
        
        return response;
    }
}
