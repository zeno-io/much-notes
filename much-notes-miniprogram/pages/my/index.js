// pages/my/index.js
let app = getApp().globalData;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl:'',
    nickname:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let avatarUrl = app.userInfo.avatarUrl
    let nickname = app.userInfo.nickName
    this.setData({
      nickname: nickname,
      avatarUrl:avatarUrl
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        selected: 2
      })
    }
  },
  toPersonalBalance() {
    wx.navigateTo({
      url: '../balance/index',
    })
  },
  toFamilyBalance() {
    wx.navigateTo({
      url: '../balance/index?type=family',
    })
  }

})
