/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.doap.app;

import org.searchisko.doap.json.Converter;
import org.searchisko.doap.model.Project;
import org.searchisko.doap.parser.DOAPParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class CLRunner {

	private static Logger log = LoggerFactory.getLogger(CLRunner.class);

	public static void main(String[] args) {
		AppOptions options = new AppOptions();
		options.parseArgs(args);
		log.info("Command line options: valid[{}]",options.isValid());
		log.info(" - DOAP file URI: {}",options.getDoapURI());
		log.info(" - server location URI: {}",options.getServerURI());
		if (options.isValid()) {
			DOAPParser parser = new DOAPParser();
			try {
				// get and parse RDF/XML file
				parser.repositoryInit();
				parser.loadInputStream(options.getDoapURI().toURL().openStream());
				Project project = parser.getProject();
				parser.repositoryClose();
				// Push data to server
				String jsonString = Converter.objectToJSON(project);
				//options.getServerURI()
			} catch (Exception e) {
				log.error("Unexpected error",e);
			}
		}
	}
}
