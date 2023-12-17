package net.thevpc.gaming.atom.examples.kombla.main.server.dal.rmi;

import net.thevpc.gaming.atom.engine.SceneEngine;
import net.thevpc.gaming.atom.examples.kombla.main.client.dal.rmi.OperationsRMI;
import net.thevpc.gaming.atom.examples.kombla.main.server.dal.MainServerDAO;
import net.thevpc.gaming.atom.examples.kombla.main.server.dal.MainServerDAOListener;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DALServerRMI implements MainServerDAO {

    private MainServerDAOListener listener;
    private ServerOperationsRMIImpl server;


    public DALServerRMI() throws RemoteException {
    }

    @Override
    public void start(MainServerDAOListener callback, AppConfig properties){
        try {
            this.listener = callback;
            Registry registry = LocateRegistry.createRegistry(9854);
            registry.bind("KomblaRMI", server = new ServerOperationsRMIImpl(listener));
        } catch (RemoteException | AlreadyBoundException  ex) {
            throw new RuntimeException("Impossible de lancer le serveur",ex);
        }
    }

    @Override
    public void sendModelChanged(DynamicGameModel dynamicGameModel){
        OperationsRMI client = server.getClient();
        if (client != null) {
            try {
                client.modelChanged(dynamicGameModel);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                //ignore error
            }
        }
    }



}
