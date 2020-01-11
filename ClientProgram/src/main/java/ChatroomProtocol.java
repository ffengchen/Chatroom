import java.io.DataOutputStream;

/**
 * Interface for different message types to implement to write to dataOutputStream.
 */
public interface ChatroomProtocol {
  void writeToStream(DataOutputStream outputStream);
}
