package com.common.utils.datamall;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

/**
 * 设置页眉页脚
 *
 * 通过重写相关事件来进行
 * onOpenDocument，文档打开时触发
 * onCloseDocument，文档关闭时触发
 * onStartPage，页面开始写入前触发
 * onEndPage，页面写入完成后触发
 * @author djq
 * @date 2022/5/30 10:31
 */
public class PdfHeaderFooter extends PdfPageEventHelper {
    Font font;
    int fontSize = 10;
    Image headerImage;
    Image footerImage;

    /**
     * 文档打开时读取页眉,页脚的图片
     */
    //@Override
    //public void onOpenDocument(PdfWriter writer, Document document){
    //    try {
    //        font = new Font(BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
    //        headerImage = Image.getInstance("E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\页眉.png");
    //        footerImage = Image.getInstance("E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\页脚.png");
    //        headerImage.scalePercent(50);
    //        headerImage.setAbsolutePosition(8,document.top());
    //        footerImage.scalePercent(50);
    //        footerImage.setAbsolutePosition(8,document.bottom());
    //    }catch (Exception e){
    //        e.printStackTrace();
    //    }
    //
    //}

    @Override
    public void onEndPage(PdfWriter writer, Document document){
        try {
            int pageNumber = writer.getPageNumber();
            if (pageNumber>2){
                //画直线
                Paragraph paragraph = new Paragraph();
                //
                LineSeparator lineSeparator = new LineSeparator();
                lineSeparator.setLineColor(new BaseColor(180,180,180));
                lineSeparator.setLineWidth(0.3f);
                //lineSeparator.setPercentage(0.9f);
                paragraph.add(new Chunk(lineSeparator));
                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_CENTER, paragraph,
                        document.left(), document.bottom() - 3, 0);
                ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_CENTER, paragraph,
                        document.left(), document.top(-16), 0);
                pageNumber = pageNumber - 1;
                //得到层
                PdfContentByte cb = writer.getDirectContent();
                //cb.setLineWidth(0.8);
                cb.saveState();
                //开始
                cb.beginText();
                cb.setFontAndSize(BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED),8);
                //设置颜色
                cb.setColorFill(new BaseColor(180,180,180));
                // Header
                float x = document.top(-20);
                float xx = document.top(-8);
                // 左
                cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "目标公司", document.left(), x, 0);
                cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "递交时间", document.left(), xx, 0);
                Image image = Image.getInstance("E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\logo.png");
                //设置图片位置
                image.setAbsolutePosition(document.right()-200, document.top(-8));
                //设置图片大小
                image.scaleAbsolute(200,20);
                //cb.addImage(image);
                writer.getDirectContent().addImage(image);

                // Footer
                float y = document.bottom(-20);
                // 左
                cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "免责声明: 本资信报告仅供使用者参考之用，不能用作诉讼依据，在任何情况下，使用本报告所引起的所有一切后果，本公司不承担任何责任", document.left(), y, 0);
                // 右
                cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "-" + pageNumber + "-", document.right(),y,0);

                cb.endText();
                cb.restoreState();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
