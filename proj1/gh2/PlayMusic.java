package gh2;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class PlayMusic {
    public static void main(String[] args) {
        GuitarPlayer player = new GuitarPlayer(new java.io.File("D:\\ASMR\\春日影（钢琴版）CRYCHIC_爱给网_aigei_com.mid"));
        player.play();
        // You can also do this:
        // player.play();
    }
}
