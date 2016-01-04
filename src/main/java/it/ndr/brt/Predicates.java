package it.ndr.brt;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class Predicates {

    public static Predicate isAValidXML() {
        return new Predicate() {
            public boolean matches(Exchange exchange) {
                try {
                    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    Object body = exchange.getIn().getBody();
                    if (body instanceof InputStream) {
                        builder.parse((InputStream)body);
                    }
                    else {
                        builder.parse(IOUtils.toInputStream(String.valueOf(body)));
                    }
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }

}
