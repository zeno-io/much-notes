// pages/recorddetail/index.js
let c = require('../../utils/common.js');
import Dialog from '../../components/dist/dialog/dialog';
import Toast from '../../components/dist/toast/toast';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: '',
    icon: '',
    categoryName: '',
    money: 0,
    accountBookId: '',
    accountBookName: '',
    accountTypeName: '',
    account_type: '',
    creatorName: '',
    updaterName: '',
    time: '',
    updateTime: '',
    remark: null,
    uid: null,
    type: '支出'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    getApp().globalData.refreshJz = false;
    this.data.id = options.id;
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.getData();
  },
  getData() {
    let vm = this
    c.requestGet('/mp/account/record/getRecordById', {id: this.data.id},
      (succ, data) => {
        if (succ) {
          vm.setInfo(data.result)
        }
      })
  },
  setInfo(data) {
    let icon = JSON.parse(data.category.icon)[1];
    this.setData({
      categoryName: data.categoryName,
      money: data.money,
      accountBookId: data.accountBookId,
      accountBookName: data.accountBookName,
      accountTypeName: getApp().globalData.accountTypes[data.accountType],
      time: data.time,
      updateTime: data.updateTime,
      creatorName: data.creatorName,
      updaterName: data.updaterName,
      remark: data.remark,
      uid: data.uid,
      account_type: data.accountType,
      icon: icon,
      type: (data.type == 1) ? "收入" : "支出"
    });
  },
  del() {
    Dialog.confirm({
      title: '确认删除吗？',
      message: '删除后无法恢复'
    }).then(() => {
      c.requestGet('/mp/account/record/deleteRecord', {recordId: this.data.id},
        (succ, data) => {
          if (succ) {
            getApp().globalData.refreshJz = true;
            Toast.success({
              type: 'success',
              duration: 2000,
              message: '删除成功',
              onClose: function () {
                wx.navigateBack();
              }
            });
          }
        })
    }).catch(() => {
      // on cancel
    });
  },
  edit() {
    c.requestGet('/mp/account/record/checkRecordAuth',
      {id: this.data.accountBookId},
      (succ, data) => {
        if (succ) {
          wx.navigateTo({
            url: '../addrecord/index?id=' + this.data.id,
          });
        }
      });
  }
});
