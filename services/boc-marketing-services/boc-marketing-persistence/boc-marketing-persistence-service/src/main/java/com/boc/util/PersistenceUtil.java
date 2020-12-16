package com.boc.util;

import com.backbase.buildingblocks.backend.communication.event.proxy.RequestProxyWrapper;
import com.backbase.buildingblocks.backend.internalrequest.InternalRequest;

public class PersistenceUtil {

    private PersistenceUtil(){}

    public static <T> RequestProxyWrapper<T> buildRequestProxyWrapper(T data, String method) {
        InternalRequest<T> internalRequest = new InternalRequest<>();
        internalRequest.setData(data);
        RequestProxyWrapper<T> result = new RequestProxyWrapper<>();
        result.setRequest(internalRequest);
        result.setHttpMethod(method);
        return result;
    }
}
