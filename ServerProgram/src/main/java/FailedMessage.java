import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a piece of message describing the failure.
 * */
public class FailedMessage implements ChatroomProtocol {

  private int MessageIdentifier = MessageIdentifiers.FAILED_MESSAGE;
  private int messageSize;
  private byte[] message;

  /**
   * Sets up a new failed message.
   * @param message the content of the message.
   * */
  public FailedMessage(byte[] message) {
    this.messageSize = message.length;
    this.message = message;
  }

  /**
   * Writes the message to the output stream.
   * @param out the output stream.
   * */
  public void writeToStream(DataOutputStream out) {
    try {
      out.writeInt(MessageIdentifier);
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
   * Checks if two pieces of failed messages are equal.
   * */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FailedMessage that = (FailedMessage) o;
    return MessageIdentifier == that.MessageIdentifier &&
        messageSize == that.messageSize &&
        Arrays.equals(message, that.message);
  }
  
  /**
   * Generates a hashcode.
   * */
  @Override
  public int hashCode() {
    int result = Objects.hash(MessageIdentifier, messageSize);
    result = 31 * result + Arrays.hashCode(message);
    return result;
  }

  /**
   * Generates a string of this message.
   * */
  @Override
  public String toString() {
    return "Protocol.FailedMessage{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", messageSize=" + messageSize +
        ", message=" + Arrays.toString(message) +
        '}';
  }
}
