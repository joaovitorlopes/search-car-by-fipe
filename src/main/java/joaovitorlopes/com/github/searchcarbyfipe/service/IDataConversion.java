package joaovitorlopes.com.github.searchcarbyfipe.service;

public interface IDataConversion {
    <T> T getData(String json, Class<T> tClass);
}
