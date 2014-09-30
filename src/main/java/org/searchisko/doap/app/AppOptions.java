/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */

package org.searchisko.doap.app;

import static org.kohsuke.args4j.ExampleMode.ALL;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Options of the application started from the command line.
 *
 * @author Lukas Vlcek (lvlcek@redhat.com)
 */
public class AppOptions {

	@Option(name="-doap",usage="specify DOAP file location URI")
	private URI doapURI;

	@Option(name="-server",usage="specify server location URI")
	private URI serverURI;

	@Argument
	private List<String> arguments = new ArrayList<String>();

	private boolean valid = false;

	public URI getDoapURI() {
		return this.doapURI;
	}

	public URI getServerURI() {
		return this.serverURI;
	}

	public boolean isValid() {
		return this.valid;
	}

	public static void main(String[] args) throws IOException {
		new AppOptions().doMain(args);
	}

	protected void doMain(String[] args) {
		CmdLineParser parser = new CmdLineParser(this);

		try {
			parser.parseArgument(args);
			if (doapURI == null) {
				throw new CmdLineException(parser,"No DOAP file location given");
			}
			if (serverURI == null) {
				throw new CmdLineException(parser,"No server location given");
			}
//			if( arguments.isEmpty() )
//				throw new CmdLineException(parser,"No argument is given");
			valid = true;

		} catch( CmdLineException e ) {

			System.err.println(e.getMessage());
			System.err.println("java AppOptions [options...] arguments...");
			parser.printUsage(System.err);
			System.err.println();
			System.err.println("  Example: java AppOptions "+parser.printExample(ALL));

//			return;
		}
	}
}
