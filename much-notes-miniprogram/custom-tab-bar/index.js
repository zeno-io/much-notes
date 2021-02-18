Component({
  data: {
    hide:false,
    selected: 0,
    color: "#7A7E83",
    selectedColor: "#1296db",
    list: [
      {
        pagePath: "/pages/home/index",
        iconPath: "/imgs/icon_account_book.png",
        selectedIconPath: "/imgs/icon_account_book_active.png",
        text: "统计"
      },
      {
        pagePath: "/pages/jizhang/index",
        iconPath: "/imgs/icon_plus_circle.png",
        selectedIconPath: "/imgs/icon_plus_circle_active.png",
        text: "记账"
      },
      {
        pagePath: "/pages/my/index",
        iconPath: "/imgs/icon_my.png",
        selectedIconPath: "/imgs/icon_my_active.png",
        text: "我的"
      }
    ]
  },
  attached() {
  },
  methods: {
    switchTab(e) {
      const data = e.currentTarget.dataset
      if (this.data.selected == data.index && data.index == 1) {
        wx.navigateTo({
          url: '../addrecord/index'
        })
        return
      }
      const url = data.path
      wx.switchTab({ url })
      this.setData({
        selected: data.index
      })
    }
  }
})