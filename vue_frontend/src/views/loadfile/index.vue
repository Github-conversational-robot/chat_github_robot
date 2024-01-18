<template>
    <div>
      <el-card class="login-form-layout">
        <el-form
          autocomplete="on"
          :model="loginForm"
          ref="loginForm"
          label-position="left"
        >
          <div style="text-align: center">
            <svg-icon icon-class="login-mall" style="width: 56px;height: 56px;color: #409EFF"></svg-icon>
          </div>
          <h2 class="login-title color-main">仓库地址</h2>
          <el-form-item prop="username" style="margin-top: 30px">
            <el-input
              name="username"
              type="text"
              v-model="loginForm.username"
              autocomplete="on"
              placeholder="请输入仓库url"
            >
              <span slot="prefix">
                <svg-icon icon-class="user" class="color-main"></svg-icon>
              </span>
            </el-input>
          </el-form-item>

          <el-form-item style="margin-bottom: 60px">
            <el-button
              style="width: 100%"
              type="primary"
              :loading="loading"
              @click.native.prevent="handleLogin"
            >上传</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
  import { uploadfile } from "@/api/upload";
  export default {
    name: "login",
    data() {
      return {
        loginForm: {
          username: "",
        },
      };
    },
    methods: {
      handleLogin() {
        console.log("log file ");
        uploadfile(this.loginForm.username);
        this.loginForm = "";
      }
    }
  };
  </script>
  
  <style scoped>
  .login-form-layout {
    position: absolute;
    left: 0;
    right: 0;
    width: 360px;
    margin: 140px auto;
  }
  
  .login-title {
    text-align: center;
  }
  
  .login-center-layout {
    background: #409eff;
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
    margin-top: 200px;
  }
  </style>