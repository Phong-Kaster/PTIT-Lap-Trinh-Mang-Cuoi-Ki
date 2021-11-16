/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author phong
 */
public class ClientUDP {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket();
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("1. CONG DON");
        System.out.println("2. TRU DON");
        System.out.println("3. TRUNG BINH CONG");
        
        String yeuCau = sc.nextLine();
        String request = yeuCau + "#";
        System.out.println("NHAP KICH THUOC MANG : ");
        int arraySize = sc.nextInt();
        
        for( int i = 0 ; i < arraySize ; i ++)
        {
            int number = sc.nextInt();
            request += number + "#";
        }
        System.out.println(request);
        byte[] buffer1 = new byte[1000];
        buffer1 = String.valueOf(request).getBytes();
        
        
        // GUI DU LIEU
        InetAddress IP = InetAddress.getByName("localhost");
        int port = 5555;
        
        DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length, IP, port);
        socket.send(packet1);
        
        // NHAN DU LIEU
        byte[] buffer2 = new byte[1000];
        DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length);
        socket.receive(packet2);
        
        //IN DU LIEU
        String result = new String( packet2.getData(),0 , packet2.getLength() );
        System.out.println(result);
    }
}
