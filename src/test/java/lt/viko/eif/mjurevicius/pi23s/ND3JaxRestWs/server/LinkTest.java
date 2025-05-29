package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {

    @Test
    void getRel_shouldReturnCorrectRel() {
        Link link = new Link("self", "/foodmenus");
        assertEquals("self", link.getRel());
    }

    @Test
    void setRel_shouldSetCorrectRel() {
        Link link = new Link();
        link.setRel("item");
        assertEquals("item", link.getRel());
    }

    @Test
    void getHref_shouldReturnCorrectHref() {
        Link link = new Link("order", "/foodorders/1");
        assertEquals("/foodorders/1", link.getHref());
    }

    @Test
    void setHref_shouldSetCorrectHref() {
        Link link = new Link();
        link.setHref("/fooditems/pizza");
        assertEquals("/fooditems/pizza", link.getHref());
    }

    @Test
    void constructor_shouldInitializeRelAndHref() {
        Link link = new Link("menu", "/foodmenus/breakfast");
        assertEquals("menu", link.getRel());
        assertEquals("/foodmenus/breakfast", link.getHref());
    }

    @Test
    void defaultConstructor_shouldInitializeNullValues() {
        Link link = new Link();
        assertNull(link.getRel());
        assertNull(link.getHref());
    }
}