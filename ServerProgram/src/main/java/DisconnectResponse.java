import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
/**
 * Represents a piece of disconnect response.
 */
public class DisconnectResponse implements ChatroomProtocol {


  private int MessageIdentifier=MessageIdentifiers.DISCONNECT_RESPONSE;
  private int msgSize;
  private byte[] message;

  /**
   * Initialize Direct Response data.
   * @param msg message
   */
  public DisconnectResponse(byte[] msg) {
    this.message = msg;
    this.msgSize = msg.length;
  }
  /**
   * Writes the message to the output stream.
   */
  @Override
  public void writeToStream(DataOutputStream out) {
    try {
      out.writeInt(MessageIdentifier);
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

    DisconnectResponse that = (DisconnectResponse) o;

    if (MessageIdentifier != that.MessageIdentifier) {
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
    result = 31 * result + msgSize;
    result = 31 * result + Arrays.hashCode(message);
    return result;
  }
  /**
   * Generates a string of this class.
   */
  @Override
  public String toString() {
    return "Protocol.DisconnectResponse{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", msgSize=" + msgSize +
        ", message=" + Arrays.toString(message) +
        '}';
  }
}
