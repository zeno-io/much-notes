//index.js
let c = require('../../utils/common.js');
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    type: null,
    key: null
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function (options) {
    var that = this
    if(c.checkStrNotNull(options.type)){
      that.setData({
        type: options.type
      });
      if (c.checkStrNotNull(options.key)) {
        that.setData({
          key: options.key
        });

      }
    }
    //在这里一进入就获取之前存储的账本id
    app.globalData.account_book_id = wx.getStorageSync("account_book_id");
    //判断session是否过期
    wx.checkSession({
      success() {
        //session_key 未过期，并且在本生命周期一直有效
        that.checkUserInfoAndOpenid();
      },
      fail() {
        // session_key 已经失效，需要重新执行登录流程
        that.getUserData();
      }
    })

  },
  checkUserInfoAndOpenid: function(){
    let vm = this;
    var user = wx.getStorageSync('user') || {};
    var userInfo = wx.getStorageSync('userInfo') || {};
    //首先判断本地存储的信息是否还存在，不存在就去重新获取
    if (!userInfo.nickName) {
      vm.getUserData();
    } else if (!user.openId){
      vm.wxLogin();
    } else {
      app.globalData.userInfo = userInfo
      app.globalData.appUserInfo = user
      vm.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
      vm.toHome();
    }
  },
  wxLogin: function (encryptedData = null,iv=null){
    let vm = this;
    wx.login({
      success(res) {
        console.log(res);
        if (res.code) {
           //发起网络请求
          c.request(
            "/mp/wx/login",
            {
              encryptedData: encryptedData,
              iv: iv,
              code: res.code,
              headImg: app.globalData.userInfo.avatarUrl,
              nickName: app.globalData.userInfo.nickName
            },
            function (success, data) {
              if (!success) {
                vm.setData({
                  hasUserInfo: false
                })
                app.globalData.regetToken = false;
                return;
              }
              app.globalData.appUserInfo = data.result;
              wx.setStorageSync('user', data.result);
              vm.toHome();
            },
            'GET'
          );

        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    })
  },
  getUserData: function() {
    let vm = this;
    //获取用户信息
    if (vm.data.canIUse) {
      var userInfo = wx.getStorageSync('userInfo');
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        vm.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        app.globalData.userInfo = res.userInfo
        wx.setStorageSync('userInfo', res.userInfo);
        if (res.encryptedData != null && res.iv != null) {
          vm.wxLogin(res.encryptedData, res.iv);
        } else {
          vm.wxLogin();
        }
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          vm.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          });
          wx.setStorageSync('userInfo', res.userInfo);
          if (res.encryptedData != null && res.iv != null) {
            vm.wxLogin(res.encryptedData, res.iv);
          }else{
            vm.wxLogin();
          }

        }
      })
    }
  },
  getUserInfo: function(e) {
    app.globalData.userInfo = e.detail.userInfo
    wx.setStorageSync('userInfo', app.globalData.userInfo);
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
    if (e.detail.encryptedData!=null&&e.detail.iv!=null){
      this.wxLogin(e.detail.encryptedData, e.detail.iv);
    } else {
      this.wxLogin();
    }
  },
  toHome: function() {
    //到首页前先来处理分享的数据
    if (this.data.type!=null){
      if(this.data.type=='share'){
        this.addToAbUser();
        //执行完后置空
        this.data.type = null;
      }
    }
    wx.switchTab({
      url: '../jizhang/index'
    })
  },
  addToAbUser:function() {
    let vm = this;
    c.requestGet(
      "/mp/account/book/addUserToAccountBook",
      {
        key: vm.data.key
      },
      function (success, data) {
        if (success) {
          app.globalData.account_book_id = data.result;
          wx.setStorageSync('account_book_id', data.result);
        }
        vm.toHome();
      }
    );
  }
})
