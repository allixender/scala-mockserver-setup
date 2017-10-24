package mockserv

import org.mockserver.client.server.MockServerClient
import org.mockserver.integration.ClientAndServer
import org.mockserver.integration.ClientAndServer.startClientAndServer

object Hello extends Greeting with App {

  var mockServer: ClientAndServer = _
  var mockServerClient: MockServerClient = _

  println(args mkString)

  def initializeMockserver() : Unit = {

    mockServer = startClientAndServer(1080)
    mockServerClient = new MockServerClient("localhost", 1080)

    val mocka = new CswMockServer()(mockServerClient)

    mocka.noCswGetCapa300()
    mocka.cswGetCapaLocal()
    mocka.cswGetCapaLocalFuller()
    mocka.postCswTransactionOk()
    mocka.getIndexUpdateOk()
  }

  def stopMockserver()(implicit mockServerClient: MockServerClient) : Unit = {
    mockServerClient.stop()
  }

  args.head match {
    case "start" => initializeMockserver()
    case "stop" => stopMockserver()(mockServerClient)
    case _ => println(greeting)
  }

}

trait Greeting {
  lazy val greeting: String = "Usage $1 [start|stop]"
}

