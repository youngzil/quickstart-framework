/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.quickstart.joox;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.nCopies;
import static org.joox.JOOX.$;
import static org.joox.JOOX.attr;
import static org.joox.JOOX.chain;
import static org.joox.JOOX.paths;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.joox.Content;
import org.joox.Context;
import org.joox.Each;
import org.joox.Filter;
import org.joox.JOOX;
import org.joox.Mapper;
import org.joox.Match;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Lukas Eder
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JOOXTest2 {

    public static void main(String[] args) throws XPathExpressionException, IOException, SAXException {

        String xmlExampleString;
        Document xmlExampleDocument;
        Element xmlExampleElement;

        String xmlDatesString;
        Document xmlDatesDocument;
        @SuppressWarnings("unused")
        Element xmlDatesElement;

        String xmlNamespacesString;
        Document xmlNamespacesDocument;
        @SuppressWarnings("unused")
        Element xmlNamespacesElement;

        int totalElements;
        Match $;
        XPath xPath;

        DocumentBuilder builder = JOOX.builder();

        xmlExampleString = IOUtils.toString(JOOXTest2.class.getResourceAsStream("/example.xml"));
        xmlExampleDocument = builder.parse(new ByteArrayInputStream(xmlExampleString.getBytes()));
        xmlExampleElement = xmlExampleDocument.getDocumentElement();

        $ = $(xmlExampleDocument);
        xPath = XPathFactory.newInstance().newXPath();
        totalElements = ((Number) xPath.evaluate("count(//*)", xmlExampleDocument, XPathConstants.NUMBER)).intValue() - 1;

        System.out.println($.size());
        
        
        System.out.println($.find("book"));
        System.out.println("---------------------------------");
        System.out.println($.find("book").reverse());
        
        System.out.println("---------------------------------");
        
        System.out.println($.find("book").reverse().ids());
        
        

    }

}
