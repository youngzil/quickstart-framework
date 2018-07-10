/**
 * 项目名称：quickstart-xmlbeans 
 * 文件名：SampleTemplate.java
 * 版本信息：
 * 日期：2017年3月8日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.xmlbeans.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * SampleTemplate 
 *  
 * @author：yangzl@asiainfo.com
 * @2017年3月8日 下午9:53:35 
 * @version 1.0
 */
/*   Copyright 2004 The Apache Software Foundation
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*  limitations under the License.
*/

import org.apache.xmlbeans.*;
import org.apache.xmlbeans.samples.template.sampletemplate.HelloDocument;

/**
 * This is a template sample. A nice description would go here.
 */
public class SampleTemplate {
    /**
     * Prints out the names in the xml instance conforming to hello.xsd.
     */
    public static void main(String[] args) throws org.apache.xmlbeans.XmlException, java.io.IOException {
        SampleTemplate sample = new SampleTemplate();
        sample.start(args);
    }

    public void start(String[] args) throws org.apache.xmlbeans.XmlException, java.io.IOException {
        for (int i = 0; i < args.length; i++) {
            validate(args[i]);
        }
    }

    public void validate(String filename) throws org.apache.xmlbeans.XmlException, java.io.IOException {
        System.out.println("parsing document: " + filename);
        filename = "customers.xml";
        HelloDocument doc = HelloDocument.Factory.parse(new File(filename));

        ArrayList errors = new ArrayList();
        XmlOptions opts = new XmlOptions();
        opts.setErrorListener(errors);

        if (doc.validate(opts)) {
            System.out.println("document is valid.");

            String[] names = doc.getHello().getNameArray();
            for (int i = 0; i < names.length; i++) {
                System.out.println(" hi there, " + names[i] + "!");
            }
        } else {
            System.out.println("document is invalid!");

            Iterator iter = errors.iterator();
            while (iter.hasNext()) {
                System.out.println(">> " + iter.next());
            }
        }
        System.out.println();
    }
}
