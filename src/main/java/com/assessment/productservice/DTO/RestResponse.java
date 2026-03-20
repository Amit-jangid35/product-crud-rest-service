package com.assessment.productservice.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Standard API response wrapper")
public class RestResponse<T> {

	@Schema(description = "Response status (SUCCESS or ERROR)")
	private RestStatus status;
	private T data;
	private ApiError error;
	private String message;

	public static <T> RestResponse<T> success(T data, String message) {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(RestStatus.SUCCESS);
		response.setData(data);
		response.setMessage(message);
		return response;
	}

	public static <T> RestResponse<T> error(int code, String message) {
		RestResponse<T> response = new RestResponse<>();
		response.setStatus(RestStatus.ERROR);
		response.setError(new ApiError(code, message));
		return response;
	}
}
