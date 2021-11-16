/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author phong
 */
public class ServerUDP {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket server = new DatagramSocket(5555);
        
        // NHAN DU LIEU
        byte buffer1[] = new byte[1000];
        DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);
        server.receive(packet1);
        
        // XU LY DU LIEU
        String request = new String( packet1.getData(), 0 , packet1.getLength() );
        String data[] = request.split("#");
        
        String require = data[0];
        int result = 0;
        if( require.contains("1"))
        {
            System.out.println("YEU CAU CONG DON");
            for( int i = 1; i < data.length; i++)
            {
                int n = Integer.parseInt(data[i]);
                result += n;
            }
        }
        if( require.contains("2"))
        {
            for( int i = 1 ; i< data.length; i++)
            {
                int n = Integer.parseInt(data[i]);
                result = result - n;
            }
        }
        
        
        // GUI DU LIEU VE
        byte buffer2[] = new byte[1000];
        buffer2 = String.valueOf(result).getBytes();
        DatagramPacket packet2 = new DatagramPacket( buffer2, buffer2.length, packet1.getAddress(), packet1.getPort());
        server.send(packet2);
    }
}
