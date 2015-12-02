package com.dietz.chris.aliihealthtest;

import com.dietz.chris.aliihealthtest.network.AliiServices;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *
 */
public class NetworkTests {

    @Test
    public void stub() {
        assertThat(true, equalTo(false));
    }

    @Test
    public void successCall() {
        final AliiServices services = AliiServices.getServices();
        String test = services.getDoctor("HZPxxFtZyZp2kgpyeyH5", "GA");
        assertThat(true, equalTo(true));
    }

}
