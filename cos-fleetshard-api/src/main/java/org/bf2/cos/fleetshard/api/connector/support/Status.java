package org.bf2.cos.fleetshard.api.connector.support;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.fabric8.kubernetes.api.model.Condition;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.BuildableReference;

@Buildable(builderPackage = "io.fabric8.kubernetes.api.builder", refs = @BuildableReference(Condition.class))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {
    private String phase;
    private List<Condition> conditions;

    @JsonProperty
    public String getPhase() {
        return phase;
    }

    @JsonProperty
    public void setPhase(String phase) {
        this.phase = phase;
    }

    @JsonIgnore
    public void setPhase(Enum<?> type) {
        setPhase(type.name());
    }

    @JsonIgnore
    public boolean isInPhase(String type) {
        return Objects.equals(getPhase(), type);
    }

    @JsonIgnore
    public boolean isInPhase(Enum<?> type) {
        return Objects.equals(getPhase(), type.name());
    }

    @JsonProperty
    public List<Condition> getConditions() {
        return conditions;
    }

    @JsonProperty
    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @JsonIgnore
    public Optional<Condition> getLatestCondition() {
        return conditions != null
                ? Optional.of(conditions.get(conditions.size() - 1))
                : Optional.empty();
    }

    @Override
    public String toString() {
        return "Status{" +
                "phase='" + phase + '\'' +
                ", conditions=" + conditions +
                '}';
    }
}
