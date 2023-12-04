/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.AlimentoDAO;
import modelo.PacienteDAO;
import modelo.pojo.Alimento;
import modelo.pojo.Mensaje;
import modelo.pojo.Paciente;
import validator.PacienteValidator;

/**
 *
 * @author andre
 */
@Path("paciente")
public class PacientesWS {

    @Context
    private UriInfo context;
    
    /*@Inject
    private PacienteDAO pacienteDAO;*/

    @GET
    @Path("obtenerPorMedico/{idMedico}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> obtenerPacientePorIdMedico(@PathParam("idMedico") Integer idMedico){
      PacienteDAO dao = new PacienteDAO();
      return dao.obtenerPacientePorIdMedico(idMedico);
    }
    
    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje agregarPaciente(Paciente paciente){
        Mensaje mensaje = PacienteValidator.isValid(paciente);
        if(mensaje.isError()) {
            return mensaje;
        }
        PacienteDAO dao = new PacienteDAO();
        return dao.registrarPaciente(paciente);
    }
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarPaciente(Paciente paciente){
        Mensaje mensaje = PacienteValidator.isValid(paciente);
        if(mensaje.isError()) {
            return mensaje;
        }
        PacienteDAO dao = new PacienteDAO();
        return dao.editarPaciente(paciente);
    }
    
    @DELETE
    @Path("eliminar/{idPaciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaciente(@PathParam("idPaciente") Integer idPaciente){
        PacienteDAO dao = new PacienteDAO();
        return dao.eliminarPaciente(idPaciente);
    }
    
    @Path("registrarFoto/{idPaciente}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarFotografia(@PathParam("idPaciente") Integer idPaciente, byte[] foto){
        Mensaje msj = null;
        if (idPaciente != null && idPaciente > 0 && foto != null) {
            msj = PacienteDAO.subirFotografiaPaciente(idPaciente, foto);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return msj;
    }
    
    @Path("obtenerFoto/{idPaciente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Paciente obtenerFotografia(@PathParam("idPaciente") Integer idPaciente){
        Paciente paciente = null;
        
        if (idPaciente != null && idPaciente > 0 ) {
            paciente = PacienteDAO.obtenerFotografia(idPaciente);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return paciente;
    }
    
    @Path("dieta/{idPaciente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alimento> obtenerDietaPaciente(@PathParam("idPaciente") Integer idPaciente){
      AlimentoDAO dao = new AlimentoDAO();
      return dao.obtenerDietaPaciente(idPaciente);
    }
}
