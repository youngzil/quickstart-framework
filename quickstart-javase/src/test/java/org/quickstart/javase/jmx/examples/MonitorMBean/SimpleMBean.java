package org.quickstart.javase.jmx.examples.MonitorMBean;
/*
 * @(#)file      SimpleMBean.java
 * @(#)author    Sun Microsystems, Inc.
 * @(#)version   4.8
 * @(#)lastedit      03/07/15
 *
 * Copyright 2000-2003 Sun Microsystems, Inc.  All rights reserved.
 * This software is the proprietary information of Sun Microsystems, Inc.
 * Use is subject to license terms.
 * 
 * Copyright 2000-2003 Sun Microsystems, Inc.  Tous droits r�serv�s.
 * Ce logiciel est propriet� de Sun Microsystems, Inc.
 * Distribu� par des licences qui en restreignent l'utilisation. 
 */


public interface SimpleMBean {

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */
    
    public String getState();

    public void setState(String s);

    public int getNbChanges();
        
    public void performReset();
}
