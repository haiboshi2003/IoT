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
          {{ selectedDate ? "所选日期: " + selectedDate : "请选择日期" }}
        </div>
      </template>
    </el-date-picker>

    <el-tabs type="border-card" @tab-click="handleClick">
      <el-tab-pane label="当日温度">
        <!-- 折线图占位容器 -->
        <div
          id="temperature"
          style="width: 1000px; height: 400px; background: #fff"
        ></div>
      </el-tab-pane>

      <el-tab-pane label="当日压强">
        <!-- 折线图占位容器 -->
        <div
          id="pressure"
          style="width: 1000px; height: 400px; background: #fff"
        ></div>
      </el-tab-pane>

      <el-tab-pane label="当日湿度">
        <!-- 折线图占位容器 -->
        <div
          id="humidity"
          style="width: 1000px; height: 400px; background: #fff"
        ></div>
      </el-tab-pane>
      <!-- 其他标签页的内容 -->
    </el-tabs>
  </div>
</template>

<script>
import * as echarts from "echarts";
import axios from "axios";

export default {
  name: "ForecastResult",
  data() {
    return {
      selectedDate: new Date("2014-02-14"), // 改为 Date 对象
      selectedData: [],
      DATE: "",
      chartData: null,
    };
  },
  created() {
    this.getData();
  },

  mounted() {
    this.updateSelectedData();
  },

  methods: {
    getData() {
      axios
        .get("http://localhost:7078/data/getByDate", {
          params: {
            date: this.DATE,
          },
        })
        .then((response) => {
          console.log("test", response);

          console.log("这里来获取该日期的所有数据");
          console.log(response.data.message);
          console.log(response.data.code);
          console.log(response.data.data);

          if (response.data.code === 200) {
            console.log("这里来获取该日期的所有数据成功！");
            this.selectedData = response.data.data;
            console.log(this, this.selectedData);
            this.drawLine("temperature");
            this.drawLine("pressure");
            this.drawLine("humidity");
          } else {
            console.log("这里来获取该日期的所有数据失败！");
          }
        })
        .catch((error) => {
          console.error("这这里来获取该日期的所有数据出错！", error);
        });
    },

    updateSelectedData() {
      // 格式化所选日期以匹配 simulatedData 中的键
      const formattedDate = this.selectedDate.toISOString().split("T")[0];
      console.log("选择的日期" + this.selectedDate);

      // 将 selectedDate 转换为 yy-mm-dd 形式并赋值给 DATE
      // 将 selectedDate 转换为 yy-mm-dd 形式并赋值给 DATE
      const year = this.selectedDate.getFullYear();
      const month = String(this.selectedDate.getMonth() + 1).padStart(2, "0");
      const day = String(this.selectedDate.getDate()).padStart(2, "0");
      this.DATE = `${year}-${month}-${day}`;

      this.getData();
      console.log("更新时间");
      console.log(this.DATE);
    },

    drawLine(id) {
      const charts = echarts.init(document.getElementById(id));

      let yPropertyName, yAxisMin, yAxisMax;
      if (id === "temperature") {
        yPropertyName = "temperature";
        yAxisMin = -4; // 根据实际情况调整
        yAxisMax = 30; // 根据实际情况调整
      } else if (id === "pressure") {
        yPropertyName = "pressure"; // 假设在您的数据中有 "pressure" 属性
        yAxisMin = 980; // 根据实际情况调整
        yAxisMax = 1040; // 根据实际情况调整
      } else {
        yPropertyName = "humidity"; // 假设在您的数据中有 "humidity" 属性
        yAxisMin = 12; // 根据实际情况调整
        yAxisMax = 100; // 根据实际情况调整
      }

      this.chartData = this.selectedData.map((item) => ({
        time: item.time,
        yValue: item[yPropertyName], // 使用选择的 y 轴属性
      }));

      console.log("用来画图的数据");
      console.log(this.chartData);

      charts.setOption({
        title: {
          text: `${id}变化折线图`, // 根据 id 动态设置标题
          left: "center",
        },
        xAxis: {
          type: "category",
          data: this.chartData.map((item) => item.time),
        },
        yAxis: {
          type: "value",
          name: id, // 根据 id 动态设置 y 轴名称
          min: yAxisMin, // 设置 y 轴最小值
          max: yAxisMax, // 设置 y 轴最大值
        },
        dataZoom: [{ type: "inside" }],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            animation: false,
          },
        },
        series: [
          {
            name: id,
            type: "line",
            data: this.chartData.map((item) => item.yValue),
            smooth: 1,
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
