package org.searchisko.doap.model;

import java.net.URI;

/**
 * Created by lukas on 26/09/14.
 */
public abstract class AbstractRepository implements Repository {

	private URI location;
	private URI browse;

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

}
