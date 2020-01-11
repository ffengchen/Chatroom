import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Set;

/**
 * Represents a response to queries.
 * */
public class QueryResponse implements ChatroomProtocol {

  private int MessageIdentifier= MessageIdentifiers.QUERY_USER_RESPONSE;
  private  int numberOfUsers;
  private Set<String> clientsUsernames;

  /**
   * Sets up a query response.
   * @param clientsUsernames a set of the names of all connected users.
   * */
  public QueryResponse( Set<String> clientsUsernames) {
    this.numberOfUsers = clientsUsernames.size();
    this.clientsUsernames = clientsUsernames;
  }

  /**
   * Writes the response to the output stream.
   * @param out the output stream.
   * */
  @Override
  public void writeToStream(DataOutputStream out) {
    try {
      out.writeInt(MessageIdentifier);
      out.writeChar(' ');

      out.writeInt(numberOfUsers);

      for(String name : clientsUsernames) {
        out.writeChar(' ');

        out.writeInt(name.length());
        out.writeChar(' ');

        out.writeBytes(name);
      }
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks if two responses are equal.
   * */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    QueryResponse that = (QueryResponse) o;

    if (MessageIdentifier != that.MessageIdentifier) {
      return false;
    }
    if (numberOfUsers != that.numberOfUsers) {
      return false;
    }
    return clientsUsernames != null ? clientsUsernames.equals(that.clientsUsernames)
        : that.clientsUsernames == null;
  }

  /**
   * Generates a hashcode.
   * */
  @Override
  public int hashCode() {
    int result = MessageIdentifier;
    result = 31 * result + numberOfUsers;
    result = 31 * result + (clientsUsernames != null ? clientsUsernames.hashCode() : 0);
    return result;
  }

  /**
   * Generates a string of the response.
   * */
  @Override
  public String toString() {
    return "Protocol.QueryResponse{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", numberOfUsers=" + numberOfUsers +
        ", clientsUsernames=" + clientsUsernames +
        '}';
  }
}
