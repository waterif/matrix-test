package com.matrix.common.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author DuFeng
 * @date 2018/08/09 15:56:56
 */
public class XmlUtil {

    @SuppressWarnings("unchecked")
    public static <T> T xml2Bean(String xmlContent, Class<T> returnClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(returnClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlContent);
        return (T)unmarshaller.unmarshal(reader);
    }

    public static <T> String bean2Xml(T t, String encoding) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
        StringWriter writer = new StringWriter();
        marshaller.marshal(t, writer);
        return writer.toString();
    }
}
