package com.matrix.common.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtil
{

    /**
     * 图片裁剪
     * 
     * @param srcImg 原始图片文件
     * @param dstImg 目标图片文件
     * @param x 坐标( left )
     * @param y 坐标( top )
     * @param width 宽度
     * @param height 高度
     * 
     * @throws IOException
     */
    public static void cutImage( File srcImg, File dstImg, int x, int y, int width, int height ) throws IOException
    {
        FileInputStream fis = null;
        ImageInputStream iis = null;
        try
        {
            String extName = "jpg";
            int index = srcImg.getName().lastIndexOf( "." );
            if ( index != -1 )
            {
                extName = srcImg.getName().substring( index + 1 );
            }

            fis = new FileInputStream( srcImg );

            /**
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader能够解码指定格式。
             */
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName( extName );
            ImageReader reader = it.next();

            iis = ImageIO.createImageInputStream( fis ); // 获取图片流
            reader.setInput( iis, true );

            /**
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。
             */
            Rectangle rect = new Rectangle( x, y, width, height );

            ImageReadParam param = reader.getDefaultReadParam();
            param.setSourceRegion( rect );

            BufferedImage bi = reader.read( 0, param );

            // 保存新图片
            ImageIO.write( bi, extName, dstImg );
        }
        finally
        {
            if ( null != fis )
            {
                fis.close();
            }
            if ( null != iis )
            {
                iis.close();
            }
        }
    }

    public static void main( String[] args ) throws Exception
    {
        String srcName = "D:/image/pengyouquan.png";
        String dstName = "D:/image/pengyouquan_dst.png";
        try
        {
            File srcFile = new File( srcName );
            File dstFile = new File( dstName );

            ImageUtil.cutImage( srcFile, dstFile, 50, 20, 10000, 20000 );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}