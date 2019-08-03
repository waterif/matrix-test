package com.matrix.common.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class Test
{

    public static void main( String[] args )
    {
        while ( true )
        {

            try
            {
                Thread t = new Thread( new Runnable()
                {

                    @Override
                    public void run()
                    {
                        sendGet( "http://118.31.17.57/test/ip", null, "UTF-8" );

                        sendGet( "http://118.31.17.57/test/ip2", null, "UTF-8" );

                    }

                } );

                t.start();

                t.join();

//                System.out.println( "1---：" + new Date() );
                Thread.sleep( 10 * 60 * 1000 );
//                System.out.println( "2---：" + new Date() );

            }
            catch ( InterruptedException e )
            {
            }

        }

    }

    public static String sendGet( String urlParam, Map<String, Object> params, String charset )
    {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if ( params != null && params.size() > 0 )
        {
            for ( Entry<String, Object> entry : params.entrySet() )
            {
                sbParams.append( entry.getKey() );
                sbParams.append( "=" );
                sbParams.append( entry.getValue() );
                sbParams.append( "&" );
            }
        }
        HttpURLConnection con = null;
        BufferedReader br = null;
        try
        {
            URL url = null;
            if ( sbParams != null && sbParams.length() > 0 )
            {
                url = new URL( urlParam + "?" + sbParams.substring( 0, sbParams.length() - 1 ) );
            }
            else
            {
                url = new URL( urlParam );
            }
            con = ( HttpURLConnection ) url.openConnection();
            con.setRequestProperty( "Content-Type", "application/json; charset=UTF-8" );
            con.connect();
            resultBuffer = new StringBuffer();
            br = new BufferedReader( new InputStreamReader( con.getInputStream(), charset ) );
            String temp;
            while ( ( temp = br.readLine() ) != null )
            {
                resultBuffer.append( temp );
            }
        }
        catch ( Exception e )
        {
            throw new RuntimeException( e );
        }
        finally
        {
            if ( br != null )
            {
                try
                {
                    br.close();
                }
                catch ( IOException e )
                {
                    br = null;
                    throw new RuntimeException( e );
                }
                finally
                {
                    if ( con != null )
                    {
                        con.disconnect();
                        con = null;
                    }
                }
            }
        }
        return resultBuffer.toString();
    }

}
