package net.thevpc.gaming.atom.examples.kombla.main.client.dal;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.pong.main.shared.dal.sockets.DALUtilByteArray;
import net.thevpc.gaming.atom.model.DefaultPlayer;
import net.thevpc.gaming.atom.model.DefaultSprite;

import java.io.*;
import java.net.*;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Map;

import static java.awt.SystemColor.info;

public class  SocketMainClientDAO implements MainClientDAO{

    private MainClientDAOListener listener;
    private MulticastSocket socket;
    private InetAddress groupAddress;
    private InetAddress serverListeningAddress;
    private int serverListeningPort;
    protected String name;
    long lastRecievedFrame = 0;
    long lastSentFrame = 0;
    String IPaddr;
    int port;
    int id;
    int[][] maze;
    protected Map<String, Object> properties;

    @Override
    public void start (MainClientDAOListener listener, AppConfig properties) {
        this.listener = listener;
        IPaddr = properties.getServerAddress();
        port = properties.getServerPort();

    }
    public void onLoopReceiveModelChanged(DynamicGameModel model) {

        new Thread(String.valueOf(this)).start();

    }

    @Override
    public StartGameInfo connect() {

        try {
            socket = new MulticastSocket(port);
            groupAddress = InetAddress.getByName(IPaddr);
            if (!groupAddress.isMulticastAddress()) {
                groupAddress = InetAddress.getByName("localhost");
            }
            socket.joinGroup(groupAddress);

            ByteArrayOutputStream tab = new ByteArrayOutputStream();
            int taille = tab.toByteArray().length;
            DatagramPacket packet = new DatagramPacket(tab.toByteArray(), taille);
            System.out.println("Connected");
            socket.receive(packet);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    public StartGameInfo readStartGameInfo(){
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] reeded = packet.getData();
        int countRead=packet.getLength();
        int fromRead=packet.getOffset();
        reeded=Arrays.copyOfRange(reeded,fromRead,fromRead+countRead);
        ByteArrayInputStream bis=new ByteArrayInputStream(reeded);
        DataInputStream dis=new DataInputStream(bis);
        try {
            dis.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        id = readStartGameInfo().getPlayerId();
        maze = readStartGameInfo().getMaze();
        ByteArrayOutputStream tab = new ByteArrayOutputStream();
        tab.write(id);
        return null;


    }
    public void readPlayer(DefaultPlayer player){
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        id = player.getId();
        name = player.getName();
        properties = player.getProperties();

    }
    public void readSprite(DefaultSprite sprite){
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] whatHaveBeenRead = packet.getData();
        int countRead=packet.getLength();
        int fromRead=packet.getOffset();
        whatHaveBeenRead=Arrays.copyOfRange(whatHaveBeenRead,fromRead,fromRead+countRead);
        ByteArrayInputStream bis=new ByteArrayInputStream(whatHaveBeenRead);
        DataInputStream dis=new DataInputStream(bis);
        try {
            dis.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sprite.getId();
        sprite.getKind();
        sprite.getName();
        sprite.getLocation();
        sprite.getDirection();
        id = sprite.getPlayerId();
        properties = sprite.getProperties();

    }


    @Override
    public void sendMoveLeft() {sendInt(2);}

    @Override
    public void sendMoveRight() {sendInt(3);}

    @Override
    public void sendMoveUp() {sendInt(4);}

    @Override
    public void sendMoveDown() {sendInt(5);}

    @Override
    public void sendFire() {sendInt(6);}

    private void sendInt(int i) {
        try {
            lastSentFrame++;
            byte[] buffer = new byte[4 + 2];
            DALUtilByteArray.IntHolder intref = new DALUtilByteArray.IntHolder();
            DALUtilByteArray.writeLong(lastSentFrame, buffer, intref);
            DALUtilByteArray.writeInt(i, buffer, intref);
            socket.send(new DatagramPacket(buffer, buffer.length, serverListeningAddress, serverListeningPort));
        } catch (IOException ex) {
            ex.printStackTrace();
            //ignore error
        }
    }
}

