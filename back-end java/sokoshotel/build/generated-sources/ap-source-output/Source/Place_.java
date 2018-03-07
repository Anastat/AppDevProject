package Source;

import Source.Tasks;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T15:55:19")
@StaticMetamodel(Place.class)
public class Place_ { 

    public static volatile SingularAttribute<Place, Integer> placeID;
    public static volatile CollectionAttribute<Place, Tasks> tasksCollection;
    public static volatile SingularAttribute<Place, String> placeName;

}