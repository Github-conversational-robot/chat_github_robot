from app import create_app

# 创建 Flask 应用对象
app = create_app()

if __name__ == '__main__':
    # 在开发环境中运行应用
    app.run(debug=True)