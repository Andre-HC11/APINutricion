/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import modelo.pojo.Alimento;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author andre
 */
public class AlimentoDAO {
    public List<Alimento> obtenerDietaPaciente(Integer idPaciente) {
        List<Alimento> alimento = null;
        SqlSession conexionDB = MyBatisUtil.getSesion();

        if (conexionDB != null) {
            try {
                alimento = conexionDB.selectList("dieta.obtenerDietaPaciente", idPaciente);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.close();
            }
        }
        return alimento;
    }
}
