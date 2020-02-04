package semsdn.constant;

public class Constant {

	// OWLh中的一些命名空间
	public static final String SDN_NAMESPACES          = "http://www.semanticweb.org/ssj/ontologies/2018/10/semsdn#";
	public static final String RDF_NAMESPACES          = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public static final String OWL_NAMESPACES          = "http://www.w3.org/2002/07/owl#";
	public static final String RDFS_NAMESPACES         = "http://www.w3.org/2000/01/rdf-schema#";
	public static final String XSD_NAMESPACES          = "http://www.w3.org/2001/XMLSchema#";
	
	// OWL文件存放的位置,该OWL文件存放在了当前目录下
	public static final String PATH_SDN                = "sdn.owl";
	public static final String PATH_RULES              = "rules.rules";
	public static String DESTHOST_IP_ADDRESS     = "";
	public static String SOURCE_IP_ADDRESS     = "";
	public static final int    DESTHOST_PORT           = 3868;
	
	// OWL文件中主要的类的全限定名，即URL+类名
	public static final String SWITCH_CLASS_NAMESPACES = SDN_NAMESPACES + "OVS";
	public static final String HOST_CLASS_NAMESPACES   = SDN_NAMESPACES + "Host";
	public static final String LINK_CLASS_NAMESPACES   = SDN_NAMESPACES + "Link";
	public static final String PORT_CLASS_NAMESPACES   = SDN_NAMESPACES + "Port";
	
	
	
}