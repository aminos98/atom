package net.thevpc.gaming.atom.examples.kombla.main.client.dal.rmi;

import net.thevpc.gaming.atom.examples.kombla.main.client.dal.MainClientDAO;
import net.thevpc.gaming.atom.examples.kombla.main.client.dal.MainClientDAOListener;
import net.thevpc.gaming.atom.examples.kombla.main.server.dal.rmi.ServerOperationsRMI;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.net.Inet4Address;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DALClientRMI implements MainClientDAO {
    private MainClientDAOListener listener;
    private ServerOperationsRMI server;
    private OperationsRMI client;
    private int clientId;

    public DALClientRMI() throws RemoteException {
    }
    @Override
    public void start(MainClientDAOListener callback, AppConfig properties) {
        try {
            this.listener = callback;
            Registry registry = LocateRegistry.getRegistry("localhost", 9854);
            server = (ServerOperationsRMI) registry.lookup("KomblaRMI");
            server.setClient(client = new OperationsRMIImpl(listener));

        } catch (Exception ex) {
            ex.printStackTrace();
            //ignore error
        }
    }

    @Override
    public StartGameInfo connect() {

        StartGameInfo s = null;
        try {
            s = server.onReceivePlayerJoined("AA");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        this.clientId=s.getPlayerId();
        return s;
    }

    @Override
    public void sendMoveLeft() {
        try {
            server.onReceiveMoveLeft(clientId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveRight() {
        try {
            server.onReceiveMoveRight(clientId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveUp() {
        try {
            server.onReceiveMoveUp(clientId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveDown() {
        try {
            server.onReceiveMoveDown(clientId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendFire() {
        try {
            server.onReceiveReleaseBomb(clientId);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
