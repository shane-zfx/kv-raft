package com.shane.example.core.rpc;

import com.google.common.base.Objects;

/**
 * @author: shane
 * @date: 2023-12-27 16:34:22
 * @version: 1.0
 */
public class EndpointPair {
    private Endpoint endpoint01;
    private Endpoint endpoint02;


    public EndpointPair(Endpoint endpoint01, Endpoint endpoint02) {
        this.endpoint01 = endpoint01;
        this.endpoint02 = endpoint02;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndpointPair)) return false;
        EndpointPair that = (EndpointPair) o;
        return (this.endpoint01.equals(that.getEndpoint01()) && this.endpoint02.equals(that.getEndpoint02()))
                || (this.endpoint01.equals(that.getEndpoint02()) && this.endpoint02.equals(that.getEndpoint02()));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(endpoint01, endpoint02);
    }

    public Endpoint getEndpoint01() {
        return endpoint01;
    }

    public Endpoint getEndpoint02() {
        return endpoint02;
    }
}
