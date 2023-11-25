package net.thevpc.gaming.atom.examples.kombla.main.server.dal;

import net.thevpc.gaming.atom.examples.kombla.main.client.dal.SocketMainClientDAO;

public class ClientSession {
    int playerId;
    SocketMainClientDAO socket ;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public SocketMainClientDAO getSocket() {
        return socket;
    }

    public void setSocket(SocketMainClientDAO socket) {
        this.socket = socket;
    }

    public ClientSession(int playerId, SocketMainClientDAO socket) {
        this.playerId = playerId;
        this.socket = socket;
    }
}
