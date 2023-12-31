<template>
  <div class="pageContent">
    <h1 class="title">ENSO预测结果</h1>
    <p></p>

    <el-date-picker
      v-model="selectedDate"
      type="date"
      placeholder="选择日期"
      @change="update_charts"
    >

    <template v-slot:prepend>
        <div class="selectedDate">
          {{ selectedDate ? '所选日期: ' + selectedDate : '请选择日期' }}
        </div>
      </template>
  </el-date-picker>



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
      start_year: '2023',
      start_month: '01',
      simulatedData: [],
    };
  },

  mounted() {
    this.generateSimulatedData();
    this.drawLine('main');
  },

  methods: {
    generateSimulatedData() {
      for (let i = 1; i <= 70; i++) {
        this.simulatedData.push({
          month: i + '月',
          value: Math.floor(Math.random() * 100),
        });
      }
    },

    drawLine(id) {
      const charts = echarts.init(document.getElementById(id));
      charts.setOption({
        title: {
          text: '示例折线图',
          left: 'center',
        },
        xAxis: {
          type: 'category',
          data: this.simulatedData.map(item => item.month),
        },
        yAxis: {
          type: 'value',
          name: '值',
        },
        dataZoom: [{ type: 'inside' }],
        tooltip: {
          trigger: 'axis',
        },
        series: [
          {
            name: '数据',
            type: 'line',
            data: this.simulatedData.map(item => item.value),
          },
        ],
      });
    },

    update_charts() {
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
