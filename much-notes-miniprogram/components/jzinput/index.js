// components/jz-input.js
let c = require('../../utils/common.js');
Component({
  lifetimes: {
    attached: function() {
      // 在组件实例进入页面节点树时执行
      this.init();
    },
  },
  //这里订阅是为了防止父组件数据传递过来慢了，已经执行过attached，数据不更新的问题
  observers: {
    'initValue': function (initValue) {
      // 在 value被设置时，执行这个函数
      this.init()
    }
  },
  // 以下是旧式的定义方式，可以保持对 <2.2.3 版本基础库的兼容
  attached: function() {
    // 在组件实例进入页面节点树时执行
    this.init();
  },
  /**
   * 组件的属性列表
   */
  properties: {
    initDate:{
      type:String,
      value:''
    },
    accountType: {
      type: String,
      value: ''
    },
    initRemark: {
      type: String,
      value: ''
    },
    initValue: {
      type: String,
      value: ''
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    accountSelectedIndex: 3,
    day: '',
    remark: '',
    date: '',
    value: '',
    showAccountSelect: false,
    show: false,
    currentDate: new Date().getTime(),
    formatter(type, value) {
      if (type === 'year') {
        return `${value}年`;
      } else if (type === 'month') {
        return `${value}月`;
      }
      return value;
    },
    accountList: [
      // 3不选帐户 2微信  1支付宝 0现金
      {
        name: "现金",
        balance: 0,
        type: 0,
        icon: "http://jz.wwfarm.cn/assets/account_type_icon/icon_zf_xj.png",
        selected: false
      },
      {
        name: "支付宝",
        balance: 0,
        type: 1,
        icon: "http://jz.wwfarm.cn/assets/account_type_icon/icon_zf_zfb.png",
        selected: false
      },
      {
        name: "微信",
        balance: 0,
        type: 2,
        icon: "http://jz.wwfarm.cn/assets/account_type_icon/icon_zf_wx.png",
        selected: false
      },
      {
        name: "不选",
        balance: 0,
        type: 3,
        icon: "http://jz.wwfarm.cn/assets/account_type_icon/icon_zf_bx.png",
        selected: true
      },
    ]
  },

  /**
   * 组件的方法列表
   */
  methods: {
    init(){
      if (this.data.initDate!=''){
        this.initDate(new Date(this.data.initDate));
      }else{
        this.initDate(new Date());
      }
      if (this.data.initValue != '') {
        this.data.value = this.data.initValue;
      }
      if (this.data.initRemark != '') {
        this.setData({
          remark: this.data.initRemark
        }) 
      }
      if (this.data.accountType != '') {
        let index = 0;
        for (let i in this.data.accountList) {
          if(this.data.accountList[i].type == this.data.accountType) {
            this.data.accountList[i].selected = true;
            index = i;
          } else{
            this.data.accountList[i].selected = false;
          }
        }
        this.setData({
          accountSelectedIndex: index,
          accountList: this.data.accountList
        })
      }
    },
    //初始化日期
    initDate: function(cdate) {
      if (cdate == null) {
        cdate = new Date();
      }
      let strDate = c.formatDate("y-m-d", cdate.getTime());
      let cday = cdate.getDate();
      this.setData({
        day: cday,
        date: strDate
      })
    },
    //数字变换
    numInput: function(e) {
      let type = e.target.dataset.type;
      if (type === 'num') {
        let num = e.target.dataset.num;
        //判断是不是点
        if (num === '.') {
          if (this.data.value === '') {
            this.setData({
              value: '0.'
            })
          } else if(this.data.value.indexOf('.')!=-1) {
            //不允许输入多个点
          }else{
            this.setData({
              value: this.data.value + num
            })
          }
        } else {
          //如果第一个字符为0，现在输入第二个字符，则自动变为0.
          if (this.data.value === '0' && num !== '.') {
            this.setData({
              value: '0.' + num
            })
          } else {
            let length = this.data.value.length;
            let index=this.data.value.indexOf('.');
            //这是不让他输入太多位小数，默认为2位
            if (index != -1 && (length-index)>2 ){
              return;
            }
            this.setData({
              value: this.data.value + num
            })
          }
        }
        //判断删除操作
      } else if (type === 'del' && this.data.value !== '') {
        if (this.data.value === '0.') {
          this.setData({
            value: ''
          })
        } else {
          this.setData({
            value: this.data.value.substring(0, (this.data.value.length - 1))
          })
        }
      } else if (type === 'empty') {
        this.setData({
          value: ''
        })
      }
      var myEventDetail = {
        value: this.data.value
      } // detail对象，提供给事件监听函数
      var myEventOption = {} // 触发事件的选项
      this.triggerEvent('numChange', myEventDetail, myEventOption)
    },
    //确定
    ok: function() {
      var myEventDetail = {
        value: this.data.value,
        account: this.data.accountList[this.data.accountSelectedIndex].type,
        date: this.data.date,
        remark: this.data.remark
      } // detail对象，提供给事件监听函数
      var myEventOption = {} // 触发事件的选项
      this.triggerEvent('submit', myEventDetail, myEventOption)
    },
    dateInput(event) {
      this.initDate(new Date(event.detail))
      this.onClose();
    },
    showDateSelect() {
      this.setData({
        show: true
      });
    },
    //关闭选择日期
    onClose() {
      this.setData({
        show: false
      });
    },
    //显示选择账户类型
    selectAccount() {
      this.setData({
        showAccountSelect: true
      })
    },
    //关闭选择账户类型
    onAccountSelectClose() {
      this.setData({
        showAccountSelect: false
      })
    },
    //选中账户类型后
    onAccountSelect(e) {
      let index = e.currentTarget.dataset.index
      for (let i in this.data.accountList) {
        i == index ? this.data.accountList[i].selected = true : this.data.accountList[i].selected = false;
      }
      this.setData({
        accountType: this.data.accountList[index].type,
        accountSelectedIndex: index,
        accountList: this.data.accountList
      })
      this.onAccountSelectClose();
    },
    remarkInput(e){
      this.data.remark = e.detail.value
    }
  }
})