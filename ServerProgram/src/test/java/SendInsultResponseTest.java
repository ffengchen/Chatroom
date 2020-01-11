import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SendInsultResponseTest {
  private SendInsultResponse sendInsultResponse;
  private int senderUsernameSize;
  private byte[] senderUsername;
  private int recipientUsernameSize;
  private byte[] recipientUsername;

  @Before
  public void setUp() throws Exception {
    senderUsername = "AAA".getBytes();
    senderUsernameSize = senderUsername.length;
    recipientUsername = "BBB".getBytes();
    recipientUsernameSize = recipientUsername.length;
    sendInsultResponse = new SendInsultResponse(senderUsername, recipientUsername);
    byte[] insult = sendInsultResponse.createInsult();
  }

  @Test
  public void writeToStream() {
  }

  @Test
  public void createInsult() {
//    Assert.assertEquals(insult, sendInsultResponse.createInsult());
  }

  @Test
  public void testEqualsA() {
    Assert.assertEquals(sendInsultResponse, new SendInsultResponse(senderUsername, recipientUsername));
  }

  @Test
  public void testEqualsB() {
    Assert.assertEquals(sendInsultResponse, sendInsultResponse);
  }

  @Test
  public void testEqualsC() {
    Assert.assertFalse(sendInsultResponse.equals(null));
  }

  @Test
  public void testEqualsD() {
    Assert.assertFalse(sendInsultResponse.equals(new SendInsultResponse("CCC".getBytes(), "BBB".getBytes())));
  }

  @Test
  public void testEqualsE() {
    Assert.assertFalse(sendInsultResponse.equals(new SendInsultResponse("AAA".getBytes(), "CCC".getBytes())));
  }

  @Test
  public void testHashCode() {
    Assert.assertEquals(sendInsultResponse.hashCode(), new SendInsultResponse(senderUsername, recipientUsername).hashCode());
  }

  @Test
  public void testToString() {
    Assert.assertEquals("SendInsultResponse{" +
        "MessageIdentifier=" + MessageIdentifiers.INSULT_RESPONSE +
        ", senderUsernameSize=" + senderUsernameSize +
        ", senderUsername=" + Arrays.toString(senderUsername) +
        ", recipientUsernameSize=" + recipientUsernameSize +
        ", recipientUsername=" + Arrays.toString(recipientUsername) +
        '}', sendInsultResponse.toString());
  }
}