
package au.edu.unsw.soacourse.autochecks;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the au.edu.unsw.soacourse.autochecks package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: au.edu.unsw.soacourse.autochecks
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ADRRequest }
     * 
     */
    public ADRRequest createADRRequest() {
        return new ADRRequest();
    }

    /**
     * Create an instance of {@link ADRResponse }
     * 
     */
    public ADRResponse createADRResponse() {
        return new ADRResponse();
    }

    /**
     * Create an instance of {@link DLRequest }
     * 
     */
    public DLRequest createDLRequest() {
        return new DLRequest();
    }

    /**
     * Create an instance of {@link DLResponse }
     * 
     */
    public DLResponse createDLResponse() {
        return new DLResponse();
    }

    /**
     * Create an instance of {@link SelectorRequest }
     * 
     */
    public SelectorRequest createSelectorRequest() {
        return new SelectorRequest();
    }

    /**
     * Create an instance of {@link SelectorResponse }
     * 
     */
    public SelectorResponse createSelectorResponse() {
        return new SelectorResponse();
    }

}
