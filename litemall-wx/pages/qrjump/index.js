var util = require('../../utils/util.js');
var api = require('../../config/api.js');
// pages/qrjump/index.js
Page({

  /**
   * Page initial data
   */
  data: {
    q: ''
  },

  getPageUrl: function (url) {
    let that = this;
    util.request(api.DecodePageUrl, {
      param: url
    }, 'POST').then(function (res) {
      if (res.errno === 0) {
        console.log(res)
        if(res.tab && res.tab == true){
          wx.switchTab({
            url: res.page
          })
        }else{

          if (res.page == '/pages/index/index') {
            wx.switchTab({
              url: '/pages/index/index'
            })
          } else {

            wx.redirectTo({
              url: res.page
            })
          }
        }
      }
    });
  },
  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    console.log(options)
    var url = decodeURIComponent(options.q)
    console.log(url)
    this.q = url

    // var search = url.substring(url.indexOf('?'));
    // var params = JSON.parse('{"' + decodeURI(search).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}')
    // console.log(params)
  },

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function () {

  },

  /**
   * Lifecycle function--Called when page show
   */
  onShow: function () {
    console.log('try to decode page')
    this.getPageUrl(this.q)

  },

  /**
   * Lifecycle function--Called when page hide
   */
  onHide: function () {

  },

  /**
   * Lifecycle function--Called when page unload
   */
  onUnload: function () {

  },

  /**
   * Page event handler function--Called when user drop down
   */
  onPullDownRefresh: function () {

  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function () {

  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function () {

  }
})