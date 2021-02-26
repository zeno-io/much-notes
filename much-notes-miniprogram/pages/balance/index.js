// pages/balance/index.js
let app = getApp().globalData;
let c = require('../../utils/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    total:0,
    accountList: [
      // 3不选帐户 2微信  1支付宝 0现金
      {
        name: "现金",
        balance: 0,
        type: 0,
        icon: "../../imgs/account_type_icon/icon_zf_xj.png",
      },
      {
        name: "支付宝",
        balance: 0,
        type: 1,
        icon: "../../imgs/account_type_icon/icon_zf_zfb.png",
      },
      {
        name: "微信",
        balance: 0,
        type: 2,
        icon: "../../imgs/account_type_icon/icon_zf_wx.png",
      }
    ]

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getData();
  },
  getData(){
    let vm = this;
    c.request('/mp/account/info/getBalance', {}, (succ, data) => {
      if (succ) {
        let total = 0;
        vm.data.accountList.forEach(v => {
          v.balance = data.result[v.type]
          total += v.balance
        });
        vm.setData({
          accountList: vm.data.accountList,
          total: total
        })
      }
    });
  },
  toEdit(e){

    let index = e.currentTarget.dataset.index;
    let item = this.data.accountList[index];
    wx.navigateTo({
      url: '../balanceedit/index?type=' + item.type + "&balance=" + item.balance
    })
  }
})
