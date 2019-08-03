package com.matrix.common.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class IOUtil
{
    public static Logger log = LoggerFactory.getLogger( IOUtil.class );

    public static byte[] readByte( InputStream is )
    {
        try
        {
            int numRead = 0;
            byte buf[] = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            while ( (numRead = is.read( buf )) > 0 )
            {
                bos.write( buf, 0, numRead );
            }

            return bos.toByteArray();
        }
        catch ( Exception e )
        {
            log.error( String.format( "read byte from input stream error: %s.", e.getMessage() ) );
            return null;
        }
    }

    public static String readText( InputStream is, String charset )
    {
        byte[] data = readByte( is );

        if ( null == data )
        {
            return null;
        }

        try
        {
            return new String( data, charset );
        }
        catch ( Exception e )
        {
            log.error( String.format( "new String for byte charset %s error: %s.", charset, e.getMessage() ) );
            return null;
        }
    }

    public static String readText( InputStream is )
    {
        return readText( is, "utf-8" );
    }

    public static String readText( Reader r )
    {
        try
        {
            int numRead = 0;
            char buf[] = new char[1024];
            StringWriter dataWriter = new StringWriter();

            while ( (numRead = r.read( buf )) > 0 )
            {
                dataWriter.write( buf, 0, numRead );
            }

            return dataWriter.toString();
        }
        catch ( Exception e )
        {
            log.error( String.format( "read text from input stream error: %s.", e.getMessage() ) );
            return null;
        }
    }

    public static int writeTo( InputStream in, OutputStream out ) throws IOException
    {
        int total = 0;
        int n = -1;
        byte buf[] = new byte[2048];

        while ( 0 < (n = in.read( buf )) )
        {
            out.write( buf, 0, n );
            total += n;
        }

        return total;
    }
}
