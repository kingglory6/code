<template>

<el-container class="app-container"
  :class="{
    'mini-side': isCollapse,
    'hide-side': hideSide
  }"
  >
  <el-aside class="app-side" :width="hideSide ? '0' : sideWidth + 'px'"
    :style="{background: theme.theme.backgroundColor}"
    >
    <div class="app-header-logo-box"
      :style="{height: headerHeight + 'px', color: theme.theme.activeTextColor}"
      >
      <img src="../assets/logo.png" alt="" class="header-logo">
      <span class="header-logo-text" >控制台</span>
    </div>
    <app-side :collapse="isCollapse" :theme="theme.theme"></app-side>
  </el-aside>
  <el-container style="overflow-x: auto">
    <el-header class="app-header" :height="headerHeight + 'px'">
      <app-header @switch="handleSideSwitch" @set-theme="handleSetTheme" @hide-side="handleSwitchHideSide"></app-header>
    </el-header>
    <el-main class="app-body">
      <el-container style="height: 100%;min-height: 100%;overflow: auto" id="appBody">
        <el-main class="app-page-body">
          <!-- 使用动态的 transition name -->
          <transition :name="transitionName">
            <router-view class="router"></router-view>
          </transition>
        </el-main>
        <el-footer class="app-footer" :height="footerHeight + 'px'">
          <app-footer></app-footer>
        </el-footer>
      </el-container>
    </el-main>
  </el-container>
  <m-back-top body-id="appBody"></m-back-top>
</el-container>


</template>
<script type="text/javascript">
import AppHeader from '@/components/app-header'
import AppFooter from '@/components/app-footer'
import AppSide from '@/components/app-side'
export default {
  name: 'app-view',
  data () {
    return {
      isCollapse: false,
      hideSide: false,
      sideWidth: 200,
      footerHeight: 50,
      headerHeight: 50,
      theme: {theme: {}},
      transitionName:''
    }
  },
  components: {
    AppHeader,
    AppFooter,
    AppSide
  },
  methods: {
    handleSideSwitch (val) {
      this.isCollapse = val
      this.sideWidth = val ? 60 : 200
    },
    handleSwitchHideSide () {
      this.hideSide = !this.hideSide
    },
    handleSetTheme (theme) {
      this.theme = theme
    },
    
  },
  watch: {
  '$route' (to, from) {
      //如果to索引大于from索引,判断为前进状态,反之则为后退状态
      if(to.meta.index > from.meta.index){
	    //设置动画名称
        this.transitionName = 'slide-left';
      }else{
        this.transitionName = 'slide-right';
      }
    }
  }
}
</script>
<style type="text/css">
.router{
  width: 100%;
}
.slide-right-enter-active,
.slide-right-leave-active,
.slide-left-enter-active,
.slide-left-leave-active {
  will-change: transform;
  transition: all 500ms;
  position: absolute;
}
.slide-right-enter {
  opacity: 0;
  transform: translate(0, -100%);
}
.slide-right-leave-active {
  opacity: 0;
  transform: translate(0, 100%);
}
.slide-left-enter {
  opacity: 0;
  transform: translate(0, 100%);
}
.slide-left-leave-active {
  opacity: 0;
  transform: translate(0, -100%);
}
  .app-container{
    margin: 0 auto;
    position: absolute;
    width: 100%;
    height: 100%;
  }
  .app-container .app-header{
    padding: 0;
    background: #16AAD8;
    overflow: visible;
    z-index: 100;
  }
  .app-container .app-side{
    width: 200px;
    transition: all 0.5s ease;
  }
  .app-container .app-body{
    padding: 0;
  }
  .app-container .app-footer{
    background: #fff;
    border-top: solid 1px rgba(48, 54, 62, 0.14);
  }
  .app-container .app-page-body{
    overflow: visible;
    padding: 0px;
  }
  .app-header-logo-box{
    padding: 15px;
    box-sizing: border-box;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .app-header-logo-box .header-logo{
    height: 42px;
    margin-top: -17px;
    margin-right: 5px;
    vertical-align: middle;
  }
  .app-header-logo-box .header-logo-text{
    font-size: 20px;
    font-weight: bold;
    opacity: 1;
  }

  /*mini-side*/
  .app-container.mini-side .app-side{
    overflow: visible;
  }
  .app-container.mini-side .app-side .el-menu--collapse{
    width: 60px;
  }
  .app-container.mini-side .header-logo{
    margin-left: -10px;
  }
  .app-container.mini-side .header-logo-text{
    opacity: 0;
  }
  /*hide-side*/
  .app-container.hide-side .app-side{
    display: none;
  }
</style>
