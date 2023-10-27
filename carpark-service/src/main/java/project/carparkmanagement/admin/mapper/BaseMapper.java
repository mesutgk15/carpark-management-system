package project.carparkmanagement.admin.mapper;

public interface BaseMapper<T, S> {
    T map(S model, Object... params);
}
