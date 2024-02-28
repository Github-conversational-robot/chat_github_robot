class Config:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance.OPENAI_API_KEY = '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'
            cls._instance.PATH_TO_GITREPO_DIR = '/Users/cengqingning/github_code/'
            cls._instance.DATABASE_USER = 'root'
            cls._instance.DATABASE_PSW = '111'
            cls._instance.DATABASE_DBNAME = 'test01'
            cls._instance.DATABASE_PORT = 6001
            cls._instance.DATABASE_HOST = '127.0.0.1'
            cls._instance.LLM_MODEL_NAME = 'gpt-3.5-turbo'
        return cls._instance