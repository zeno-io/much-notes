//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    regetToken: false,//是否在获取token中，用于防止同一个页面调用多个接口token过期后多次获取token
    appUserInfo: null,
    userInfo: null,
    refreshJz:true,
    // url:"https://jz.shangyee.site/miniprogram",
    url: "https://jz.yqlife.net/miniprogram",
    account_book_id: null,
    constants:{
      SUCCESS:1,
      ERROR_CODE:-1,
      INVAILD_TOKEN:-14

    },
    accountTypes:{
      // 3不选帐户 2微信  1支付宝 0现金
      3: "未选账户",
      2: "微信",
      1: "支付宝",
      0:"现金"
    },
    yunOSS:{
      YUN_OSS_URL:"http://dx-zhdjimg.oss-cn-hangzhou.aliyuncs.com/",
      YUN_OSS_SMALL_IMG_SIZE:"x-oss-process=image/resize,h_100"
    }
  }
});