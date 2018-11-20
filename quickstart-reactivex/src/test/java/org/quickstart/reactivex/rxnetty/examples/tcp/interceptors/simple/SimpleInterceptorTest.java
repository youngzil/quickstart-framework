/*
 * Copyright 2016 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.quickstart.reactivex.rxnetty.examples.tcp.interceptors.simple;

import org.junit.Test;
import org.quickstart.reactivex.rxnetty.examples.ExamplesTestUtil;
import org.quickstart.reactivex.rxnetty.examples.tcp.interceptors.simple.InterceptingClient;

import java.util.Queue;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SimpleInterceptorTest {

    @Test(timeout = 60000)
    public void testSimpleInterceptor() throws Exception {

        Queue<String> output = ExamplesTestUtil.runClientInMockedEnvironment(InterceptingClient.class);

        assertThat("Unexpected number of messages echoed", output, hasSize(1));
        assertThat("Unexpected number of messages echoed", output, contains("echo => Hello World!"));
    }
}