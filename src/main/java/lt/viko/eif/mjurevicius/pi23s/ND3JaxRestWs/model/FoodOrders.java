    package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

    import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.server.Link;

    import java.util.ArrayList;
    import java.util.List;

    /**
     * Represents a collection of food orders.
     * This class is used to encapsulate a list of {@link FoodOrder} objects and includes
     * hypermedia links for accessing the collection itself or related resources.
     */
    public class FoodOrders {

        /**
         * A list containing multiple {@link FoodOrder} objects. This represents the collection
         * of placed food orders.
         */
        public List<FoodOrder> foodOrderList;

        /**
         * A list of hypermedia links associated with this collection of food orders, enabling
         * clients to navigate and interact with the resource.
         */
        public List<Link> links = new ArrayList<>();

        /**
         * Default constructor for creating a new FoodOrders instance.
         */
        public FoodOrders() {
        }

        /**
         * Gets the list of food orders.
         *
         * @return The list of {@link FoodOrder} objects.
         */
        public List<FoodOrder> getFoodOrderList() {
            return foodOrderList;
        }

        /**
         * Sets the list of food orders.
         *
         * @param foodOrderList The list of {@link FoodOrder} objects to set.
         */
        public void setFoodOrderList(List<FoodOrder> foodOrderList) {
            this.foodOrderList = foodOrderList;
        }

        /**
         * Gets the list of hypermedia links associated with this collection of food orders.
         *
         * @return The list of links.
         */
        public List<Link> getLinks() {
            return links;
        }

        /**
         * Sets the list of hypermedia links for this collection of food orders.
         *
         * @param links The list of links to set.
         */
        public void setLinks(List<Link> links) {
            this.links = links;
        }

        /**
         * Adds a hypermedia link to the list of links for this collection of food orders.
         *
         * @param link The link to add.
         */
        public void addLink(Link link) {
            this.links.add(link);
        }

    }
