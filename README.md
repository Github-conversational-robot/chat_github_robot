
 <img src="https://img.shields.io/badge/platform-MacOS-white.svg" alt="macos"/> <img src="https://img.shields.io/badge/platform-Linux-9cf.svg" alt="linux"/> <img src="https://img.shields.io/badge/License-Apache%202.0-red.svg" alt="license"/> <img src="https://img.shields.io/badge/Language-Java-blue.svg" alt="language"/> <img src="https://img.shields.io/badge/Language-Python-green.svg" alt="language"/>
<br>
# chat_github_robot

本项目希望开发一个github仓库对话机器人。用户通过输入仓库对应URL后，机器人能够回答关于仓库的相关信息，帮助用户学习使用相应仓库。在系统设计上采用了**springboot** + **Flask**的混合框架。同时基于**matrix one**完成了向量存储 && 检索以构建本地知识库。

## 前端
采用VUE编写，代码参考了 [https://github.com/seisgo/EllipseFit](https://gitee.com/mao-yongyao/chatroom)在此表示感谢！
<br>
## 后端
💥 技术栈 **springboot**, **mybatis plus**, **matrix one**, **spring security**, **redis**, **mysql**, **flask** <br>
### 🧃 相关问题及解决方案

## milestone
### 2-20
  集成swagger到springboot中，方便接口文档配置 <br>
  修改了仓库存储逻辑，分为了仓库表和用户对应仓库两张表
### 2-21
  完成了HTTP Client的实现及测试
### 2-23
  将python backend 修改成了使用文心一言接口，来解决chatgpt key无法使用问题。<br>
  录制了问答my_malloc的demo视频
### 2-25
  对对话存储进行了优化，采用redis进行缓存，并使用spring scheduler进行定时更新。<br>


## 贡献规则
参考contributing.md
