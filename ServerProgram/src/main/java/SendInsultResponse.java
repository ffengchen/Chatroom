import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import org.json.simple.JSONArray;

/**
 * Represents a response to insult message.
 * */
public class SendInsultResponse implements ChatroomProtocol {
  private final int MessageIdentifier= MessageIdentifiers.INSULT_RESPONSE;
  private int senderUsernameSize;
  private byte[] senderUsername;
  private int recipientUsernameSize;
  private byte[] recipientUsername;
  private byte[] insult;

  /**
   * Sets up a insult response.
   * @param senderUsername the username of the sender.
   * @param recipientUsername the username of the recipient.
   * */
  public SendInsultResponse(byte[] senderUsername, byte[] recipientUsername) {
    this.senderUsername = senderUsername;
    this.recipientUsername = recipientUsername;
    this.senderUsernameSize =senderUsername.length;
    this.recipientUsernameSize = recipientUsername.length;
    this.insult = createInsult();
  }

  /**
   * Writes the response to the output stream.
   * */
  @Override
  public void writeToStream(DataOutputStream out) {
    try {
      out.writeInt(MessageIdentifier);
      out.writeChar(' ');

      out.writeInt(senderUsernameSize);
      out.writeChar(' ');

      out.write(senderUsername);
      out.writeChar(' ');

      out.writeInt(recipientUsernameSize);
      out.writeChar(' ');

      out.write(recipientUsername);
      out.writeChar(' ');

      out.writeInt(insult.length);
      out.writeChar(' ');

      out.write(insult);

      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a random insult.
   * @return a randomly generated insult sentence.
   * */
  public byte[] createInsult()  {
    Random random = new Random();
    String sentence="";
    try {
      File grammarFile = new File("libs/grammars/insult_grammar.json");
      ParserHelper.getGrammar(grammarFile);
      JSONArray jsonArray = (JSONArray) ParserHelper.grammar.get("start");
      sentence = ParserHelper.parse((String)jsonArray.get(random.nextInt(jsonArray.size())));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sentence.getBytes();
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
    SendInsultResponse that = (SendInsultResponse) o;
    return MessageIdentifier == that.MessageIdentifier &&
        senderUsernameSize == that.senderUsernameSize &&
        recipientUsernameSize == that.recipientUsernameSize &&
        Arrays.equals(senderUsername, that.senderUsername) &&
        Arrays.equals(recipientUsername, that.recipientUsername);
  }

  /**
   * Generates a hashcode.
   * */
  @Override
  public int hashCode() {
    int result = Objects.hash(MessageIdentifier, senderUsernameSize, recipientUsernameSize);
    result = 31 * result + Arrays.hashCode(senderUsername);
    result = 31 * result + Arrays.hashCode(recipientUsername);
    return result;
  }

  /**
   * Generates a string of this class.
   * */
  @Override
  public String toString() {
    return "SendInsultResponse{" +
        "MessageIdentifier=" + MessageIdentifier +
        ", senderUsernameSize=" + senderUsernameSize +
        ", senderUsername=" + Arrays.toString(senderUsername) +
        ", recipientUsernameSize=" + recipientUsernameSize +
        ", recipientUsername=" + Arrays.toString(recipientUsername) +
        '}';
  }
}
