/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author phong
 */
public class TCPConnectionClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5555);
        
        DataInputStream din = new DataInputStream( socket.getInputStream() );
        DataOutputStream dout = new DataOutputStream( socket.getOutputStream() );
        Scanner sc = new Scanner(System.in);
        
        System.out.println("1. THEM");
        System.out.println("2. XOA");
        System.out.println("3. SUA");
        System.out.print("Moi nhap lua chon: ");
        
        // khoi tao bien
        int request;
        String value = "";
        String id = "0";
        do
        {
            request = sc.nextInt();
            if( request < 1 || request > 3)
            {
                System.out.println("LUA CHON KHONG HOP LE ! NHAP LAI");
            }
        }
        while( request < 1 || request > 3);
         sc.nextLine();
        // xu ly yeu cau
        if( request == 1)
        {
            System.out.print("MOI NHAP TEN NGUOI MUON THEM : ");
            value = sc.nextLine();
        }
        if( request == 2)
        {
            System.out.println("MOI NHAP ID NGUOI MUON XOA");
            id = sc.nextLine();
        }
        if( request == 3)
        {
            System.out.print("MOI NHAP TEN NGUOI MUON THEM : ");
            value = sc.nextLine();
            System.out.println("MOI NHAP ID NGUOI MUON SUA");
            id = sc.nextLine();
        }
        dout.writeInt(request);
        dout.writeUTF(value);
        dout.writeUTF(id);
        
        // nhan du lieu
        String result = din.readUTF();
        System.out.println("KET QUA THUC HIEN : " + result);
    }
}
