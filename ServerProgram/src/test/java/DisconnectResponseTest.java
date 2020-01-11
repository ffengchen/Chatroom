import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DisconnectResponseTest {
  private DisconnectResponse disconnectResponse;
  private byte[] message;

  @Before
  public void setUp() throws Exception {
    message = "You are no longer connected.".getBytes();
    disconnectResponse = new DisconnectResponse(message);
  }


  @Test
  public void writeToStream() throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream("src/test/java/disconnectResponse.bin");
    DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
    FileInputStream fileInputStream = new FileInputStream("src/test/java/disconnectResponse.bin");
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
    disconnectResponse.writeToStream(dataOutputStream);

    int identifier = dataInputStream.readInt();
    dataInputStream.readChar();
    int len = dataInputStream.readInt();
    dataInputStream.readChar();
    byte[] msg = new byte[len];
    dataInputStream.readFully(msg);
    Assert.assertEquals(MessageIdentifiers.DISCONNECT_RESPONSE, identifier);
    Assert.assertEquals(message.length, len);
    assertArrayEquals(message, msg);
  }

  @Test
  public void testEqualsA() {
    Assert.assertEquals(disconnectResponse, disconnectResponse);
  }

  @Test
  public void testEqualsB() {
    Assert.assertEquals(disconnectResponse, new DisconnectResponse(message));
  }

  @Test
  public void testEqualsC() {
    Assert.assertFalse(disconnectResponse.equals(null));
  }

  @Test
  public void testEqualsD() {
    byte[] message = "You are no longer connected!".getBytes();
    DisconnectResponse disconnectResponse1 = new DisconnectResponse(message);
    Assert.assertFalse(disconnectResponse1.equals(disconnectResponse));
  }

  @Test
  public void testHashCode() {
    byte[] message = "You are no longer connected.".getBytes();
    DisconnectResponse disconnectResponse1 = new DisconnectResponse(message);
    Assert.assertEquals(disconnectResponse1.hashCode(), disconnectResponse.hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Protocol.DisconnectResponse{" +
        "MessageIdentifier=" + MessageIdentifiers.DISCONNECT_RESPONSE +
        ", msgSize=" + message.length +
        ", message=" + Arrays.toString(message) +
        '}', disconnectResponse.toString());
  }
}