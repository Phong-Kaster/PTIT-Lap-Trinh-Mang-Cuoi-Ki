/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author phong
 */
public class ClientRMI {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 5555);
        
        chuNhat rmi = (chuNhat) registry.lookup("chuNhat");
        
        
        Scanner sc = new Scanner( System.in );
        System.out.println("Nhap chieu dai : ");
        int a = sc.nextInt();
        
        System.out.println("Nhap chieu rong : ");
        int b = sc.nextInt();
        
        System.out.println("Chu vi : " + rmi.chuVi(a, b) );
        System.out.println("Dien tich : " + rmi.dienTich(a, b));
    } 
}
