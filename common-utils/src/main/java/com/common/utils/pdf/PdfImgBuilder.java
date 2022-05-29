package com.common.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.*;

import java.io.IOException;

/**
 * TODO:
 *
 * @author djq
 * @date 2022/5/27 16:26
 */
public class PdfImgBuilder extends PdfPageEventHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfImgBuilder.class);

    public Phrase header;

    public Phrase footer;

    // 模板
    public PdfTemplate total;

    // 基础字体对象
    public BaseFont bf = null;

    // 利用基础字体生成的字体对象，一般用于生成中文文字
    public Font fontDetail = null;

    public PdfImgBuilder() {

    }

    public PdfImgBuilder(Phrase header, Phrase footer) {
        this.header = header;
        this.footer = footer;
    }

    public void setHeader(Phrase header) {
        this.header = header;
    }

    public void setFooter(Phrase footer) {
        this.footer = footer;
    }

    /**
     * 文档打开时创建模板
     *
     * @param writer
     * @param document
     */
    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(50, 50);// 共 页 的矩形的长宽高
    }

    /**
     * 关闭每页的时候，写入页眉，页脚。
     *
     * @param writer
     * @param document
     */
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        this.addPage(writer, document);
        //this.addWatermark(writer);
    }

    public static void setImg(PdfWriter writer, String path, float fitWidth, float fitHeight, float absoluteX, float absoluteY) {
        try {
            Image image = Image.getInstance(path);
            image.setAlignment(Image.MIDDLE);
            image.scaleToFit(fitWidth, fitHeight);
            image.setAbsolutePosition(absoluteX, absoluteY);
            writer.getDirectContent().addImage(image);
        } catch (Exception e) {
            LOGGER.error("[ERROR] Set Img : img file does not exist.");
        }
    }

    //加分页
    public void addPage(PdfWriter writer, Document document) {
        if (document.getPageNumber() > 1) {
            try {
                bf = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BaseFont blodf = null;
            try {
                blodf = BaseFont.createFont("D:\\插件\\字体\\苹方字体\\PingFang Bold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            fontDetail = new Font(bf, 9, Font.NORMAL);// 数据体字体
            Font titleFnot = new Font(blodf, 10.5f, Font.NORMAL);
            Font blodFnot = new Font(blodf, 9f, Font.NORMAL);
            Font font = new Font(bf, 10.5f, Font.NORMAL);

            setImg(writer, "E:\\transwrap资料\\pierce需求文档\\南网PDF需求\\模板\\页眉.png", 85, 45, 65, 785);
            Phrase pageNumberPh = new Phrase(String.valueOf(document.getPageNumber() - 1), fontDetail);
            float center = document.getPageSize().getRight() / 2;//页面的水平中点
            float left = document.getPageSize().getLeft(90);//页面的z左边距
            float headerleft = document.getPageSize().getLeft(180);//页面的z左边距
            float right = document.getPageSize().getRight(90);//页面的z左边距
            float top = document.getPageSize().getTop() - 36;
            float bottom = document.getPageSize().getBottom() + 36;

            header = new Paragraph("Beijing XXXX Information Technology Hn.,JKI.", titleFnot);
            Paragraph header1 = new Paragraph("5f, building C2, hkhkjhd Industrial Park,", font);
            Paragraph header2 = new Paragraph("No. 44, Changhu Road, Jiangxi Industrial Park", font);
            Paragraph header3 = new Paragraph("www.xxx.com", font);
            Paragraph header4 = new Paragraph("China (sss) xxxxxxxxxxxx Dgyhi Code Zone", font);
            Paragraph header5 = new Paragraph("400-1234-567", font);
            Paragraph footer1 = new Paragraph("Acnnecfds Kdjklsnf", blodFnot);
            Paragraph footer2 = new Paragraph("The dfdfdf fddffdfdf by fdfdf in thfdfiscxcx fdfd is based on fdfd fdfd has been fdf fdfdf fdfdfdfd erwerwerwerwrww",
                    new Font(bf, 9, Font.NORMAL));
            Paragraph footer3 = new Paragraph("ffdfdf and fdfdf fffdfd. fdsf fdfd fdfdf xcxcfdfd fdf fdf fdfd to fdfd that the dfdfdssxx is fdfdfdf and werwerwe",
                    new Font(bf, 9, Font.NORMAL));
            Paragraph footer4 = new Paragraph("seewwdrr, we fdf fdfdf fdfd any fdfdfdxcx for fddfdfdfd, fdfdf, fdferere rererer or rer-rere rer and a werwerwer",
                    new Font(bf, 9, Font.NORMAL));
            Paragraph footer5 = new Paragraph("rerer or rer of the rererer in rere rerer cxcxcxcxrer rere the retg of tfhe fffgvfgddsss on its sdfsd. The dddddw ",
                    new Font(bf, 9, Font.NORMAL));
            Paragraph footer6 = new Paragraph("information dsfsdf is DSFSDS DFFDDD and Sdcxvxcvdfdfsd not be dfsdfsdf to any sdfsdf sdfsdfsd the dsfsdfsfdsdfee ",
                    new Font(bf, 9, Font.NORMAL));
            Paragraph footer7 = new Paragraph("erdsfsdfsd. sdfsdf dsfsdf & dsfsdf dsfsdf sdfsdf of any sfddsfsdf dfdfdfdf the dfdf of the inforfmationssdeeetyy ",
                    new Font(bf, 9, Font.NORMAL));
            //            Paragraph footer8 = new Paragraph("dfdfddff in this report.", new Font(bf, 9, Font.NORMAL));
            Paragraph footer9 = new Paragraph("[fdddfdfdfdf]", blodFnot);

            //                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, headerImg, center, top, 0); //页眉图标
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, header, headerleft, top + 13, 0); //页眉标题
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, header1, headerleft, top - 3, 0); //页眉内容
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, header2, headerleft, top - 15, 0); //页眉
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, header3, right, top - 15, 0); //页眉
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, header4, headerleft, top - 30, 0); //页眉
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, header5, right, top - 30, 0); //页眉
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, pageNumberPh, right, bottom + 65, 0); //页码
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, footer1, center, bottom + 60, 0); //页脚标题
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer2, left, bottom + 50, 0); //页脚
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer3, left, bottom + 40, 0); //页脚
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer4, left, bottom + 30, 0); //页脚
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer5, left, bottom + 20, 0); //页脚
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer6, left, bottom + 10, 0); //页脚
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer7, left, bottom, 0); //页脚
            //            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, footer8, left, bottom - 10, 0); //页脚
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, footer9, center, bottom - 20, 0); //页脚
        }
    }

    //加水印
    public void addWatermark(PdfWriter writer) {
        // 水印图片
        Image image;
        try {
            image = Image.getInstance("");
            PdfContentByte content = writer.getDirectContentUnder();
            content.beginText();
            // 开始写入水印
            for (int k = 0; k < 5; k++) {
                for (int j = 0; j < 4; j++) {
                    image.setAbsolutePosition(150 * j, 170 * k);
                    content.addImage(image);
                }
            }
            content.endText();
        } catch (IOException | DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     *
     *
     * TODO 关闭文档时，替换模板，完成整个页眉页脚组件
     * @see PdfPageEventHelper#onCloseDocument(PdfWriter,
     *      Document)
     */

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        // 7.最后一步了，就是关闭文档的时候，将模板替换成实际的 Y 值,至此，page x of y 制作完毕，完美兼容各种文档size。
        total.beginText();
        total.setFontAndSize(bf, 9);// 生成的模版的字体、颜色
        String foot2 = " " + (writer.getPageNumber()) + " 页"; //页脚内容拼接  如  第1页/共2页
        total.showText(foot2);// 模版显示的内容
        total.endText();
        total.closePath();
    }
}
