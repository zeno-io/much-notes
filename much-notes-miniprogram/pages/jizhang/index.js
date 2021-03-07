// pages/jizhang/index.js
let c = require('../../utils/common.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    abid:'',
    accountBook: {
      name: '',
      countInfo: ''
    },
    BackgroundImg: '',
    recordList: [],
    records: [],
    positions: [],
    daysObj: {},
    cacheTotal:{},
    cdate: '',
    cmonth: '',
    topHeigth: 0,
    height: 0,
    total: 0,
    pageSize: 20,
    categorys:null,
    zc:0,
    sr:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.data.height = wx.getSystemInfoSync().windowHeight;
    this.data.topHeigth = this.data.height * 0.25;
    this.setData({
      height: this.data.height,
      topHeigth: this.data.topHeigth
    })
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
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        selected: 1
      })
    }
    if (getApp().globalData.refreshJz){
      this.reSetData();
    }
    getApp().globalData.refreshJz = true;
  },



  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  getData: function() {
    let vm = this;
    c.requestGet('/mp/account/book/getAccountBookById', {
      "id": vm.data.abid
    }, function (success, data) {
      let accountBook = {
        name: data.result.name,
        countInfo: (data.result.count <= 1) ? '' : data.result.count + '人'
      };
      vm.setData({
        accountBook: accountBook
      });
    });
    c.request('/mp/category/getUnTypeList', {
        "id": vm.data.abid
      },
      (succ, data) => {
        vm.data.categorys = {};
        data.result.forEach(v => {
          let id = v.id;
          if (!(id in vm.data.categorys)) {
            if (v.icon != null && v.icon.length > 0) {
              let icons = JSON.parse(v.icon)
              let category = {icon: icons[1], name: v.name}
              vm.data.categorys[id]= category;
            }
          }
        })
        vm.setData({
          categorys:vm.data.categorys
        })
      })
  },
  //到账本详情去
  toAbDetail: function() {
    if (this.data.abid == -1) {
      return;
    }
    wx.navigateTo({
      url: '../accountbookdetail/index'
    });
  },
  toAbList: function() {
    wx.navigateTo({
      url: '../accountbooks/index'
    });
  },
  toJz() {
    wx.navigateTo({
      url: '../addrecord/index',
    })
  },
  //获取账本记录
  getRecordList() {

    let vm = this;
    let page = vm.data.records.length / vm.data.pageSize + 1;
    c.requestGet('/mp/account/record/getListByAccountBookId', {
      "id": vm.data.abid,
      "page": page
    }, function (success, data) {

      vm.setData({
        total: data.result.total
      })
      //提取日期，主要为展示日期的统计
      if (data.result.days != null && data.result.days.length > 0) {
        vm.getDays(data.result.days)
      }
      //合并数组
      if (data.result.data != null && data.result.data.length > 0) {
        vm.data.records = vm.data.records.concat(data.result.data);
        let temp = '';
        if (vm.data.recordList.length > 0) {
          temp = vm.data.recordList[vm.data.recordList.length - 1].total.time
        }

        data.result.data.forEach((v, index) => {
          //如果日期与上一个数据的日期相同，就添加到上一个日期所在数组中去
          if (v.time == temp) {
            vm.data.recordList[vm.data.recordList.length - 1].datas.push(v)
          } else {
            //对上一个数组进行排序
            if (vm.data.recordList.length>0){
              vm.datasSort();
            }
            let item = {
              total: vm.data.daysObj[v.time],
              datas: [v]
            }
            vm.data.recordList.push(item)
            temp = v.time
          }
        })
        vm.setData({
          recordList: vm.data.recordList
        });
        vm.disconnect();
        if (page==1){
          vm.updateCdate(0);
        }
        vm.init();
      }
    });
  },
  //排序
  datasSort(){
    let datas = this.data.recordList[this.data.recordList.length - 1].datas;
    datas.sort(function (a, b) {
      if (a.id > b.id) {
        return -1;
      } else if (a.id == b.id) {
        return 0;
      } else {
        return 1;
      }
    })
    this.data.recordList[this.data.recordList.length - 1].datas = datas;
  },
  //这里是将每个月的每天以日期为key日期对应数据为value存于daysObj对象里
  getDays(data) {
    let vm = this;
    data.forEach(v => {
      let time = v.time;
      if (!(time in vm.data.daysObj)) {
        vm.data.daysObj[v.time] = v;
      }
      //第一个默认就是fixed
      if (vm.data.positions.length == 0) {
        vm.data.positions.push('fixed')
      } else {
        vm.data.positions.push('absolute')
      }
      vm.setData({
        positions: vm.data.positions
      })
    })
  },
  updateCdate(index){
    let time = this.data.recordList[index].total.time.substr(0, 7);
    if (this.data.cdate != time){
      this.data.cdate = time;
      if (!(time in this.data.cacheTotal)) {
        this.getTotal();
      }else{
        this.setTotal(this.data.cacheTotal[time]);
      }

    }
  },
  //设置总计并缓存数据
  setTotal(data){
    let time = this.data.cdate;
    if (!(time in this.data.cacheTotal)){
      this.data.cacheTotal[time] = data;
    }
    this.setData({
      zc: data.zc,
      sr: data.sr,
      cmonth: time.substr(5, 7)
    });
  },
  getTotal(){
    let vm = this;
    c.requestGet('/mp/stat/getTotalWithMonth',
      {id: vm.data.abid, date: this.data.cdate}, (succ, data) => {
        if (succ) {
          vm.setTotal(data.result);
        }
      });
  },
  init() {
    let vm = this;
    this._observerHeader = wx.createIntersectionObserver(this, {
      thresholds: [0, 1],
      observeAll: true
    });
    this._observerFooter = wx.createIntersectionObserver(this, {
      thresholds: [0, 1],
      observeAll: true
    });
    //这里用于监听当前组件是否到顶了
    this._observerHeader.relativeTo('.list-wrap').observe('.sticky_sentinel--top', (res) => {
      let index = res.id.replace('sticky_sentinel--top', '');
      if (res.intersectionRatio == 0 && res.boundingClientRect.top < vm.data.topHeigth) {
        // console.log(index);
        this.updateCdate(index);
        this.setData({
          ['positions[' + index + ']']: 'fixed'
        })
      }
      if (res.intersectionRatio > 0) {
        //标题顶部下滑离开顶部(也就是sticky_sentinel--top这个view底部向下到顶了)，就取消固定在头部
        //这里还要判断一下，如果是第一个标题的话，就不执行下面的了
        if (index != 0) {
          this.setData({
            ['positions[' + index + ']']: 'absolute top'
          })
        }
      }
    });
    this._observerFooter.relativeTo('.list-wrap').observe('.sticky_sentinel--bottom', (res) => {
      let index = res.id.replace('sticky_sentinel--bottom', '');
      if (res.intersectionRatio < 1 && res.boundingClientRect.top < vm.data.topHeigth) {
        this.setData({
          ['positions[' + res.id.replace('sticky_sentinel--bottom', '') + ']']: 'absolute bottom'
        })
      }
      if (index == 0 && res.intersectionRatio == 1) {
        this.updateCdate(index);
        //这里是防止快速下滑时没有触发
        this.setData({
          ['positions[' + res.id.replace('sticky_sentinel--bottom', '') + ']']: 'fixed'
        })
      } else {
        if (res.intersectionRatio >= 1 && res.boundingClientRect.top <= (res.boundingClientRect.height  + vm.data.topHeigth)) {
          this.setData({
            ['positions[' + res.id.replace('sticky_sentinel--bottom', '') + ']']: 'fixed'
          })
        }
      }

    });
  },
  disconnect() {
    if (this._observerHeader) this._observerHeader.disconnect()
    if (this._observerFooter) this._observerFooter.disconnect()
  },
  onUnload() {
    this.disconnect();
  },
  onBottom(e) {
    if (this.data.total <= this.data.records.length) {
      return;
    }
    this.getRecordList();
  },
  reSetData() {
    let accountBook = {
      name: '',
      count: ''
    };
    this.setData({
      total: 0,
      accountBook: accountBook,
      BackgroundImg: '',
      recordList:[],
      cacheTotal:[],
      records:[],
      positions:[],
      daysObj:{},
      cdate:'',
      cmonth:'',
      categorys:{},
      zc:0,
      sr:0
    })
    //这里先判断是否有选择过账本id，如果没有就去选择
    let abid = getApp().globalData.account_book_id;
    if (!c.checkStrNotNull(abid)) {
      wx.navigateTo({
        url: '../accountbooks/index'
      })
      return;
    }
    this.data.abid = abid;
    this.getData();
    this.setData({
      BackgroundImg: getApp().globalData.userInfo.avatarUrl
    });
    this.getRecordList();
  },
  //到详情页面去
  toDetail(e){
     let id = e.currentTarget.dataset.id;
     wx.navigateTo({
       url: '../recorddetail/index?id='+id,
     })
  }
})
