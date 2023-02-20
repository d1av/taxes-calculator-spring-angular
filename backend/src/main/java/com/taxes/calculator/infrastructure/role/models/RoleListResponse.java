package com.taxes.calculator.infrastructure.role.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RoleListResponse(
	@JsonProperty("id") String id,
	@JsonProperty("authority") String authority,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt	
	) {

}
