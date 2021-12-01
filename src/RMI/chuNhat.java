/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author phong
 */
public interface chuNhat extends Remote {
    public int chuVi(int a, int b) throws RemoteException;
    public int dienTich(int a, int b) throws RemoteException;
}
