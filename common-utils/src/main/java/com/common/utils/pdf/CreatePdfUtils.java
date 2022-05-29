package com.common.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 创建pdf
 *
 * @author djq
 * @date 2022/5/26 14:40
 */
public class CreatePdfUtils {

    public static void main(String[] args) throws Exception {
        downloadCreateListPdf(null);
    }
    public static void downloadCreateListPdf(BaselineBO baselineData) throws Exception {

        //模板内所需要的数据（测试数据）

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("0", "0");
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        //map.put("4", “4”);
        //map.put("5", “5”);
        //
        //map.put("6", “6”);
        //
        //map.put("7", “7”);
        //
        //map.put("8", “8”);
        //map.put("9", “9”);
        //
        //map.put("10", “10”);
        //
        //map.put("11", “11”);

        String ret = "E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\信用报告测试版.pdf";//pdf模板
        String newPath = "F:\\filePath\\pdf\\new信用报告测试版.pdf";
        // 1.读取pdf模板并写入数据
        writePdfModel(ret, newPath, map);
        String finalPath = "F:\\filePath\\pdf\\result信用报告测试版.pdf";//最终模板生成路径
        String type = "create";
        generateFinalPdf(newPath, finalPath, type, baselineData);
    }

    private static void writePdfModel(String inPath, String outPath, HashMap<String, String> templateMap) throws IOException, DocumentException {
        FileOutputStream outputStream = new FileOutputStream(outPath);
        PdfReader reader1 = new PdfReader(inPath);// 读取pdf模板
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader1, bos);
        AcroFields form = stamper.getAcroFields();//获取form域
        BaseFont bfChinese = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        form.addSubstitutionFont(bfChinese);
        Iterator<String> iterator = form.getFields().keySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String name = iterator.next();
            String key = String.valueOf(i);//form域我设置名称为0-11,方便循环set值
            form.setField(name, templateMap.get(key));
            i++;
        }
        stamper.setFormFlattening(true);
        stamper.close();
        outputStream.write(bos.toByteArray());
        outputStream.flush();
        outputStream.close();
        bos.close();
    }

    private static void generateFinalPdf(String newPath, String finalPath, String baselineType, BaselineBO baselineData) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(finalPath);
        PdfReader reader = new PdfReader(newPath);// 读取pdf模板
        Rectangle pageSize = reader.getPageSize(1);
        Document document = new Document(pageSize);
        document.addAuthor("djq");
        document.addCreationDate();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        PdfBuilder1 pdfBuilder = new PdfBuilder1();
        //HeaderFooter headerFooter = new HeaderFooter();
        //ImageHeaderFooter imageHeaderFooter = new ImageHeaderFooter();
        //PdfImgBuilder pdfImgBuilder = new PdfImgBuilder();

        document.open();
        //writer.setPageEvent(pdfBuilder);
        PdfContentByte cbUnder = writer.getDirectContentUnder();
        PdfImportedPage pageTemplate = writer.getImportedPage(reader, 1);
        cbUnder.addTemplate(pageTemplate, 0, 0);
        document.newPage();//新创建一页来存放后面生成的表格
        if ("create".equals(baselineType)) {
            Paragraph paragraph = generatePdfATATable(baselineData);//此处为生成的表格及内容方法，只已ＡＴＡ表为例，其余两个就不写了
            //Paragraph paragraphFile = generatePdfFileTable(baselineData);
            //Paragraph paragraphDM = generatePdfDMTable(baselineData);
            document.add(paragraph);
            //document.add(paragraphFile);
            //document.add(paragraphDM);
        }
        document.close();
        reader.close();

    }

    /**
     * 生成pdf表格
     *
     * @return
     * @see
     */
    private static Paragraph generatePdfATATable(BaselineBO baselineData) throws Exception {
        BaseFont bfChinese = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font fontChinese = new Font(bfChinese, 10.5F, Font.NORMAL);// 五号
        Paragraph ret = new Paragraph("电户一", fontChinese);
        PdfPTable tableBox = new PdfPTable(6);
        tableBox.setWidths(new float[]{0.16f, 0.16f, 0.16f, 0.16f, 0.16f,0.2f});// 每个单元格占多宽
        tableBox.setWidthPercentage(80f);
        // 获取ATA分类的结果集
        // 创建表格格式及内容




        // 遍历查询出的结果
        for (int i = 0; i < 9; i++) {
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
        }
        ret.add(tableBox);
        return ret;

    }

    //每个ｃｅｌｌ的格式，合并单元格情况

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
    private static PdfPCell getCell1(Phrase phrase, boolean yellowFlag, int colSpan, int rowSpan) {

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
        cells.setNoWrap(false);
        return cells;
    }
}
