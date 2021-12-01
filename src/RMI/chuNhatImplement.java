/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author phong
 */
public class chuNhatImplement extends UnicastRemoteObject implements chuNhat{

    public chuNhatImplement() throws RemoteException
    {
        super();
    }
    
    @Override
    public int chuVi(int a, int b) throws RemoteException {
        return (a+b)*2;
    }

    @Override
    public int dienTich(int a, int b) throws RemoteException {
        return a*b;
    }
    
    
}
