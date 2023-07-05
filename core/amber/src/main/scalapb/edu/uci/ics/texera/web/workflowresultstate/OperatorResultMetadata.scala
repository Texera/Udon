// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package edu.uci.ics.texera.web.workflowresultstate

@SerialVersionUID(0L)
final case class OperatorResultMetadata(
    tupleCount: _root_.scala.Int = 0,
    changeDetector: _root_.scala.Predef.String = ""
    ) extends scalapb.GeneratedMessage with scalapb.lenses.Updatable[OperatorResultMetadata] {
    @transient
    private[this] var __serializedSizeCachedValue: _root_.scala.Int = 0
    private[this] def __computeSerializedValue(): _root_.scala.Int = {
      var __size = 0
      
      {
        val __value = tupleCount
        if (__value != 0) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeInt32Size(1, __value)
        }
      };
      
      {
        val __value = changeDetector
        if (!__value.isEmpty) {
          __size += _root_.com.google.protobuf.CodedOutputStream.computeStringSize(2, __value)
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
        val __v = tupleCount
        if (__v != 0) {
          _output__.writeInt32(1, __v)
        }
      };
      {
        val __v = changeDetector
        if (!__v.isEmpty) {
          _output__.writeString(2, __v)
        }
      };
    }
    def withTupleCount(__v: _root_.scala.Int): OperatorResultMetadata = copy(tupleCount = __v)
    def withChangeDetector(__v: _root_.scala.Predef.String): OperatorResultMetadata = copy(changeDetector = __v)
    def getFieldByNumber(__fieldNumber: _root_.scala.Int): _root_.scala.Any = {
      (__fieldNumber: @_root_.scala.unchecked) match {
        case 1 => {
          val __t = tupleCount
          if (__t != 0) __t else null
        }
        case 2 => {
          val __t = changeDetector
          if (__t != "") __t else null
        }
      }
    }
    def getField(__field: _root_.scalapb.descriptors.FieldDescriptor): _root_.scalapb.descriptors.PValue = {
      _root_.scala.Predef.require(__field.containingMessage eq companion.scalaDescriptor)
      (__field.number: @_root_.scala.unchecked) match {
        case 1 => _root_.scalapb.descriptors.PInt(tupleCount)
        case 2 => _root_.scalapb.descriptors.PString(changeDetector)
      }
    }
    def toProtoString: _root_.scala.Predef.String = _root_.scalapb.TextFormat.printToSingleLineUnicodeString(this)
    def companion = edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata
    // @@protoc_insertion_point(GeneratedMessage[edu.uci.ics.texera.web.OperatorResultMetadata])
}

object OperatorResultMetadata extends scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata] {
  implicit def messageCompanion: scalapb.GeneratedMessageCompanion[edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata] = this
  def parseFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata = {
    var __tupleCount: _root_.scala.Int = 0
    var __changeDetector: _root_.scala.Predef.String = ""
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 8 =>
          __tupleCount = _input__.readInt32()
        case 18 =>
          __changeDetector = _input__.readStringRequireUtf8()
        case tag => _input__.skipField(tag)
      }
    }
    edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata(
        tupleCount = __tupleCount,
        changeDetector = __changeDetector
    )
  }
  implicit def messageReads: _root_.scalapb.descriptors.Reads[edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata] = _root_.scalapb.descriptors.Reads{
    case _root_.scalapb.descriptors.PMessage(__fieldsMap) =>
      _root_.scala.Predef.require(__fieldsMap.keys.forall(_.containingMessage eq scalaDescriptor), "FieldDescriptor does not match message type.")
      edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata(
        tupleCount = __fieldsMap.get(scalaDescriptor.findFieldByNumber(1).get).map(_.as[_root_.scala.Int]).getOrElse(0),
        changeDetector = __fieldsMap.get(scalaDescriptor.findFieldByNumber(2).get).map(_.as[_root_.scala.Predef.String]).getOrElse("")
      )
    case _ => throw new RuntimeException("Expected PMessage")
  }
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.Descriptor = WorkflowresultstateProto.javaDescriptor.getMessageTypes().get(1)
  def scalaDescriptor: _root_.scalapb.descriptors.Descriptor = WorkflowresultstateProto.scalaDescriptor.messages(1)
  def messageCompanionForFieldNumber(__number: _root_.scala.Int): _root_.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__number)
  lazy val nestedMessagesCompanions: Seq[_root_.scalapb.GeneratedMessageCompanion[_ <: _root_.scalapb.GeneratedMessage]] = Seq.empty
  def enumCompanionForFieldNumber(__fieldNumber: _root_.scala.Int): _root_.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__fieldNumber)
  lazy val defaultInstance = edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata(
    tupleCount = 0,
    changeDetector = ""
  )
  implicit class OperatorResultMetadataLens[UpperPB](_l: _root_.scalapb.lenses.Lens[UpperPB, edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata]) extends _root_.scalapb.lenses.ObjectLens[UpperPB, edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata](_l) {
    def tupleCount: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Int] = field(_.tupleCount)((c_, f_) => c_.copy(tupleCount = f_))
    def changeDetector: _root_.scalapb.lenses.Lens[UpperPB, _root_.scala.Predef.String] = field(_.changeDetector)((c_, f_) => c_.copy(changeDetector = f_))
  }
  final val TUPLE_COUNT_FIELD_NUMBER = 1
  final val CHANGE_DETECTOR_FIELD_NUMBER = 2
  def of(
    tupleCount: _root_.scala.Int,
    changeDetector: _root_.scala.Predef.String
  ): _root_.edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata = _root_.edu.uci.ics.texera.web.workflowresultstate.OperatorResultMetadata(
    tupleCount,
    changeDetector
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[edu.uci.ics.texera.web.OperatorResultMetadata])
}
