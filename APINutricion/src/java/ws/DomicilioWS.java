/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.awt.MediaTracker;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.DomicilioDAO;
import modelo.pojo.Domicilio;
import modelo.pojo.Mensaje;
import validator.DomicilioValidator;

/**
 *
 * @author andre
 */
@Path("domicilio")
public class DomicilioWS {
    
    @Path("paciente/{idPaciente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Domicilio obtenerDomicilioPaciente(@PathParam ("idPaciente") Integer idPaciente) {
        if(idPaciente != null && idPaciente > 0){
            return DomicilioDAO.obtenerDomicilioPaciente(idPaciente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarDomicilio(Domicilio domicilio){
        Mensaje mensaje = DomicilioValidator.isValid(domicilio);
        if(mensaje.isError()) {
            return mensaje;
        }
        DomicilioDAO dao = new DomicilioDAO();
        return dao.registrarDomicilioPaciente(domicilio);
    }
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarDomicilio(Domicilio domicilio){
        Mensaje mensaje = DomicilioValidator.isValid(domicilio);
        if(mensaje.isError()) {
            return mensaje;
        }
        DomicilioDAO dao = new DomicilioDAO();
        return dao.editarPaciente(domicilio);
    }
}
