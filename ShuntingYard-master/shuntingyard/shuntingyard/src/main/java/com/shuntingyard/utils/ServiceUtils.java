package com.shuntingyard.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceUtils {

    public static final String LANGUAGETAG = "[language]";
    public static final String COUNTRYTAG = "[country]";
    public static final String CURRENCYTAG = "[currency]";
    public static final String ERRORCODETAG = "[code]";

    public static String objToString(Object o)
    {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        }catch (Exception e){
            return null;
        }
    }

    public static <T> String objectToXml(T input, String contextPath) throws JAXBException
    {
        //Marshaller marshaller = JAXBContext.newInstance(input.getClass()).createMarshaller();
        Marshaller marshaller = JAXBContext.newInstance(contextPath).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter sw = new StringWriter();

        marshaller.marshal(input, sw);

        return sw.toString();
    }

    public static String toString(InputStream inputStream) throws IOException
    {
        int nRead;
        byte[] data = new byte[1024];
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    }


}