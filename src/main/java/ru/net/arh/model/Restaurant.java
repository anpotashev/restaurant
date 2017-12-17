package ru.net.arh.model;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import ru.net.arh.model.mapped.NamedBasedEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "restaurant")
//@NamedEntityGraphs({
//        @NamedEntityGraph(name = Restaurant.GRAPH_FIND_WITH_PRICES
//                , attributeNodes = {
//                @NamedAttributeNode(value = "prices", subgraph = "restaurant.price")
//        }
////                , subgraphs = {
////                @NamedSubgraph(name = "restaurant.price", attributeNodes = @NamedAttributeNode("key.dish"))
////        }
//        )
//        , @NamedEntityGraph(name = Restaurant.GRAPH_FIND_WITH_VOTES, attributeNodes = {
//                @NamedAttributeNode("votes")
//        })
//})
@SQLDelete(sql = "UPDATE Restaurant SET deleted=true WHERE id=? ")
@Loader(namedQuery = Restaurant.FIND_BY_ID)
@Where(clause = "deleted=false")
@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE_QUERY_NAME, query = "update Restaurant r set r.deleted = true where r.id = :id")
        , @NamedQuery(name = Restaurant.FIND_ALL_QUERY_NAME, query = "select r from Restaurant r where r.deleted=false order by r.id asc")
        , @NamedQuery(name = Restaurant.FIND_ALL_BY_FIRST_PART_OF_NAME_QUERY_NAME, query = "select r from Restaurant r " +
        "where r.deleted = false and lower(r.name) like lower(concat(:firstPartOfName, '%') ) order by r.id asc")
        , @NamedQuery(name = Restaurant.FIND_BY_ID, query = "SELECT r FROM Restaurant r WHERE r.id=?1 AND r.deleted=false")
})
public class Restaurant extends NamedBasedEntity {

    public static final String GRAPH_FIND_WITH_PRICES = "Restaurant.graph_with_prices";
    public static final String GRAPH_FIND_WITH_VOTES = "Restaurant.graph_with_votes";
    static final String DELETE_QUERY_NAME = "Restaurant.delete";
    static final String FIND_BY_ID = "Restaurant.findById";
    public static final String FIND_ALL = "RESTAURANT.findAll";
    public static final String FIND_BY_NAME_IGNORE_CASE = "RESTAURANT.findByNameIgnoreCase";
    public static final String FIND_BY_NAME = "RESTAURANT.findByName";
    static final String FIND_ALL_QUERY_NAME = "Restaurant.findAll";
    static final String FIND_ALL_BY_FIRST_PART_OF_NAME_QUERY_NAME = "Restaurant.findAllByFirstPartOfName";


    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(String name) {
        super(name);
    }

//    @Getter
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "key.restaurant")
//    private List<Price> prices;
//
//    @Getter
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    private List<Vote> votes;
}
