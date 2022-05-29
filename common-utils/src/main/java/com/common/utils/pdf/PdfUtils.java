package com.common.utils.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.jfree.data.time.Day;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * pdf工具类
 *
 * @author djq
 * @date 2022/5/20 15:03
 */
public class PdfUtils {


    public static void pdfout(Map<String,Object> o){
        String templatePath = "E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\信用报告测试版.pdf";
        String newPdfPath = "F:\\filePath\\pdf\\信用报告测试版.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            BaseFont bf = BaseFont.createFont("c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font FontChinese = new Font(bf, 5, Font.NORMAL);
            out = new FileOutputStream(newPdfPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //文字类的内容处理
            Map<String,String> datemap = (Map<String,String>)o.get("datemap");
            form.addSubstitutionFont(bf);
            for(String key : datemap.keySet()){
                String value = datemap.get(key);
                form.setField(key,value);
            }
            //图片类的内容处理
            Map<String,String> imgmap = (Map<String,String>)o.get("imgmap");
            for(String key : imgmap.keySet()) {
                String value = imgmap.get(key);
                String imgpath = value;
                int pageNo = form.getFieldPositions(key).get(0).page;
                Rectangle signRect = form.getFieldPositions(key).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.close();
            Document doc = new Document();
            Font font = new Font(bf, 32);
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            PdfImportedPage importPage1 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 2);
            PdfImportedPage importPage2 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 3);
            PdfImportedPage importPage3 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 4);
            PdfImportedPage importPage4 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 5);
            PdfImportedPage importPage5 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 6);
            PdfImportedPage importPage6 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 7);
            PdfImportedPage importPage7 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 8);
            copy.addPage(importPage);
            copy.addPage(importPage1);
            copy.addPage(importPage2);
            copy.addPage(importPage3);
            copy.addPage(importPage4);
            copy.addPage(importPage5);
            copy.addPage(importPage6);
            copy.addPage(importPage7);
            doc.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String,String> map = new HashMap();
        map.put("bgTime","20220523");
        map.put("bgId","891234798701");
        map.put("entName","番禺市总工会");
        map.put("assResult","中度风险，谨慎处理");
        map.put("entNameHead","番禺市总工会");
        map.put("year","2022");
        map.put("mouth","05");
        map.put("day","23");
        map.put("score","46");
        map.put("code","13440113455410938Q");
        map.put("a","番禺市总工会");
        map.put("b","2022.05.25");
        map.put("c","番禺市总工会");
        map.put("d","13440113455410938Q");
        map.put("e","1500万");
        map.put("f","24");
        map.put("g","10次");
        map.put("h","6");
        map.put("i","66666");
        map.put("j","10");
        map.put("k","600");
        map.put("l","20222-05");
        map.put("m","+");
        Map<String,Object> o=new HashMap();
        Map<String,String> map2 = new HashMap();
        map2.put("img","E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\没有分值.png");
        Map<String, Object> mapt = new HashMap<>();
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
        mapt.put("目标企业户均用电量", timeMap);
        mapt.put("所在群体户均用电量", timeMap1);
        String path = JFreeChartUtils.timeLineChart(mapt,"对比所在群体户均用电量","日期","金额");
        map2.put("line",path);
        o.put("datemap",map);
        o.put("imgmap",map2);
        pdfout(o);
    }
}