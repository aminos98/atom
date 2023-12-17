package net.thevpc.gaming.atom.examples.kombla.main.server.dal.rmi;

import net.thevpc.gaming.atom.examples.kombla.main.client.dal.rmi.OperationsRMI;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerOperationsRMI extends Remote {
    StartGameInfo onReceivePlayerJoined(String name) throws RemoteException;
    public void setClient(OperationsRMI client) throws RemoteException;
    void onReceiveMoveLeft(int playerId) throws RemoteException;

    void onReceiveMoveRight(int playerId) throws RemoteException;

    void onReceiveMoveUp(int playerId) throws RemoteException;

    void onReceiveMoveDown(int playerId) throws RemoteException;

    void onReceiveReleaseBomb(int playerId) throws RemoteException;
}
