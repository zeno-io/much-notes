const app = getApp()

Page({
  data: {
    list:[
      {
        title:"这是第一个",
        records: ['支出600', '支出600', '支出600', '支出600', '支出600', '支出600', '支出600']
      },
      {
        title: "这是第二个",
        records: ['支出600', '支出600', '支出600', '支出600', '支出600', '支出600', '支出600']
      },
      {
        title: "这是第三个",
        records: ['支出600', '支出600', '支出600', '支出600', '支出600', '支出600', '支出600']
      },
      {
        title: "这是第四个",
        records: ['支出600', '支出600', '支出600', '支出600', '支出600', '支出600', '支出600']
      },],
    positions: ['fixed', 'absolute', 'absolute', 'absolute', 'absolute', 'absolute', 'absolute', 'absolute']
  },
  onLoad: function () {
    this._observerHeader = wx.createIntersectionObserver(this, { thresholds: [0, 0.5, 0.9], observeAll: true });
    this._observerFooter = wx.createIntersectionObserver(this, { thresholds: [0, 0.5, 1], observeAll: true });
    //这里用于监听当前组件是否到顶了
    this._observerHeader.relativeToViewport().observe('.sticky_sentinel--top', (res) => {
      let index = res.id.replace('sticky_sentinel--top', '');
      if (res.intersectionRatio == 0 && res.boundingClientRect.top < 0) {
        console.log('--' + index + 'Header--fixed', res);
        //标题顶部到顶了(也就是sticky_sentinel--top这个view底部到顶了)，就固定在头部
        this.setData({
          ['positions[' + res.id.replace('sticky_sentinel--top', '') + ']']: 'fixed'
        })
      }

      if (res.intersectionRatio > 0) {

        //标题顶部下滑离开顶部(也就是sticky_sentinel--top这个view底部向下到顶了)，就取消固定在头部
        let index = res.id.replace('sticky_sentinel--top', '');
        //这里还要判断一下，如果是第一个标题的话，就不执行下面的了
        if (index != 0) {
          console.log('--' + index +'Header--absolute top', res);
          this.setData({
            ['positions[' + res.id.replace('sticky_sentinel--top', '') + ']']: 'absolute top'
          })
        }
      }
      // console.log(this.data);
    });
    this._observerFooter.relativeToViewport().observe('.sticky_sentinel--bottom', (res) => {
      let index = res.id.replace('sticky_sentinel--bottom', '');
      if (res.intersectionRatio < 1 && res.boundingClientRect.top < 0) {
        console.log('--' + index + 'Footer--absolute bottom', res);
        //到底了
        this.setData({
          ['positions[' + res.id.replace('sticky_sentinel--bottom', '') + ']']: 'absolute bottom'
        })
      }
      // if (res.intersectionRatio == 1 && res.boundingClientRect.top <= res.boundingClientRect.height) {
      if (res.intersectionRatio == 1) {
        console.log('--' + index + 'Footer--fixed', res);
        this.setData({
          ['positions[' + res.id.replace('sticky_sentinel--bottom', '') + ']']: 'fixed'
        })
      }
      // console.log(this.data);
    });
  },
  onUnload() {
    if (this._observerHeader) this._observerHeader.disconnect()
    if (this._observerFooter) this._observerFooter.disconnect()
  }
})
