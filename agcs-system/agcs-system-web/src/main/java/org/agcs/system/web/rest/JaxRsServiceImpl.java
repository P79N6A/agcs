package org.agcs.system.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import net.sf.json.JSONObject;

@Path("/jaxrs")
public class JaxRsServiceImpl implements JaxRsServiceI {
	
	final String XMLNS_NAMESPACE = "http://yjmyzz.cnblogs.com/rest/service";
	final String ROOT_NODE = "root";

	@GET
	@Path("/xml/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String jaxrs() {
		System.out.println("Hello JAX-RS!");
		//JAXBElement<String> result = new JAXBElement<String>(new QName(XMLNS_NAMESPACE,ROOT_NODE), String.class, "Hello JAX-RS");
		JSONObject jobj = new JSONObject();//new一个JSON 
		jobj.accumulate("root", "Hello JAX-RS!");
		return jobj.toString();
	}
	
	@GET
	@Path("/xml/hello2")
	@Produces(MediaType.APPLICATION_XML)
	public JAXBElement<String> jaxrs2() {
		System.out.println("Hello JAX-RS!");
		JAXBElement<String> result = new JAXBElement<String>(new QName(XMLNS_NAMESPACE, ROOT_NODE), String.class, "Hello JAX-RS");
		return result;
	}

}
