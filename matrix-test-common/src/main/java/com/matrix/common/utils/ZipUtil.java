package com.matrix.common.utils;

import java.util.zip.InflaterOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.zip.DeflaterOutputStream;
import java.io.ByteArrayOutputStream;


public class ZipUtil
{
    private static Logger log = LoggerFactory.getLogger( ZipUtil.class );

    public static byte[] compress( byte data[] )
    {
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();   
            DeflaterOutputStream zos = new DeflaterOutputStream( out );

            zos.write( data );
            zos.close();

            return out.toByteArray();
        }
        catch( Exception e )
        {
            log.error( "compress fail: " + e.getMessage() );
            return null;
        }
    }

    public static byte[] uncompress( byte data[] )
    {
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();   
            InflaterOutputStream zos = new InflaterOutputStream( out );

            zos.write( data );
            zos.close();

            return out.toByteArray();
        }
        catch( Exception e )
        {
            log.error( "compress fail: " + e.getMessage() );
            return null;
        }
    }
}
