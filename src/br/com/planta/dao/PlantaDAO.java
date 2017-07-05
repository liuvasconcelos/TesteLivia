package br.com.planta.dao;

import br.com.planta.factory.ConnectionFactory;
import br.com.planta.model.Planta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PlantaDAO {
    
    Connection conexao = null;
    
    public List<Planta> buscaTipo(String tipo){
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "select * from teste5.planta where tipo = ? order by nome";
        
        List<Planta> lista = new ArrayList<>();
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Planta p = new Planta();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome").toUpperCase());
                p.setTipo(rs.getString("tipo"));
                p.setValor(rs.getDouble("valor"));
                
                lista.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }return lista;
    }
    
    
     public List<Planta> buscaNome(String nome){
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "select * from teste5.planta where upper(nome) like upper(?) order by nome";
        
        List<Planta> lista = new ArrayList<>();
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%"+nome+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Planta p = new Planta();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome").toUpperCase());
                p.setTipo(rs.getString("tipo"));
                p.setValor(rs.getDouble("valor"));
                
                lista.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }return lista;
    }
    
    public boolean delPlanta(Planta p){
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "delete from teste5.planta where id=?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ps.setInt(1, p.getId());
            
            ps.execute();
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }return false;
    }
    
    public boolean altPlanta(Planta p){
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "update teste5.planta set nome=?, tipo=?, valor=? where id=?";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getTipo());
            ps.setDouble(3, p.getValor());
            ps.setInt(4, p.getId());
            
            ps.executeUpdate();
            
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }return false;
    }
    
    public boolean cadPlanta(Planta p){
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "insert into teste5.planta (nome, tipo, valor) values (?,?,?)";
        
        try {
        	
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getTipo());
            ps.setDouble(3, p.getValor());
            
            ps.execute();
            
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List<Planta> listaPlanta(){
        
        conexao = ConnectionFactory.getConnection();
        
        String sql = "select * from teste5.planta order by nome";
        
        List<Planta> lista = new ArrayList<>();
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Planta p = new Planta();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setTipo(rs.getString("tipo"));
                p.setValor(rs.getDouble("valor"));
                
                lista.add(p);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                conexao.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }return lista;
    }
    
}
