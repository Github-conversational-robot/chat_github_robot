"""The Python implementation of the GRPC ask.LangchainAsk client."""

from __future__ import print_function

import logging

import grpc
import ask_pb2
import ask_pb2_grpc


def run():
    # NOTE(gRPC Python Team): .close() is possible on a channel and should be
    # used in circumstances in which the with statement does not fit the needs
    # of the code.
    print("Will try to greet world ...")
    with grpc.insecure_channel("localhost:50051") as channel:
        stub = ask_pb2_grpc.LangchainAskStub(channel)
        response = stub.LoadGithubRepository(ask_pb2.LoadRepoRequest(name="miniSQL"))
        print("client received: " + response.message)
        response = stub.ProcessUserQuestion(ask_pb2.QuestionRequest(name="miniSQL", question="让我们聊聊这个项目的大致内容"))
        response = stub.ProcessUserQuestion(ask_pb2.QuestionRequest(name="miniSQL", question="这个项目的结构是怎样的？"))
        print("client received: " + response.answer + "\nhistory: " + response.history)

    # print("client received: " + response.message)



if __name__ == "__main__":
    logging.basicConfig()
    run()
