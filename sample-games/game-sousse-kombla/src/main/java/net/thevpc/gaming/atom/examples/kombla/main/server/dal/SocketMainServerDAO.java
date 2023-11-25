package net.thevpc.gaming.atom.examples.kombla.main.server.dal;

import net.thevpc.gaming.atom.engine.SceneEngine;
import net.thevpc.gaming.atom.examples.kombla.main.client.dal.MainClientDAO;
import net.thevpc.gaming.atom.examples.kombla.main.client.dal.SocketMainClientDAO;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;
import net.thevpc.gaming.atom.examples.pong.main.server.dal.DALServerListener;
import net.thevpc.gaming.atom.examples.pong.main.shared.dal.DALUtil;
import net.thevpc.gaming.atom.examples.pong.main.shared.dal.model.DALStructModel;
import net.thevpc.gaming.atom.examples.pong.main.shared.dal.sockets.DALUtilByteArray;
import net.thevpc.gaming.atom.examples.pong.main.shared.dal.sockets.DALUtilMulticast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

public class SocketMainServerDAO implements MainServerDAO{
    private DALServerListener listener;
    private MulticastSocket serverSocket;
    private InetAddress groupAddress;
    private int groupPort = 2050;
    private boolean clientConnected = false;
    private Map<Integer, ClientSession> playerToSocketMap = new HashMap<>() ;
    @Override
    public void start(MainServerDAOListener listener, AppConfig properties) {
        this.listener = (DALServerListener) listener;
        this.groupPort = properties.getServerPort();
        try {
            serverSocket = new MulticastSocket();
            groupAddress = InetAddress.getByName(properties.getServerAddress());
            if (!groupAddress.isMulticastAddress()) {
                groupAddress = InetAddress.getByName(DALUtilMulticast.DEFAULT_ADDRESS);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Impossible de lancer le serveur", ex);
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                broadcast();
            }
        }).start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                listen();
            }
        }).start();
    }
    public void broadcast() {
        byte[] buffer = DALUtilMulticast.createBurst(); //burst
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, groupPort);
        while (!clientConnected) {
            try {
                Thread.sleep(1000);
                serverSocket.send(packet);
            } catch (Exception ex) {
                throw new RuntimeException("Erreur reseau", ex);
            }
        }
        System.out.println("STOP SENDING BURST : Client Already connected");

    }
    private void listen() {
        try {
            byte[] buffer = new byte[DALUtilMulticast.BURST_SIZE]; //burst
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            while (true) {
                System.out.println("WAITING FOR BIRST");
                serverSocket.receive(p);
                if (DALUtilMulticast.isBurst(buffer)) {
                    System.out.println("RECIEVED BIRST FROM " + p.getSocketAddress());
                    break;
                } else {
                   System.out.println("RECIEVED ??? FROM " + p.getSocketAddress() + " Ignored");
                }
            }
            clientConnected = true;
            listener.clientConnected();
            long lastFrame = -1;
            LOOP:
            while (true) {
                System.out.println("WAITING FOR KEY");
                serverSocket.receive(p);
                System.out.println("RECIEVED KEY FROM " + p.getSocketAddress());
                DALUtilByteArray.IntHolder refInt = new DALUtilByteArray.IntHolder();
                long frame = DALUtilByteArray.readLong(buffer, refInt);
                if (lastFrame < frame) {
                    lastFrame = frame;
                    int i = DALUtilByteArray.readInt(buffer, refInt);
                    switch (i) {
                        case 2: {
                            onReceiveMoveLeft();
                            break;
                        }
                        case 3: {
                            onReceiveMoveRight();
                            break;
                        }
                        case 4: {
                            onReceiveMoveUp();
                            break;
                        }
                        case 5: {
                            onReceiveMoveDown();
                        }
                        case 6: {
                            onReceiveReleaseBomb();
                        }
                        default:
                            throw new IllegalStateException("Unexpected value: " + i);
                    }
                }
            }
            serverSocket.close();
        } catch (Exception ex) {
            throw new RuntimeException("Impossible de lancer le serveur", ex);
        }
    }

    @Override
    public void sendModelChanged(DynamicGameModel dynamicGameModel) {
        if (clientConnected) {
            DALStructModel data = DALUtil.toDALStructModel((SceneEngine) dynamicGameModel);
            try {
                byte[] bytes = DALUtilByteArray.toByteArray(data);
                serverSocket.send(new DatagramPacket(bytes, bytes.length, groupAddress, groupPort));
            } catch (Exception ex) {
                ex.printStackTrace();
                //ignore error
            }
        }

    }
    public void processClient(ClientSession cs){



    }

}
