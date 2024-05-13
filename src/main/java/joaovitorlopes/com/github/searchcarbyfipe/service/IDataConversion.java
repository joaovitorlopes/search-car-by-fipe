package joaovitorlopes.com.github.searchcarbyfipe.service;

import java.util.List;

public interface IDataConversion {
    <T> T getData(String json, Class<T> tClass);

    <T> List<T> getList(String json, Class<T> tClass);
}
