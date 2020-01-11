import java.io.IOException;

/**
 * Client Driver to start the client.
 */
public class ClientDriver {

  /**
   * Main method to of ClientDriver.

   * @param args commandline arguments.
   */
  public static void main(String[] args) {
    try {
      new Client("localhost", 9999);
    } catch (IOException e) {
      System.out.println("Server is offline. Please come back later.");
    }
  }
}
