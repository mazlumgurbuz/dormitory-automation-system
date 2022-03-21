/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import util.DBConnection;

/**
 *
 * @author Muhammed ARSLAN
 */
abstract class BaseDao {

    private DBConnection connector;
    private Connection connection;

    public DBConnection getConnector() {
        if (this.connector == null) {
            this.connector = new DBConnection();
        }

        return connector;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            this.connection = this.getConnector().connect();
        }
        return connection;
    }
}
