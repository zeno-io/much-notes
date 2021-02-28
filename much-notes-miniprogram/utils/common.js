const WARNING = 1;
var app = getApp();
const constants = app.globalData.constants;
const yunOSS = app.globalData.yunOSS;
function showToast (content,type = null) {
  let img=null;
  switch(type){
    case WARNING:
      img = '/imgs/icon_warning.png';
    break

  }
  if(type==null){
    wx.showToast({
      title: content
    })
  }else{
    wx.showToast({
      title: content,
      image: img
    })
  }
}

/**
 * 数据获取通用方法，主要用于登陆后的一般的数据请求
 */
function requestCommon(url, params, callBack,method = 'POST') {
  wx.showNavigationBarLoading();
  // wx.showLoading({
  //   title: 'loading',
  // });
  //添加两个通用的参数
  params.from = "app";

  wx.request({
    url: app.globalData.url + url,
    data: params,
    method: method,
    header: {
      'Content-type': 'application/json;charset=utf-8', // 默认值
      'token': app.globalData.appUserInfo==null?"":app.globalData.appUserInfo.token
    },
    success: function (res) {
      wx.hideNavigationBarLoading();
      // wx.hideLoading();
      //如果是token失效了，重启去首页
      if (res.data.code === constants.INVALID_TOKEN){
        showToast("登陆过期", WARNING);

        try {
          // app.globalData.userInfo = null;
          app.globalData.appUserInfo = null;
          // app.globalData.account_book_id = null;
          wx.removeStorageSync('user');
          // wx.clearStorage();
        } catch (e) {

        }
        if (!app.globalData.regetToken){
          wx.reLaunch({
            url: '/pages/index/index'
          })
          app.globalData.regetToken = true;
        }

        // callBack(false, null);
        return;
      }
      if (res.data.code !== constants.SUCCESS) {
        showToast(checkStrNotNull(res.data.message) ? res.data.message : "访问失败",
          WARNING);
        console.log(res);
        // TODO 具体哪些错误这里可以统一处理
      } else {
        callBack(true, res.data);
      }
    },
    fail: function () {
      wx.hideLoading();
      callBack(false, null);
    }
  })
}

/**
 * 数据获取通用方法，主要用于登陆后的一般的数据请求
 */
function requestGetCommon(url, params, callback) {
  requestCommon(url, params, callback, "GET");
}

/**
 * 用于获取str内容，避免对每个str进行n非ull判断
 */
function getString(str){
  if(str==null||str==undefined){
    return "";
  }
  return str;
}
/**
 * 对str进行n非ull判断
 */
function checkStrNotNull(str) {
  if (str == null || str == undefined || str == '') {
    return false;
  }
  return true;
}
/**
 * 将json格式的data转换成yyyy-MM-dd格式
 */
function dateString(str) {
  if(str==""||str==null||str==undefined){
    return "";
  }

  var date = new Date(parseInt(str.replace("/Date(", "").replace(")/", ""), 10));
  var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
  var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
  return date.getFullYear() + "-" + month + "-" + currentDate;

}
/**
 * 将图片字符串转化为标准图片链接
 */
function formateImg(str) {
  let weburl = "http://dxdj.o2odj.cn"
  let titlurl = ''
  if (str == undefined || str == "" || str.count==0) {
    return titlurl;
  }
  else {
    try{
      var b = JSON.parse(str); //JSON.parse(str);
    }catch(err){
      var b = [];
      b.push(str)
    }
    if (b.length > 0) {
      titlurl = b[0];
      var dd = titlurl.substr(0, 1);
      if (dd == "~") {
        titlurl = titlurl.replace("~", weburl);
      }
    }
  }

  return titlurl;

}
//替换为阿里云的小图片
function  replaceToSmallImg(url) {
  if (url == null || url == undefined) {
    return "";
  }
  url = url.replace("~/upload/", yunOSS.YUN_OSS_URL);
  url += "?" + yunOSS.YUN_OSS_SMALL_IMG_SIZE;
  return url;
}
//替换为阿里云的小图片
function replaceToYunOSSImg(url) {
  if (url == null || url == undefined) {
    return "";
  }
  url = url.replace("~/upload/", yunOSS.YUN_OSS_URL);
  return url;
}
function formatDate(format, timestamp, full) {
  format = format.toLowerCase();
  if (!format) format = "y-m-d h:i:s";

  function zeroFull(str) {
    // return (str >= 10 ? str : ('0' + str));
    return (str >= 10 ? str : str);
  }
  function timeZeroFull(str) {
    return (str >= 10 ? str : ('0' + str));
  }
  var time = new Date(timestamp),
    o = {
      y: time.getFullYear(),
      m: zeroFull(time.getMonth() + 1),
      d: timeZeroFull(time.getDate()),
      h: timeZeroFull(time.getHours()),
      i: timeZeroFull(time.getMinutes()),
      s: zeroFull(time.getSeconds())
    };
  return format.replace(/([a-z])(\1)*/ig, function (m) {
    return o[m];
  });
}
module.exports = {
  showToast: showToast,
  request: requestCommon,
  requestGet: requestGetCommon,
  getString: getString,
  checkStrNotNull: checkStrNotNull,
  dateString: dateString,
  formateImg: formateImg,
  replaceToSmallImg: replaceToSmallImg,
  replaceToYunOSSImg: replaceToYunOSSImg,
  formatDate: formatDate
}
