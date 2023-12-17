package net.thevpc.gaming.atom.examples.kombla.main.client.dal.rmi;

import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.RemoteException;

public interface OperationsRMI {

    public void modelChanged(DynamicGameModel data) throws RemoteException;
}
