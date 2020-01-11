
import java.io.DataOutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Processing center for messages come to the server.
 * */
public class Operation {

  private static final String VALID_USERNAME_PATTERN = "^[_a-zA-Z0-9]{3,12}$";
  private static final String SYSTEM_RESERVED_WORD = "^[^A-Za-z0-9]*(all|admin)[^A-Za-z0-9]*$";
  private static final int MAXIMUM_CONNECTED_USER_NUM = 10;

  /**
   * Respond a connect message.
   * @param out a data output stream pointing to the client.
   * @param clients a map storing all connected users.
   * @param username the username sent by the client.
   * @return a boolean value indicates the result of connection.
   * */
  public static boolean connectResponse(DataOutputStream out, Map<String, DataOutputStream> clients,
      byte[] username) {
    String usernameStr = new String(username);
    boolean successLogin = false;
    if (clients.containsKey(usernameStr)) {
      new ConnectResponse(false, username, "Username already exists!".getBytes())
          .writeToStream(out);
    }
    else if (clients.size() >= MAXIMUM_CONNECTED_USER_NUM) {
      new ConnectResponse(false, username, "The chatroom is full. Please wait for an available seat.".getBytes()).writeToStream(out);
    } else if (!Pattern.matches(VALID_USERNAME_PATTERN, usernameStr) || Pattern.matches(
        SYSTEM_RESERVED_WORD, usernameStr)) {
      new ConnectResponse(false, username,
          "Username is invalid!\nPlease choose a username with 3 - 12 characters (_, a-z, A-Z, 0-9). 'all' and 'admin' are reserved by the system."
              .getBytes()).writeToStream(out);
    } else {
      new ConnectResponse(true, username,
          ("Welcome, " + usernameStr + ". There are " + clients.size() + " other connected clients")
              .getBytes()).writeToStream(out);
      clients.put(new String(username), out);
      System.out.format(usernameStr + " is online now.\nOnline users(%d):\n", clients.size());
      showOnlineUsers(clients);
      successLogin = true;
    }
    return successLogin;
  }

  /**
   * Respond a insult message.
   * @param out a data output stream pointing to the client.
   * @param clients a map storing all connected users.
   * @param username the username of the sender.
   * @param toUsername the username of the recipient.
   * */
  public static void insultResponse(DataOutputStream out, Map<String, DataOutputStream> clients,
      byte[] username, byte[] toUsername) {
    if (!clients.containsKey(new String(toUsername)) || !clients
        .containsKey(new String(username))) {
      new FailedMessage("Username is invalid.".getBytes()).writeToStream(out);
    } else {
      SendInsultResponse sendInsultResponse = new SendInsultResponse(username, toUsername);
      for (DataOutputStream dataOutputStream : clients.values()) {
        sendInsultResponse.writeToStream(dataOutputStream);
      }
    }
  }

  /**
   * Respond a direct message.
   * @param out a data output stream pointing to the client.
   * @param clients a map storing all connected users.
   * @param username the username of the sender.
   * @param toUsername the username of the recipient.
   * @param message the content of the direct message.
   * */
  public static void directMsgResponse(DataOutputStream out, Map<String, DataOutputStream> clients,
      byte[] username, byte[] toUsername, byte[] message) {
    String senderName = new String(username);
    String recipientName = new String(toUsername);
    if (!clients.containsKey(senderName) || !clients.containsKey(recipientName)) {
      new FailedMessage("Username is invalid".getBytes()).writeToStream(out);
    } else if (senderName.equals(recipientName)) {
      new FailedMessage("Please do not try sending a msg to yourself.".getBytes())
          .writeToStream(out);
    } else {
      new DirectMessage(username, toUsername, message).writeToStream(clients.get(recipientName));
    }

  }

  /**
   * Respond a broadcast message.
   * @param out a data output stream pointing to the client.
   * @param clients a map storing all connected users.
   * @param username the username of the sender.
   * @param message the content of the direct message.
   * */
  public static void broadcastResponse(DataOutputStream out, Map<String, DataOutputStream> clients,
      byte[] username, byte[] message) {
    if (!clients.containsKey(new String(username)) && !Arrays
        .equals(username, "[Admin]".getBytes())) {
      new FailedMessage("Sender Username is invalid.".getBytes()).writeToStream(out);
    } else {
      BroadcastMessage boardCastMessage = new BroadcastMessage(username, message);
      for (DataOutputStream dataOutputStream : clients.values()) {
        boardCastMessage.writeToStream(dataOutputStream);
      }
    }

  }

  /**
   * Respond a query message.
   * @param out a data output stream pointing to the client.
   * @param clients a map storing all connected users.
   * @param username the username of the sender.
   * */
  public static void queryResponse(DataOutputStream out, Map<String, DataOutputStream> clients,
      byte[] username) {
    if (!clients.containsKey(new String(username))) {
      new FailedMessage("Wrong username".getBytes()).writeToStream(out);
    } else {
      new QueryResponse(clients.keySet()).writeToStream(out);
    }
  }

  /**
   * Respond a disconnect message.
   * @param out a data output stream pointing to the client.
   * @param clients a map storing all connected users.
   * @param username the username sent by the client.
   * */
  public static void disconnectResponse(DataOutputStream out, Map<String, DataOutputStream> clients,
      byte[] username) {
    String usernameStr = new String(username);
    if (!clients.containsKey(new String(username))) {
      new FailedMessage("Username doesn't exist.".getBytes()).writeToStream(out);
    } else {
      new DisconnectResponse("Your are no longer connected.".getBytes()).writeToStream(out);
      clients.remove(new String(username));
      System.out.format(usernameStr + " is offline now.\nOnline users(%d):\n", clients.size());
      showOnlineUsers(clients);
    }
  }

  /**
   * Print out a list of online users.
   * @param clients a map of connected clients
   * */
  public static void showOnlineUsers(Map<String, DataOutputStream> clients) {
    for (String client : clients.keySet()) {
      System.out.println(client + "\t");
    }
    System.out.println("--------------------");
  }
}
