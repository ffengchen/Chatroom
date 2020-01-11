import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConnectResponseTest {
  private ConnectResponse connectResponse;
  private byte[] username;
  private byte[] message;

  @Before
  public void setUp() throws Exception {
    username = "AAA".getBytes();
    message = "Connect".getBytes();
    connectResponse = new ConnectResponse(true, username, message);
  }


  @Test
  public void writeToStream() throws IOException {
    FileOutputStream fileOutputStream = new FileOutputStream("src/test/java/connectResponse.bin");
    DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
    FileInputStream fileInputStream = new FileInputStream("src/test/java/connectResponse.bin");
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
    connectResponse.writeToStream(dataOutputStream);

    int identifier = dataInputStream.readInt();
    dataInputStream.readChar();
    boolean flag = dataInputStream.readBoolean();
    dataInputStream.readChar();
    int nameLen = dataInputStream.readInt();
    dataInputStream.readChar();
    byte[] name = new byte[nameLen];
    dataInputStream.readFully(name);
    dataInputStream.readChar();
    int msgLen = dataInputStream.readInt();
    byte[] msg = new byte[msgLen];
    dataInputStream.readChar();
    dataInputStream.readFully(msg);
    Assert.assertEquals(MessageIdentifiers.CONNECT_RESPONSE, identifier);
    Assert.assertEquals(true, flag);
    Assert.assertEquals(username.length, nameLen);
    assertArrayEquals(username, name);
    Assert.assertEquals(message.length, msgLen);
    assertArrayEquals(message, msg);
  }

  @Test
  public void testEqualsA() {
    Assert.assertEquals(connectResponse, connectResponse);
  }

  @Test
  public void testEqualsB() {
    byte[] username = "AAA".getBytes();
    byte[] message = "Connect".getBytes();
    ConnectResponse connectResponse1 = new ConnectResponse(true, username, message);
    Assert.assertEquals(connectResponse1, connectResponse);
  }

  @Test
  public void testEqualsC() {
    byte[] username = "AAA".getBytes();
    byte[] message = "connect".getBytes();
    ConnectResponse connectResponse1 = new ConnectResponse(true, username, message);
    Assert.assertFalse(connectResponse1.equals(connectResponse));
  }

  @Test
  public void testEqualsD() {
    byte[] username = "AAA".getBytes();
    byte[] message = "Connect".getBytes();
    ConnectResponse connectResponse1 = new ConnectResponse(false, username, message);
    Assert.assertFalse(connectResponse1.equals(connectResponse));
  }

  @Test
  public void testEqualsE() {
    Assert.assertFalse(connectResponse.equals(new ConnectResponseTest()));
  }

  @Test
  public void testEqualsF() {
    Assert.assertFalse(connectResponse.equals(null));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(connectResponse.hashCode(), new ConnectResponse(true, username, message).hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Protocol.ConnectResponse{" +
        "MessageIdentifier=" + MessageIdentifiers.CONNECT_RESPONSE +
        ", isSuccess=" + true +
        ", msgSize=" + message.length +
        ", message=" + Arrays.toString(message) +
        '}', connectResponse.toString());
  }
}