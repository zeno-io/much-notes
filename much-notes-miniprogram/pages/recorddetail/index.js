// pages/recorddetail/index.js
let c = require('../../utils/common.js');
import Dialog from '../../components/dist/dialog/dialog';
import Toast from '../../components/dist/toast/toast';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    icon:'',
    categoryNmae:'',
    money:0,
    account_type_name: '',
    account_type:'',
    nickname:'',
    time: '',
    date:'',
    remark:null,
    uid:null,
    type:'支出'
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
  getData(){
    let vm = this
    c.request('/Record/getById',{id:this.data.id},(succ,data)=>{
      if(succ){
        vm.setInfo(data.data)
      }
    })
  },
  setInfo(data) {
    let icon = JSON.parse(data.category.icon)[1];
    this.setData({
      categoryNmae: data.category.name,
      money: data.money,
      account_type_name: getApp().globalData.accountTypes[data.account_type],
      nickname: data.nickname,
      date: data.update_time,
      time: data.time,
      remark: data.remark,
      uid: data.uid,
      account_type: data.account_type,
      icon: icon,
      type:(data.type==1)?"收入":"支出"
    })
  },
  del(){
    Dialog.confirm({
      title: '确认删除吗？',
      message: '删除后无法恢复'
    }).then(() => {
      c.request('/Record/del',{record_id:this.data.id},(succ,data)=>{
        if(succ){
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
  edit(){
    let uid = getApp().globalData.appUserInfo.uid;
    if(uid!=this.data.uid){
      c.showToast('本人才能修改');
    }else{
      wx.navigateTo({
        url: '../addrecord/index?id=' + this.data.id,
      })
    }
  }
})