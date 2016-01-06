package it.ndr.brt;

import static org.apache.commons.io.IOUtils.toInputStream;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;

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
                        builder.parse(toInputStream(String.valueOf(body)));
                    }
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }

}
