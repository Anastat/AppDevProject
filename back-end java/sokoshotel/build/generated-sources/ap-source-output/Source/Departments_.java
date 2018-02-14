package Source;

import Source.Tasks;
import Source.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-14T13:48:26")
@StaticMetamodel(Departments.class)
public class Departments_ { 

    public static volatile SingularAttribute<Departments, String> departmentName;
    public static volatile SingularAttribute<Departments, Integer> departmentID;
    public static volatile CollectionAttribute<Departments, Tasks> tasksCollection;
    public static volatile CollectionAttribute<Departments, Users> usersCollection;

}