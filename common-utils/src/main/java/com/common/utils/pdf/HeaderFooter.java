package com.common.utils.pdf;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.xml.ws.Action;

/**
 * TODO:
 *
 * @author djq
 * @date 2022/5/26 18:08
 */
public class HeaderFooter extends PdfPageEventHelper {
    // 总页数
    PdfTemplate totalPage;
    Font hfFont;


    {
        try {
            hfFont = new Font(BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 打开文档时，创建一个总页数的模板
    @Override
    public void onOpenDocument(PdfWriter writer, Document document){
        // 设置页数矩阵的长宽
        PdfContentByte cb = writer.getDirectContent();
        totalPage = cb.createTemplate(30,16);
    }

    // 一页加载完成触发，写入页眉页脚
    @Override
    public void onEndPage(PdfWriter writer, Document document){
        if (writer.getPageNumber() > 1){
            PdfContentByte cb = writer.getDirectContent();
            totalPage = cb.createTemplate(30,16);
            PdfPTable table = new PdfPTable(3);
            try {
                table.setTotalWidth(PageSize.A4.getWidth() - 100);
                table.setWidths(new int[]{24, 24 ,3});
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(-10);
                table.getDefaultCell().setBorder(Rectangle.BOTTOM);

                table.addCell(new Paragraph("我是页眉/页脚",hfFont));
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(new Paragraph("-"+ writer.getPageNumber() +"-"));

                //总页数
                PdfPCell cell = new PdfPCell(Image.getInstance(totalPage));
                cell.setBorder(Rectangle.BOTTOM);
                table.addCell(cell);

                //将页眉写道docment中，位置可以指定，指定到下面就是页脚
                table.writeSelectedRows(0,-1,50,PageSize.A4.getHeight() - 20, writer.getDirectContent());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //全部完成后，将总页数的pdf写到指定位置
    @Override
    public void onCloseDocument(PdfWriter writer, Document document){
        String text = "总" + (writer.getPageNumber()) + "页";
        ColumnText.showTextAligned(totalPage,Element.ALIGN_LEFT,new Paragraph(text,hfFont),2,2,0);
    }

}
