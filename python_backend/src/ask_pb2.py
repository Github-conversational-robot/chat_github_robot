# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: ask.proto
# Protobuf Python Version: 4.25.0
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\task.proto\x12\x03\x61sk\"\x1f\n\x0fLoadRepoRequest\x12\x0c\n\x04name\x18\x01 \x01(\t\"1\n\rLoadRepoReply\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"\'\n\x17\x45stablishSessionRequest\x12\x0c\n\x04name\x18\x01 \x01(\t\"9\n\x15\x45stablishSessionReply\x12\x0f\n\x07success\x18\x01 \x01(\x08\x12\x0f\n\x07message\x18\x02 \x01(\t\"1\n\x0fQuestionRequest\x12\x0c\n\x04name\x18\x01 \x01(\t\x12\x10\n\x08question\x18\x02 \x01(\t\"0\n\rQuestionReply\x12\x0e\n\x06\x61nswer\x18\x01 \x01(\x08\x12\x0f\n\x07history\x18\x02 \x01(\t\"\x1c\n\x0cHelloRequest\x12\x0c\n\x04name\x18\x01 \x01(\t\"\x1d\n\nHelloReply\x12\x0f\n\x07message\x18\x01 \x01(\t2\xa6\x03\n\x0cLangchainAsk\x12\x30\n\x08SayHello\x12\x11.ask.HelloRequest\x1a\x0f.ask.HelloReply\"\x00\x12=\n\x13SayHelloStreamReply\x12\x11.ask.HelloRequest\x1a\x0f.ask.HelloReply\"\x00\x30\x01\x12>\n\x12SayHelloBidiStream\x12\x11.ask.HelloRequest\x1a\x0f.ask.HelloReply\"\x00(\x01\x30\x01\x12\x42\n\x14LoadGithubRepository\x12\x14.ask.LoadRepoRequest\x1a\x12.ask.LoadRepoReply\"\x00\x12N\n\x10\x45stablishSession\x12\x1c.ask.EstablishSessionRequest\x1a\x1a.ask.EstablishSessionReply\"\x00\x12Q\n\x13processUserQuestion\x12\x1c.ask.EstablishSessionRequest\x1a\x1a.ask.EstablishSessionReply\"\x00\x42)\n\x15io.grpc.langchain.askB\x08\x41skProtoP\x01\xa2\x02\x03HLWb\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'ask_pb2', _globals)
if _descriptor._USE_C_DESCRIPTORS == False:
  _globals['DESCRIPTOR']._options = None
  _globals['DESCRIPTOR']._serialized_options = b'\n\025io.grpc.langchain.askB\010AskProtoP\001\242\002\003HLW'
  _globals['_LOADREPOREQUEST']._serialized_start=18
  _globals['_LOADREPOREQUEST']._serialized_end=49
  _globals['_LOADREPOREPLY']._serialized_start=51
  _globals['_LOADREPOREPLY']._serialized_end=100
  _globals['_ESTABLISHSESSIONREQUEST']._serialized_start=102
  _globals['_ESTABLISHSESSIONREQUEST']._serialized_end=141
  _globals['_ESTABLISHSESSIONREPLY']._serialized_start=143
  _globals['_ESTABLISHSESSIONREPLY']._serialized_end=200
  _globals['_QUESTIONREQUEST']._serialized_start=202
  _globals['_QUESTIONREQUEST']._serialized_end=251
  _globals['_QUESTIONREPLY']._serialized_start=253
  _globals['_QUESTIONREPLY']._serialized_end=301
  _globals['_HELLOREQUEST']._serialized_start=303
  _globals['_HELLOREQUEST']._serialized_end=331
  _globals['_HELLOREPLY']._serialized_start=333
  _globals['_HELLOREPLY']._serialized_end=362
  _globals['_LANGCHAINASK']._serialized_start=365
  _globals['_LANGCHAINASK']._serialized_end=787
# @@protoc_insertion_point(module_scope)