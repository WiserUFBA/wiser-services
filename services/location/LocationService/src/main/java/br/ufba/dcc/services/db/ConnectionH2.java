/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufba.dcc.services.db;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Andressa
 */
public class ConnectionH2 {

    private static Connection cn;

    public static Connection getConnection() {
        try {
            if (cn == null) {
                // GG https://ops4j1.jira.com/wiki/spaces/PAXJDBC/pages/61767710/Create+DataSource+from+config
                Runtime.getRuntime().addShutdownHook(new MiShDwnHook());
                Class.forName("org.h2.Driver");
                //cn = DriverManager.getConnection("jdbc:h2:C:\\Users\\User\\Documents\\NetBeansProjects\\h2test\\lib\\h2tst","sa","");  
                cn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            }
            return cn;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(new PrintStream(System.err));
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            ex.printStackTrace(new PrintStream(System.err));
            throw new RuntimeException(ex);
        }
    }

    static class MiShDwnHook extends Thread {

        private DataSource dataSource;

        public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public void run() {
            try {
                Connection cn = this.dataSource.getConnection();
                cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace(new PrintStream(System.err));
                throw new RuntimeException(ex);
            }
        }
    }

}
