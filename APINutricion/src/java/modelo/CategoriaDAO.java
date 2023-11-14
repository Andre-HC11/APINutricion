/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.pojo.Estado;
import modelo.pojo.Municipio;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author andre
 */
public class CategoriaDAO {
    public List<Estado> obtenerListaEstado() {
        List<Estado> estado = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();

        if (conexionDB != null) {
            try {
                estado = conexionDB.selectList("estado.listarEstado");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return estado;
    }
    
    public List<Municipio> obtenerMunicipio(Integer idEstado) {
        List<Municipio> paciente = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();

        if (conexionDB != null) {
            try {
                paciente = conexionDB.selectList("estado.obtenerMunicipio", idEstado);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return paciente;
    }
}
