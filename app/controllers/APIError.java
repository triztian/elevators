package controllers;

/**
 * The JSON error response
 */
public final class APIError {
	public final String title;
	public final String description;

	public APIError(final String title, final String description) {
		this.title = title;
		this.description = description;
	}
}