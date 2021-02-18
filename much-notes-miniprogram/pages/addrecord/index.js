// pages/addrecord/index.js
let c = require('../../utils/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    initDate: '',
    accountType: 0,
    remark: '',
    value: '',
    zcCategoryList: [],
    srCategoryList: [],
    zcCategorySelectedIndex: 0,
    srCategorySelectedIndex: 0,
    tabActive: 0,
    id:null
  },
  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let id = options.id;
    if (id != undefined && id != null) {
      this.data.id = id;
    }
    this.getCategory()
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
  numChange: function(e) {
    this.setData({
      value: e.detail.value
    })
  },
  submit: function(e) {
    console.log(e);
    if (!c.checkStrNotNull(e.detail.value)) {
      c.showToast("请输入金额");
      return;
    }
    let categoryItem = this.data.tabActive == 0 ?
      this.data.zcCategoryList[this.data.zcCategorySelectedIndex] :
      this.data.srCategoryList[this.data.srCategorySelectedIndex];
    let params = {
      money: parseFloat(e.detail.value) * 100,
      type: this.data.tabActive,
      account_type: e.detail.account,
      id: getApp().globalData.account_book_id, //账单id
      time: e.detail.date,
      category_id: categoryItem.id,
      category_name: categoryItem.name,
      remark: e.detail.remark
    }
    let url = '/Record/add';
    if(this.data.id!=null){
      params['record_id'] = this.data.id;
      url = '/Record/edit'
    }
    c.request(url, params, (succ, data) => {
      if (succ) {
        getApp().globalData.refreshJz = true;
        wx.navigateBack();
      }
    })
  },
  //获取分类数据
  getCategory() {
    let vm = this;
    c.request('/Category/getList', {}, (success, res) => {
      for (let i in res.data.srCategory) {
        res.data.srCategory[i].icon = JSON.parse(res.data.srCategory[i].icon)
      }
      for (let i in res.data.zcCategory) {
        res.data.zcCategory[i].icon = JSON.parse(res.data.zcCategory[i].icon)
      }
      vm.setData({
        srCategoryList: res.data.srCategory,
        zcCategoryList: res.data.zcCategory
      })
      if(vm.data.id!=null){
        vm.getDetailById();
      }
    })
  },
  getDetailById() {
    let vm = this
    c.request('/Record/getById', { id: this.data.id }, (succ, data) => {
      if (succ) {
        vm.setInfo(data.data)
      }
    })
  },
  setInfo(data) {
    this.setData({
      accountType: data.account_type,
      initDate: data.time,
      remark: data.remark,
      value: data.money / 100,
      tabActive: data.type
    })
    if (data.type == 0) {
      for (let i in this.data.zcCategoryList) {
        if (data.category_id == this.data.zcCategoryList[i].id) {
          this.setData({
            zcCategorySelectedIndex: i
          })
        }
      }
    } else {
      for (let i in this.data.srCategoryList) {
        if (data.category_id == this.data.srCategoryList[i].id) {
          this.setData({
            srCategorySelectedIndex: i
          })
        }
      }
    }
  },
  //分类被选中
  categroyItemSelect(e) {
    let index = e.currentTarget.dataset.index;
    if (this.data.tabActive === 0) {
      this.setData({
        zcCategorySelectedIndex: index
      })
    } else {
      this.setData({
        srCategorySelectedIndex: index
      })
    }

  },
  //tabs切换
  onTabChange(e) {
    this.setData({
      tabActive: e.detail.index
    })
  }
})