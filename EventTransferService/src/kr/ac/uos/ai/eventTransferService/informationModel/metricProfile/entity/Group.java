package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import kr.ac.uos.ai.eventTransferService.informationModel.utilityPackage.RDFStringBuilder;

public class Group extends Entity{
	
	private String						name;
	private Organization				organization;
	private CourseSection				courseSection;
	
	public Group(String id) {
		super(id, EntityType.Group.getType());
	}
	
	public Model toRDFModel(){
		Model model = super.toRDFModel();
		Property pName = model.createProperty("name");
		Property pOrganization = model.createProperty("organization");
		Property pCourseSection = model.createProperty("courseSection");
		Resource resource = model.getResource(this.getId());
		
		resource.addProperty(pName, name);
		resource.addProperty(pOrganization, organization.getId());
		resource.addProperty(pCourseSection, courseSection.getId());
		return model;
	}
	
	public String toString(){
		RDFStringBuilder rb = super.buildRDFString();
		rb.buildRDF("name",name);
		rb.buildRDF("organization",organization.toString());
		rb.buildRDF("courseSection",courseSection.toString());
		return rb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public CourseSection getCourseSection() {
		return courseSection;
	}

	public void setCourseSection(CourseSection courseSection) {
		this.courseSection = courseSection;
	}
	
}
