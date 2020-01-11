import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents a piece of direct message.
 */
public class DirectMessage implements ChatroomProtocol {

  private final int MessageIdentifier = MessageIdentifiers.DIRECT_MESSAGE;
  private int senderUsernameSize;
  private byte[] senderUsername;
  private int recipientUsernameSize;
  private byte[] recipientUsername;
  private int messageSize;
  private byte[] message;

  /**
   * Initialize Direct Message data.
   * @param senderUsername senderUsername
   * @param recipientUsername recipientUsername
   * @param message message
   */
  public DirectMessage(byte[] senderUsername, byte[] recipientUsername, byte[] message) {
    this.senderUsername = senderUsername;
    this.recipientUsername = recipientUsername;
    this.message = message;
    this.senderUsernameSize = senderUsername.length;
    this.recipientUsernameSize = recipientUsername.length;
    this.messageSize = message.length;
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

      out.writeInt(recipientUsernameSize);
      out.writeChar(' ');

      out.write(recipientUsername);
      out.writeChar(' ');

      out.writeInt(messageSize);
      out.writeChar(' ');

      out.write(message);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
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

    DirectMessage that = (DirectMessage) o;

    if (MessageIdentifier != that.MessageIdentifier) {
      return false;
    }
    if (senderUsernameSize != that.senderUsernameSize) {
      return false;
    }
    if (recipientUsernameSize != that.recipientUsernameSize) {
      return false;
    }
    if (messageSize != that.messageSize) {
      return false;
    }
    if (!Arrays.equals(senderUsername, that.senderUsername)) {
      return false;
    }
    if (!Arrays.equals(recipientUsername, that.recipientUsername)) {
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
    result = 31 * result + recipientUsernameSize;
    result = 31 * result + Arrays.hashCode(recipientUsername);
    result = 31 * result + messageSize;
    result = 31 * result + Arrays.hashCode(message);
    return result;
  }

  /**
   * Generates a string of this class.
   */
  @Override
  public String toString() {
    return "Protocol.DirectMessage{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", senderUsernameSize=" + senderUsernameSize +
        ", senderUsername=" + Arrays.toString(senderUsername) +
        ", recipientUsernameSize=" + recipientUsernameSize +
        ", recipientUsername=" + Arrays.toString(recipientUsername) +
        ", messageSize=" + messageSize +
        ", message=" + Arrays.toString(message) +
        '}';
  }
}
