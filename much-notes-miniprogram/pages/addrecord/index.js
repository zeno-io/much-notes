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
    id: null,
    showAccountBookInfo: false,
    showAccountBookSelect: false,
    accountBookId: null,
    accountBookList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let id = options.id;
    if (id != undefined && id != null) {
      this.data.id = id;
    }
    this.getCategory()
    if (getApp().globalData.account_book_id == -1) {
      this.getAccountBooks()
    }
    if (this.data.id != null) {
      this.getDetailById();
    }
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

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },
  numChange: function (e) {
    this.setData({
      value: e.detail.value
    })
  },
  submit: function (e) {
    console.log(e);
    if (!c.checkStrNotNull(e.detail.value)) {
      c.showToast("请输入金额");
      return;
    }
    let categoryItem = this.data.tabActive == 0 ?
      this.data.zcCategoryList[this.data.zcCategorySelectedIndex] :
      this.data.srCategoryList[this.data.srCategorySelectedIndex];
    let accountBookId = getApp().globalData.account_book_id;//账本id
    if (accountBookId == -1) {
      accountBookId = this.data.accountBookId;
    }
    let params = {
      money: parseFloat(e.detail.value) * 100,
      type: this.data.tabActive,
      accountType: e.detail.account,
      accountBookId: accountBookId,
      time: e.detail.date,
      categoryId: categoryItem.id,
      categoryName: categoryItem.name,
      remark: e.detail.remark
    }
    let url = '/mp/account/record/addRecord';
    if (this.data.id != null) {
      params['recordId'] = this.data.id;
      url = '/mp/account/record/editRecord'
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
    c.request('/mp/category/getList', {}, (success, res) => {
      for (let i in res.result.srCategory) {
        res.result.srCategory[i].icon = JSON.parse(
          res.result.srCategory[i].icon)
      }
      for (let i in res.result.zcCategory) {
        res.result.zcCategory[i].icon = JSON.parse(
          res.result.zcCategory[i].icon)
      }
      vm.setData({
        srCategoryList: res.result.srCategory,
        zcCategoryList: res.result.zcCategory
      })
    })
  },
  getDetailById() {
    let vm = this
    c.requestGet('/mp/account/record/getRecordById', {id: this.data.id},
      (succ, data) => {
        if (succ) {
          vm.setInfo(data.result)
        }
      })
  },
  setInfo(data) {
    this.setData({
      accountType: data.accountType,
      accountBookId: data.accountBookId,
      initDate: data.time,
      remark: data.remark,
      value: data.money / 100,
      tabActive: data.type
    })
    if (data.type == 0) {
      for (let i in this.data.zcCategoryList) {
        if (data.categoryId == this.data.zcCategoryList[i].id) {
          this.setData({
            zcCategorySelectedIndex: i
          })
        }
      }
    } else {
      for (let i in this.data.srCategoryList) {
        if (data.categoryId == this.data.srCategoryList[i].id) {
          this.setData({
            srCategorySelectedIndex: i
          })
        }
      }
    }
    let index = 0;
    for (let i in this.data.accountBookList) {
      if (this.data.accountBookList[i].id == data.accountBookId) {
        this.data.accountBookList[i].selected = true;
        index = i;
      } else {
        this.data.accountBookList[i].selected = false;
      }
    }
    this.setData({
      accountBookSelectedIndex: index,
      accountBookList: this.data.accountBookList
    })
  },
  // 分类被选中
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
  //获取账本数据
  getAccountBooks() {
    let vm = this;
    c.requestGet('/mp/account/book/getList', {
      "all": false
    }, (success, data) => {
      vm.setData({
        showAccountBookInfo: true,
        accountBookSelectedIndex: 0,
        accountBookId: data.result[0].id,
        accountBookList: data.result
      })
    })
  },
  //显示选择账本
  selectAccountBook() {
    this.setData({
      showAccountBookSelect: true
    })
  },
  //关闭选择账本
  onAccountBookSelectClose() {
    this.setData({
      showAccountBookSelect: false
    })
  },
  //选中账户本后
  onAccountBookSelect(e) {
    let index = e.currentTarget.dataset.index
    for (let i in this.data.accountBookList) {
      i == index ? this.data.accountBookList[i].selected = true
        : this.data.accountBookList[i].selected = false;
    }
    this.setData({
      accountBookId: this.data.accountBookList[index].id,
      accountBookSelectedIndex: index,
      accountBookList: this.data.accountBookList
    })
    this.onAccountBookSelectClose();
  },
  // tabs切换
  onTabChange(e) {
    this.setData({
      tabActive: e.detail.index
    })
  }
})
