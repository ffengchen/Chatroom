import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Chatroom server.
 * */
public class Server {

  private final int PORT = 9999;
  private Map<String, DataOutputStream> clients;

  /**
   * Set up a new server.
   * @throws IOException IOException
   * */
  public Server() throws IOException {

    ServerSocket server = new ServerSocket(PORT);
    clients = new HashMap<>();
    while (true) {
      Socket socket = server.accept();
      new Thread(new Channel(socket)).start();
    }

  }

  /**
   * Set up a new server thread.
   * */
  private class Channel implements Runnable {

    private DataInputStream in;
    private DataOutputStream out;
    private Socket serverSocket;
    private boolean loginStatus = false;

    /**
     * Create a new channel.
     * @param socket server socket.
     * @throws IOException IOException
     * */
    public Channel(Socket socket) throws IOException {
      in = new DataInputStream(socket.getInputStream());
      out = new DataOutputStream(socket.getOutputStream());
      serverSocket = socket;
    }

    /**
     * Read data from data input stream.
     * @return a byte array of data.
     * */
    public byte[] readData() {
      byte[] data = null;
      try {
        int length = in.readInt();
        in.readChar();

        data = new byte[length];
        in.readFully(data);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return data;
    }

    /**
     * Run the thread.
     * */
    @Override
    public void run() {
      String clientName = null;
      while (serverSocket.isConnected()) {
        try {
          int identifier = in.readInt();
          in.readChar();

          if (identifier == MessageIdentifiers.CONNECT_MESSAGE) {
            byte[] username = readData();
            loginStatus = Operation.connectResponse(out, clients, username);

            clientName = new String(username);
            if (loginStatus) {
              Operation.broadcastResponse(out, clients, "[Admin]".getBytes(),
                  (clientName + " is online now.").getBytes());
            }
          } else if (identifier == MessageIdentifiers.DISCONNECT_MESSAGE) {
            byte[] username = readData();
            Operation.disconnectResponse(out, clients, username);
            if (clients.size() > 0) {
              Operation.broadcastResponse(out, clients, "[Admin]".getBytes(),
                  (clientName + " is offline now.").getBytes());
            }
            break;
          } else if (identifier == MessageIdentifiers.QUERY_CONNECTED_USERS) {
            byte[] username = readData();
            Operation.queryResponse(out, clients, username);
          } else if (identifier == MessageIdentifiers.BROADCAST_MESSAGE) {
            byte[] username = readData();
            in.readChar();

            byte[] message = readData();
            Operation.broadcastResponse(out, clients, username, message);
          } else if (identifier == MessageIdentifiers.DIRECT_MESSAGE) {

            byte[] username = readData();
            in.readChar();


            byte[] toUsername = readData();
            in.readChar();


            byte[] message = readData();

            Operation.directMsgResponse(out, clients, username, toUsername, message);
          } else if (identifier == MessageIdentifiers.SEND_INSULT) {
            byte[] username = readData();
            in.readChar();


            byte[] toUsername = readData();

            Operation.insultResponse(out, clients, username, toUsername);
          }


        } catch (IOException e) {
          if (clientName == null) {
            System.out.println("An unknown client crashed.");
          } else {
            clients.remove(clientName);
            System.out
                .format("Client " + clientName + " crashed.\nOnline users(%d):\n", clients.size());
            for (String client : clients.keySet()) {
              System.out.println(client + "\t");
            }
            System.out.println("--------------------");
          }
          break;
        }
      }
    }
  }

}
