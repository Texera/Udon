package edu.uci.ics.amber.engine.architecture.controller.promisehandlers

import com.google.protobuf.timestamp.Timestamp
import edu.uci.ics.amber.engine.architecture.controller.ControllerAsyncRPCHandlerInitializer
import edu.uci.ics.amber.engine.architecture.controller.ControllerEvent.PythonConsoleMessageTriggered
import edu.uci.ics.amber.engine.architecture.controller.promisehandlers.PythonConsoleMessageHandler.PythonConsoleMessage
import edu.uci.ics.amber.engine.architecture.worker.controlcommands.PythonConsoleMessageV2
import edu.uci.ics.amber.engine.common.rpc.AsyncRPCServer.ControlCommand

object PythonConsoleMessageHandler {

  final case class PythonConsoleMessage(
      timestamp: Timestamp,
      level: String,
      source: String,
      message: String
  ) extends ControlCommand[Unit]
}

trait PythonConsoleMessageHandler {
  this: ControllerAsyncRPCHandlerInitializer =>
  registerHandler { (msg: PythonConsoleMessage, sender) =>
    {
      // report the print message to the frontend
      sendToClient(
        PythonConsoleMessageTriggered(
          workflow.getOperator(sender).id.operator,
          sender.name,
          PythonConsoleMessageV2(msg.timestamp, msg.level, msg.level, msg.message)
        )
      )
    }
  }
}
