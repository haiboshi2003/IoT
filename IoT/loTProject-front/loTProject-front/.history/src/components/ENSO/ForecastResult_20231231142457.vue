<template>
  <div class="pageContent">
    <h1 class="title">ENSO预测结果</h1>
    <p></p>

    <el-date-picker
      v-model="selectedDate"
      type="date"
      placeholder="选择日期"
      @change="updateSelectedData"
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
      <!-- 其他标签页的内容 -->
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
      simulatedData: {
        '2023-01-01': Array.from({ length: 10 }, () => Math.floor(Math.random() * 100)),
        '2023-01-02': Array.from({ length: 10 }, () => Math.floor(Math.random() * 100)),
        '2023-01-03': Array.from({ length: 10 }, () => Math.floor(Math.random() * 100)),
      },
      selectedDate: new Date(), // 改为 Date 对象
      selectedData: [],
    };
  },

  mounted() {
    this.updateSelectedData();
    this.drawLine('main');
  },

  methods: {
    updateSelectedData() {
      // 格式化所选日期以匹配 simulatedData 中的键
      const formattedDate = this.selectedDate.toISOString().split('T')[0];
      this.selectedData = this.simulatedData[formattedDate] || [];
      console.log("选择的日期"+this.selectedDate)
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
          data: Array.from({ length: this.selectedData.length }, (_, i) => (i + 1) + '号'), // 调整这里的计算方式
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
            data: this.selectedData,
          },
        ],
      });
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
