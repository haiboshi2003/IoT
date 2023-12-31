<template>
  <div class="pageContent">
    <h1 class="title">ENSO预测结果</h1>
    <p></p>
    <div class="datePickerContainer">
      <el-date-picker @change="update_charts()" v-model="start_year" type="year" format="YYYY" value-format="YYYY"
        :clearable="false" :disabledDate="limitedDateRange" style="width: 80px; height: 25px" />
      <div class="text">年</div>
      <el-date-picker @change="update_charts()" v-model="start_month" type="month" format="MM" value-format="MM"
        :clearable="false" :disabledDate="limitedDateRange" style="width: 60px; height: 25px" />
      <div class="text">月</div>
    </div>

    <el-tabs type="border-card" @tab-click="handleClick">
      <el-tab-pane label="指数预测">
        <!-- 折线图占位容器 -->
        <div id="main" style="width: 100%; height: 520px; background: #fff;"></div>
      </el-tab-pane>
      <el-tab-pane label="模态预测">
        <!-- 在其他分页中可以添加需要的内容 -->
      </el-tab-pane>
      <el-tab-pane label="模态预测">
        <!-- 在其他分页中可以添加需要的内容 -->
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: 'ForecastResult',
  data() {
    return {
      start_year: '', // 你的数据
      start_month: '', // 你的数据
    };
  },

  created() {
    this.drawLine('main');
  },

  methods: {
    drawLine(id) {
      const charts = echarts.init(document.getElementById(id));
      charts.setOption({
        title: {
          text: '示例折线图', // 图表标题
          left: 'center',
        },
        xAxis: {
          type: 'category',
          data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'], // X轴数据
        },
        yAxis: {
          type: 'value',
          name: '值', // Y轴标题
        },
        series: [
          {
            name: '数据',
            type: 'line',
            data: [10, 30, 20, 40, 15, 25, 35, 50, 45, 30, 60, 55], // 折线图数据
          },
        ],
      });
    },

    update_charts() {
      // 在需要更新图表时调用
      this.drawLine('main');
    },
  },
};
</script>

<style scoped lang="scss">
.title {
  text-align: center;
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
</style>
