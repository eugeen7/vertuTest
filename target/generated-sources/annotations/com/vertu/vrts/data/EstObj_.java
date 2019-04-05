package com.vertu.vrts.data;

import com.vertu.vrts.data.InsContr;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-05T10:49:18")
@StaticMetamodel(EstObj.class)
public class EstObj_ { 

    public static volatile SingularAttribute<EstObj, String> country;
    public static volatile SingularAttribute<EstObj, String> zipCode;
    public static volatile SingularAttribute<EstObj, Integer> buildNum;
    public static volatile SingularAttribute<EstObj, String> aprtNum;
    public static volatile SingularAttribute<EstObj, Integer> versnum;
    public static volatile SingularAttribute<EstObj, String> oblast;
    public static volatile SingularAttribute<EstObj, String> corpus;
    public static volatile CollectionAttribute<EstObj, InsContr> insContrCollection;
    public static volatile SingularAttribute<EstObj, String> locPoint;
    public static volatile SingularAttribute<EstObj, String> building;
    public static volatile SingularAttribute<EstObj, Long> estobjId;
    public static volatile SingularAttribute<EstObj, String> street;
    public static volatile SingularAttribute<EstObj, String> district;

}