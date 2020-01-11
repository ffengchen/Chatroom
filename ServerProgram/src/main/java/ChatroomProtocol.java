import java.io.DataOutputStream;

public interface ChatroomProtocol {
  void writeToStream(DataOutputStream outputStream);
}
