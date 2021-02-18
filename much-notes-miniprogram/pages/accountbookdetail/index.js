// pages/accountbookdetail/index.js
let c = require('../../utils/common.js');
import Dialog from '../../components/dist/dialog/dialog';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: [],
    shareKey: null,
    show: false,
    editShow: false,
    cuid: null,
    uid: '',
    adminUid:'',
    actions: [{
        name: '邀请好友',
        subname: '邀请2天内有效',
        loading: true,
        openType: 'share'
      },
      {
        name: '退出账单',
        subname: '所记账单不会清除',
        loading: false
      },
    ],
    editActions: [{
        name: '移出',
        subname: '',
        loading: false
      },
      {
        name: '移交管理员',
        subname: '',
        loading: false
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    //this.getShareKey();//TODO 这里以后要改一下，先用进来就请求的方式
    this.setData({
      uid: getApp().globalData.appUserInfo.uid
    })
    this.getData();
  },
  getData: function() {
    let vm = this;
    c.request('/AccountBooks/getUsers', {
        id: getApp().globalData.account_book_id
      },
      function(success, data) {
        let uid = '';
        data.data.user_roles.forEach(v => {
          if(v.is_owner==5){
            uid = v.uid;
          }
        })
        vm.setData({
          adminUid:uid,
          list: data.data.data
        })
        
      }
    );
  },
  onShareAppMessage: function() {
    return {
      title: getApp().globalData.userInfo.nickName + "邀请您加入账单",
      imageUrl: getApp().globalData.userInfo.avatarUrl,
      path: "/pages/index/index?type=share&key=" + this.data.shareKey
    }
    this.onClose()
  },
  getShareKey: function() {
    let vm = this;
    c.request('/AccountBooks/getShareKey', {
        id: getApp().globalData.account_book_id
      },
      function(success, data) {
        if (success){
          vm.setData({
            shareKey: data.data,
            ['actions[0].loading']: false
          });
        }
      }
    );
  },
  showActions: function(e) {
    this.setData({
      show: true
    });
    this.getShareKey();
  },
  onSelect: function(event) {
    if (event.detail.name == '退出账单') {
      this.doOut()

    }
    this.onClose()
  },
  onClose: function(event) {
    this.setData({
      show: false
    });
  },
  showEditActions: function(e) {
    this.data.cuid = e.currentTarget.dataset.uid;
    this.setData({
      editShow: true
    });
  },
  onEditSelect: function(event) {
    console.log(event);
    if (event.detail.name == '移出') {
      this.doRemove()
    } else if (event.detail.name == '移交管理员') {
      this.doChangeAdmin()
    }
    this.onEditClose()
  },
  onEditClose: function(event) {
    this.setData({
      editShow: false
    });
  },
  doOut() {
    let vm = this;
    Dialog.confirm({
      title: '确认退出吗？',
      message: '所记账单依然会留存'
    }).then(() => {
      c.request('/AccountBooks/out', {
        id: getApp().globalData.account_book_id
      }, (succ, data) => {
        if (succ) {
          getApp().globalData.account_book_id = null;
          c.showToast('退出成功');
          wx.navigateBack()
        }
      })
    }).catch(() => {});
  },
  doRemove() {
    let vm = this;
    Dialog.confirm({
      title: '确认移除吗？',
      message: '用户所记账单依然会留存'
    }).then(() => {
      c.request('/AccountBooks/removeUserByUid', {
        uid: this.data.cuid,
        id: getApp().globalData.account_book_id
      }, (succ, data) => {
        if (succ) {
          c.showToast('移除成功');
          vm.getData()
        }
      })
    }).catch(() => {});
  },
  doChangeAdmin() {
    let vm = this;
    Dialog.confirm({
      title: '确认移交吗？',
      message: '管理员移交后你将失去用户管理权限'
    }).then(() => {
      c.request('/AccountBooks/changeAdmin', {
        uid: this.data.cuid,
        id: getApp().globalData.account_book_id
      }, (succ, data) => {
        if (succ) {
          c.showToast('移交成功');
          vm.getData()
        }
      })
    }).catch(() => {});
  }

})