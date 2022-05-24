package com.common.utils.pdf;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * java 创建各种图表
 *
 * @author djq
 * @date 2022/5/23 15:19
 */
public class JFreeChartUtils {
    public static void main(String args[]) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Map<Day, Number> timeMap = new HashMap<>();
        timeMap.put(new Day(20, 1, 2004), 200);
        timeMap.put(new Day(20, 2, 2004), 250);
        timeMap.put(new Day(20, 3, 2004), 450);
        timeMap.put(new Day(20, 4, 2004), 475);
        timeMap.put(new Day(20, 5, 2004), 125);
        timeMap.put(new Day(20, 6, 2004), 150);
        Map<Day, Number> timeMap1 = new HashMap<>();
        timeMap1.put(new Day(20, 1, 2004), 100);
        timeMap1.put(new Day(20, 2, 2004), 150);
        timeMap1.put(new Day(20, 3, 2004), 350);
        timeMap1.put(new Day(20, 4, 2004), 375);
        timeMap1.put(new Day(20, 5, 2004), 25);
        timeMap1.put(new Day(20, 6, 2004), 50);
        map.put("目标企业户均用电量", timeMap);
        map.put("所在群体户均用电量", timeMap1);
        String path = timeLineChart(map,"对比所在群体户均用电量","日期","金额");
        System.out.println(path);
    }

    public static String timeLineChart(Map<String, Object> map, String title, String xAxisLabel, String yAxisLabel) throws IOException {
        TimeSeriesCollection dataSet = new TimeSeriesCollection();
        for (String key : map.keySet()) {
            TimeSeries timeSeries = new TimeSeries(key);
            Map<Day, Number> xyMap = (Map<Day, Number>) map.get(key);
            for (Day x : xyMap.keySet()) {
                timeSeries.add(x, xyMap.get(x));
            }
            dataSet.addSeries(timeSeries);
        }
        //处理中文乱码
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
        //标题
        standardChartTheme.setExtraLargeFont(new Font("宋体",Font.BOLD,20));
        // legend
        standardChartTheme.setRegularFont(new Font("宋体",Font.PLAIN,18));
        //轴向字体
        standardChartTheme.setLargeFont(new Font("宋体",Font.PLAIN,16));
        ChartFactory.setChartTheme(standardChartTheme);
        Image img = Toolkit.getDefaultToolkit().getImage("F:\\filePath\\折线图\\数据区背景图.png");
        //Image img = Toolkit.getDefaultToolkit().getImage("F:\\filePath\\折线图\\愤怒的小鸟.png");

        JFreeChart chart = ChartFactory.createTimeSeriesChart(title, xAxisLabel, yAxisLabel, dataSet, true, true, false);
        //添加背景图
        chart.setBackgroundImage(img);
        //chart.setBackgroundPaint(null);
        //设置时间格式
        XYPlot plot = chart.getXYPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("yyyy/MM"));
        //横轴文字竖着展示
        axis.setVerticalTickLabels(true);
        axis.setTickMarkPosition(DateTickMarkPosition.END);
        // 数据区添加背景图
        plot.setBackgroundImage(img);
        //Color color = new Color(252,12,245);
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(Color.CYAN);
        //plot.setBackgroundPaint(color);
        //plot.setBackgroundImageAlpha(0.01f);


        //设置横纵标签位置
        //ValueAxis rangeAxis = plot.getRangeAxis();

        //横坐标旋转一个角度
        //CategoryPlot categoryPlot = chart.getCategoryPlot();
        //CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
        //categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);


        int width = 1000;
        int height = 480;
        File timeChart = new File("F:\\filePath\\折线图\\" + new Random().nextInt(9999) + ".png");
        ChartUtilities.saveChartAsPNG(timeChart, chart, width, height);
        //ChartFrame cf = new ChartFrame("时序图", chart);
        //cf.pack();
        //cf.setVisible(true);
        return timeChart.getPath();
    }
}
