import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class with the function of sending and receiving data.
 */
public class Client {

  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;
  private String clientName;

  /**
   * Initialize Client with ip and port.
   *
   * @param ip   ip address
   * @param port port
   * @throws IOException IOException
   */
  public Client(String ip, int port) throws IOException {
    socket = new Socket(ip, port);
    out = new DataOutputStream(socket.getOutputStream());
    in = new DataInputStream(socket.getInputStream());
    new Thread(new Receive()).start();
    new Thread(new Send()).start();
  }

  /**
   * Receive thread to receive response data from server output stream.
   */
  private class Receive implements Runnable {

    @Override
    public void run() {
      while (true) {
        try {
          //read identifier of the message first.
          int identifier = in.read();
          in.readChar();

          //read connect response data from server and output.
          if (identifier == MessageIdentifiers.CONNECT_RESPONSE) {

            if (in.readBoolean()) {
              in.readChar();

              clientName = readData();
            } else {
              in.readChar();

              readData();
            }
            in.readChar();
            String message = readData();
            System.out.println(message);
          }

          //read disconnect response from server and disconnect.
          else if (identifier == MessageIdentifiers.DISCONNECT_RESPONSE) {
            String message = readData();

            System.out.println(message);
            socket.close();
            break;
          }

          //read query response from server and output.
          else if (identifier == MessageIdentifiers.QUERY_USER_RESPONSE) {
            int numberOfUsers = in.readInt();

            byte[][] users = new byte[numberOfUsers][];
            for (int i = 0; i < numberOfUsers; i++) {
              in.readChar();
              users[i] = readData().getBytes();
            }

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < users.length; i++) {
              sb.append(new String(users[i]) + " ");
            }
            System.out.println(sb.toString());
          }
          //read broadcast message from server and output.
          else if (identifier == MessageIdentifiers.BROADCAST_MESSAGE) {

            String sender = readData();
            in.readChar();

            String message = readData();
            System.out.println(sender + ": " + message);
          }
          //read direct message from server and output.
          else if (identifier == MessageIdentifiers.DIRECT_MESSAGE) {

            String sender = readData();
            in.readChar();

            readData();
            in.readChar();

            String message = readData();

            System.out.println("[private message]" + sender + ": " + message);
          }
          //read failed message from server and output.
          else if (identifier == MessageIdentifiers.FAILED_MESSAGE) {
            String message = readData();
            System.out.println(message);
          }
          //read insult response from server and output.
          else if (identifier == MessageIdentifiers.INSULT_RESPONSE) {
            String sender = readData();
            in.readChar();

            String receiver = readData();
            in.readChar();

            String insult = readData();
            System.out.println(sender + " -> " + receiver + ": " + insult);
          }

        } catch (IOException e) {
          System.out.println("Connection lost!");
        }
      }
    }
  }

  /**
   * Send thread to write data to input stream.
   */
  private class Send implements Runnable {

    @Override
    public void run() {
      Scanner scanner = new Scanner(System.in);
      while (true) {

        String line = scanner.nextLine();
        if ("?".equals(line)) {
          listCommands();
        }
        //send login message to server through writeToStream in ConnectMessage class.
        else if (line.toLowerCase().startsWith("login ")) {
          byte[] username = line.substring("login ".length()).getBytes();
          new ConnectMessage(username).writeToStream(out);
        } else if (clientName == null) {
          System.out.println("Please login first! (login <username>)");
        }

        //send disconnect message to server through writeToStream in DisconnectMessage class.
        else if (line.toLowerCase().startsWith("logoff")) {
          new DisconnectMessage(clientName.getBytes()).writeToStream(out);

        }

        //send query message to server through writeToStream in QueryUsers class.
        else if (line.toLowerCase().startsWith("who")) {
          new QueryUsers(clientName.getBytes()).writeToStream(out);

        }

        //send direct message to server through writeToStream in DirectMessage class.
        else if (line.startsWith("@") && !line.toLowerCase().startsWith("@all ")
            && line.length() > 1 && line.contains(" ")
            && line.substring(line.indexOf(" ") + 1).length() >= 1) {
          String message = line.substring(line.indexOf(" ") + 1);
          String to = line.substring(1, line.indexOf(" "));
          new DirectMessage(clientName.getBytes(), to.getBytes(), message.getBytes())
              .writeToStream(out);

        }

        //send broadcast message to server through writeToStream in BroadcastMessage class.
        else if (line.toLowerCase().startsWith("@all ") && line.contains(" ") && line.substring(5).length() >= 1) {
          String message = line.substring(5);
          new BroadcastMessage(clientName.getBytes(), message.getBytes()).writeToStream(out);
        }

        //send insult message to server through writeToStream in SendInsult class.
        else if (line.startsWith("!")) {
          String to = line.substring(1);
          new SendInsult(clientName.getBytes(), to.getBytes()).writeToStream(out);

        } else {
          System.out.println("Invalid input!");
          listCommands();
        }


      }
    }
  }

  /**
   * List the option of commands .
   */
  private void listCommands() {
    System.out.println("======Commands======\n" +
        "login <username>: connect to the server with the assigned username.\n" +
        "logoff: log off from the server.\n" +
        "who: see all online users.\n" +
        "@<username> <message>: send a message to a user.\n" +
        "@all <message>: broadcast a message to all users.\n" +
        "!<username>: send a random insult to a user.\n" +
        "?: request a usage description of the chat room.\n");
  }

  /**
   * Read data from input stream, read size first and then read the byte[size] from the stream.
   *
   * @return String data
   */
  private String readData() {
    String data = "";
    try {
      int length = in.readInt();
      in.readChar();
      byte[] bytes = new byte[length];
      in.readFully(bytes);
      data = new String(bytes);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }
}
