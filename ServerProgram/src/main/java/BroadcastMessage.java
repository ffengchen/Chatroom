import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a piece of broadcast message.
 */
public class BroadcastMessage implements ChatroomProtocol {

  private final int MessageIdentifier = MessageIdentifiers.BROADCAST_MESSAGE;
  private int senderUsernameSize;
  private byte[] senderUsername;
  private int messageSize;
  private byte[] message;

  /**
   * Sets up a piece of broadcast message.
   *
   * @param senderUsername the username of the sender.
   * @param message        the content of the message.
   */
  public BroadcastMessage(byte[] senderUsername, byte[] message) {
    this.senderUsername = senderUsername;
    this.senderUsernameSize = senderUsername.length;
    this.message = message;
    this.messageSize = message.length;
  }

  /**
   * Checks if two pieces of message are equal.
   */
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

  /**
   * Generates a hashcode.
   */
  @Override
  public int hashCode() {
    int result = MessageIdentifier;
    result = 31 * result + senderUsernameSize;
    result = 31 * result + Arrays.hashCode(senderUsername);
    result = 31 * result + messageSize;
    result = 31 * result + Arrays.hashCode(message);
    return result;
  }

  /**
   * Generates a string of this class.
   */
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

  /**
   * Writes the message to the output stream.
   */
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
