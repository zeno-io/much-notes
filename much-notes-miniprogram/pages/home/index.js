//x.js 逻辑处理，这里还需要引入 F2 用于绘制图表，结构如下，注意路径正确。
// index.js
let c = require('../../utils/common.js');

let chart = null;

function initChart(canvas, width, height, F2) { // 使用 F2 绘制图表
  const data = [];
  chart = new F2.Chart({
    el: canvas,
    width,
    height
  });
  //设置样式
  F2.Global.setTheme({
    pixelRatio: 2,
    guide: {
      line: {
        lineWidth: 2
      }
    }
  });
  chart.source(data, {
    type:{
      type:'cat'
    } ,
    // month: {
    //   type: 'timeCat'
    // } ,
    count: {
      tickCount: 5
    }
  });
  chart.scale('count',{
    formatter:function(e){
      console.log(e)
      return e;
    }
  }),
  chart.tooltip({
    showCrosshairs: true,
    onShow(ev) {
      const {
        items
      } = ev;
      items[0].name = null;
      items[0].name = items[0].title;
      items[0].value = items[0].origin.type+":" + items[0].value;
    }
  });
  // chart.area().position('month*count').adjust('stack');
  // chart.line().position('month*count');
  chart.interval().position('month*count').color("type").adjust('stack');
  chart.render();
  return chart;
}

Page({
  data: {
    years: [],
    cIndex: 0,
    totalTranslate: 0,
    windowWidth: 320,
    ani: '',
    abid: '',
    opts: {
      onInit: initChart
    }
  },

  onReady() {},
  onLoad() {
    let abid = getApp().globalData.account_book_id;
    this.setData({
      abid: abid
    })
    let year = new Date().getFullYear();
    let years = [];

    for (let i = 2017; i < year; i++) {
      years.push(i);
    }
    years.push(year);
    this.setData({
      years: years,
      cIndex: (years.length - 1)
    })
    this.getYearData();
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.data.windowWidth = wx.getSystemInfoSync().windowWidth;
    if (typeof this.getTabBar === 'function' && this.getTabBar()) {
      this.getTabBar().setData({
        selected: 0
      })
    }
  },
  getYearData() {
    c.requestGet('/mp/stat/getTotalByYear', {
      id: this.data.abid,
      year: this.data.years[this.data.cIndex]
    }, (succ, data) => {
      if (data.result != null && data.result.length > 0) {
        let formatData = [];
        data.result.forEach(v => {
          formatData.push({
            type: '收入',
            count: parseInt((v.sr) / 100),
            month: v.month
          })
          formatData.push({
            type: '支出',
            count: parseInt((v.zc) / 100),
            month: v.month
          })
          formatData.push({
            type: '净收入',
            count: parseInt((v.sr-v.zc) / 100),
            month: v.month
          })
        })
        chart.changeData(formatData);
      }else{
        chart.changeData([]);
      }
    })
  },
  swiper(e) {
    let type = e.currentTarget.dataset.type;
    if (type == 0) {
      if (this.data.years.length > this.data.cIndex + 1) {
        this.data.cIndex++;
      } else {
        return;
      }
    } else {
      if (this.data.cIndex > 0) {
        this.data.cIndex--;
      } else {
        return;
      }
    }
    this.move2left(type);
    this.getYearData();
  },
  //向左滑动操作

  move2left(type) {
    let that = this;
    let animation = wx.createAnimation({
      duration: 700,
      timingFunction: 'ease',
      delay: 100
    });

    let width = (type == 0 ? -1 : 1) * this.data.windowWidth * 0.2;
    this.data.totalTranslate += width;
    animation.translate(this.data.totalTranslate, 0).step()
    this.setData({
      ani: animation.export()
    })
  }
});
