// pages/createbook/index.js
let c = require('../../utils/common.js');
import Toast from '../../components/dist/toast/toast';
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    value: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  submit: function() {
    //发起网络请求
    c.request(
      "/AccountBooks/addNormal", {
        name: this.data.value
      },
      function(success, data) {
        Toast.success({
          type: 'success',
          duration:2000,
          message: '创建成功',
          onClose: function(){
            wx.navigateBack();
          }
        });
        
      }
    );
  },
  onChange(event) {
    // event.detail 为当前输入的值
    this.setData({
      value: event.detail
    })
  }
})