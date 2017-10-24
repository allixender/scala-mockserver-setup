package mockserv

import com.github.unisay.mockserver.scala.DSL._
import com.github.unisay.mockserver.scala.DSL.Statuses._
import org.mockserver.client.server.MockServerClient

import scala.io.Source
import scala.language.postfixOps

class CswMockServer()(implicit mockServerClient: MockServerClient) {

  val capaAsString = Source.fromURL(this.getClass().getResource("/pycsw.local.capabilities.xml")).getLines.mkString("\n")
  val insertAsString = Source.fromURL(this.getClass().getResource("/pycsw.local.insertresponse.xml")).getLines.mkString("\n")

  // implicit val mockServerClient = new MockServerClient("localhost", 1080)

  def noCswGetCapa300() : Unit = {
    when get "/pycsw/csw/" has {
      param("request", "GetCapabilities") and
        param("service", "CSW") and
        param("version", "3.0.0")
    } respond {
      BadRequest
    } always
  }

  def cswGetCapaLocal() : Unit = {
    when get "/pycsw/csw/" has {
      param("request", "GetCapabilities") and
        param("service", "CSW") and
        param("version", "2.0.2")
    } respond Ok + body(capaAsString) always
  }

  // /?service=CSW&version=2.0.2&request=GetCapabilities&sections=OperationsMetadata
  def cswGetCapaLocalFuller() : Unit = {
    when get "/pycsw/csw/" has {
      param("service", "CSW") and
        param("version", "2.0.2") and
      param("request", "GetCapabilities") and
      param("sections", "OperationsMetadata")
    } respond Ok + body(capaAsString) always
  }

  def postCswTransactionOk() : Unit = {
    when post "/pycsw/csw" respond Ok + body(insertAsString) always
  }

  def getIndexUpdateOk() : Unit = {
    when get "/cswi-api/v1/buildIndex/smart" respond {
      Ok + body("building index for smart")
    } always
  }

  // http://localhost:1080/pycsw/csw?request=GetCapabilities&version=2.0.2&service=CSW

}
