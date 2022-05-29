package com.common.utils.datamall;

import com.common.utils.pdf.BaselineBO;
import com.common.utils.pdf.ImageHeaderFooter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * TODO:
 *
 * @author djq
 * @date 2022/5/29 9:51
 */
public class ExportPdf {
    public static void main(String[] args) throws Exception {
        createPdfList(null);
    }

    public static void createPdfList(BaselineBO param) throws Exception {
        //模板路径
        String temPath = "E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\信用报告测试版.pdf";
        // 读取模板并生成新的pdf模板路径
        String newTemPath = "F:\\filePath\\pdf\\temporary.pdf";
        // 报告生成完成后存储路径
        String resultPath = "F:\\filePath\\pdf\\企业电力信用评估报告.pdf";

        // 定义参数集 Map
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("bgTime","20220523");
        paramMap.put("bgId","891234798701");
        paramMap.put("entName","番禺市总工会");
        paramMap.put("assResult","中度风险，谨慎处理");
        paramMap.put("entNameHead","番禺市总工会");
        paramMap.put("year","2022");
        paramMap.put("mouth","05");
        paramMap.put("day","23");
        paramMap.put("score","46");
        paramMap.put("code","13440113455410938Q");
        paramMap.put("a","番禺市总工会");
        paramMap.put("b","2022.05.25");
        paramMap.put("c","番禺市总工会");
        paramMap.put("d","13440113455410938Q");
        paramMap.put("e","1500万");
        paramMap.put("f","24");
        paramMap.put("g","10次");
        paramMap.put("h","6");
        paramMap.put("i","66666");
        paramMap.put("j","10");
        paramMap.put("k","600");
        paramMap.put("l","20222-05");
        paramMap.put("m","+");
        // 读取模板并填入form参数生成新的模板
        writePdfModel(temPath, newTemPath, paramMap);

        // 生成最终pdf
        generateFinalPdf(newTemPath, resultPath, param);
    }

