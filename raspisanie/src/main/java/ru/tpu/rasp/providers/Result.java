package ru.tpu.rasp.providers;

/**
 * Обёртка над данными вместе с ошибкой.
 */
public class Result<T> {

    private T data;
    private Exception e;

    public Result(Processor<T> processor) {
        try {
            this.data = processor.process();
        } catch (Exception e) {
            this.e = e;
        }
    }

    public T get() throws Exception {
        if (e != null)
            throw e;
        return data;
    }

    public interface Processor<T> {
        T process() throws Exception;
    }
}
