package garageWithSpringBoot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	public ResourceNotFoundException(String resouceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : '%s'", resouceName ,fieldName, fieldValue));
		this.resourceName = resouceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public String getResourceName() {
		return this.resourceName;
		
	}
	public String getFieldName() {
		return this.fieldName;
		
	}
	public Object getFieldValue() {
		return this.fieldValue;
		
	}

}

