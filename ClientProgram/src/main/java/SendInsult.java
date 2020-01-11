import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * SendInsult data with message identifier, size and content.
 */
public class SendInsult implements ChatroomProtocol {
  private final int MessageIdentifier= MessageIdentifiers.SEND_INSULT;
  private int senderUsernameSize;
  private byte[] senderUsername;
  private int recipientUsernameSize;
  private byte[] recipientUsername;

  /**
   * Initialize the data senderUsername, recipientUsername, senderUsernameSize, recipientUsernameSize based on the input
   * @param senderUsername senderUsername
   * @param recipientUsername recipientUsername
   */
  public SendInsult(byte[] senderUsername, byte[] recipientUsername) {
    this.senderUsername = senderUsername;
    this.recipientUsername = recipientUsername;
    this.senderUsernameSize =senderUsername.length;
    this.recipientUsernameSize = recipientUsername.length;
  }
  /**
   * Write data to output stream following the protocol.
   * @param out output stream
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

      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SendInsult that = (SendInsult) o;

    if (MessageIdentifier != that.MessageIdentifier) {
      return false;
    }
    if (senderUsernameSize != that.senderUsernameSize) {
      return false;
    }
    if (recipientUsernameSize != that.recipientUsernameSize) {
      return false;
    }
    if (!Arrays.equals(senderUsername, that.senderUsername)) {
      return false;
    }
    return Arrays.equals(recipientUsername, that.recipientUsername);
  }

  @Override
  public int hashCode() {
    int result = MessageIdentifier;
    result = 31 * result + senderUsernameSize;
    result = 31 * result + Arrays.hashCode(senderUsername);
    result = 31 * result + recipientUsernameSize;
    result = 31 * result + Arrays.hashCode(recipientUsername);
    return result;
  }

  @Override
  public String toString() {
    return "SendInsult{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", senderUsernameSize=" + senderUsernameSize +
        ", senderUsername=" + Arrays.toString(senderUsername) +
        ", recipientUsernameSize=" + recipientUsernameSize +
        ", recipientUsername=" + Arrays.toString(recipientUsername) +
        '}';
  }
}
