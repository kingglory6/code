<template>
<div class="app-footer-box">
  <!-- <img src="../assets/logo.png" alt="logo" class="footer-logo"> -->
  <i class="fa fa-meetup" style="color: #29ABE2"></i>&nbsp;
  <span class="footer-text">{{appName}} ©make by <a href="https://www.github.com/mengdu" target="_blank">{{author}}</a>
  &nbsp;版本号：<el-tag size="mini">{{version}}</el-tag>
  </span>
</div>
</template>
<script type="text/javascript">
export default {
  data () {
    return {
      author: '进击的咸鱼',
      version: '0.0.1',
      appName: '自营电商'
    }
  }
}
</script>
<style type="text/css">
  .app-footer-box{
    height: 100%;
    display: flex;
    text-align: center;
    justify-content: center;
    align-items: center;
  }
  .app-footer-box .footer-logo{
    width: 24px;
    margin-right: 10px;
    vertical-align: middle;
  }
  .app-footer-box .footer-text{
    color: #8b949e;
  }
</style>
