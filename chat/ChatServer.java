import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class ChatServer {
  public static void main(String[] args) {
    Context context = ZMQ.context(1);

    Socket pub = context.socket(ZMQ.PUB);
    pub.bind("tcp://localhost:5000");

    Socket receive = context.socket(ZMQ.PULL);
    receive.bind("tcp://localhost:5001");

    while (!Thread.currentThread().isInterrupted()) {
      String message = receive.recvStr(0);
      System.out.println("Received: " + message);
      pub.send(message, 0);
    }

    receive.close();
    pub.close();
    context.term();
  }
}

