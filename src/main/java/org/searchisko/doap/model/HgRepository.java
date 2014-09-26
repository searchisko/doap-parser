/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.model;

import com.viceversatech.rdfbeans.annotations.RDFBean;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
@RDFBean("doap:HgRepository")
public class HgRepository extends AbstractRepository implements Repository {
}
