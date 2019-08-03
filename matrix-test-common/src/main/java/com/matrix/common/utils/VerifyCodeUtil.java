package com.matrix.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCodeUtil 
{
	//使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符  
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";      
    public static final String NUMBER_CODES = "0123456789";
    private static Random random = new Random();    
  
    public static String generateNumVerifyCode( int size )
    {
    	 return generateVerifyCode(size, NUMBER_CODES);  
    }
    
    /** 
     * 使用系统默认字符源生成验证码 
     * @param verifySize    验证码长度 
     * @return 
     */  
    public static String generateVerifyCode(int verifySize)
    {  
        return generateVerifyCode(verifySize, VERIFY_CODES);  
    }  
    /** 
     * 使用指定源生成验证码 
     * @param verifySize    验证码长度 
     * @param sources   验证码字符源 
     * @return 
     */  
    public static String generateVerifyCode(int verifySize, String sources)
    {  
        if( sources == null || sources.length() == 0 )
        {  
            sources = VERIFY_CODES;  
        }  
        
        int codesLen = sources.length();  
        Random rand = new Random(System.currentTimeMillis());  
        StringBuilder verifyCode = new StringBuilder(verifySize);  
        for(int i = 0; i < verifySize; i++)
        {  
            verifyCode.append( sources.charAt( rand.nextInt(codesLen-1) ) );  
        }  
        
        return verifyCode.toString();  
    }  
      
    /** 
     * 输出指定验证码图片流 
     * @param w 
     * @param h 
     * @param os 
     * @param code
     * 
     * @return byte[]
     * @throws IOException 
     */  
    public static byte[] outputImage(int width, int height, String code) throws IOException
    {  
    	width = width <= 0 ? 300 : width;
    	height = height <= 0 ? 100 : height;    	
        int verifySize = code.length(); 
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        Random rand = new Random();  
        Graphics2D g2 = image.createGraphics();  
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);  
        Color[] colors = new Color[5];  
        Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN,  
                Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,  
                Color.PINK, Color.YELLOW };  
        float[] fractions = new float[colors.length];  
        for(int i = 0; i < colors.length; i++)
        {  
            colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];  
            fractions[i] = rand.nextFloat();  
        }  
        Arrays.sort(fractions);  
        
        g2.setColor(Color.GRAY);              // 设置边框色  
        g2.fillRect(0, 0, width, height);  
        
        Color c = getRandColor(200, 250);  
        g2.setColor(c);// 设置背景色  
        g2.fillRect(0, 2, width, height-4);  
        
        //绘制干扰线  
        Random random = new Random();  
        g2.setColor(getRandColor(160, 200));   // 设置线条的颜色  
        for (int i = 0; i < 20; i++) 
        {  
            int x = random.nextInt(width - 1);  
            int y = random.nextInt(height - 1);  
            int xl = random.nextInt(6) + 1;  
            int yl = random.nextInt(12) + 1;  
            g2.drawLine(x, y, x + xl + 40, y + yl + 20);  
        }  
        
        // 添加噪点  
        float yawpRate = 0.05f;              // 噪声率  
        int area = (int) ( yawpRate * width * height );  
        for (int i = 0; i < area; i++) 
        {  
            int x = random.nextInt( width );  
            int y = random.nextInt( height );  
            int rgb = getRandomIntColor();  
            image.setRGB(x, y, rgb);  
        }  
          
        shear(g2, width, height, c);               // 使图片扭曲  
  
        g2.setColor(getRandColor(100, 160));  
        int fontSize = height-4;  
        Font font = new Font("Algerian", Font.ITALIC, fontSize);  
        g2.setFont(font);  
        char[] chars = code.toCharArray();  
        for(int i = 0; i < verifySize; i++)
        {  
            AffineTransform affine = new AffineTransform();  
            affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (width / verifySize) * i + fontSize/2, height/2);  
            g2.setTransform(affine);  
            g2.drawChars( chars, i, 1, ( ( width-10 ) / verifySize ) * i + 5, height/2 + fontSize/2 - 10) ;  
        }  
          
        g2.dispose();  
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try
        {
        	ImageIO.write(image, "gif", out);  
        	return out.toByteArray();        	
        }
        finally{
        	if ( null != out )
        	{
        		out.close();
        	}
        }
        
    }  
      
    private static Color getRandColor(int fc, int bc)
    {  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    }  
      
    private static int getRandomIntColor() 
    {  
        int[] rgb = getRandomRgb();  
        int color = 0;  
        for (int c : rgb) 
        {  
            color = color << 8;  
            color = color | c;  
        }  
        return color;  
    }  
      
    private static int[] getRandomRgb() 
    {  
        int[] rgb = new int[3];  
        for (int i = 0; i < 3; i++) 
        {  
            rgb[i] = random.nextInt(255);  
        }  
        return rgb;  
    }  
  
    private static void shear(Graphics g, int w1, int h1, Color color) 
    {  
        shearX(g, w1, h1, color);  
        shearY(g, w1, h1, color);  
    }  
      
    private static void shearX(Graphics g, int w1, int h1, Color color) 
    {    
        int period = random.nextInt(2);  
  
        boolean borderGap = true;  
        int frames = 1;  
        int phase = random.nextInt(2);  
  
        for (int i = 0; i < h1; i++) 
        {  
            double d = (double) (period >> 1)  
                    * Math.sin((double) i / (double) period  
                            + (6.2831853071795862D * (double) phase)  
                            / (double) frames);  
            g.copyArea(0, i, w1, 1, (int) d, 0);  
            if (borderGap) 
            {  
                g.setColor(color);  
                g.drawLine((int) d, i, 0, i);  
                g.drawLine((int) d + w1, i, w1, i);  
            }  
        }  
  
    }  
  
    private static void shearY(Graphics g, int w1, int h1, Color color) 
    {  
  
        int period = random.nextInt(40) + 10; // 50;  
  
        boolean borderGap = true;  
        int frames = 20;  
        int phase = 7;  
        for (int i = 0; i < w1; i++) 
        {  
            double d = (double) (period >> 1)  
                    * Math.sin((double) i / (double) period  
                            + (6.2831853071795862D * (double) phase)  
                            / (double) frames);  
            g.copyArea(i, 0, 1, h1, 0, (int) d);  
            if (borderGap) 
            {  
                g.setColor(color);  
                g.drawLine(i, (int) d, i, 0);  
                g.drawLine(i, (int) d + h1, i, h1);  
            }  
  
        }   
    }  
   
    /*
    public static void main(String[] args) throws Exception
    {  
    	System.out.println( generateNumVerifyCode(6) );    	
    	System.out.println( StringHelper.randomString(4, 7) );
    	
        File dir = new File("E:/verifies");  
        int width = 200, height = 100;  
        for(int i = 0; i < 5; i++)
        {      
        	String verifyCode = generateNumVerifyCode( 6 );
            byte[] image = outputImage( width, height, verifyCode);  
            
            System.out.println( verifyCode );
            File file = new File(dir, verifyCode + ".jpg");  
             
            file.createNewFile();  
            FileOutputStream fos = new FileOutputStream(file);  
            fos.write( image );
            fos.close();
        }  
    }
    */
}
