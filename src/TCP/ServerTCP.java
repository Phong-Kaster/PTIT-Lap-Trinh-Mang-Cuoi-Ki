/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author phong
 */
public class ServerTCP {
    
    public static String findBiggestAndSecondBiggest(Integer array[])
    {
        int biggestNumber = 0;
        int biggestPosition = 0;
        String result = "";
        
        for( int i = 0 ; i < array.length ; i++)
        {
            if( array[i] > biggestNumber)
            {
                biggestNumber = array[i];
                biggestPosition = i;
            }
        }
        
       
        
        int secondBiggestNumber = 0;
        int secondBiggestPosition = 0;
        
        for( int i = 0 ; i < array.length ; i++)
        {
            if( array[i] < biggestNumber && array[i] > secondBiggestNumber)
            {
                secondBiggestNumber = array[i];
                secondBiggestPosition = i;
            }
        }
        
        result = "So lon nhat la " + biggestNumber + " tai vi tri " + biggestPosition +
                "\n So lon thu 2 la " + secondBiggestNumber + "tai vi tri " + secondBiggestPosition + "\n";
        
        return result;
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5555);
        Socket socket = server.accept();
        System.out.println("Server is running !");
        
        
        DataOutputStream dout = new DataOutputStream( socket.getOutputStream() );
        DataInputStream din  = new DataInputStream( socket.getInputStream() );
        
        
        // REQUEST
        int request = din.readInt();
        System.out.println("YEU CAU NHAN DUOC : " +  request);
        
        // ARRAY
        int arraySize = din.readInt();
        Integer array[] = new Integer[arraySize];
        
        for( int i = 0 ; i < array.length; i ++)
        {
            array[i] = din.readInt();
        }
        
        if( request == 1)
        {
            String result = "";
            result = findBiggestAndSecondBiggest(array);
            dout.writeUTF(result);
        }
        if( request == 2)
        {
            Arrays.sort( array, Collections.reverseOrder());
            dout.writeUTF( Arrays.toString(array));
        }
        if( request == 3)
        {
            int insertNumber = din.readInt();
            int insertPosition = din.readInt();
            
            System.out.println(insertNumber + "-" + insertPosition);
            
            Integer array2[] = new Integer[ arraySize + 1];
            
            for( int i = 0 ; i < array.length ; i ++)
            {
                array2[i] = array[i];
            }
            
            for( int i = array.length ; i >= insertPosition ; i -- )
            {
                array2[i] = array2[i - 1];
            }
             array2[insertPosition] = insertNumber;
            
            dout.writeUTF( Arrays.toString(array2));
        }
        
    }
}
