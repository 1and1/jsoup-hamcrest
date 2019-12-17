/*
 * Copyright 1&1 Internet AG, https://github.com/1and1/
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
 */

package net.oneandone.jsoup.hamcrest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.junit.BeforeClass;

public abstract class HtmlBaseTest {
    protected static String source;

    @BeforeClass
    public static void setupClass() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        FluentExampleTest.class.getResourceAsStream("/examples/index.html"), StandardCharsets.UTF_8))) {
            source = reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
