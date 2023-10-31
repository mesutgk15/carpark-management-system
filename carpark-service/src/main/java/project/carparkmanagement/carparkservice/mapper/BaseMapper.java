package project.carparkmanagement.carparkservice.mapper;

public interface BaseMapper<T, S> {
    T map(S model, Object... params);
}
