
package io.ericnjuguna.data_gads.data;

/**
 *  Wrapper for data
 * @param <T> data type to be Returned
 */

public class Resource<T> {

    final Status status;
    final T data;
    final String message;

    private Resource(Status status, T data, String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <S> Resource<S> success(S data){
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static Resource<Exception> error(String message, Exception data){
        return new Resource<>(Status.ERROR, data, message);
    }

    public static  Resource<Void> loading(){
        return new Resource<Void>(Status.LOADING, null, null);
    }
}
