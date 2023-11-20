/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import modelo.pojo.Domicilio;
import modelo.pojo.Mensaje;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author andre
 */
public class DomicilioDAO {
    
    public static Domicilio obtenerDomicilioPaciente(int idPaciente){
        Domicilio domicilio = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if (conexionDB != null) {
            try {
                domicilio = conexionDB.selectOne("domicilio.obtenerDomicilioPaciente", idPaciente);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionDB.close();
            }
        }
        return domicilio;
    }
    
    public Mensaje registrarDomicilioPaciente(Domicilio domicilio){
         
        Mensaje msj = new Mensaje();
        SqlSession conexionDB = MyBatisUtil.getSesion();
        
        if (conexionDB != null) {
            try {
                int numeroFilasAfectadas = conexionDB.insert("domicilio.registrar", domicilio);
                conexionDB.commit();
                if (numeroFilasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("El Domicilio del Paciente guardado con éxito, " + domicilio.getNumero());
                } else {
                    msj.setError(true);
                    msj.setMensaje("Lo sentimos, no se pudo registrar el Domicilio del Paciente.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("Error: " + e.getMessage());
            } finally {
                conexionDB.close();
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no hay conexión con la base de datos.");
        }

        return msj;
    }
    
    private HashMap<String, Object> toparam(Domicilio domicilio) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idDomicilio", domicilio.getIdDomicilio());
        parametros.put("calle", domicilio.getCalle());
        parametros.put("numero", domicilio.getNumero());
        parametros.put("colonia", domicilio.getColonia());
        parametros.put("codigoPostal", domicilio.getCodigoPostal());
        parametros.put("idMunicipio", domicilio.getIdMunicipio());
        parametros.put("idPaciente", domicilio.getIdPaciente());

        return parametros;
    }
    
    public Mensaje editarPaciente(Domicilio domicilio) {

        Mensaje response = new Mensaje();
        HashMap<String, Object> parametros = toparam(domicilio);
        SqlSession conn = MyBatisUtil.getSesion();
        response.setMensaje("OK");

        if (conn != null) {
            try {
                if (domicilio.getIdDomicilio()== 0) {
                    response.setMensaje("ID necesario para actualizar");
                }
                Domicilio found = conn.selectOne("domicilio.obtenerDomicilioPaciente", domicilio.getIdDomicilio());
                if (found != null) {
                    int count = conn.update("domicilio.editar", parametros);
                    conn.commit();
                    if (count > 0) {
                        response.setMensaje("El Domicilio del Paciente actualizado con éxito.");
                    } else {
                        response.setMensaje("Lo sentimos, no se pudo actualizar el Domicilio del Paciente.");
                    }
                }
            } catch (Exception e) {
                response.setError(true);
                response.setMensaje("Error: " + e.getMessage());
            } finally {
                conn.close();
            }
        } else {
            response.setMensaje("Por el momento no hay conexión con la base de datos.");
        }

        return response;
    }
}
