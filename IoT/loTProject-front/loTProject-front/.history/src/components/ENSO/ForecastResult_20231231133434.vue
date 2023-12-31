<template>
  <div class="pageContent">
    <h1 class="title">ENSO预测结果</h1>
    <p></p>
    <div class="datePickerContainer">
      <el-date-picker @change="update_charts()" v-model="start_date" type="date" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
        :clearable="false" :disabledDate="limitedDateRange" style="width: 120px; height: 25px" />
      <div class="text">日期</div>
      <div class="selectedDate">已选择日期: {{ selectedDate }}</div> <!-- 新增的元素 -->
    </div>

    <el-tabs type="border-card" @tab-click="handleClick">
      <el-tab-pane label="指数预测">
        <!-- 折线图占位容器 -->
        <div id="main" style="width: 850px; height: 400px; background: #fff;"></div>
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
      start_year: '2023', // 设置默认值
      start_month: '01', // 设置默认值
      simulatedData: [], // 模拟的大量数据
    };
  },

  mounted() {
    this.generateSimulatedData();
    this.drawLine('main');
  },

  methods: {
    generateSimulatedData() {
      // 生成模拟的大量数据
      for (let i = 1; i <= 70; i++) {
        this.simulatedData.push({
          month: i + '月',
          value: Math.floor(Math.random() * 100), // 随机生成值
        });
      }
    },

    drawLine(id) {
      const charts = echarts.init(document.getElementById(id));
      charts.setOption({
        title: {
          text: '示例折线图', // 图表标题
          left: 'center',
        },
        xAxis: {
          type: 'category',
          data: this.simulatedData.map(item => item.month), // 使用模拟数据的月份
        },
        yAxis: {
          type: 'value',
          name: '值', // Y轴标题
        },
        dataZoom: [{ type: 'inside' }],
        tooltip: {
          trigger: 'axis', // 使用坐标轴轴线触发
        },
        series: [
          {
            name: '数据',
            type: 'line',
            data: this.simulatedData.map(item => item.value), // 使用模拟数据的值
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
