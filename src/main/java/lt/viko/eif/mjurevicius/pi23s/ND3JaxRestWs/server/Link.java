package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server;

/**
 * Represents a hypermedia link, containing a relation type and a URI.
 * This class is used to add HATEOAS (Hypermedia as the Engine of Application State)
 * functionality to the API responses, allowing clients to discover related resources.
 */
public class Link {

    /**
     * The relation type of the link, describing its purpose (e.g., "self", "item", "menu", "order", "orders").
     */
    private String rel;

    /**
     * The URI (Uniform Resource Identifier) of the linked resource.
     */
    private String href;

    /**
     * Default constructor for creating a new Link instance.
     */
    public Link() {
    }

    /**
     * Constructor to create a new Link with a specified relation type and URI.
     *
     * @param rel  The relation type of the link.
     * @param href The URI of the linked resource.
     */
    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    /**
     * Gets the relation type of the link.
     *
     * @return The relation type.
     */
    public String getRel() {
        return rel;
    }

    /**
     * Sets the relation type of the link.
     *
     * @param rel The relation type to set.
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * Gets the URI of the linked resource.
     *
     * @return The URI.
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the URI of the linked resource.
     *
     * @param href The URI to set.
     */
    public void setHref(String href) {
        this.href = href;
    }
}
