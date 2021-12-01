/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author phong
 */
public class ServerRMI {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(5555);
        System.out.println("Server is started ! ");
        
        //khai bao class chuNhatImplement
        chuNhatImplement chuNhatImplements = new chuNhatImplement();
        
        // dang ky ten class chuNhatImplement voi interface chuNhat
        registry.rebind("chuNhat", chuNhatImplements);
    }
}
