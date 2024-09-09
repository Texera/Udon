// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package edu.uci.ics.amber.engine.architecture.worker.controlreturns

@SerialVersionUID(0L)
final case class StateReturn(
    bytes: _root_.com.google.protobuf.ByteString
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[StateReturn] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = bytes
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeBytesSize(1, __value)
        }
      };
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): _root_.scala.Unit = {
      {
        val __v = bytes
        if (!__v.isEmpty) {
          _output__.writeBytes(1, __v)
        }
      };
    }
    def withBytes(__v: _root_.com.google.protobuf.ByteString): StateReturn = copy(bytes = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = bytes
          if (__t != _root_.com.google.protobuf.ByteString.EMPTY) __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PByteString(bytes)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToSingleLineUnicodeString(this)
    def companion = edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn
    // @@protoc_insertion_point(GeneratedMessage[edu.uci.ics.amber.engine.architecture.worker.StateReturn])
}

object StateReturn extends scalapb.GeneratedMessageCompanion[edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn = {
    var __bytes: _root_.com.google.protobuf.ByteString = _root_.com.google.protobuf.ByteString.EMPTY
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __bytes = _input__.readBytes()
        case tag => _input__.skipField(tag)
      }
    }
    edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn(
        bytes = __bytes
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn(
        bytes = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.com.google.protobuf.ByteString]).getOrElse(_root_.com.google.protobuf.ByteString.EMPTY)
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = ControlreturnsProto.javaDescriptor.getMessageTypes().get(4)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = ControlreturnsProto.scalaDescriptor.messages(4)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn(
    bytes = _root_.com.google.protobuf.ByteString.EMPTY
  )
  implicit class StateReturnLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn](_l) {
    def bytes: _root_.scalapb.lenses.Lens[UpperPB, _root_.com.google.protobuf.ByteString] = field(_.bytes)((c_, f_) => c_.copy(bytes = f_))
  }
  final val BYTES_FIELD_NUMBER = 1
  def of(
    bytes: _root_.com.google.protobuf.ByteString
  ): _root_.edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn = _root_.edu.uci.ics.amber.engine.architecture.worker.controlreturns.StateReturn(
    bytes
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[edu.uci.ics.amber.engine.architecture.worker.StateReturn])
}
