// pages/accountbooks/index.js
let c = require('../../utils/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
    this.getData();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },



  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  getData: function(){
    let vm = this;
    c.request('/AccountBooks/getList',{},function(success,data){
      console.log(data);
      vm.setData({
        list: data.data
      })
    });
    
  },
  onItemClick: function(e){
    let index = e.currentTarget.id;
    let selectId = this.data.list[index].id;
    wx.setStorageSync("account_book_id", selectId);
    getApp().globalData.account_book_id = selectId;
    wx.navigateBack();
  },
  createAb: function(){
    wx.navigateTo({
      url: '../createbook/index',
    })
  }
})