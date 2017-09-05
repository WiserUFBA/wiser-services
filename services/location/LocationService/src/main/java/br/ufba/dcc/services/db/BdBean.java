/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufba.dcc.services.db;

import br.dcc.ufba.wiser.smartufba.services.local.Location;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.h2.jdbc.JdbcSQLException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 *
 * @author Andressa
 */
public class BdBean {

    private Connection con;
    private PreparedStatement pstm;
    //private ResultSet rs;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static BdBean instance = null;
    
    public static BdBean getInstance(){
        if(instance == null){
            instance = new BdBean();
        }
        
        return instance;
    }
    
    private BdBean() {
        BundleContext ctx = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
        ServiceReference serviceReference = ctx.getServiceReference(DataSource.class.getName());
   
        dataSource = (DataSource) ctx.getService(serviceReference);
        try {
            System.out.println(dataSource.getConnection().getMetaData());
        } catch (SQLException ex) {
            Logger.getLogger(BdBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //this.dataSource.getConnection();  
        this.createSchema();
        this.createTable();
    }

    private void createSchema() {
        try {
            con = this.dataSource.getConnection();
            pstm = con.prepareStatement("CREATE SCHEMA IF NOT EXISTS device_location");
            pstm.executeUpdate();
            System.out.println("Schema Created");
        } catch (JdbcSQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Schema already exists");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Schema ERROR");
        }

    }

    private void createTable() {
        try {
            con = this.dataSource.getConnection();
            pstm = con.prepareStatement("CREATE TABLE IF NOT EXISTS device_location.gateway "
                    + "(id BIGINT AUTO_INCREMENT PRIMARY KEY, longitude DECIMAL(9, 7), latitude DECIMAL(10, 7))");
            pstm.executeUpdate();

            System.out.println("'Gateway' table created");
        } catch (JdbcSQLException e) {
            e.printStackTrace(System.out);
            System.out.println("Table already exists");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Table ERROR");
        }
    }

    public Location selectOne(String id) {
        ResultSet rs_local;
        Location location;
        try {
            con = this.dataSource.getConnection();
            pstm = con.prepareStatement("SELECT * FROM device_location.gateway WHERE id=?;");
            pstm.setString(1, id);
            rs_local = pstm.executeQuery();
            
            if (rs_local.next()) {
                location = new Location();
                location.setId(rs_local.getLong("id"));
                location.setLatitude(rs_local.getFloat("latitude"));
                location.setLongitude(rs_local.getFloat("longitude"));
                return location;
            } else {
                return null;
            }
        } catch (JdbcSQLException e) {
            e.printStackTrace(System.out);
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

    public ArrayList<Location> selectAll() {
        ResultSet rs_local;
        ArrayList<Location> ar = new ArrayList();
        try {
            con = this.dataSource.getConnection();
            pstm = con.prepareStatement("SELECT * FROM device_location.gateway");
            rs_local = pstm.executeQuery();
            while (rs_local.next()) {
                Location loc = new Location();
                //dm.setId(rs_local.getLong("id"));
                //dm.setLatitude(rs_local.getFloat("latitude"));
                //dm.setLongitude(rs_local.getFloat("longitude")); 
                ar.add(loc);
            }
            return ar;
        } catch (JdbcSQLException e) {
            e.printStackTrace(new PrintStream(System.err));
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace(new PrintStream(System.err));
            return null;
        }
    }

    public boolean delete(String id) {
        try {
            con = this.dataSource.getConnection();
            pstm = con.prepareStatement("DELETE FROM device_location.gateway WHERE id=?");
            pstm.setString(1, id);
            int r = pstm.executeUpdate();
            return r >= 1;
        } catch (SQLException ex) {
            ex.printStackTrace(new PrintStream(System.err));
            return false;
        }
    }

    public boolean insert(Location loc) {
        try {
            con = this.dataSource.getConnection();
            pstm = con.prepareStatement("INSERT INTO device_location.gateway (latitude, longitude) VALUES(?, ?)");
            pstm.setFloat(1, loc.getLatitude());
            pstm.setFloat(2, loc.getLongitude());
            int r = pstm.executeUpdate();

            return r == 1;
        } catch (SQLException ex) {
            ex.printStackTrace(new PrintStream(System.err));
            return false;
        }
    }

    public boolean update(Location location) {
        try {
            con = this.dataSource.getConnection();
            pstm = con.prepareStatement("UPDATE device_location.gateway SET latitude=?, longitude=? WHERE id=?");
            pstm.setFloat(1, location.getLatitude());
            pstm.setFloat(2, location.getLongitude());
            pstm.setLong(3, location.getId());
            int r = pstm.executeUpdate();
            return r == 1;
        } catch (SQLException ex) {
            ex.printStackTrace(new PrintStream(System.err));
            return false;
        }
    }

}
