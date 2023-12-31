<template>
  <div class="pageContent">
    <h1 class="title">ENSO预测结果</h1>
    <p></p>
    <div class="datePickerContainer">
      <!-- 已控制时间选择范围 -->
      <el-date-picker @change="update_charts()" v-model="start_year" type="year" format="YYYY" value-format="YYYY"
        :clearable="false" :disabledDate="limitedDateRange" style="width: 80px; height: 25px" />
      <div class="text">年</div>
      <el-date-picker @change="update_charts()" v-model="start_month" type="month" format="MM" value-format="MM"
        :clearable="false" :disabledDate="limitedDateRange" style="width: 60px; height: 25px" />
      <div class="text">月</div>
    </div>

    <el-tabs type="border-card" @tab-click="handleClick">

      <el-tab-pane label="指数预测">

      </el-tab-pane>

      <el-tab-pane label="模态预测">

      </el-tab-pane>

      <el-tab-pane label="模态预测">


</el-tab-pane>
    </el-tabs>
  </div>
</template>
    

<script setup>
import { ref, onMounted, reactive, watch, nextTick, defineExpose } from "vue";
import * as echarts from "echarts";
import axios from "axios";
import VChart from 'vue-echarts';
import { configProviderContextKey } from "element-plus";
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'


</script>
    

<style scoped lang="scss">
.title {
  text-align: center
}

.datePickerContainer {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
}

.text {
  margin-left: 5px;
  margin-right: 10px;
}

/*chart1的表和文字*/
.chart_1 {
  height: 400px;
}

.text1 {
  text-align: center;
}

.picture {
  width: 700px;
  display: block;
  /* 将元素设置为块级元素 */
  margin: auto;
}

.pic_container {
  overflow: hidden;
}

.picture_title {
  text-align: center;
  font-size: 18px;
}

/* 预报误差页面的容器 */
.chart-container {
  position: relative;
}

/* 设置左箭头按钮的样式 */
.el-button.arrow-left {
  position: absolute;
  top: 50%;
  /* 将箭头按钮的顶部与父容器的中间对齐 */
  left: 0;
  /* 将箭头按钮的左侧与父容器的左侧对齐 */
  width: 40px;
  /* 设置按钮宽度 */
  height: 80px;
  /* 设置按钮高度 */
  transform: translateY(-50%);
  /* 垂直居中箭头按钮 */
}

/* 设置右箭头按钮的样式 */
.el-button.arrow-right {
  position: absolute;
  top: 50%;
  /* 将箭头按钮的顶部与父容器的中间对齐 */
  right: 0;
  /* 将箭头按钮的右侧与父容器的右侧对齐 */
  width: 40px;
  /* 设置按钮宽度 */
  height: 80px;
  /* 设置按钮高度 */
  transform: translateY(-50%);
  /* 垂直居中箭头按钮 */
}
</style>