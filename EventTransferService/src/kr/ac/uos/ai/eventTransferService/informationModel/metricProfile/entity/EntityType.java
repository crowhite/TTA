package kr.ac.uos.ai.eventTransferService.informationModel.metricProfile.entity;

public enum EntityType {
	Assessment			("http://purl.imsglobal.org/caliper/v1/Assessment"),
	AssessmentItem		("http://purl.imsglobal.org/caliper/v1/AssessmentItem"),
	CourseSection		("http://purl.imsglobal.org/caliper/v1/lis/CourseSection"),
	Frame				("http://purl.imsglobal.org/caliper/v1/Frame"),
	Group				("http://purl.imsglobal.org/caliper/v1/Group"),
	Organization		("http://purl.imsglobal.org/caliper/v1/lis/Organization"),
	Person				("http://purl.imsglobal.org/caliper/v1/lis/Person"),
	Reading				("http://purl.imsglobal.org/caliper/v1/Reading"),
	Session				("http://purl.imsglobal.org/caliper/v1/Session"),
	SoftwareApplication	("http://purl.imsglobal.org/caliper/v1/SoftwareApplication"),
	EdupubChapter   	("http://www.idpf.org/epub/vocab/structure/#chapter"),
	DigitalResource     ("http://purl.imsglobal.org/caliper/v1/DigitalResource"),
	Attempt			    ("http://purl.imsglobal.org/caliper/v1/Attempt"),
	Result 			    ("http://purl.imsglobal.org/caliper/v1/Result"),
	Volume				("http://www.idpf.org/epub/vocab/structure/#volume"),
	HighlightAnnotation ("http://purl.imsglobal.org/caliper/v1/HighlightAnnotation"),
						;

	private final String type;

	EntityType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
