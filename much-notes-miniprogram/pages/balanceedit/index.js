// pages/balanceedit/index.js
let c = require('../../utils/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    type:'',
    icon:'',
    name:'',
    balance:0,
    accountList: [
      // 3不选帐户 2微信  1支付宝 0现金
      {
        name: "现金",
        balance: 0,
        type: 0,
        icon: "http://jz.oss.shangyee.site/assets/account_type_icon/icon_zf_xj.png",
      },
      {
        name: "支付宝",
        balance: 0,
        type: 1,
        icon: "http://jz.oss.shangyee.site/assets/account_type_icon/icon_zf_zfb.png",
      },
      {
        name: "微信",
        balance: 0,
        type: 2,
        icon: "http://jz.oss.shangyee.site/assets/account_type_icon/icon_zf_wx.png",
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let type = options.type;
    let balance = options.balance;
    for (let index in this.data.accountList) {
      if(this.data.accountList[index].type == type){
        this.setData({
          type:type,
          balance: balance,
          icon: this.data.accountList[index].icon,
          name: this.data.accountList[index].name
        })
        break;
      }
    };
  },
  onChange(e){
    this.data.balance = parseFloat(e.detail)*100;
  },
  submit(){
    c.request('/Account/balanceEdit',{type:this.data.type,balance:this.data.balance},(succ,data)=>{
      if(succ){
        wx.navigateBack();
      }
    })
  }

})