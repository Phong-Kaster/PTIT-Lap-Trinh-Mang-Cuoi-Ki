/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author phong
 */
public class ClientTCP {
       public static void main(String[] args) throws IOException {
         Socket socket = new Socket("localhost", 5555);
         
           DataInputStream din = new DataInputStream( socket.getInputStream() );
           DataOutputStream dout = new DataOutputStream( socket.getOutputStream() );
           
           Scanner sc = new Scanner( System.in );
           
           // MENU
           System.out.println("1. TIM SO LON NHAT VA THU 2 TRONG MANG");
           System.out.println("2. SAP XEP GIAM DAN");
           System.out.println("3. CHEN THEM 1 SO");
           System.out.println("0. EXIT");
           System.out.print("Moi nhap lua chon : ");
           
           int request = sc.nextInt();
           dout.writeInt(request);
           
           // ARRAY SIZE
           System.out.print("NHAP KICH THUOC MANG : ");
           int arraySize = sc.nextInt();
           dout.writeInt(arraySize);
           
           // ENTER ARRAY ELEMENT
           for( int i = 0 ; i < arraySize ;i++)
           {
               System.out.print("Moi nhap phan tu thu " + i + " = ");
               int number = sc.nextInt();
               dout.writeInt(number);
           }
           
           if( request == 3)
           {
                System.out.print("Moi nhap phan tu can chen: ");
                int insertNumber = sc.nextInt();
                dout.writeInt(insertNumber);
                
                System.out.println("Moi nhap vi tri can chen: ");
                int insertPosition = sc.nextInt();
                if( insertPosition > arraySize)
                {
                    insertPosition = arraySize;
                }
                    
                dout.writeInt(insertPosition);
           }
           
           // RESULT
           String result = din.readUTF();
           System.out.println(result);
    }
}
