import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Broadcast message data with message identifier size and content, then write the data into dataoutputstream following the protocol.
 */
public class BroadcastMessage implements ChatroomProtocol {
  private final int MessageIdentifier = MessageIdentifiers.BROADCAST_MESSAGE;
  private int senderUsernameSize;
  private byte[] senderUsername;
  private int messageSize;
  private byte[] message;

  /**
   * Initialize data based on the byte[] input
   * @param senderUsername sender username
   * @param message message to broadcast
   */
  public BroadcastMessage(byte[] senderUsername, byte[] message) {
    this.senderUsername = senderUsername;
    this.senderUsernameSize = senderUsername.length;
    this.message = message;
    this.messageSize = message.length;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BroadcastMessage that = (BroadcastMessage) o;

    if (MessageIdentifier != that.MessageIdentifier) {
      return false;
    }
    if (senderUsernameSize != that.senderUsernameSize) {
      return false;
    }
    if (messageSize != that.messageSize) {
      return false;
    }
    if (!Arrays.equals(senderUsername, that.senderUsername)) {
      return false;
    }
    return Arrays.equals(message, that.message);
  }



  @Override
  public int hashCode() {
    int result = MessageIdentifier;
    result = 31 * result + senderUsernameSize;
    result = 31 * result + Arrays.hashCode(senderUsername);
    result = 31 * result + messageSize;
    result = 31 * result + Arrays.hashCode(message);
    return result;
  }

  @Override
  public String toString() {
    return "BoardCastMessage{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", senderUsernameSize=" + senderUsernameSize +
        ", senderUsername=" + Arrays.toString(senderUsername) +
        ", messageSize=" + messageSize +
        ", message=" + Arrays.toString(message) +
        '}';
  }

  @Override
  public void writeToStream(DataOutputStream out) {
    try {
      out.writeInt(MessageIdentifier);
      out.writeChar(' ');

      out.writeInt(senderUsernameSize);
      out.writeChar(' ');

      out.write(senderUsername);
      out.writeChar(' ');

      out.writeInt(messageSize);
      out.writeChar(' ');

      out.write(message);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
