package Source;

import Source.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T13:48:26")
@StaticMetamodel(Rights.class)
public class Rights_ { 

    public static volatile SingularAttribute<Rights, String> rightsDetails;
    public static volatile SingularAttribute<Rights, Integer> rightsID;
    public static volatile CollectionAttribute<Rights, Users> usersCollection;

}