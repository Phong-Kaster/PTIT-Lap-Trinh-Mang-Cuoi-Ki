/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phong
 */
public class TCPConnectionServer {
    public static Connection getConnection() throws SQLException{
        String database = "LTM";
        String user = "sa";
        String password = "123456";
        String port = "1433";
        String server = "localhost";
        String url = "jdbc:sqlserver://"+server+":"+port+";databaseName="+database+";user="+user+";password="+password;
        return DriverManager.getConnection(url);
    }
    
    public static String handleRequest( int request, String value,String id)
    {
        try 
        {
            
            Connection con = getConnection();
            Statement stm = con.createStatement();
            String query = "";
            
            // them moi
            if( request == 1)
            {
                query = "INSERT INTO person(name) VALUES ('" + value + "');";
            }
            // xoa di
            if( request == 2 )
            {
                query = "DELETE FROM person WHERE id = " + id;
            }
            // sua
            if( request == 3)
            {
                query = "UPDATE person SET name = '" + value + "' WHERE id = " + id;
            }
            
            int rs = stm.executeUpdate(query);
            if( rs > -1)
            {
               return "success"; 
            }
            else
            {
                return "fail";
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TCPConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
            return "fail";
        }
    }
    
    
    public static void main(String[] args) {
        try 
        {
            ServerSocket server = new ServerSocket(5555);
            Socket socket = server.accept();
            
            
            DataInputStream din = new DataInputStream( socket.getInputStream() );
            DataOutputStream dout = new DataOutputStream( socket.getOutputStream() );
            
            // nhan du lieu
            int request = din.readInt();
            String value = din.readUTF();
            String id = din.readUTF();
            System.out.println("DU LIEU NHAN DUOC ");
            System.out.println(request);
            System.out.println(value);
            System.out.println(id);
            String result = handleRequest( request, value, id );
            
            dout.writeUTF(result);
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(TCPConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
