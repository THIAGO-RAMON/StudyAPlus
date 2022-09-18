package connection;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/studyaplus";
    private static final String USER = "root";
    private static final String PASS = "studyaplus";

    public static Connection getConnection(){

        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados\n"+e, "ERROR", JOptionPane.WARNING_MESSAGE);
            return null;
        }

    }

    public static void closeConnection(Connection con ){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "Erro no fechamento com o banco de dados", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt ){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "Erro no fechamento com o banco de dados", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
        closeConnection(con);
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs ){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "Erro no fechamento com o banco de dados", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        }
        closeConnection(con, stmt);
    }
}
