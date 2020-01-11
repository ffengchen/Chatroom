import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Represents Connect response message.
 */
public class ConnectResponse implements ChatroomProtocol {

  private final int MessageIdentifier = MessageIdentifiers.CONNECT_RESPONSE;
  private boolean isSuccess;
  private int usernameSize;
  private byte[] username;
  private int msgSize;
  private byte[] message;

  /**
   * Initialize connect response message.
   *
   * @param isSuccess connect success or fail
   * @param username  username
   * @param message   connect message
   */
  public ConnectResponse(boolean isSuccess, byte[] username, byte[] message) {
    this.isSuccess = isSuccess;
    this.username = username;
    this.usernameSize = username.length;
    this.message = message;
    this.msgSize = message.length;
  }

  /**
   * Writes the message to the output stream.
   */
  @Override
  public void writeToStream(DataOutputStream out) {
    try {
      out.writeInt(MessageIdentifier);
      out.writeChar(' ');

      out.writeBoolean(isSuccess);
      out.writeChar(' ');

      out.writeInt(usernameSize);
      out.writeChar(' ');

      out.write(username);
      out.writeChar(' ');

      out.writeInt(msgSize);
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

    ConnectResponse that = (ConnectResponse) o;

    if (MessageIdentifier != that.MessageIdentifier) {
      return false;
    }
    if (isSuccess != that.isSuccess) {
      return false;
    }
    if (msgSize != that.msgSize) {
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
    result = 31 * result + (isSuccess ? 1 : 0);
    result = 31 * result + msgSize;
    result = 31 * result + Arrays.hashCode(message);
    return result;
  }

  /**
   * Generates a string of this class.
   */
  @Override
  public String toString() {
    return "Protocol.ConnectResponse{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", isSuccess=" + isSuccess +
        ", msgSize=" + msgSize +
        ", message=" + Arrays.toString(message) +
        '}';
  }

}
