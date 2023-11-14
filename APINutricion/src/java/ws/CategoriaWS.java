/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.CategoriaDAO;
import modelo.pojo.Estado;
import modelo.pojo.Municipio;

/**
 *
 * @author andre
 */

@Path("categoria")
public class CategoriaWS {
    @Path("listaEstados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> obtenerListaEstado(){
        CategoriaDAO dao = new CategoriaDAO();
      return dao.obtenerListaEstado();
    }
    
    @GET
    @Path("obtenerMunicipio/{idEstado}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Municipio> obtenerMunicipioEstado(@PathParam("idEstado") Integer idEstado){
      CategoriaDAO dao = new CategoriaDAO();
      return dao.obtenerMunicipio(idEstado);
    }
}
