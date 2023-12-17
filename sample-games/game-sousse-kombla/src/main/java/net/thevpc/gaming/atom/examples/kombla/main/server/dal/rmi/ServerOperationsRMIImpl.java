package net.thevpc.gaming.atom.examples.kombla.main.server.dal.rmi;

import net.thevpc.gaming.atom.examples.kombla.main.client.dal.rmi.OperationsRMI;
import net.thevpc.gaming.atom.examples.kombla.main.server.dal.MainServerDAOListener;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerOperationsRMIImpl extends UnicastRemoteObject implements ServerOperationsRMI{
    private MainServerDAOListener listener;
    private  OperationsRMI client;

    public  ServerOperationsRMIImpl(MainServerDAOListener listener) throws RemoteException {
        this.listener = listener;
    }

    @Override
    public StartGameInfo onReceivePlayerJoined(String name) {
        return listener.onReceivePlayerJoined(name);
    }

    @Override
    public void setClient(OperationsRMI client) throws RemoteException {
        this.client = client;
    }

    @Override
    public void onReceiveMoveLeft(int playerId)  throws RemoteException{
    listener.onReceiveMoveLeft(playerId);
    }
    public OperationsRMI getClient() {
        return client;
    }

    @Override
    public void onReceiveMoveRight(int playerId)  throws RemoteException{
    listener.onReceiveMoveRight(playerId);
    }

    @Override
    public void onReceiveMoveUp(int playerId) throws RemoteException {

        listener.onReceiveMoveUp(playerId);
    }

    @Override
    public void onReceiveMoveDown(int playerId)  throws RemoteException{
    listener.onReceiveMoveDown(playerId);
    }

    @Override
    public void onReceiveReleaseBomb(int playerId) throws RemoteException {

        listener.onReceiveReleaseBomb(playerId);
    }

}
