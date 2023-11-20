/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorionutricion.modelo.dao;

import clienteescritorionutricion.modelo.ConexionHTTP;
import clienteescritorionutricion.modelo.pojo.CodigoHTTP;
import clienteescritorionutricion.modelo.pojo.Domicilio;
import clienteescritorionutricion.modelo.pojo.Estado;
import clienteescritorionutricion.modelo.pojo.Mensaje;
import clienteescritorionutricion.modelo.pojo.Municipio;
import clienteescritorionutricion.utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author andre
 */
public class DomicilioDAO {
    public static List<Estado> obtenerEstados(){
        List<Estado> estados = new ArrayList<>();
        String url = Constantes.URL_WS + "categoria/listaEstados";
        CodigoHTTP  respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type tipoListaEstados = new TypeToken<List<Estado>>(){}.getType();
            estados = gson.fromJson(respuesta.getContenido(), tipoListaEstados);
        }
        return estados;
    }
    
    public static List<Municipio> obtenerMunicipiosEstado(int idEstado){
        List<Municipio> municipios = new ArrayList<>();
        String url = Constantes.URL_WS + "categoria/obtenerMunicipio/" + idEstado;
        CodigoHTTP  respuesta = ConexionHTTP.peticionGET(url);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type tipoListaMunicipios = new TypeToken<List<Municipio>>(){}.getType();
            municipios = gson.fromJson(respuesta.getContenido(), tipoListaMunicipios);
        }
        return municipios;
    }
    
    public static HashMap<String, Object> obtenerDomicilioPaciente(int idPaciente){
        HashMap<String, Object> resultado = new LinkedHashMap<>();
        String url = Constantes.URL_WS + "domicilio/paciente/" + idPaciente;
        CodigoHTTP  respuesta = ConexionHTTP.peticionGET(url);
        
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Domicilio domicilio = gson.fromJson(respuesta.getContenido(), Domicilio.class);
            resultado.put("domicilio", domicilio);
        }
        resultado.put("codigo", respuesta.getCodigoRespuesta());
        return resultado;
    }
    
    public static Mensaje registrarDomicilio(Domicilio domicilio){
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "domicilio/registrar";
        Gson gson = new Gson();
        String parametros = gson.toJson(domicilio);
        CodigoHTTP respuesta = ConexionHTTP.postRequestDomicilio(url, domicilio);
        
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
        }else{
            msj.setError(true);
            msj.setMensaje("Error al agregar el Domicilio del paciente.");
        }
        return msj;
    }
    
    public static Mensaje editarDomicilio(Domicilio domicilio){
        Mensaje msj = new Mensaje();
        CodigoHTTP respuestaWS = ConexionHTTP.putRequestDomicilio(Constantes.URL_WS + "domicilio/editar", domicilio);

        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            msj = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            msj.setError(true);
            msj.setMensaje("Error en la petición para el editar del Paciente.");
        }
        return msj;
    }
}
