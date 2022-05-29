package com.common.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * TODO:
 *
 * @author djq
 * @date 2022/5/27 14:59
 */
public class ImageHeaderFooter extends PdfPageEventHelper {
    Font bfChinese;
    int fontSize = 10;
    Image image;

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        try {
            bfChinese = new Font(BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
            image = Image.getInstance("E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\页眉.png");
            //image = Image.getInstance(PDFUtil.class.getResource("/template/small.png"));
            image.scalePercent(50);
            image.setAbsolutePosition(8, document.top());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            Rectangle rect = new Rectangle(0, 38, 50, 50);

            String line1 = String.format("- %d -", writer.getPageNumber());
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase(line1, bfChinese),
                    (document.rightMargin() + document.right() + document.leftMargin() - document.left() - bfChinese.getBaseFont().getWidthPoint(line1, fontSize) / 2) / 2.0F,
                    document.bottom() - 22,
                    0);
            String line2 = "本文件保密，未经允许，不得向任何第三方提供。";

            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
                    new Phrase(line2, bfChinese),
                    (document.rightMargin() + document.right() + document.leftMargin() - document.left()) / 2.0F,
                    document.bottom() - 32,
                    0);
            writer.getDirectContent().addImage(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
