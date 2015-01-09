/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.model;

import com.viceversatech.rdfbeans.annotations.RDFBean;
import com.viceversatech.rdfbeans.annotations.RDFNamespaces;

/**
 * Red Hat Project model.
 *
 * This is Red Hat specific DOAP extension.
 *
 * @author lvlcek@redhat.com (Lukas Vlcek)
 */
@RDFNamespaces({
    "doap = http://usefulinc.com/ns/doap#",
    "foaf = http://xmlns.com/foaf/0.1/",
    "rhtdoap = https://www.jboss.org/schema/rdf/doap-extension"
})
@RDFBean("rhtdoap:Project")
public class RhtProject extends Project {

}
