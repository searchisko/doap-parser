package org.searchisko.doap.model;

import java.net.URI;

/**
 * Created by lukas on 26/09/14.
 */
public abstract class AbstractRepository implements Repository {

	private URI location;
	private URI browse;
	private String anon_root;

	public URI getLocation() {
		return this.location;
	}

	public void setLocation(URI location) {
		this.location = location;
	}

	public URI getBrowse() {
		return this.browse;
	}

	public void setBrowse(URI browse) {
		this.browse = browse;
	}

	public String getAnon_root() { return this.anon_root; }

	public void setAnon_root(String anon_root) {
		this.anon_root = anon_root;
	}
}
