from flask import Flask, request, render_template, Blueprint
from .utils import *
from .config import Config
from .matrixone import *
import subprocess
config = Config()

main_bp = Blueprint('main', __name__)

os.environ["OPENAI_API_KEY"] = config.OPENAI_API_KEY

# 定义路由和视图函数
@main_bp.route('/', methods=['GET', 'POST'])
def home():
    # download 代码到本地
    # 1.Load 导入Document Loaders
    base_dir = './gitRepo'  # 文档的存放目录
    documents = load_documents_from_folder(base_dir)

    # 2.Split 将Documents切分成块以便后续进行嵌入和向量存储
    chunked_documents = split_documents(documents, 200, 10)

    # 3.Store 将分割嵌入并存储在矢量数据库MO中
    # from langchain.vectorstores import Matrixone
    from langchain.embeddings import OpenAIEmbeddings
    vectorstore = Matrixone.from_documents(
        documents=chunked_documents, # 以分块的文档
        embedding=OpenAIEmbeddings(), # 用OpenAI的Embedding Model做嵌入
        user="root",
        password="111",
        dbname="test",
        port=6001)  # 指定collection_name

    # 4. Retrieval 准备模型和Retrieval链
    qa_chain = generate_qa_chain(vectorstore)
    if request.method == 'POST':

        # 接收用户输入作为问题
        question = request.form.get('question')        
        
        # RetrievalQA链 - 读入问题，生成答案
        result = qa_chain({"query": question})
        
        # 把大模型的回答结果返回网页进行渲染
        return render_template('index.html', result=result)
    
    return render_template('index.html')

@main_bp.route('/repos/<string:repoName>/<string:userID>')
def handle_repo(repoName, userID):
    # 在本地的repo文件夹中找到该仓库的文件
    if not check_repo_folder_exists(repoName):
        return "git clone 失败"
    # 1.Load 导入Document Loaders
    repo_dir = config.PATH_TO_GITREPO_DIR + repoName
    documents = load_documents_from_folder(repo_dir)
    # 2.Split 将Documents切分成块以便后续进行嵌入和向量存储
    chunked_documents = split_documents(documents, 200, 10)
    # 3.Store 将分割嵌入并存储在矢量数据库MO中
    add_documents_to_vectorstore(repoName, chunked_documents)
    return f"repo_dir: {repo_dir}, User ID: {userID}"

# test for the connection between flask and springboot
@main_bp.route('/hello/', methods=['GET'])
def hello():
    return "hello"

@main_bp.route('/talks/<string:repoName>/<string:userID>/<string:question>', methods=['GET'])
def talk(repoName, userID, question):

    # return repoName + userID + question
    vectorstore = get_vectorstore(repoName)
    qa_chain = generate_qa_chain(vectorstore)
    result = qa_chain({"query": question})
    return f"answer: {result['result']}"
    #return chat_onetime(question)


import requests
import json


def get_access_token():
    """
    使用 API Key，Secret Key 获取access_token，替换下列示例中的应用API Key、应用Secret Key
    """

    url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=k6Agl8y3sHxnnkAYPIHXU9sa&client_secret=rVwhVb75PUxyH2eLojRt28qbtNsZuvlS"

    payload = json.dumps("")
    headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }

    response = requests.request("POST", url, headers=headers, data=payload)
    return response.json().get("access_token")


def chat_onetime(message):
    url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions_pro?access_token=" + get_access_token()

    payload = json.dumps({
        "messages": [
            {
                "role": "user",
                "content": message
            }
        ]
    })
    headers = {
        'Content-Type': 'application/json'
    }

    response = requests.request("POST", url, headers=headers, data=payload)

    print(response.text)
    parsed_data = json.loads(response.text)
    return parsed_data["result"]





