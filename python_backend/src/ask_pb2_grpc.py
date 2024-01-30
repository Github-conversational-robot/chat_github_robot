# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import ask_pb2 as ask__pb2


class LangchainAskStub(object):
    """The greeting service definition.
    """

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.SayHello = channel.unary_unary(
                '/ask.LangchainAsk/SayHello',
                request_serializer=ask__pb2.HelloRequest.SerializeToString,
                response_deserializer=ask__pb2.HelloReply.FromString,
                )
        self.SayHelloStreamReply = channel.unary_stream(
                '/ask.LangchainAsk/SayHelloStreamReply',
                request_serializer=ask__pb2.HelloRequest.SerializeToString,
                response_deserializer=ask__pb2.HelloReply.FromString,
                )
        self.SayHelloBidiStream = channel.stream_stream(
                '/ask.LangchainAsk/SayHelloBidiStream',
                request_serializer=ask__pb2.HelloRequest.SerializeToString,
                response_deserializer=ask__pb2.HelloReply.FromString,
                )
        self.LoadGithubRepository = channel.unary_unary(
                '/ask.LangchainAsk/LoadGithubRepository',
                request_serializer=ask__pb2.LoadRepoRequest.SerializeToString,
                response_deserializer=ask__pb2.LoadRepoReply.FromString,
                )
        self.EstablishSession = channel.unary_unary(
                '/ask.LangchainAsk/EstablishSession',
                request_serializer=ask__pb2.EstablishSessionRequest.SerializeToString,
                response_deserializer=ask__pb2.EstablishSessionReply.FromString,
                )
        self.ProcessUserQuestion = channel.unary_unary(
                '/ask.LangchainAsk/ProcessUserQuestion',
                request_serializer=ask__pb2.QuestionRequest.SerializeToString,
                response_deserializer=ask__pb2.QuestionReply.FromString,
                )


class LangchainAskServicer(object):
    """The greeting service definition.
    """

    def SayHello(self, request, context):
        """Sends a greeting
        """
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def SayHelloStreamReply(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def SayHelloBidiStream(self, request_iterator, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def LoadGithubRepository(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def EstablishSession(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def ProcessUserQuestion(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_LangchainAskServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'SayHello': grpc.unary_unary_rpc_method_handler(
                    servicer.SayHello,
                    request_deserializer=ask__pb2.HelloRequest.FromString,
                    response_serializer=ask__pb2.HelloReply.SerializeToString,
            ),
            'SayHelloStreamReply': grpc.unary_stream_rpc_method_handler(
                    servicer.SayHelloStreamReply,
                    request_deserializer=ask__pb2.HelloRequest.FromString,
                    response_serializer=ask__pb2.HelloReply.SerializeToString,
            ),
            'SayHelloBidiStream': grpc.stream_stream_rpc_method_handler(
                    servicer.SayHelloBidiStream,
                    request_deserializer=ask__pb2.HelloRequest.FromString,
                    response_serializer=ask__pb2.HelloReply.SerializeToString,
            ),
            'LoadGithubRepository': grpc.unary_unary_rpc_method_handler(
                    servicer.LoadGithubRepository,
                    request_deserializer=ask__pb2.LoadRepoRequest.FromString,
                    response_serializer=ask__pb2.LoadRepoReply.SerializeToString,
            ),
            'EstablishSession': grpc.unary_unary_rpc_method_handler(
                    servicer.EstablishSession,
                    request_deserializer=ask__pb2.EstablishSessionRequest.FromString,
                    response_serializer=ask__pb2.EstablishSessionReply.SerializeToString,
            ),
            'ProcessUserQuestion': grpc.unary_unary_rpc_method_handler(
                    servicer.ProcessUserQuestion,
                    request_deserializer=ask__pb2.QuestionRequest.FromString,
                    response_serializer=ask__pb2.QuestionReply.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'ask.LangchainAsk', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class LangchainAsk(object):
    """The greeting service definition.
    """

    @staticmethod
    def SayHello(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ask.LangchainAsk/SayHello',
            ask__pb2.HelloRequest.SerializeToString,
            ask__pb2.HelloReply.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def SayHelloStreamReply(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(request, target, '/ask.LangchainAsk/SayHelloStreamReply',
            ask__pb2.HelloRequest.SerializeToString,
            ask__pb2.HelloReply.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def SayHelloBidiStream(request_iterator,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.stream_stream(request_iterator, target, '/ask.LangchainAsk/SayHelloBidiStream',
            ask__pb2.HelloRequest.SerializeToString,
            ask__pb2.HelloReply.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def LoadGithubRepository(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ask.LangchainAsk/LoadGithubRepository',
            ask__pb2.LoadRepoRequest.SerializeToString,
            ask__pb2.LoadRepoReply.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def EstablishSession(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ask.LangchainAsk/EstablishSession',
            ask__pb2.EstablishSessionRequest.SerializeToString,
            ask__pb2.EstablishSessionReply.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def ProcessUserQuestion(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/ask.LangchainAsk/ProcessUserQuestion',
            ask__pb2.QuestionRequest.SerializeToString,
            ask__pb2.QuestionReply.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
