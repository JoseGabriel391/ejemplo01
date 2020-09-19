/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jose.romero
 */
public class Controlador {
    
    private Connection con;
    
    private void abrirConexion(){
        try{
            con = DriverManager.getConnection("jdbc:sqlserver://SOPINV-78\\SQLEXPRESS:1433;databaseName=Visitas", "sa", "sa123");
            
        }catch(SQLException ex){}
    }
    private void cerrarConexion(){
        try{
            con.close();
        }catch(SQLException ex){}
    }
    
    public boolean insertarVisita(Visita v){
        boolean inserto = true;
        
        try{
            abrirConexion();
            String sql = "insert into visitas(idPaciente, legajoRecepcionista, nombre, duracion) values(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setInt(1, v.getIdPaciente().getId());
            st.setInt(2, v.getLegajoRecepcionista().getLegajo());
            st.setString(3, v.getNombre());
            st.setInt(4, v.getDuracion());
            st.execute();
            inserto = true;
            
        }catch(SQLException ex){ex.printStackTrace();}
        finally{
            cerrarConexion();
        }
        return inserto;
        
    }
    
       public ArrayList<Paciente> obtenerPaciente(){
           ArrayList<Paciente> lista = new ArrayList<Paciente>();
        try{
            abrirConexion();
            String sql = "select * from Pacientes";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                int id = rs.getInt("idPaciente");
                String nombre = rs.getString("nombre");
                lista.add(new Paciente(id, nombre));
            }
            rs.close();
        }catch(SQLException ex){ex.printStackTrace();}
        finally{
            cerrarConexion();
        }
        return lista;
       }
       
       public ArrayList<Empleado> obtenerRecepcionista(){
           ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try{
            abrirConexion();
            String sql = "select * from Empleados";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                int id = rs.getInt("Legajo");
                String nombre = rs.getString("nombre");
                lista.add(new Empleado(id, nombre));
            }
            rs.close();
        }catch(SQLException ex){ex.printStackTrace();}
        finally{
            cerrarConexion();
        }
        return lista;
       }
       
       public ArrayList<Visita> obtenerVisita(){
          ArrayList<Visita> lista = new ArrayList<Visita>();
          try{
              abrirConexion();
              String sql = "select p.nombre as 'nombreP', v.nombre as 'nombreV', e.nombre as 'nombreE', v.duracion as 'duracion' from Pacientes p inner join visitas v on p.IdPaciente = v.idPaciente inner join empleados e on v.LegajoRecepcionista = e.Legajo order by p.nombre";
              Statement st = con.createStatement();
              ResultSet rs = st.executeQuery(sql);
              
              while(rs.next()){
                  String nomPaciente = rs.getString("nombreP");
                  String nomVisita = rs.getString("nombreV");
                  String nomEmpleado = rs.getString("nombreE");
                  int duracion = rs.getInt("duracion");
                  Paciente p = new Paciente();
                  p.setNombre(nomPaciente);
                  Empleado e = new Empleado();
                  e.setNombre(nomEmpleado);
                  lista.add(new Visita(p, e, nomVisita, duracion));
                  
              }
              rs.close();
       }catch(SQLException ex){ex.printStackTrace();}
          finally{
              cerrarConexion();
          }
          return lista;
       }
        
       public int obtenerTotal(){
        int cantidad = 0;
        
        try{
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select p.nombre as 'nombre', count(v.IdVisita) as 'Cantidad' from visitas v inner join Pacientes p on v.IdPaciente = p.IdPaciente group by p.nombre");

            while(rs.next()){
                cantidad = rs.getInt("Cantidad");
            }
            rs.close();
        }catch(SQLException ex){}
        finally{
            cerrarConexion();
        }
        return cantidad;
    }

     public float obtenerPromedio(){
        float prom = 0;
        
        try{
            abrirConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select avg(duracion) as 'Promedio' from Visitas where duracion > 10");

            while(rs.next()){
                prom = rs.getFloat("Promedio");
            }
            rs.close();
        }catch(SQLException ex){}
        finally{
            cerrarConexion();
        }
        return prom;
    }  
    
}
