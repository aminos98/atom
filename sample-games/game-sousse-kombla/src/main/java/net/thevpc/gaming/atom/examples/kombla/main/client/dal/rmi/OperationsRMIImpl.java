package net.thevpc.gaming.atom.examples.kombla.main.client.dal.rmi;

import net.thevpc.gaming.atom.examples.kombla.main.client.dal.MainClientDAOListener;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OperationsRMIImpl extends UnicastRemoteObject implements OperationsRMI{
    private MainClientDAOListener listener;
    public OperationsRMIImpl(MainClientDAOListener listener) throws RemoteException {
        this.listener = listener;
    }
    @Override
    public void modelChanged(DynamicGameModel data) throws RemoteException {
        listener.onModelChanged(new DynamicGameModel());
    }
}