    /**
     * 生成最终pdf 包含添加表格、时序图、页眉、页脚等内容
     */
    private static void generateFinalPdf(String newTemPath, String resultPath, BaselineBO param) {
        try {
            FileOutputStream outputStream = new FileOutputStream(resultPath);
            PdfReader reader = new PdfReader(newTemPath);
            // 获取pdf尺寸 并创建相同尺寸的文档
            Rectangle pageSize = reader.getPageSize(2);
            Document document = new Document(pageSize);
            //创建编辑器
            PdfWriter writer = PdfWriter.getInstance(document,outputStream);
            document.open();
            // 设置页眉、页脚
            // 待实现
            ImageHeaderFooter headerFooter = new ImageHeaderFooter();
            writer.setPageEvent(headerFooter);
            // 文本定位编辑
            PdfContentByte contentByte = writer.getDirectContentUnder();
            PdfImportedPage pageTemplateOne = writer.getImportedPage(reader, 1);
            PdfImportedPage pageTemplatetwo = writer.getImportedPage(reader, 2);
            contentByte.addTemplate(pageTemplateOne,0,0);
            // 创建新的一页
            document.newPage();
            // 生成电户用电详情
            // todo: 取得电户数量
            int doorCont = 5;
            for (int i = 0 ; i < doorCont; i++){
                Paragraph paragraph = generateDoorTable(param);
                document.add(paragraph);
                document.add(Chunk.NEWLINE);
            }
            // 插入群体信用信息对比 表格
            // 设置字体
            BaseFont bfChinese = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 设置字号
            Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);
            document.add(new Paragraph("用电信息评估",fontChinese));
            document.add(Chunk.NEWLINE);
            Paragraph groupParagraph = generateGroupTable(param);
            document.add(groupParagraph);
            document.add(Chunk.NEWLINE);

            //绘制 对比所在行业户均拖缴金额 表格
            Paragraph perParagraph = generatePerTable(param);
            document.add(perParagraph);
            document.add(Chunk.NEWLINE);

            // 绘制 对比所在行业户均拖缴金额 时序图
            Image image = Image.getInstance("F:\\filePath\\折线图\\1098.png");
            //image.scalePercent(pageSize.getWidth(),pageSize.getHeight());
            //image.setAbsolutePosition(pageSize.getLeft(),pageSize.getBottom());
            float docWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            float docHeigh = docWidth/580*320;
            image.scaleAbsolute(docWidth,docHeigh);
            document.add(image);
            document.add(Chunk.NEWLINE);

            // 绘制 生产经营能力评估 表格
            Paragraph abilityParagraph = generateAbilityTable(param);
            document.add(abilityParagraph);
            document.add(Chunk.NEWLINE);


            document.close();
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 绘制 生产经营能力评估 表格
     */
    private static Paragraph generateAbilityTable(BaselineBO param) {
        try {
            // 设置字体
            BaseFont bfChinese = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 设置字号
            Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);
            // 设置标题 电户的名字
            Paragraph ret = new Paragraph("生产经营能力评估", fontChinese);
            // 定义表格列数
            PdfPTable tableBox = new PdfPTable(2);
            // 设置每个单元格占多宽
            tableBox.setWidths(new float[]{0.5f, 0.5f});
            // 设置宽度百分比
            tableBox.setWidthPercentage(100f);

            // 开始画表格 todo: 表格背景色待调
            //第一行
            tableBox.addCell(getCell(new Phrase("目标企业户均用电对比所在行业户均用电情况", fontChinese), true, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("+",fontChinese), true, 1, 1,null));
            //第二行
            tableBox.addCell(getCell(new Phrase("目标企业户均用电对比所在区位户均用电情况", fontChinese), true, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("+",fontChinese), true, 1, 1,null));
            //第三行
            tableBox.addCell(getCell(new Phrase("目标企业户均用电对比所在客群户均用电情况", fontChinese), true, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("+",fontChinese), true, 1, 1,null));

            ret.add(tableBox);
            return ret;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 绘制 对比所在行业户均拖缴金额 表格
     */
    private static Paragraph generatePerTable(BaselineBO param) {
        try {
            // 设置字体
            BaseFont bfChinese = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 设置字号
            Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);
            // 设置标题 电户的名字
            Paragraph ret = new Paragraph("群体信用信息", fontChinese);
            // 定义表格列数
            PdfPTable tableBox = new PdfPTable(3);
            // 设置每个单元格占多宽
            tableBox.setWidths(new float[]{0.33f, 0.33f, 0.34f});
            // 设置宽度百分比
            tableBox.setWidthPercentage(100f);

            // 开始画表格 todo: 表格背景色待调
            //第一行
            tableBox.addCell(getCell(new Phrase("对象", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("日期",fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("拖缴金额(单位: 人民币)", fontChinese), false, 1, 1,BaseColor.GREEN));
            //第二行
            tableBox.addCell(getCell(new Phrase("所在行业户均(中位数)", fontChinese), false, 1, 1,null));
            tableBox.addCell(getCell(new Phrase("2022-01", fontChinese), false, 1, 1,null));
            tableBox.addCell(getCell(new Phrase("1200",fontChinese), false, 1, 1,null));
            // 根据用户数决定行数
            for (int i = 0; i < 3; i++){
                tableBox.addCell(getCell(new Phrase("电户", fontChinese), false, 1, 1,null));
                tableBox.addCell(getCell(new Phrase("2022-01", fontChinese), false, 1, 1,null));
                tableBox.addCell(getCell(new Phrase("1200",fontChinese), false, 1, 1,null));
            }
            ret.add(tableBox);
            return ret;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 群体信用信息对比表格创建
     */
    private static Paragraph generateGroupTable(BaselineBO param) {
        try {
            // 设置字体
            BaseFont bfChinese = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 设置字号
            Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);
            // 设置标题 电户的名字
            Paragraph ret = new Paragraph("群体信用信息", fontChinese);
            // 定义表格列数
            PdfPTable tableBox = new PdfPTable(3);
            // 设置每个单元格占多宽
            tableBox.setWidths(new float[]{0.33f, 0.33f, 0.34f});
            // 设置宽度百分比
            tableBox.setWidthPercentage(100f);

            // 开始画表格 todo: 表格背景色待调
            //第一行
            tableBox.addCell(getCell(new Phrase("对象", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("拖缴天数",fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("拖缴金额", fontChinese), false, 1, 1,BaseColor.GREEN));
            //第二行
            tableBox.addCell(getCell(new Phrase("目标企业用电缴费情况", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("20", fontChinese), false, 1, 1,null));
            tableBox.addCell(getCell(new Phrase("1200元",fontChinese), false, 1, 1,null));
            //第三行
            tableBox.addCell(getCell(new Phrase("目标企业所在行业用电缴费情况", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("20", fontChinese), false, 1, 1,null));
            tableBox.addCell(getCell(new Phrase("1200元",fontChinese), false, 1, 1,null));
            //第四行
            tableBox.addCell(getCell(new Phrase("目标企业用所在区位电缴费情况", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("20", fontChinese), false, 1, 1,null));
            tableBox.addCell(getCell(new Phrase("1200元",fontChinese), false, 1, 1,null));
            //第五行
            tableBox.addCell(getCell(new Phrase("目标企业用所在群体电缴费情况", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("20", fontChinese), false, 1, 1,null));
            tableBox.addCell(getCell(new Phrase("1200元",fontChinese), false, 1, 1,null));
            ret.add(tableBox);
            return ret;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 画电户用电详情biaoge
     */
    private static Paragraph generateDoorTable(BaselineBO param) {
        try {
            // 设置字体
            BaseFont bfChinese = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 设置字号
            Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);
            // 设置标题 电户的名字
            Paragraph ret = new Paragraph("电户", fontChinese);
            // 定义表格列数
            PdfPTable tableBox = new PdfPTable(6);
            // 设置每个单元格占多宽
            tableBox.setWidths(new float[]{0.16f, 0.16f, 0.16f, 0.16f, 0.16f,0.2f});
            // 设置宽度百分比
            tableBox.setWidthPercentage(100f);

            // 开始画表格 todo: 表格背景色待调
            //第一行
            tableBox.addCell(getCell(new Phrase("户号", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("12123",fontChinese), false, 2, 1,null));
            tableBox.addCell(getCell(new Phrase("在网时长", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("123",fontChinese), false, 2, 1,null));
            //第二行
            tableBox.addCell(getCell(new Phrase("电户地址", fontChinese), false, 1, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase("江苏省南京雨花台区云密城J栋10楼", fontChinese), false, 5, 1,null));
            //第三行
            tableBox.addCell(getCell(new Phrase(" 累计拖缴次数 ", fontChinese), false, 2, 2,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 最近半年 ", fontChinese), false, 2, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 6次 ", fontChinese), false, 2, 1,null));
            tableBox.addCell(getCell(new Phrase(" 最近一年 ", fontChinese), false, 2, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 6次 ", fontChinese), false, 2, 1,null));
            //第四行
            tableBox.addCell(getCell(new Phrase(" 累计拖缴金额 ", fontChinese), false, 2, 2,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 最近半年 ", fontChinese), false, 2, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 600人名币 ", fontChinese), false, 2, 1,null));
            tableBox.addCell(getCell(new Phrase(" 最近一年 ", fontChinese), false, 2, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 1200人名币 ", fontChinese), false, 2, 1,null));
            //第五行
            tableBox.addCell(getCell(new Phrase(" 累计拖缴天数 ", fontChinese), false, 2, 2,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 最近半年 ", fontChinese), false, 2, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 30天 ", fontChinese), false, 2, 1,null));
            tableBox.addCell(getCell(new Phrase(" 最近一年 ", fontChinese), false, 2, 1,BaseColor.GREEN));
            tableBox.addCell(getCell(new Phrase(" 60天 ", fontChinese), false, 2, 1,null));

            ret.add(tableBox);
            return ret;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 每个ｃｅｌｌ的格式，合并单元格情况
     */

    private static PdfPCell getCell(Phrase phrase, boolean yellowFlag, int colSpan, int rowSpan,BaseColor color) {
        PdfPCell cells = new PdfPCell(phrase);
        //设置居中
        cells.setUseAscender(true);
        //设置单元格高度
        cells.setMinimumHeight(20f);
        //设置水平居中
        cells.setHorizontalAlignment(1);
        //设置垂直居中
        cells.setVerticalAlignment(5);
        cells.setColspan(colSpan);
        cells.setRowspan(rowSpan);
        cells.setBackgroundColor(color);
        cells.setNoWrap(false);
        return cells;
    }

    /**
     * 读取模板并填入form参数生成新的模板
     */
    private static void writePdfModel(String temPath, String newTemPath, Map<String, String> paramMap) {
        try {
            FileOutputStream outputStream = new FileOutputStream(newTemPath);
            PdfReader temReader = new PdfReader(temPath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(temReader,bos);
            // 获取form文本域
            AcroFields fields = stamper.getAcroFields();
            //定义字体编码
            BaseFont baseFont = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fields.addSubstitutionFont(baseFont);
            // 迭代填入form
            Iterator<String> iterator = fields.getFields().keySet().iterator();
            int i = 0;
            while (iterator.hasNext()){
                String formName = iterator.next();
                //todo 这里确认当参数为空时是否添加默认图标
                fields.setField(formName,paramMap.get(formName));
            }
            stamper.setFormFlattening(true);
            stamper.close();
            outputStream.write(bos.toByteArray());
            outputStream.flush();
            outputStream.close();
            bos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
