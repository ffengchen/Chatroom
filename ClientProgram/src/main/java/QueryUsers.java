import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
/**
 * QueryUsers data with message identifier, size and content.
 */
public class QueryUsers implements ChatroomProtocol {
  private int MessageIdentifier= MessageIdentifiers.QUERY_CONNECTED_USERS;
  private int usernameSize;
  private byte[] username;

  /**
   * Initialize data usernameSize and username based on the input.

   * @param username the username of the client who wants to send a query.
   */
  public QueryUsers(byte[] username) {
    this.usernameSize = username.length;
    this.username = username;
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

      out.writeInt(usernameSize);
      out.writeChar(' ');

      out.write(username);

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

    QueryUsers that = (QueryUsers) o;

    if (MessageIdentifier != that.MessageIdentifier) {
      return false;
    }
    if (usernameSize != that.usernameSize) {
      return false;
    }
    return Arrays.equals(username, that.username);
  }

  @Override
  public int hashCode() {
    int result = MessageIdentifier;
    result = 31 * result + usernameSize;
    result = 31 * result + Arrays.hashCode(username);
    return result;
  }

  @Override
  public String toString() {
    return "QueryUsers{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", usernameSize=" + usernameSize +
        ", username=" + Arrays.toString(username) +
        '}';
  }
}
