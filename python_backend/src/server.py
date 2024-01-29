from concurrent import futures
import logging

import grpc
import ask_pb2
import ask_pb2_grpc

from utils import *
from config import Config
config = Config()


class LangchainAsk(ask_pb2_grpc.LangchainAskServicer):
    def SayHello(self, request, context):
        return ask_pb2.HelloReply(message="Hello, %s!" % request.name)

    def LoadGithubRepository(self, request, context):
        reply = ask_pb2.LoadRepoReply()

        # 在本地的repo文件夹中找到该仓库的文件
        if not check_repo_folder_exists(request.name):
            reply.success = False
            reply.message = "Project '%s' not found." % request.name
            return reply

        # 1.Load 导入Document Loaders
        repo_dir = config.PATH_TO_GITREPO_DIR + request.name
        documents = load_documents_from_folder(repo_dir)

        # 2.Split 将Documents切分成块以便后续进行嵌入和向量存储
        chunked_documents = split_documents(documents, 200, 10)

        # 3.Store 将分割嵌入并存储在矢量数据库MO中
        add_documents_to_vectorstore(request.name, chunked_documents)

        # 构建 LoadRepoReply 响应消息
        reply.success = True
        reply.message = "Project loaded successfully."

        return reply

    def EstablishSession(self, request, context):
        # 构建 EstablishSessionReply 响应消息
        reply = ask_pb2.EstablishSessionReply()
        reply.success = True
        reply.message = "test_EstablishSession()"

        return reply

    def processUserQuestion(self, request, context):
        # 构建 QuestionReply 响应消息
        reply = ask_pb2.QuestionReply()

        vectorstore = get_vectorstore(request.name)
        qa_chain = generate_qa_chain(vectorstore)
        answer = qa_chain({"query": request.question})

        reply.answer = answer
        reply.history = ""

        return reply


def serve():
    port = "50051"
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    ask_pb2_grpc.add_LangchainAskServicer_to_server(LangchainAsk(), server)
    server.add_insecure_port("[::]:" + port)
    server.start()
    print("Server started, listening on " + port)
    server.wait_for_termination()


if __name__ == "__main__":
    logging.basicConfig()
    serve()
