import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Publisher {
  public static void main(String[] args) {
    Context context = ZMQ.context(1);

    Socket socket = context.socket(ZMQ.PUB);
    socket.bind("tcp://localhost:5000");

    String[] topics = { "tech", "music", "design" };
    while (!Thread.currentThread().isInterrupted()) {
      for (int i = 0; i < topics.length; i++) {
        if (socket.send(topics[i], 0))
          System.out.println("Publish: " + topics[i]);
      }
    }

    socket.close();
    context.term();
  }
}

